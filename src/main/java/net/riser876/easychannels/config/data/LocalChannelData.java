package net.riser876.easychannels.config.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.config.adapters.ChannelRadiusAdapter;

import java.util.Objects;

public class LocalChannelData {

    private static final String DEFAULT_FORMAT = "<white><bold>[L]</bold></white> <gold>${player}</gold> <gray>>></gray> <white>${message}";

    @Expose
    @SerializedName("enabled")
    private boolean enabled = true;

    @Expose
    @SerializedName("radius")
    @JsonAdapter(ChannelRadiusAdapter.class)
    private int radius = 50;

    @Expose
    @SerializedName("dimension_only")
    private boolean dimensionOnly = true;

    @Expose
    @SerializedName("format")
    private String format = DEFAULT_FORMAT;

    @Expose
    @SerializedName("permission_sender")
    private PermissionData permissionSender = null;

    @Expose
    @SerializedName("permission_receiver")
    private PermissionData permissionReceiver = null;

    public boolean isEnabled() {
        return this.enabled;
    }

    public int getRadius() {
        return this.radius;
    }

    public boolean isDimensionOnly() {
        return this.dimensionOnly;
    }

    public String getFormat() {
        if (Objects.isNull(this.format) || this.format.isBlank()) {
            this.format = DEFAULT_FORMAT;
        }

        return this.format;
    }

    public PermissionData getPermissionSender() {
        return this.permissionSender;
    }

    public PermissionData getPermissionReceiver() {
        return this.permissionReceiver;
    }
}
