package net.riser876.easychannels.config.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ConfigData {

    @SerializedName("enabled")
    public boolean enabled = true;

    @SerializedName("permissions_required_message")
    public String permissionsRequiredMessage = "<red>You don't have the required permissions to use this chat channel.";

    @SerializedName("local_channel")
    public LocalChannelData localChannel = new LocalChannelData();

    @SerializedName("custom_channels")
    public List<CustomChannelData> customChannel = new ArrayList<>();
}
