package ruan.demo.rocketmq.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface Sink {

    String INPUT = "input";

    String KAFKAINPUT = "kafkainput";

    @Input(INPUT)
    SubscribableChannel rocketMqInput();

    @Input(KAFKAINPUT)
    SubscribableChannel kafkaInput();
}
