package num.complexwiring.world.ore.decor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ModuleWorld;

import java.util.List;

public class BlockOreDecor extends Block {
    public BlockOreDecor(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".world.ore.decor");
        setHardness(2.0F);
        setResistance(3.0F);
        setCreativeTab(ModuleWorld.tabCWWorld);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumOreDecor.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumOreDecor orePrimary : EnumOreDecor.VALID) {
            orePrimary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumOreDecor orePrimary : EnumOreDecor.VALID) {
            list.add(orePrimary.getIS(1));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    public void registerOres() {
        for (EnumOreDecor orePrimary : EnumOreDecor.VALID) {
            orePrimary.registerOre();
        }
    }
}
