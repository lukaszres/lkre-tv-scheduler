package pl.sda.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesUtil {
    @Value("${server.port}")
    private String serverPort;

    public String getServerPort() {
        return serverPort;
    }
}
