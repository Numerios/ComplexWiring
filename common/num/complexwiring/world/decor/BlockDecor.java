package num.complexwiring.world.decor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ModuleWorld;

import java.util.List;

public class BlockDecor extends Block {
    public BlockDecor() {
        super(Material.rock);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".world.decor");
        setHardness(1.8F);
        setResistance(2F);
        setCreativeTab(ModuleWorld.tabCWWorld);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return EnumDecor.VALID[meta].icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        for (EnumDecor decor : EnumDecor.VALID) {
            decor.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumDecor decor : EnumDecor.VALID) {
            list.add(decor.getIS(1));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
    }

    public void registerOres() {
        for (EnumDecor decor : EnumDecor.VALID) {
            decor.registerOre();
        }
    }
}
