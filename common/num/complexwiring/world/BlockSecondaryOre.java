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

public class BlockSecondaryOre extends Block {
    public BlockSecondaryOre(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName("complexwiring.world.secondaryore");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ComplexWiring.tabCW);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumSecondaryOre.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumSecondaryOre secondaryOre : EnumSecondaryOre.VALID) {
            secondaryOre.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumSecondaryOre secondaryOre : EnumSecondaryOre.VALID) {
            list.add(secondaryOre.getIS(1));
        }
    }

    public void registerOre() {
        for (EnumSecondaryOre secondaryOre : EnumSecondaryOre.VALID) {
            secondaryOre.registerOre();
        }
    }

    public enum EnumSecondaryOre {
        CHALCOPYRITE(Strings.ORE_CHALCOPYRITE_NAME, BlockPrimaryOre.EnumPrimaryOre.COPPER),
        CASSITERITE(Strings.ORE_CASSITERITE_NAME, BlockPrimaryOre.EnumPrimaryOre.TIN),
        ARGENTITE(Strings.ORE_ARGENTITE_NAME, BlockPrimaryOre.EnumPrimaryOre.SILVER);

        public static final EnumSecondaryOre[] VALID = values();
        public final String name;
        public final BlockPrimaryOre.EnumPrimaryOre origin;
        public final int meta = this.ordinal();
        public Icon icon;

        private EnumSecondaryOre(String name, BlockPrimaryOre.EnumPrimaryOre origin) {
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
            return new ItemStack(ModuleWorld.secondaryOre, amount, meta);
        }

        public void registerOre() {
            OreDictionary.registerOre(this.name, this.getIS(1));
        }

    }
}
