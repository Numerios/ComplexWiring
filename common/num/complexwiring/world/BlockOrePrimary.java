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

public class BlockOrePrimary extends Block {
    public BlockOrePrimary(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName("complexwiring.world.ore.primary");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ComplexWiring.tabCW);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumOrePrimary.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumOrePrimary orePrimary : EnumOrePrimary.VALID) {
            orePrimary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumOrePrimary orePrimary : EnumOrePrimary.VALID) {
            list.add(orePrimary.getIS(1));
        }
    }

    public void registerOre() {
        for (EnumOrePrimary orePrimary : EnumOrePrimary.VALID) {
            orePrimary.registerOre();
        }
    }

    public enum EnumOrePrimary {
        COPPER(Strings.ORE_COPPER_NAME),
        TIN(Strings.ORE_TIN_NAME),
        SILVER(Strings.ORE_SILVER_NAME);
        public static final EnumOrePrimary[] VALID = values();
        public final String name;
        public final int meta = this.ordinal();
        public Icon icon;


        private EnumOrePrimary(String name) {
            this.name = name;
        }

        public String getUnlocalizedName() {
            // removes the "ore" from the name and makes it lowercase (ex. oreCopper -> copper)
            return name.toLowerCase().substring(3);
        }

        public void registerIcon(IconRegister ir) {
            icon = ir.registerIcon(Reference.TEXTURE_PATH + name);
        }

        public ItemStack getIS(int amount) {
            return new ItemStack(ModuleWorld.orePrimary, amount, meta);
        }

        public void registerOre() {
            OreDictionary.registerOre(this.name, this.getIS(1));
        }

    }
}
