package net.riser876.easychannels.core;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.riser876.easychannels.EasyChannels;
import net.riser876.easychannels.config.Config;
import net.riser876.easychannels.config.data.PermissionData;
import net.riser876.easychannels.enums.ChannelPermission;
import net.riser876.easychannels.util.PermissionsUtils;
import net.riser876.easychannels.util.PlaceholderUtils;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class LocalChannelManager {

    private static final int CHANNEL_RADIUS = Config.getLocalChannelData().getRadius();
    private static final String CHANNEL_FORMAT = Config.getLocalChannelData().getFormat();
    private static final ChannelPermission CHANNEL_PERMISSION = Config.getLocalChannelData().getChannelPermission();
    private static Function<ServerPlayerEntity, Boolean> PERMISSION_CHECKER_SENDER = null;
    private static Function<ServerPlayerEntity, Boolean> PERMISSION_CHECKER_RECEIVER = null;

    public static void load() {
        if (!Config.getLocalChannelData().isEnabled()) {
            return;
        }

        BiConsumer<SignedMessage, ServerPlayerEntity> consumer = LocalChannelManager.getConsumer();

        if (Objects.isNull(consumer)) {
            EasyChannels.LOGGER.error("[EasyChannels] Local channel manager failed to load.");
            return;
        }

        ServerMessageEvents.ALLOW_CHAT_MESSAGE.register((message, sender, params) -> {
            consumer.accept(message, sender);
            return false;
        });

        EasyChannels.LOGGER.info("[EasyChannels] Local channel manager loaded.");
    }

    private static BiConsumer<SignedMessage, ServerPlayerEntity> getConsumer() {
        switch (CHANNEL_PERMISSION) {
            case NONE: {
                return LocalChannelManager::sendMessagePermissionNone;
            }
            case SENDER: {
                LocalChannelManager.loadPermissionCheckerSender();
                return LocalChannelManager::sendMessagePermissionSender;
            }
            case RECEIVER: {
                LocalChannelManager.loadPermissionCheckerReceiver();
                return LocalChannelManager::sendMessagePermissionReceiver;
            }
            case BOTH: {
                LocalChannelManager.loadPermissionCheckerSender();
                LocalChannelManager.loadPermissionCheckerReceiver();
                return LocalChannelManager::sendMessagePermissionBoth;
            }
            default: {
                return null;
            }
        }
    }

    private static void loadPermissionCheckerSender() {
        PermissionData permissionData = Config.getLocalChannelData().getPermissionSender();
        PERMISSION_CHECKER_SENDER = PermissionsUtils.getPermissionChecker(
                permissionData.getPermissionType(),
                permissionData.getOperatorLevel(),
                permissionData.getPermission()
        );
    }

    private static void loadPermissionCheckerReceiver() {
        PermissionData permissionData = Config.getLocalChannelData().getPermissionReceiver();
        PERMISSION_CHECKER_RECEIVER = PermissionsUtils.getPermissionChecker(
                permissionData.getPermissionType(),
                permissionData.getOperatorLevel(),
                permissionData.getPermission()
        );
    }

    private static void sendMessagePermissionNone(SignedMessage message, ServerPlayerEntity sender) {
        Text text = PlaceholderUtils.formatPlayerMessage(CHANNEL_FORMAT, sender, message.getContent());

        Box boundingBox = sender.getBoundingBox().expand(CHANNEL_RADIUS);

        for (ServerPlayerEntity player : sender.getServerWorld().getEntitiesByClass(ServerPlayerEntity.class, boundingBox, entity -> true)) {
            player.sendMessage(text);
        }
    }

    private static void sendMessagePermissionSender(SignedMessage message, ServerPlayerEntity sender) {
        if (!PERMISSION_CHECKER_SENDER.apply(sender)) {
            PermissionsUtils.sendPermissionRequiredMessage(sender);
            return;
        }

        Text text = PlaceholderUtils.formatPlayerMessage(CHANNEL_FORMAT, sender, message.getContent());

        Box boundingBox = sender.getBoundingBox().expand(CHANNEL_RADIUS);

        for (ServerPlayerEntity player : sender.getServerWorld().getEntitiesByClass(ServerPlayerEntity.class, boundingBox, entity -> true)) {
            player.sendMessage(text);
        }
    }

    private static void sendMessagePermissionReceiver(SignedMessage message, ServerPlayerEntity sender) {
        Text text = PlaceholderUtils.formatPlayerMessage(CHANNEL_FORMAT, sender, message.getContent());

        Box boundingBox = sender.getBoundingBox().expand(CHANNEL_RADIUS);

        for (ServerPlayerEntity player : sender.getServerWorld().getEntitiesByClass(ServerPlayerEntity.class, boundingBox, entity -> true)) {
            if (PERMISSION_CHECKER_RECEIVER.apply(player)) {
                player.sendMessage(text);
            }
        }
    }

    private static void sendMessagePermissionBoth(SignedMessage message, ServerPlayerEntity sender) {
        if (!PERMISSION_CHECKER_SENDER.apply(sender)) {
            PermissionsUtils.sendPermissionRequiredMessage(sender);
            return;
        }

        Text text = PlaceholderUtils.formatPlayerMessage(CHANNEL_FORMAT, sender, message.getContent());

        Box boundingBox = sender.getBoundingBox().expand(CHANNEL_RADIUS);

        for (ServerPlayerEntity player : sender.getServerWorld().getEntitiesByClass(ServerPlayerEntity.class, boundingBox, entity -> true)) {
            if (PERMISSION_CHECKER_RECEIVER.apply(player)) {
                player.sendMessage(text);
            }
        }
    }
}
