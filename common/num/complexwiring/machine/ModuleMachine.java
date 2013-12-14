package num.complexwiring.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import num.complexwiring.api.recipe.RecipeRandomOutput;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;
import num.complexwiring.machine.basic.BlockBasicMachine;
import num.complexwiring.machine.basic.ItemBlockBasicMachine;
import num.complexwiring.machine.powered.BlockPoweredMachine;
import num.complexwiring.machine.powered.ItemBlockPoweredMachine;
import num.complexwiring.api.recipe.CrusherRecipe;
import num.complexwiring.api.recipe.OrelyzerRecipe;
import num.complexwiring.recipe.RecipeManager;

public class ModuleMachine extends Module {

    public static Block machineBasic, machinePowered;
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
        machineBasic = new BlockBasicMachine(670);
        GameRegistry.registerBlock(machineBasic, ItemBlockBasicMachine.class, machineBasic.getUnlocalizedName());
        ((BlockBasicMachine) machineBasic).registerTiles();

        machinePowered = new BlockPoweredMachine(671);
        GameRegistry.registerBlock(machinePowered, ItemBlockPoweredMachine.class, machinePowered.getUnlocalizedName());
        ((BlockPoweredMachine) machinePowered).registerTiles();
    }

    private void registerItems() {

    }

    private void registerRecipes() {
        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.wood, 1), 120,
                new RecipeRandomOutput(new ItemStack(Item.diamond, 1), 0.1F),
                new RecipeRandomOutput(new ItemStack(Item.stick, 2), 0.8F),
                new RecipeRandomOutput(new ItemStack(Item.stick, 1), 1F)));

        RecipeManager.add(new CrusherRecipe(new ItemStack(Block.cobblestone, 1), 100,
                new RecipeRandomOutput(new ItemStack(Block.sand, 1), 0.5F),
                new RecipeRandomOutput(new ItemStack(Block.sand, 1), 0.2F),
                new RecipeRandomOutput(new ItemStack(Block.gravel, 1), 0.4F)));
    }
}
