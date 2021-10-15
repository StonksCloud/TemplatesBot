package net.vounty.studios.templates.utilities.objects;

import net.vounty.studios.templates.utilities.enums.PlatformType;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class DiscordStorage extends PlatformStorage {

    private final List<DiscordRoleEntry> discordRoleEntries;
    private final List<DiscordChannelEntry> discordChannelEntries;

    public DiscordStorage(String uniqueId, PlatformType platform, Boolean isInEditMode, List<DiscordRoleEntry> discordRoleEntries, List<DiscordChannelEntry> discordChannelEntries, String... keywords) {
        super(uniqueId, platform, isInEditMode, keywords);
        this.discordRoleEntries = discordRoleEntries;
        this.discordChannelEntries = discordChannelEntries;
    }

    public static class DiscordRoleEntry {

        private String name;
        private Long deadline, permissions;
        private Color color;
        private Boolean hoisted, mentionable;

        public DiscordRoleEntry(String name, Long deadline, Long permissions, Color color, Boolean hoisted, Boolean mentionable) {
            this.name = name;
            this.deadline = deadline;
            this.permissions = permissions;
            this.color = color;
            this.hoisted = hoisted;
            this.mentionable = mentionable;
        }

        public String getName() {
            return name;
        }

        public Long getDeadline() {
            return deadline;
        }

        public Long getPermissions() {
            return permissions;
        }

        public Color getColor() {
            return color;
        }

        public Boolean getHoisted() {
            return hoisted;
        }

        public Boolean getMentionable() {
            return mentionable;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDeadline(Long deadline) {
            this.deadline = deadline;
        }

        public void setPermissions(Long permissions) {
            this.permissions = permissions;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setHoisted(Boolean hoisted) {
            this.hoisted = hoisted;
        }

        public void setMentionable(Boolean mentionable) {
            this.mentionable = mentionable;
        }

    }

    public static class DiscordChannelEntry {

        private String name, topic;
        private ChannelType channelType;
        private Integer bitrate, slowMode, userLimit, position;
        private Boolean news, nsfw;

        public DiscordChannelEntry(String name, String topic, ChannelType channelType, Integer bitrate, Integer slowMode, Integer userLimit, Integer position, Boolean news, Boolean nsfw) {
            this.name = name;
            this.topic = topic;
            this.channelType = channelType;
            this.bitrate = bitrate;
            this.slowMode = slowMode;
            this.userLimit = userLimit;
            this.position = position;
            this.news = news;
            this.nsfw = nsfw;
        }

        public String getName() {
            return name;
        }

        public String getTopic() {
            return topic;
        }

        public ChannelType getChannelType() {
            return channelType;
        }

        public Integer getBitrate() {
            return bitrate;
        }

        public Integer getSlowMode() {
            return slowMode;
        }

        public Integer getUserLimit() {
            return userLimit;
        }

        public Integer getPosition() {
            return position;
        }

        public Boolean getNews() {
            return news;
        }

        public Boolean getNsfw() {
            return nsfw;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public void setChannelType(ChannelType channelType) {
            this.channelType = channelType;
        }

        public void setBitrate(Integer bitrate) {
            this.bitrate = bitrate;
        }

        public void setSlowMode(Integer slowMode) {
            this.slowMode = slowMode;
        }

        public void setUserLimit(Integer userLimit) {
            this.userLimit = userLimit;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public void setNews(Boolean news) {
            this.news = news;
        }

        public void setNsfw(Boolean nsfw) {
            this.nsfw = nsfw;
        }

    }

    enum ChannelType {TEXT, VOICE, STAGE, CATEGORY}

    public List<DiscordRoleEntry> getDiscordRoleEntries() {
        return discordRoleEntries;
    }

    public List<DiscordChannelEntry> getDiscordChannelEntries() {
        return discordChannelEntries;
    }

    public List<DiscordChannelEntry> getDiscordCategoryEntries() {
        List<DiscordChannelEntry> array = new LinkedList<>();
        for (DiscordChannelEntry entry : this.getDiscordChannelEntries()) if (entry.getChannelType().equals(ChannelType.CATEGORY)) array.add(entry);
        return array;
    }

}
