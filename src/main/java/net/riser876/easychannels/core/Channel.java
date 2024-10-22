package net.riser876.easychannels.core;

import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.riser876.easychannels.config.data.CustomChannelData;
import net.riser876.easychannels.config.data.LocalChannelData;
import net.riser876.easychannels.config.data.PermissionData;
import net.riser876.easychannels.enums.ChannelPermission;
import net.riser876.easychannels.enums.ChannelType;
import net.riser876.easychannels.util.PermissionsUtils;
import net.riser876.easychannels.util.PlaceholdersUtils;
import net.riser876.easychannels.util.PlayerManagerUtils;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Channel {

    public ChannelPermission CHANNEL_PERMISSION;
    public Function<ServerPlayerEntity, Boolean> PERMISSION_CHECKER_SENDER;
    public Function<ServerPlayerEntity, Boolean> PERMISSION_CHECKER_RECEIVER;
    public Function<ServerPlayerEntity, List<ServerPlayerEntity>> RECEIVER_SELECTOR;
    public BiConsumer<SignedMessage, ServerPlayerEntity> MESSAGE_SENDER;

    public Channel(Object channelData, ChannelType channelType) {
        switch (channelType) {
            case LOCAL: {
                this.createLocalChannel(channelData);
                break;
            }
            case CUSTOM: {
                this.createCustomChannel(channelData);
                break;
            }
        }
    }

    private void createLocalChannel(Object channelData) {
        LocalChannelData localChannelData = (LocalChannelData) channelData;

        this.CHANNEL_PERMISSION = PermissionsUtils.getChannelPermission(localChannelData.getPermissionSender(), localChannelData.getPermissionReceiver());

        this.loadPermissionCheckers(localChannelData.getPermissionSender(), localChannelData.getPermissionReceiver());

        this.RECEIVER_SELECTOR = this.loadReceiverSelector(localChannelData.getRadius(), localChannelData.isDimensionOnly());

        this.MESSAGE_SENDER = this.getMessageSender(localChannelData.getFormat());
    }

    private void createCustomChannel(Object channelData) {
        CustomChannelData customChannelData = (CustomChannelData) channelData;

        this.CHANNEL_PERMISSION = PermissionsUtils.getChannelPermission(customChannelData.getPermissionSender(), customChannelData.getPermissionReceiver());

        this.loadPermissionCheckers(customChannelData.getPermissionSender(), customChannelData.getPermissionReceiver());

        this.RECEIVER_SELECTOR = this.loadReceiverSelector(customChannelData.getRadius(), customChannelData.isDimensionOnly());

        this.MESSAGE_SENDER = this.getMessageSender(customChannelData.getFormat());
    }

    private void loadPermissionCheckers(PermissionData permissionSender, PermissionData permissionReceiver) {
        switch (this.CHANNEL_PERMISSION) {
            case BOTH: {
                this.PERMISSION_CHECKER_SENDER = this.createPermissionChecker(permissionSender);
                this.PERMISSION_CHECKER_RECEIVER = this.createPermissionChecker(permissionReceiver);
                break;
            }
            case SENDER: {
                this.PERMISSION_CHECKER_SENDER = this.createPermissionChecker(permissionSender);
                break;
            }
            case RECEIVER: {
                this.PERMISSION_CHECKER_RECEIVER = this.createPermissionChecker(permissionReceiver);
                break;
            }
        }
    }

    private Function<ServerPlayerEntity, Boolean> createPermissionChecker(PermissionData permissionSender) {
        return PermissionsUtils.createPermissionChecker(
                permissionSender.getPermissionType(),
                permissionSender.getOperatorLevel(),
                permissionSender.getPermission()
        );
    }

    private Function<ServerPlayerEntity, List<ServerPlayerEntity>> loadReceiverSelector(int radius, boolean dimensionOnly) {
        if (radius > 0) {
            return (ServerPlayerEntity sender) -> {
                Box boundingBox = sender.getBoundingBox().expand(radius);

                return sender.getServerWorld().getEntitiesByClass(ServerPlayerEntity.class, boundingBox, entity -> true);
            };
        }

        if (dimensionOnly) {
            return (ServerPlayerEntity sender) -> sender.getServerWorld().getPlayers();
        }

        return (ServerPlayerEntity sender) -> PlayerManagerUtils.PLAYER_MANAGER.getPlayerList();
    }

    public BiConsumer<SignedMessage, ServerPlayerEntity> getMessageSender(String format) {
        return switch (this.CHANNEL_PERMISSION) {
            case BOTH -> (message, sender) -> {
                if (!this.PERMISSION_CHECKER_SENDER.apply(sender)) {
                    PermissionsUtils.sendPermissionRequiredMessage(sender);
                    return;
                }

                Text text = PlaceholdersUtils.formatPlayerMessage(format, sender, message.getContent());

                for (ServerPlayerEntity player : this.RECEIVER_SELECTOR.apply(sender)) {
                    if (this.PERMISSION_CHECKER_RECEIVER.apply(player)) {
                        player.sendMessage(text);
                    }
                }
            };
            case SENDER -> (message, sender) -> {
                if (!this.PERMISSION_CHECKER_SENDER.apply(sender)) {
                    PermissionsUtils.sendPermissionRequiredMessage(sender);
                    return;
                }

                Text text = PlaceholdersUtils.formatPlayerMessage(format, sender, message.getContent());

                for (ServerPlayerEntity player : this.RECEIVER_SELECTOR.apply(sender)) {
                    player.sendMessage(text);
                }
            };
            case RECEIVER -> (message, sender) -> {
                Text text = PlaceholdersUtils.formatPlayerMessage(format, sender, message.getContent());

                for (ServerPlayerEntity player : this.RECEIVER_SELECTOR.apply(sender)) {
                    if (this.PERMISSION_CHECKER_RECEIVER.apply(player)) {
                        player.sendMessage(text);
                    }
                }
            };
            default -> (message, sender) -> {
                Text text = PlaceholdersUtils.formatPlayerMessage(format, sender, message.getContent());

                for (ServerPlayerEntity player : this.RECEIVER_SELECTOR.apply(sender)) {
                    player.sendMessage(text);
                }
            };
        };
    }
}
