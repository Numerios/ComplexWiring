package num.complexwiring.world.industrial.wood;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import num.complexwiring.core.Reference;
import num.complexwiring.world.ModuleWorld;

import java.util.List;

public class BlockWoodIndustrial extends Block {
    public BlockWoodIndustrial() {
        super(Material.rock);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".world.industrial.wood");
        setHardness(1.8F);
        setResistance(2F);
        setCreativeTab(ModuleWorld.tabCWWorld);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return EnumWoodIndustrial.VALID[meta].icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        for (EnumWoodIndustrial woodIndustrial : EnumWoodIndustrial.VALID) {
            woodIndustrial.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumWoodIndustrial woodIndustrial : EnumWoodIndustrial.VALID) {
            list.add(woodIndustrial.getIS(1));
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
        for (EnumWoodIndustrial woodIndustrial : EnumWoodIndustrial.VALID) {
            woodIndustrial.registerOre();
        }
    }
}
