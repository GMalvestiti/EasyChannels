package net.riser876.easychannels.enums;

import net.riser876.easychannels.config.data.PermissionData;

import java.util.Objects;

public enum PermissionType {
    NONE,
    BOTH,
    VANILLA;

    public PermissionType resolve(PermissionData permissionData) {
        if (Objects.isNull(permissionData)) {
            return NONE;
        }

        if (Objects.isNull(permissionData.permission) || permissionData.permission.isBlank()) {
            return VANILLA;
        }

        return BOTH;
    }
}
