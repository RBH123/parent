package ruan.consumer.message.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface Sink {

    String ROCKETMQ_INPUT = "rocketmqinput";
    String KAFKAINPUT = "kafkainput";

    @Input(ROCKETMQ_INPUT)
    SubscribableChannel rocketmqInput();

    @Input(KAFKAINPUT)
    SubscribableChannel kafkaInput();
}
