package io.github.junzzzz.langreplacer;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.*;
import net.minecraft.util.StringTranslate;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

/**
 * @author Jun
 */
@SideOnly(Side.CLIENT)
public class LanguageReplacer implements IResourceManagerReloadListener {
    private final Map<String, String> localeMap;

    public LanguageReplacer() {
        localeMap = getLocaleMap();
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        for (int i = 1; i < 21; i++) {
            localeMap.put("enchantment.level." + i, getEnchantmentLevelTranslation(i));
        }
        StringTranslate.replaceWith(localeMap);
    }

    private static String getEnchantmentLevelTranslation(int level) {
        return NumberUtils.int2chineseNum(level) + "阶";
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> getLocaleMap() {
        Locale locale;
        Map<String, String> localeMap;

        Field localeField = ReflectionHelper.findField(LanguageManager.class, "currentLocale", "field_135049_a");
        localeField.setAccessible(true);
        try {
            locale = (Locale) localeField.get(Minecraft.getMinecraft().getLanguageManager());
        } catch (IllegalAccessException e) {
            Main.LOGGER.log(Level.ERROR, "Get Locale failed!", e);
            return Collections.emptyMap();
        }
        Field mapField = ReflectionHelper.findField(Locale.class, "field_135032_a");
        mapField.setAccessible(true);
        try {
            localeMap = (Map<String, String>) mapField.get(locale);
        } catch (IllegalAccessException e) {
            Main.LOGGER.log(Level.ERROR, "Get LocaleMap failed!", e);
            return Collections.emptyMap();
        }
        return localeMap;
    }

}
