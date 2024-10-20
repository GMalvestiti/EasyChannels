package net.riser876.easychannels.config.data;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.config.adapters.LocalChannelFormatAdapter;
import net.riser876.easychannels.config.adapters.RadiusAdapter;

public class LocalChannelData {

    @SerializedName("enabled")
    public boolean enabled = true;

    @SerializedName("radius")
    @JsonAdapter(RadiusAdapter.class)
    public int radius = 50;

    @SerializedName("format")
    @JsonAdapter(LocalChannelFormatAdapter.class)
    public String format;

    @SerializedName("permission_sender")
    public PermissionData permissionSender = null;

    @SerializedName("permission_receiver")
    public PermissionData permissionReceiver = null;
}
