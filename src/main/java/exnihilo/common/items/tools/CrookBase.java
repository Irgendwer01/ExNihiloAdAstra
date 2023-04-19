package exnihilo.common.items.tools;

import exnihilo.API.render.IModelRender;
import exnihilo.API.utils.data;
import exnihilo.common.CommonProxy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Collections;

public class CrookBase extends ItemTool implements IModelRender {


    public CrookBase (String name, ToolMaterial materialIn) {
        super(materialIn, Collections.emptySet());
        this.setCreativeTab(CommonProxy.creativeTab);
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setHarvestLevel("crook", 1);
        data.ITEMS.add(this);
    }

    @Override
    public void initModel(ModelRegistryEvent e) {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
