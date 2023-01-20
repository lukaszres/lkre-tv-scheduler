package pl.sda.springboot.generator.service.channel;

import pl.sda.springboot.model.Channel;

import java.io.IOException;
import java.text.ParseException;

public interface ChannelService {
    Channel getChannel(String channel) throws IOException, ParseException;
}
