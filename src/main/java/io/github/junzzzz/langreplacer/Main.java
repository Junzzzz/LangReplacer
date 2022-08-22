package io.github.junzzzz.langreplacer;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Jun
 */
@Mod(modid = "lang-replacer", name = "Language Replacer", useMetadata = true)
public class Main {
    public static final Logger LOGGER = LogManager.getLogger("LangReplacer");

    @EventHandler
    public void pre(FMLPreInitializationEvent event) {
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new LanguageReplacer());
    }

}
