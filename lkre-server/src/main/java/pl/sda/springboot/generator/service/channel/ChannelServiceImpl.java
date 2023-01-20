package pl.sda.springboot.generator.service.channel;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pl.sda.springboot.generator.service.SeanceService;
import pl.sda.springboot.generator.service.downloader.DocumentDownloader;
import pl.sda.springboot.generator.service.genre.GenreService;
import pl.sda.springboot.generator.service.genre.GenreServiceImpl;
import pl.sda.springboot.model.Channel;
import pl.sda.springboot.model.Seance;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public class ChannelServiceImpl implements ChannelService {
    private DocumentDownloader downloader;
    private SeanceService seanceService = new SeanceService();
    private GenreService genreServiceImpl = new GenreServiceImpl();

    ChannelServiceImpl(DocumentDownloader downloader) {
        this.downloader = downloader;
    }

    @Override
    public Channel getChannel(String channel) throws IOException, ParseException {
        Document document = downloader.download(channel);
        Optional<Element> dayOptional = Optional.ofNullable(document.getElementsByClass("day_0")
                .first());
        List<Seance> seancesList = seanceService.getSeances(getChannelName(channel), dayOptional);
        List<String> genres = genreServiceImpl.createGenres(seancesList);
        return new Channel(seancesList, genres);
    }

    private String getChannelName(String channel) {

        channel = channel.replace("+", " ");
        channel = channel.replace("%2B", "+");
        channel = channel.replace("%C5%82", "ł");
        return channel;
    }

}
