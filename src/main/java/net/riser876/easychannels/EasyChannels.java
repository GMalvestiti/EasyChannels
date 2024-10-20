package net.riser876.easychannels;

import net.fabricmc.api.ModInitializer;
import net.riser876.easychannels.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyChannels implements ModInitializer {

    public static final String MOD_ID = "easychannels";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void loadModules() {
        if (!Config.isModEnabled()) {
            EasyChannels.LOGGER.info("[EasyChannels] Mod is disabled. Skipping module loading.");
            return;
        }

        EasyChannels.LOGGER.info("[EasyChannels] Mod is enabled. Loading modules.");

        EasyChannels.LOGGER.info("[EasyChannels] Mod initialized.");
    }

    @Override
    public void onInitialize() {
        Config.load();
        EasyChannels.LOGGER.info("[EasyChannels] Configuration loaded.");
        EasyChannels.loadModules();
        Config.clear();
    }
}
