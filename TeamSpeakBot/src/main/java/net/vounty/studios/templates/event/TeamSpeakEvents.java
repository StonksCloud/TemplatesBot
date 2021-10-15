package net.vounty.studios.templates.event;

import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import net.vounty.studios.templates.TeamSpeakBot;
import net.vounty.studios.templates.utilities.objects.DiscordStorage;
import net.vounty.studios.templates.utilities.objects.PlatformStorage;

import java.util.*;

public class TeamSpeakEvents implements TS3Listener {

    private final TeamSpeakBot teamSpeakBot;

    public TeamSpeakEvents(TeamSpeakBot teamSpeakBot) {
        this.teamSpeakBot = teamSpeakBot;
    }

    public void sendMessage(Integer channelId, String message) {
        this.getTeamSpeakBot().getAPI().sendChannelMessage(channelId, message);
    }

    @Override
    public void onTextMessage(TextMessageEvent textMessageEvent) {
        String message = textMessageEvent.getMessage();
        Integer channelId = textMessageEvent.getTargetMode().getIndex();
        String[] arguments = message.split(" ");

        System.out.println(message);

        Client client = this.getTeamSpeakBot().getAPI().getClientByUId(String.valueOf(textMessageEvent.getTargetClientId()));

        if (message.toLowerCase(Locale.ROOT).startsWith("_templates")) {
            if (!client.isServerQueryClient()) {
                this.sendMessage(channelId, "I'm sorry, but only the server Owner has access to this command.");
                return;
            }

            if (!this.getTeamSpeakBot().getTemplates().getProtocolManager().isBackendOnline()) {
                this.sendMessage(channelId, "I'm sorry, but our backend is currently down. Please contact our support team.");
                return;
            }

            if (arguments.length == 0 || arguments.length == 1) {
                this.sendMessage(channelId, "Use '_templates copy (Keywords)' or '_templates load (ID)'");
            } else if (arguments.length == 3) {
                if (arguments[1].toLowerCase(Locale.ROOT).equals("load")) {
                    String uniqueId = arguments[2];

                    Optional<PlatformStorage> optionalPlatformStorage = this.getTeamSpeakBot().getTemplates().getProtocolManager().getPlatformStorage(uniqueId);
                    if (optionalPlatformStorage.isPresent()) {
                        PlatformStorage platformStorage = optionalPlatformStorage.get();
                        if (platformStorage instanceof DiscordStorage) {
                            DiscordStorage discordStorage = (DiscordStorage) platformStorage;

                            return;
                        }
                        this.sendMessage(channelId, "This Storage is not a Discord template!");
                        return;
                    }
                    this.sendMessage(channelId, "Can't find any Storage with the ID '" + uniqueId + "'.");
                } else this.sendMessage(channelId, "Use '_templates' for more help.");
            } else {
                if (arguments[1].toLowerCase(Locale.ROOT).equals("copy")) {
                    List<String> keywords = new ArrayList<>(Arrays.asList(arguments).subList(2, arguments.length));

                    if (keywords.size() == 0 || keywords.size() > 7) {
                        this.sendMessage(channelId, "Please use min 1 and max 7 keywords.");
                        return;
                    }



                    this.getTeamSpeakBot().getTemplates().getProtocolManager().createNewStorageElement(null);

                    this.sendMessage(channelId, "Send -> " + keywords);
                } else this.sendMessage(channelId, "Use '_templates' for more help.");
            }

        }
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {}

    @Override
    public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {}

    @Override
    public void onServerEdit(ServerEditedEvent serverEditedEvent) {}

    @Override
    public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {}

    @Override
    public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {}

    @Override
    public void onClientMoved(ClientMovedEvent clientMovedEvent) {}

    @Override
    public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {}

    @Override
    public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {}

    @Override
    public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {}

    @Override
    public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {}

    @Override
    public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {}

    public TeamSpeakBot getTeamSpeakBot() {
        return teamSpeakBot;
    }

}
