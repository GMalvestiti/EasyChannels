package net.riser876.easychannels.util;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.riser876.easychannels.config.Config;
import net.riser876.easychannels.enums.PermissionType;

import java.util.function.Function;

public class PermissionsUtils {
    public static final Text PERMISSION_REQUIRED_MESSAGE_TEXT = PlaceholderUtils.formatPlayerMessageSimple(Config.getPermissionsRequiredMessage(), null);

    private static boolean hasPermissionVanilla(ServerPlayerEntity player, int operatorLevel) {
        return player.hasPermissionLevel(operatorLevel);
    }

    private static boolean hasPermission(ServerPlayerEntity player, String permission, int operatorLevel) {
        return Permissions.check(player, permission, operatorLevel);
    }

    public static Function<ServerPlayerEntity, Boolean> getPermissionChecker(PermissionType permissionType, int operatorLevel, String permission) {
        if (PermissionType.VANILLA.equals(permissionType)) {
            return (player) -> PermissionsUtils.hasPermissionVanilla(player, operatorLevel);
        }

        return (player) -> PermissionsUtils.hasPermission(player, permission, operatorLevel);
    }

    public static void sendPermissionRequiredMessage(ServerPlayerEntity player) {
        player.sendMessage(PERMISSION_REQUIRED_MESSAGE_TEXT, false);
    }
}
