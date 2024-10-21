package net.riser876.easychannels.enums;

import net.riser876.easychannels.config.data.PermissionData;

import java.util.Objects;

public enum PermissionType {
    VANILLA,
    BOTH;

    public static PermissionType resolve(PermissionData permissionData) {
        if (Objects.isNull(permissionData.getPermission()) || permissionData.getPermission().isBlank()) {
            return VANILLA;
        }

        return BOTH;
    }
}
