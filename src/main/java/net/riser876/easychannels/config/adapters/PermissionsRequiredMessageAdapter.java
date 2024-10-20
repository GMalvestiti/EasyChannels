package net.riser876.easychannels.config.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

public class PermissionsRequiredMessageAdapter extends TypeAdapter<String> {

    @Override
    public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
    }

    @Override
    public String read(JsonReader in) throws IOException {
        String format = in.nextString();

        if (Objects.isNull(format) || format.isBlank()) {
            return "<red>You don't have the required permissions to use this chat channel.";
        }

        return format;
    }
}
