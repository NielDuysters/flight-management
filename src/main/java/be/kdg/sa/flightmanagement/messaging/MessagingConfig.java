package be.kdg.sa.flightmanagement.messaging;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "messaging")
@Getter
@Setter
@NoArgsConstructor
public class MessagingConfig {
    private String host;
    private String vhost;
    private int port;
    private String user;
    private String pass;
}
