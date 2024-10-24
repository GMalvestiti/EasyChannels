package net.riser876.easychannels.util;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.riser876.easychannels.config.Config;
import net.riser876.easychannels.core.Channel;

import java.util.function.BiConsumer;

public class CommandUtils {

    private static final String ARGUMENT_NAME = Config.getCommandArgumentName();

    public static void register(Channel channel) {
        BiConsumer<Text, ServerPlayerEntity> customChannelMessageSender = channel.MESSAGE_SENDER;
        String literal = channel.CHANNEL_LITERAL;

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal(literal)
                    .then(CommandManager.argument(ARGUMENT_NAME, StringArgumentType.greedyString())
                            .executes((context) -> {
                                ServerPlayerEntity sender = context.getSource().getPlayer();
                                Text message = Text.of(StringArgumentType.getString(context, ARGUMENT_NAME));
                                customChannelMessageSender.accept(message, sender);
                                return 1;
                            })
                    )
            );
        });
    }
}
