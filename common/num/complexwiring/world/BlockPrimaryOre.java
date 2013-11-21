package num.complexwiring.world;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.ComplexWiring;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;

import java.util.List;

public class BlockPrimaryOre extends Block {
    public BlockPrimaryOre(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName("complexwiring.world.primaryore");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ComplexWiring.tabCW);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumPrimaryOre.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumPrimaryOre primaryOre : EnumPrimaryOre.VALID) {
            primaryOre.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumPrimaryOre primaryOre : EnumPrimaryOre.VALID) {
            list.add(primaryOre.getIS(1));
        }
    }

    public void registerOre() {
        for (EnumPrimaryOre primaryOre : EnumPrimaryOre.VALID) {
            primaryOre.registerOre();
        }
    }

    public enum EnumPrimaryOre {
        COPPER(Strings.ORE_COPPER_NAME),
        TIN(Strings.ORE_TIN_NAME),
        SILVER(Strings.ORE_SILVER_NAME);

        public final String name;

        public static final EnumPrimaryOre[] VALID = values();
        public final int meta = this.ordinal();
        public Icon icon;


        private EnumPrimaryOre(String name) {
            this.name = name;
        }

        public String getUnlocalizedName() {
            return name.toLowerCase().substring(3);
        }

        public void registerIcon(IconRegister ir) {
            icon = ir.registerIcon(Reference.TEXTURE_PATH + "block" + name);
        }

        public ItemStack getIS(int amount) {
            return new ItemStack(ModuleWorld.primaryOre, amount, meta);
        }

        public void registerOre() {
            OreDictionary.registerOre(this.name, this.getIS(1));
        }

    }
}
