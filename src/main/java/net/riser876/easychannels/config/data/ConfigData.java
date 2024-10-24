package net.riser876.easychannels.config.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigData {

    private static final String DEFAULT_FORMAT = "<red>You don't have the required permissions to use this chat channel.";
    private static final String DEFAULT_COMMAND_ARGUMENT_NAME = "message";

    @Expose
    @SerializedName("enabled")
    private boolean enabled = true;

    @Expose
    @SerializedName("command_argument_name")
    private String commandArgumentName = DEFAULT_COMMAND_ARGUMENT_NAME;

    @Expose
    @SerializedName("permissions_required_message")
    private String permissionsRequiredMessage = DEFAULT_FORMAT;

    @Expose
    @SerializedName("local_channel")
    private LocalChannelData localChannel = new LocalChannelData();

    @Expose
    @SerializedName("custom_channels")
    private List<CustomChannelData> customChannel = new ArrayList<>();

    public boolean isModEnabled() {
        return this.enabled;
    }

    public String getCommandArgumentName() {
        if (Objects.isNull(this.commandArgumentName) || this.commandArgumentName.isBlank()) {
            this.commandArgumentName = DEFAULT_COMMAND_ARGUMENT_NAME;
        }

        return this.commandArgumentName;
    }

    public String getPermissionsRequiredMessage() {
        if (Objects.isNull(this.permissionsRequiredMessage) || this.permissionsRequiredMessage.isBlank()) {
            this.permissionsRequiredMessage = DEFAULT_FORMAT;
        }

        return this.permissionsRequiredMessage;
    }

    public LocalChannelData getLocalChannelData() {
        return this.localChannel;
    }

    public List<CustomChannelData> getCustomChannelData() {
        return this.customChannel;
    }
}
