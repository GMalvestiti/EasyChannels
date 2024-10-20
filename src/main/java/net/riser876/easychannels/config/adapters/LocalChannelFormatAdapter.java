package net.riser876.easychannels.config.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

public class LocalChannelFormatAdapter extends TypeAdapter<String> {

    @Override
    public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
    }

    @Override
    public String read(JsonReader in) throws IOException {
        String format = in.nextString();

        if (Objects.isNull(format) || format.isBlank()) {
            return "<white><bold>[L]</bold></white> <gold>${player}</gold> <gray>>></gray> <white>${message}";
        }

        return format;
    }
}
