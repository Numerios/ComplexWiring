package num.complexwiring.client.temp;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.IModelCustomLoader;
import net.minecraftforge.client.model.ModelFormatException;

/**
 * Code by Calclavia
 * Currently being added to Forge itself
 * https://github.com/MinecraftForge/MinecraftForge/pull/1085
 *
 * TODO: Remove this code when the PR is accepted!
 */
public class TechneModelLoader implements IModelCustomLoader {

    private static final String[] types = {"tcn"};

    @Override
    public String getType() {
        return "Techne model";
    }

    @Override
    public String[] getSuffixes() {
        return types;
    }

    @Override
    public IModelCustom loadInstance(ResourceLocation resource) throws ModelFormatException {
        return new TechneModel(resource);
    }
}
