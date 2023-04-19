package exnihilo.common.items.tools;

import exnihilo.API.render.IModelRender;
import exnihilo.API.utils.data;
import exnihilo.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Collections;

public class HammerBase extends ItemTool implements IModelRender {

    static int color;

    public HammerBase(String name, ToolMaterial materialIn, int color) {
        super(materialIn, Collections.emptySet());
        this.setCreativeTab(CommonProxy.creativeTab);
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setHarvestLevel("hammer", 1);
        data.ITEMS.add(this);
        this.color = color;
    }

    @Override
    public void initModel(ModelRegistryEvent e) {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("exnihilo:hammer", "inventory"));
    }

    public int getColor() {
        return color;
    }
}
