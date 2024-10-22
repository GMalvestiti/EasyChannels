package net.riser876.easychannels.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.PlayerManager;
import net.riser876.easychannels.EasyChannels;

public class PlayerManagerUtils {
    public static PlayerManager PLAYER_MANAGER = null;

    public static void load() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            PlayerManagerUtils.PLAYER_MANAGER = server.getPlayerManager();
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            PlayerManagerUtils.PLAYER_MANAGER = null;
        });

        EasyChannels.LOGGER.info("[EasyChannels] Player manager loaded.");
    }
}
