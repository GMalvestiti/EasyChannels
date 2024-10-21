package net.riser876.easychannels.config.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.enums.OperatorLevel;
import net.riser876.easychannels.enums.PermissionType;

public class PermissionData {

    @Expose
    @SerializedName("permission")
    private String permission = null;

    @Expose
    @SerializedName("operator_level")
    private int operatorLevel = OperatorLevel.ALL.getLevel();

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getOperatorLevel() {
        return this.operatorLevel;
    }

    public void setOperatorLevel(int operatorLevel) {
        this.operatorLevel = operatorLevel;
    }

    public PermissionType getPermissionType() {
        return PermissionType.resolve(this);
    }
}
