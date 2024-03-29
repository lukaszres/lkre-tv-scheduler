package pl.lker.tv.scheduler.generator.service.channel;

import pl.lker.tv.scheduler.generator.service.downloader.DocumentDownloaderImpl;
import pl.lker.tv.scheduler.model.Channel;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ChannelListService {
    private ChannelService channelService = new ChannelServiceImpl(new DocumentDownloaderImpl());

    public List<Channel> getChannels(List<String> channelNames) {
        List<Channel> channelList = new ArrayList<>();
        channelNames.forEach(
                channel -> {
                    try {
                        channelList.add(channelService.getChannel(channel));
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
        );
        return channelList;
    }


}

