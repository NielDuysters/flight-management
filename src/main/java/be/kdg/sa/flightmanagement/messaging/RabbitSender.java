package be.kdg.sa.flightmanagement.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class RabbitSender {

    /*
    private final MessagingConfig config;
    @Autowired
    public RabbitSender(MessagingConfig config) {
        this.config = config;
    }

    public void send(String queue, String msg) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(config.getHost());
        factory.setVirtualHost(config.getVhost());
        factory.setPort(config.getPort());
        factory.setUsername(config.getUser());
        factory.setPassword(config.getPass());

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queue, false, false, false, null);
            channel.basicPublish("", queue, null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + msg + "'");
        }
    }
*/

    private final MessagingConfig config;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitSender(MessagingConfig config, RabbitTemplate rabbitTemplate) {
        this.config = config;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String queue, String msg) {
        rabbitTemplate.convertAndSend(queue, msg);
        System.out.println("[+] Sent " + msg);
    }
}
