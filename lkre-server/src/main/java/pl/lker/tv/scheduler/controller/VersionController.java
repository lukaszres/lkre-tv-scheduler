package pl.lker.tv.scheduler.controller;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lker.tv.scheduler.Application;

import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class VersionController {

    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        try {
            return new ResponseEntity<>(getVersionFromPom(), HttpStatus.OK);
        } catch (IOException | XmlPullParserException e) {
            return new ResponseEntity<>("---", HttpStatus.OK);
        }
    }

    private String getVersionFromPom() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model;
        model = reader.read(
                new InputStreamReader(
                        Application.class.getResourceAsStream(
                                "/META-INF/maven/pl.lkre/lkre-server/pom.xml"
                        )
                )
        );
        return model.getParent().getVersion();
    }
}
