package be.kdg.sa.flightmanagement;

import be.kdg.sa.flightmanagement.messaging.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FlightManagementApplication {
    private final RabbitSender sender;

    @Autowired
    public FlightManagementApplication(RabbitSender sender) {
        this.sender = sender;
    }

    // Test

    public static void main(String[] args) {
        SpringApplication.run(FlightManagementApplication.class, args);
    }

    // TODO naar configuration class
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
