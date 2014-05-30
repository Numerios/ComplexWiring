package num.complexwiring.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import num.complexwiring.api.recipe.CrusherRecipe;
import num.complexwiring.api.recipe.OrelyzerRecipe;
import num.complexwiring.api.recipe.RecipeRandomOutput;
import num.complexwiring.api.recipe.SmasherRecipe;
import num.complexwiring.base.EnumDust;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;
import num.complexwiring.machine.basic.BlockBasicMachine;
import num.complexwiring.machine.basic.ItemBlockBasicMachine;
import num.complexwiring.machine.powered.BlockPoweredMachine;
import num.complexwiring.machine.powered.ItemBlockPoweredMachine;
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
        tabCWMachine.setIcon(new ItemStack(machineBasic));
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerBlocks() {
        machineBasic = new BlockBasicMachine();
        GameRegistry.registerBlock(machineBasic, ItemBlockBasicMachine.class, machineBasic.getUnlocalizedName());
        ((BlockBasicMachine) machineBasic).registerTiles();

        machinePowered = new BlockPoweredMachine();
        GameRegistry.registerBlock(machinePowered, ItemBlockPoweredMachine.class, machinePowered.getUnlocalizedName());
        ((BlockPoweredMachine) machinePowered).registerTiles();

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
        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Blocks.log, 1), 120,
                new RecipeRandomOutput(new ItemStack(Items.diamond, 1), 0.1F),
                new RecipeRandomOutput(new ItemStack(Items.stick, 2), 0.8F),
                new RecipeRandomOutput(new ItemStack(Items.stick, 1), 1F)));

        RecipeManager.add(new CrusherRecipe(new ItemStack(Blocks.cobblestone, 1), 100,
                new RecipeRandomOutput(new ItemStack(Blocks.sand, 1), 0.5F),
                new RecipeRandomOutput(new ItemStack(Blocks.sand, 1), 0.2F),
                new RecipeRandomOutput(new ItemStack(Blocks.gravel, 1), 0.4F)));

        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.iron_ore), 180, EnumDust.IRON.getIS(2)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.gold_ore), 270, EnumDust.GOLD.getIS(2)));
    }
}
