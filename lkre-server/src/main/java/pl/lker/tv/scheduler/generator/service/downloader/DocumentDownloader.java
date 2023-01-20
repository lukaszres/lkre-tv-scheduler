package pl.lker.tv.scheduler.generator.service.downloader;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface DocumentDownloader {
    Document download(String channel) throws IOException;
}
