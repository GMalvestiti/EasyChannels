package net.riser876.easychannels.util;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.riser876.easychannels.config.Config;
import net.riser876.easychannels.enums.PermissionType;

import java.util.function.Function;

public class PermissionsUtils {
    public static final String PERMISSION_REQUIRED_MESSAGE = Config.getPermissionsRequiredMessage();

    public static Function<ServerPlayerEntity, Boolean> getPermissionChecker(PermissionType permissionType, int operatorLevel, String permission) {
        if (PermissionType.VANILLA.equals(permissionType)) {
            return (ServerPlayerEntity player) -> player.hasPermissionLevel(operatorLevel);
        }

        return (ServerPlayerEntity player) -> Permissions.check(player, permission, operatorLevel);
    }

    public static void sendPermissionRequiredMessage(ServerPlayerEntity player) {
        Text text = PlaceholderUtils.formatPlayerMessageSimple(PERMISSION_REQUIRED_MESSAGE, player);
        player.sendMessage(text, false);
    }
}
