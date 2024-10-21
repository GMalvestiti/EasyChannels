package net.riser876.easychannels.util;

import eu.pb4.placeholders.api.PlaceholderContext;
import eu.pb4.placeholders.api.Placeholders;
import eu.pb4.placeholders.api.parsers.NodeParser;
import eu.pb4.placeholders.api.parsers.TextParserV1;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Map;

public class PlaceholderUtils {
    private static final NodeParser PARSER = NodeParser.merge(TextParserV1.DEFAULT, Placeholders.DEFAULT_PLACEHOLDER_PARSER);
    private static final Text EMPTY_TEXT = Text.of("");

    private static Text parsePlaceholders(String text, ServerPlayerEntity sender) {
        return PARSER.parseText(text, PlaceholderContext.of(sender).asParserContext());
    }

    public static Text formatPlayerMessageSimple(String format, ServerPlayerEntity sender) {
        return formatPlayerMessage(format, sender, EMPTY_TEXT);
    }

    public static Text formatPlayerMessage(String format, ServerPlayerEntity sender, Text message) {
        Text text = parsePlaceholders(format, sender);

        Map<String, Text> placeholders = Map.of(
                "message", message,
                "player", sender.getName()
        );

        return Placeholders.parseText(text, Placeholders.PREDEFINED_PLACEHOLDER_PATTERN, placeholders);
    }
}
