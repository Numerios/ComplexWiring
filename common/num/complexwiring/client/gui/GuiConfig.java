package num.complexwiring.client.gui;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import num.complexwiring.core.ConfigHandler;
import num.complexwiring.core.Reference;
import num.complexwiring.world.decor.light.EnumLightDecor;
import num.complexwiring.world.ore.primary.EnumOrePrimary;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class GuiConfig extends cpw.mods.fml.client.config.GuiConfig {
    public GuiConfig(GuiScreen parentScreen) {
        super(parentScreen, getMainMenuElements(), Reference.MOD_ID, Reference.MOD_ID, false, false, Reference.MOD_NAME);
    }

    private static List<IConfigElement> getMainMenuElements() {
        List<IConfigElement> elementList = new ArrayList<IConfigElement>();

        elementList.add(new DummyConfigElement.DummyCategoryElement<IConfigElement>("World generation", "config.complexwiring.main.worldgen", getWorldGenMenuElements()));
        return elementList;
    }

    private static List<IConfigElement> getWorldGenMenuElements() {
        List<IConfigElement> elementList = new ArrayList<IConfigElement>();

        elementList.add(new DummyConfigElement.DummyCategoryElement<IConfigElement>("Primary ores", "config.complexwiring.worldgen.primaryores", getWorldGenPrimaryOresElements()));
        elementList.add(new DummyConfigElement.DummyCategoryElement<IConfigElement>("Decor blocks", "config.complexwiring.worldgen.decor", getWorldGenDecorElements()));
        return elementList;
    }

    private static List<IConfigElement> getWorldGenPrimaryOresElements() {
        List<IConfigElement> elementList = new ArrayList<IConfigElement>();

        for (EnumOrePrimary ore : EnumOrePrimary.VALID) {
            String oreName = ore.name.substring(3).toLowerCase();
            elementList.add(new DummyConfigElement.DummyCategoryElement<IConfigElement>(ore.name.substring(3), "tile.complexwiring.world.ore.primary." + oreName + ".name", getConfigElements("worldgeneration.primaryores." + oreName)));
        }
        return elementList;
    }

    private static List<IConfigElement> getConfigElements(String category) {
        return (new ConfigElement(ConfigHandler.conf.getCategory(category)).getChildElements());
    }

    private static List<IConfigElement> getWorldGenDecorElements() {
        List<IConfigElement> elementList = new ArrayList<IConfigElement>();

        for (EnumLightDecor decor : EnumSet.of(EnumLightDecor.LIMESTONE_ROUGH, EnumLightDecor.DOLOMITE_ROUGH, EnumLightDecor.ARENITE_ROUGH)) {
            String decorName = decor.name.substring(5);
            decorName = decorName.substring(0, decorName.length() - 5);
            elementList.add(new DummyConfigElement.DummyCategoryElement<IConfigElement>(decorName, decor.getUnlocalizedName(), getConfigElements("worldgeneration.decor." + decorName.toLowerCase())));
        }
        return elementList;
    }
}
