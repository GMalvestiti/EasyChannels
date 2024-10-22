package net.riser876.easychannels.util;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.riser876.easychannels.config.Config;
import net.riser876.easychannels.config.data.PermissionData;
import net.riser876.easychannels.enums.ChannelPermission;
import net.riser876.easychannels.enums.PermissionType;

import java.util.Objects;
import java.util.function.Function;

public class PermissionsUtils {
    public static final String PERMISSION_REQUIRED_MESSAGE = Config.getPermissionsRequiredMessage();

    public static Function<ServerPlayerEntity, Boolean> createPermissionChecker(PermissionType permissionType, int operatorLevel, String permission) {
        if (PermissionType.VANILLA.equals(permissionType)) {
            return (ServerPlayerEntity player) -> player.hasPermissionLevel(operatorLevel);
        }

        return (ServerPlayerEntity player) -> Permissions.check(player, permission, operatorLevel);
    }

    public static void sendPermissionRequiredMessage(ServerPlayerEntity player) {
        Text text = PlaceholdersUtils.formatPlayerMessageSimple(PERMISSION_REQUIRED_MESSAGE, player);
        player.sendMessage(text, false);
    }

    public static ChannelPermission getChannelPermission(PermissionData permissionSender, PermissionData permissionReceiver) {
        if (Objects.isNull(permissionSender) && Objects.isNull(permissionReceiver)) {
            return ChannelPermission.NONE;
        }

        if (Objects.nonNull(permissionSender) && Objects.nonNull(permissionReceiver)) {
            return ChannelPermission.BOTH;
        }

        if (Objects.nonNull(permissionSender)) {
            return ChannelPermission.SENDER;
        }

        return ChannelPermission.RECEIVER;
    }
}
