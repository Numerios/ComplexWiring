package num.complexwiring.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.recipe.SmasherRecipe;
import num.complexwiring.base.EnumComponent;
import num.complexwiring.base.EnumMaterial;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;
import num.complexwiring.machine.basic.BlockBasicMachine;
import num.complexwiring.machine.basic.ItemBlockBasicMachine;
import num.complexwiring.machine.powered.BlockPoweredMachine;
import num.complexwiring.machine.powered.ItemBlockPoweredMachine;
import num.complexwiring.machine.smasher.BlockSmasher;
import num.complexwiring.machine.smasher.ItemBlockSmasher;
import num.complexwiring.machine.storagebox.BlockStorageBox;
import num.complexwiring.machine.storagebox.EnumStorageBox;
import num.complexwiring.machine.storagebox.ItemBlockStorageBox;
import num.complexwiring.recipe.RecipeManager;

public class ModuleMachine extends Module {
    public static Block machineBasic, machinePowered, storageBox, smasher;
    public static CreativeTabCW tabCWMachine = new CreativeTabCW("tabCWMachine", null);

    @Override
    public void preInit() {
        registerBlocks();
        registerItems();
        tabCWMachine.setIcon(new ItemStack(smasher));
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerBlocks() {
        if (ComplexWiring.DEBUG) {
            machineBasic = new BlockBasicMachine();
            GameRegistry.registerBlock(machineBasic, ItemBlockBasicMachine.class, machineBasic.getUnlocalizedName());
            ((BlockBasicMachine) machineBasic).registerTiles();

            machinePowered = new BlockPoweredMachine();
            GameRegistry.registerBlock(machinePowered, ItemBlockPoweredMachine.class, machinePowered.getUnlocalizedName());
            ((BlockPoweredMachine) machinePowered).registerTiles();
        }

        storageBox = new BlockStorageBox();
        GameRegistry.registerBlock(storageBox, ItemBlockStorageBox.class, storageBox.getUnlocalizedName());
        ((BlockStorageBox) storageBox).registerTiles();

        smasher = new BlockSmasher();
        GameRegistry.registerBlock(smasher, ItemBlockSmasher.class, smasher.getUnlocalizedName());
        ((BlockSmasher) smasher).registerTiles();
    }

    private void registerItems() {
    }

    private void registerRecipes() {
        for (EnumMaterial material : EnumMaterial.VALID) {
            RecipeManager.add(new SmasherRecipe(material.getOre(), 180, material.getDust(2)));
        }

        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.BASIC.getIS(1), "PCP", "C#C", "PCP", 'P', "plankWood", 'C', "cobblestone", '#', Blocks.chest));
        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.BASIC.getIS(1), "CPC", "P#P", "CPC", 'P', "plankWood", 'C', "cobblestone", '#', Blocks.chest));

        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.ADVANCED.getIS(1), " I ", "I#I", " I ", 'I', "ingotIron", '#', EnumStorageBox.BASIC.getIS(1)));

        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.ADVANCED.getIS(1), "PIP", "I#I", "PIP", 'P', "plankWood", 'I', "ingotIron", '#', Blocks.chest));
        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.ADVANCED.getIS(1), "IPI", "P#P", "IPI", 'P', "plankWood", 'I', "ingotIron", '#', Blocks.chest));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(smasher), "FFF", "PSP", "CCC", 'F', Items.flint, 'P', "plankWood", 'C', "cobblestone", 'S', Items.stick));

        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 0), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 1), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 2), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 3), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log2, 1, 0), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log2, 1, 1), 90, EnumComponent.WOOD_PULP.getIS(10)));

        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.planks), 90, EnumComponent.WOOD_PULP.getIS(2)));
    }
}
