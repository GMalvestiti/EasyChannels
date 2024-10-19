package net.riser876.easychannels.config.data;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.config.adapters.RadiusAdapter;

import java.util.Objects;

public class CustomChannelData {

    @SerializedName("enabled")
    public boolean enabled = true;

    @SerializedName("literal")
    public String literal = null;

    @SerializedName("format")
    public String format = null;

    @SerializedName("radius")
    @JsonAdapter(RadiusAdapter.class)
    public Integer radius = -1;

    @SerializedName("dimension_only")
    public boolean dimensionOnly = false;

    @SerializedName("permission_sender")
    public PermissionData permissionSender = null;

    @SerializedName("permission_receiver")
    public PermissionData permissionReceiver = null;

    @SerializedName("compare_metadata")
    protected String compareMetadata = null;

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isValid() {
        return Objects.nonNull(this.literal) && Objects.nonNull(this.format);
    }
}
