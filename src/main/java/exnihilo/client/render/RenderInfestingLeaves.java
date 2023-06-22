package exnihilo.client.render;

import exnihilo.client.Color;
import exnihilo.client.model.ModelVertex;
import exnihilo.common.blocks.BlockInfestingLeaves;
import exnihilo.common.blocks.tiles.TileInfestingLeaves;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.animation.FastTESR;

public class RenderInfestingLeaves extends FastTESR<TileInfestingLeaves> {

    private static final ModelVertex[] model = new ModelVertex[24];

    static {
        model[0] = new ModelVertex(EnumFacing.UP, 0, 1, 0, 0, 1);
        model[1] = new ModelVertex(EnumFacing.UP, 1, 1, 0, 1, 1);
        model[2] = new ModelVertex(EnumFacing.UP, 1, 1, 1, 1, 0);
        model[3] = new ModelVertex(EnumFacing.UP, 0, 1, 1, 0, 0);

        model[4] = new ModelVertex(EnumFacing.DOWN, 0, 0, 0, 0, 0);
        model[5] = new ModelVertex(EnumFacing.DOWN, 1, 0, 0, 1, 0);
        model[6] = new ModelVertex(EnumFacing.DOWN, 1, 0, 1, 1, 1);
        model[7] = new ModelVertex(EnumFacing.DOWN, 0, 0, 1, 0, 1);

        model[8] = new ModelVertex(EnumFacing.NORTH, 0, 0, 0, 1, 0);
        model[9] = new ModelVertex(EnumFacing.NORTH, 1, 0, 0, 0, 0);
        model[10] = new ModelVertex(EnumFacing.NORTH, 1, 1, 0, 0, 1);
        model[11] = new ModelVertex(EnumFacing.NORTH, 0, 1, 0, 1, 1);

        model[12] = new ModelVertex(EnumFacing.SOUTH, 0, 0, 1, 0, 0);
        model[13] = new ModelVertex(EnumFacing.SOUTH, 1, 0, 1, 1, 0);
        model[14] = new ModelVertex(EnumFacing.SOUTH, 1, 1, 1, 1, 1);
        model[15] = new ModelVertex(EnumFacing.SOUTH, 0, 1, 1, 0, 1);

        model[16] = new ModelVertex(EnumFacing.EAST, 1, 0, 0, 1, 0);
        model[17] = new ModelVertex(EnumFacing.EAST, 1, 0, 1, 0, 0);
        model[18] = new ModelVertex(EnumFacing.EAST, 1, 1, 1, 0, 1);
        model[19] = new ModelVertex(EnumFacing.EAST, 1, 1, 0, 1, 1);

        model[20] = new ModelVertex(EnumFacing.WEST, 0, 0, 0, 0, 0);
        model[21] = new ModelVertex(EnumFacing.WEST, 0, 0, 1, 1, 0);
        model[22] = new ModelVertex(EnumFacing.WEST, 0, 1, 1, 1, 1);
        model[23] = new ModelVertex(EnumFacing.WEST, 0, 1, 0, 0, 1);

    }

    @Override
    public void renderTileEntityFast(TileInfestingLeaves te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        if (te == null || buffer == null) {
            return;
        }

        final BlockPos blockPos = te.getPos();
        final Block block = getWorld().getBlockState(blockPos).getBlock();

        final int mixedBrightness = getWorld().getBlockState(blockPos).getPackedLightmapCoords(te.getWorld(), blockPos);
        final int skyLight = mixedBrightness >> 16 & 0xFFFF;
        final int blockLight = mixedBrightness & 0xFFFF;

        final IBlockState iBlockState = te.getLeafState();

        final Color color;
        if (block instanceof BlockInfestingLeaves) {
            color = Color.average(new Color(BiomeColorHelper.getFoliageColorAtPos(getWorld(), blockPos)), new Color(1f, 1f, 1f, 1f), (float) Math.pow((te.getPercentage() / 100f), 2.0));
        } else {
            color = new Color(1f, 1f, 1f, 1f);
        }

        final TextureAtlasSprite sprite = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(iBlockState);
        buffer.setTranslation(x - blockPos.getX(), y - blockPos.getY(), z - blockPos.getZ());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, skyLight * 16.0F, blockLight * 16.0F);

        Vec3d view = Minecraft.getMinecraft().getRenderViewEntity().getPositionEyes(partialTicks);
        for (final ModelVertex vertex : model) {
            final EnumFacing enumFacing = vertex.face;

            double dot = 0;
            switch (enumFacing) {
                case DOWN: {
                    dot = -(view.y - (blockPos.getY()));
                    break;
                }
                case UP: {
                    dot = (view.y - (blockPos.getY() + 1));
                    break;
                }
                case NORTH: {
                    dot = -(view.z - (blockPos.getZ()));
                    break;
                }
                case SOUTH: {
                    dot = (view.z - (blockPos.getZ() + 1));
                    break;
                }
                case WEST: {
                    dot = -(view.x - (blockPos.getX()));
                    break;
                }
                case EAST: {
                    dot = (view.x - (blockPos.getX() + 1));
                    break;
                }
            }
            if (dot > 0 && iBlockState.shouldSideBeRendered(getWorld(), blockPos, vertex.face)) {
                for (VertexFormatElement formatElement : buffer.getVertexFormat().getElements()) {
                    switch (formatElement.getUsage()) {
                        case COLOR: {
                            buffer.color(color.r, color.g, color.b, color.a);
                            break;
                        }

                        case NORMAL: {
                            buffer.normal(enumFacing.getXOffset(), enumFacing.getYOffset(), enumFacing.getZOffset());
                            break;
                        }

                        case POSITION: {
                            final double vertX = blockPos.getX() + vertex.x;
                            final double vertY = blockPos.getY() + vertex.y;
                            final double vertZ = blockPos.getZ() + vertex.z;

                            buffer.pos(vertX, vertY, vertZ);
                            break;
                        }

                        case UV:
                            if (formatElement.getIndex() == 1) {
                                buffer.lightmap(skyLight, blockLight);
                            } else {
                                buffer.tex(sprite.getInterpolatedU(vertex.u), sprite.getInterpolatedV(16.0 - vertex.v));
                            }
                            break;

                        default:
                            break;
                    }
                }
                buffer.endVertex();
            }
        }
        buffer.setTranslation(0, 0, 0);
    }
}
