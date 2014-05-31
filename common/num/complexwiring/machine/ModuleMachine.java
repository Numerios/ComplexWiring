package num.complexwiring.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import num.complexwiring.api.recipe.SmasherRecipe;
import num.complexwiring.base.EnumComponent;
import num.complexwiring.base.EnumMaterial;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;
import num.complexwiring.machine.smasher.BlockSmasher;
import num.complexwiring.machine.smasher.ItemBlockSmasher;
import num.complexwiring.machine.storagebox.BlockStorageBox;
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
    /*    machineBasic = new BlockBasicMachine();
        GameRegistry.registerBlock(machineBasic, ItemBlockBasicMachine.class, machineBasic.getUnlocalizedName());
        ((BlockBasicMachine) machineBasic).registerTiles();

        machinePowered = new BlockPoweredMachine();
        GameRegistry.registerBlock(machinePowered, ItemBlockPoweredMachine.class, machinePowered.getUnlocalizedName());
        ((BlockPoweredMachine) machinePowered).registerTiles();                                                            */

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

        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 0), 90, EnumComponent.WOOD_PULP.getIS(10)));                     //FIXME: Smasher derpiness!
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 1), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 2), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 3), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log2, 1, 0), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log2, 1, 1), 90, EnumComponent.WOOD_PULP.getIS(10)));
    }
}
