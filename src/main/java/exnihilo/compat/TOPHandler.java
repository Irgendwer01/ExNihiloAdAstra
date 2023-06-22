package exnihilo.compat;

import exnihilo.Values;
import exnihilo.common.blocks.tiles.TileInfestingLeaves;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TOPHandler implements IProbeInfoProvider {

    @Override
    public String getID() {
        return Values.modID;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        if (world.getTileEntity(iProbeHitData.getPos()) instanceof TileInfestingLeaves) {
            handleInfestedLeaves(iProbeInfo, world.getTileEntity(iProbeHitData.getPos()));
        }
    }

    private void handleInfestedLeaves(IProbeInfo info, TileEntity te) {
        info.vertical().text(((TileInfestingLeaves)te).getPercentage() + "% done");
    }
}
