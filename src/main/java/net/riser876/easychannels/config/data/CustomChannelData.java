package net.riser876.easychannels.config.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.config.adapters.RadiusAdapter;

import java.util.Objects;

public class CustomChannelData {

    @Expose
    @SerializedName("enabled")
    private boolean enabled = true;

    @Expose
    @SerializedName("literal")
    private String literal = null;

    @Expose
    @SerializedName("format")
    private String format = null;

    @Expose
    @SerializedName("radius")
    @JsonAdapter(RadiusAdapter.class)
    private int radius = -1;

    @Expose
    @SerializedName("dimension_only")
    private boolean dimensionOnly = false;

    @Expose
    @SerializedName("permission_sender")
    private PermissionData permissionSender = null;

    @Expose
    @SerializedName("permission_receiver")
    private PermissionData permissionReceiver = null;

    @Expose
    @SerializedName("compare_metadata")
    private String compareMetadata = null;

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isValid() {
        return Objects.nonNull(this.literal) && Objects.nonNull(this.format);
    }

    public String getLiteral() {
        return this.literal;
    }

    public String getFormat() {
        return this.format;
    }

    public int getRadius() {
        return this.radius;
    }

    public boolean isDimensionOnly() {
        return this.dimensionOnly;
    }

    public PermissionData getPermissionSender() {
        return this.permissionSender;
    }

    public PermissionData getPermissionReceiver() {
        return this.permissionReceiver;
    }

    public String getCompareMetadata() {
        return this.compareMetadata;
    }
}
