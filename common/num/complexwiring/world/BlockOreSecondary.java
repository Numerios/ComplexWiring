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

public class BlockOreSecondary extends Block {
    public BlockOreSecondary(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName("complexwiring.world.ore.secondary");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ComplexWiring.tabCW);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumOreSecondary.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumOreSecondary oreSecondary : EnumOreSecondary.VALID) {
            oreSecondary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumOreSecondary oreSecondary : EnumOreSecondary.VALID) {
            list.add(oreSecondary.getIS(1));
        }
    }

    public void registerOre() {
        for (EnumOreSecondary oreSecondary : EnumOreSecondary.VALID) {
            oreSecondary.registerOre();
        }
    }

    public enum EnumOreSecondary {
        CHALCOPYRITE(Strings.ORE_CHALCOPYRITE_NAME, BlockOrePrimary.EnumOrePrimary.COPPER),
        CASSITERITE(Strings.ORE_CASSITERITE_NAME, BlockOrePrimary.EnumOrePrimary.TIN),
        ARGENTITE(Strings.ORE_ARGENTITE_NAME, BlockOrePrimary.EnumOrePrimary.SILVER);

        public static final EnumOreSecondary[] VALID = values();
        public final String name;
        public final BlockOrePrimary.EnumOrePrimary origin;
        public final int meta = this.ordinal();
        public Icon icon;

        private EnumOreSecondary(String name, BlockOrePrimary.EnumOrePrimary origin) {
            this.name = name;
            this.origin = origin;
        }

        public String getUnlocalizedName() {
            // removes the "ore" from the name and makes it lowercase (ex. oreCopper -> copper)
            return name.toLowerCase().substring(3);
        }

        public void registerIcon(IconRegister ir) {
            icon = ir.registerIcon(Reference.TEXTURE_PATH + "block" + name);
        }

        public ItemStack getIS(int amount) {
            return new ItemStack(ModuleWorld.oreSecondary, amount, meta);
        }

        public void registerOre() {
            OreDictionary.registerOre(this.name, this.getIS(1));
        }

    }
}
