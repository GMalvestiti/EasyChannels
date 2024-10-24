package net.riser876.easychannels.config.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.config.adapters.ChannelRadiusAdapter;

import java.util.Objects;

public class CustomChannelData {

    private static final String DEFAULT_FORMAT = "<gold>${player}</gold> <gray>>></gray> <yellow>${message}";
    private static int counter = 0;

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
    @JsonAdapter(ChannelRadiusAdapter.class)
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

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isValid() {
        return Objects.nonNull(this.literal) && !this.literal.isBlank() && Objects.nonNull(this.format) && !this.format.isBlank();
    }

    public String getLiteral() {
        if (Objects.isNull(this.literal) || this.literal.isBlank()) {
            this.literal = "channel" + CustomChannelData.counter++;
        }

        return this.literal;
    }

    public String getFormat() {
        if (Objects.isNull(this.format) || this.format.isBlank()) {
            this.format = DEFAULT_FORMAT;
        }

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
}
