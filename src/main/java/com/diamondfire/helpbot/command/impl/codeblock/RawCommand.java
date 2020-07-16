package com.diamondfire.helpbot.command.impl.codeblock;

import com.diamondfire.helpbot.command.help.CommandCategory;
import com.diamondfire.helpbot.command.help.HelpContext;
import com.diamondfire.helpbot.command.help.HelpContextArgument;
import com.diamondfire.helpbot.command.permissions.Permission;
import com.diamondfire.helpbot.components.codedatabase.db.datatypes.SimpleData;
import com.diamondfire.helpbot.events.CommandEvent;
import com.diamondfire.helpbot.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.function.BiConsumer;


public class RawCommand extends AbstractSingleQueryCommand {

    private static void sendRawMessage(SimpleData data, TextChannel channel) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(data.getJson());

        for (String part : StringUtil.splitBy(json, 1950)) {
            channel.sendMessage(String.format("```%s```", part)).queue();
        }

    }

    @Override
    public String getName() {
        return "rawcode";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"coderaw"};
    }

    @Override
    public HelpContext getHelpContext() {
        return new HelpContext()
                .description("Gets raw data of a codeblock/action/game value. (Anything within simpledata class)")
                .category(CommandCategory.CODE_BLOCK)
                .addArgument(
                        new HelpContextArgument()
                                .name("codeblock|action|game value")
                );
    }

    @Override
    public Permission getPermission() {
        return Permission.BOT_DEVELOPER;
    }

    @Override
    public void run(CommandEvent event) {
        super.run(event);
    }

    @Override
    public BiConsumer<SimpleData, TextChannel> onDataReceived() {
        return RawCommand::sendRawMessage;
    }

}
