package net.riser876.easychannels.config.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.config.adapters.LocalChannelRadiusAdapter;
import net.riser876.easychannels.enums.ChannelPermission;

import java.util.Objects;

public class LocalChannelData {

    private static final String DEFAULT_FORMAT = "<white><bold>[L]</bold></white> <gold>${player}</gold> <gray>>></gray> <white>${message}";

    @Expose
    @SerializedName("enabled")
    private boolean enabled = true;

    @Expose
    @SerializedName("radius")
    @JsonAdapter(LocalChannelRadiusAdapter.class)
    private int radius = 50;

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

    public ChannelPermission getChannelPermission() {
        if (Objects.isNull(this.permissionSender) && Objects.isNull(this.permissionReceiver)) {
            return ChannelPermission.NONE;
        }

        if (Objects.nonNull(this.permissionSender) && Objects.nonNull(this.permissionReceiver)) {
            return ChannelPermission.BOTH;
        }

        if (Objects.nonNull(this.permissionSender)) {
            return ChannelPermission.SENDER;
        }

        return ChannelPermission.RECEIVER;
    }
}
