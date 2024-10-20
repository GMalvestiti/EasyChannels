package net.riser876.easychannels.config.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import net.riser876.easychannels.config.adapters.PermissionsRequiredMessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConfigData {

    @Expose
    @SerializedName("enabled")
    private boolean enabled = true;

    @Expose
    @SerializedName("permissions_required_message")
    @JsonAdapter(PermissionsRequiredMessageAdapter.class)
    private String permissionsRequiredMessage;

    @Expose
    @SerializedName("local_channel")
    private LocalChannelData localChannel = new LocalChannelData();

    @Expose
    @SerializedName("custom_channels")
    private List<CustomChannelData> customChannel = new ArrayList<>();

    public boolean isModEnabled() {
        return this.enabled;
    }

    public String getPermissionsRequiredMessage() {
        return this.permissionsRequiredMessage;
    }

    public LocalChannelData getLocalChannelData() {
        return this.localChannel;
    }

    public List<CustomChannelData> getCustomChannelData() {
        return this.customChannel;
    }
}
