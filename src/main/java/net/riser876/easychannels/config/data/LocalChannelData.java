package net.riser876.easychannels.config.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.config.adapters.LocalChannelFormatAdapter;
import net.riser876.easychannels.config.adapters.RadiusAdapter;
import net.riser876.easychannels.enums.ChannelPermission;

import java.util.Objects;

public class LocalChannelData {

    @Expose
    @SerializedName("enabled")
    private boolean enabled = true;

    @Expose
    @SerializedName("radius")
    @JsonAdapter(RadiusAdapter.class)
    private int radius = 50;

    @Expose
    @SerializedName("format")
    @JsonAdapter(LocalChannelFormatAdapter.class)
    private String format;

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
