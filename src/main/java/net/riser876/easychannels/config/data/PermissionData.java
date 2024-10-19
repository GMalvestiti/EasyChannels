package net.riser876.easychannels.config.data;

import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.enums.OperatorLevel;

public class PermissionData {

    @SerializedName("permission")
    public String permission = null;

    @SerializedName("operator_level")
    public int operatorLevel = OperatorLevel.ALL.getLevel();
}
