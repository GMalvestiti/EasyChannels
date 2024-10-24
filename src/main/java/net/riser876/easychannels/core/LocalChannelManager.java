package net.riser876.easychannels.core;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.riser876.easychannels.EasyChannels;
import net.riser876.easychannels.config.Config;
import net.riser876.easychannels.config.data.LocalChannelData;
import net.riser876.easychannels.enums.ChannelType;

import java.util.function.BiConsumer;

public class LocalChannelManager {

    public static void load() {
        if (!Config.getLocalChannelData().isEnabled()) {
            EasyChannels.LOGGER.info("[EasyChannels] Local channel is disabled. Skipping load.");
            return;
        }

        LocalChannelData localChannelData = Config.getLocalChannelData();

        Channel localChannel = new Channel(localChannelData, ChannelType.LOCAL);

        BiConsumer<Text, ServerPlayerEntity> localChannelMessageSender = localChannel.MESSAGE_SENDER;

        ServerMessageEvents.ALLOW_CHAT_MESSAGE.register((message, sender, params) -> {
            localChannelMessageSender.accept(message.getContent(), sender);
            return false;
        });

        EasyChannels.LOGGER.info("[EasyChannels] Local channel loaded.");
    }
}
