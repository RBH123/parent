package ruan.demo.kafka.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ruan.demo.rocketmq.config.Source;

@Slf4j
@Component
@EnableBinding(Source.class)
public class KafkaProduct {

    @Autowired
    private Source source;

    public void sendMessage(String body) {
        source.kafkaOutput().send(MessageBuilder.withPayload(body).build());
    }
}
