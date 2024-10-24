package net.riser876.easychannels.core;

import net.riser876.easychannels.EasyChannels;
import net.riser876.easychannels.config.Config;
import net.riser876.easychannels.config.data.CustomChannelData;
import net.riser876.easychannels.enums.ChannelType;
import net.riser876.easychannels.util.CommandUtils;

import java.util.List;

public class CustomChannelManager {

    public static void load() {
        List<CustomChannelData> customChannels = Config.getEnabledCustomChannelData();

        if (customChannels.isEmpty()) {
            EasyChannels.LOGGER.info("[EasyChannels] No custom channels are enabled. Skipping load.");
            return;
        }

        customChannels.forEach(customChannelData -> {
            Channel channel = new Channel(customChannelData, ChannelType.CUSTOM);
            CommandUtils.register(channel);
        });

        EasyChannels.LOGGER.info("[EasyChannels] Custom channels loaded.");
    }
}
