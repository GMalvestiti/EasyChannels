package net.riser876.easychannels;

import net.fabricmc.api.ModInitializer;
import net.riser876.easychannels.config.Config;
import net.riser876.easychannels.core.LocalChannelManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyChannels implements ModInitializer {

    public static final String MOD_ID = "easychannels";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Config.load();
        EasyChannels.LOGGER.info("[EasyChannels] Configuration loaded.");
        EasyChannels.loadManagers();
        Config.clear();
    }

    public static void loadManagers() {
        if (!Config.isModEnabled()) {
            EasyChannels.LOGGER.info("[EasyChannels] Mod is disabled. Skipping loading.");
            return;
        }

        LocalChannelManager.load();

        EasyChannels.LOGGER.info("[EasyChannels] Mod initialized successfully.");
    }
}
