package pl.lker.tv.scheduler.generator.service.channel;

import pl.lker.tv.scheduler.model.Channel;

import java.io.IOException;
import java.text.ParseException;

public interface ChannelService {
    Channel getChannel(String channel) throws IOException, ParseException;
}
