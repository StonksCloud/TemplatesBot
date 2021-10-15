package net.vounty.studios.templates.events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.vounty.studios.templates.DiscordBot;
import net.vounty.studios.templates.utilities.objects.DiscordStorage;
import net.vounty.studios.templates.utilities.objects.PlatformStorage;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DiscordMessageEvent extends ListenerAdapter {

    private final DiscordBot discordBot;
    private final Map<Guild, Integer> timeout = new LinkedHashMap<>();

    public DiscordMessageEvent(DiscordBot discordBot) {
        this.discordBot = discordBot;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Member member = event.getMember();
        Message message = event.getMessage();
        Guild guild = event.getGuild();

        assert member != null;

        String rawMessage = message.getContentRaw();
        String[] arguments = rawMessage.split(" ");

        if (rawMessage.toLowerCase(Locale.ROOT).startsWith("_templates")) {
            if (!member.isOwner()) {
                this.sendMessageAndDelete(event.getChannel(), "I'm sorry, but only the server Owner has access to this command.");
                return;
            }

            if (!this.getDiscordBot().getTemplates().getProtocolManager().isBackendOnline()) {
                this.sendMessageAndDelete(event.getChannel(), "I'm sorry, but our backend is currently down. Please contact our support team.");
                return;
            }

            if (arguments.length == 0 || arguments.length == 1) {
                this.sendMessageAndDelete(event.getChannel(), "Use '_templates copy (Keywords)' or '_templates load (ID)'");
            } else if (arguments.length == 3) {
                if (arguments[1].toLowerCase(Locale.ROOT).equals("load")) {
                    String uniqueId = arguments[2];

                    Optional<PlatformStorage> optionalPlatformStorage = this.getDiscordBot().getTemplates().getProtocolManager().getPlatformStorage(uniqueId);
                    if (optionalPlatformStorage.isPresent()) {
                        PlatformStorage platformStorage = optionalPlatformStorage.get();
                        if (platformStorage instanceof DiscordStorage) {
                            DiscordStorage discordStorage = (DiscordStorage) platformStorage;

                            if (this.getTimeout().containsKey(guild)) {
                                this.sendMessageAndDelete(event.getChannel(), "Please wait " + this.getTimeout().get(guild) + " seconds.");
                                return;
                            }

                            this.getTimeout().put(guild, 30);


                            return;
                        }
                        this.sendMessageAndDelete(event.getChannel(), "This Storage is not a Discord template!");
                        return;
                    }
                    this.sendMessageAndDelete(event.getChannel(), "Can't find any Storage with the ID '" + uniqueId + "'.");
                } else this.sendMessageAndDelete(event.getChannel(), "Use '_templates' for more help.");
            } else {
                if (arguments[1].toLowerCase(Locale.ROOT).equals("copy")) {
                    List<String> keywords = new ArrayList<>(Arrays.asList(arguments).subList(2, arguments.length));

                    if (keywords.size() == 0 || keywords.size() > 7) {
                        this.sendMessageAndDelete(event.getChannel(), "Please use min 1 and max 7 keywords.");
                        return;
                    }

                    boolean success = this.getDiscordBot().getTemplates().getProtocolManager().createNewStorageElement(null);
                    if (success) {
                        
                    } else this.sendMessageAndDelete(event.getChannel(), "Can't send post request to api. Please contact our staff team.");

                } else this.sendMessageAndDelete(event.getChannel(), "Use '_templates' for more help.");
            }

        }
    }

    public void sendMessageAndDelete(TextChannel textChannel, String message) {
        textChannel.sendMessage(message).queue(messageSend -> Executors.newSingleThreadScheduledExecutor().schedule(messageSend::delete, 5, TimeUnit.SECONDS));
    }

    public Map<Guild, Integer> getTimeout() {
        return timeout;
    }

    public DiscordBot getDiscordBot() {
        return discordBot;
    }

}
