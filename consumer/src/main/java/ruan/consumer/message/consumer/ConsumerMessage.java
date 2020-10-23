package ruan.consumer.message.consumer;

import ruan.consumer.message.config.Sink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Sink.class)
public class ConsumerMessage {

    @StreamListener(Sink.ROCKETMQ_INPUT)
    public void consumerRocketMqMessage(String message) {
        log.info("rocketmq消费消息成功:{}", message);
    }

    @StreamListener(Sink.KAFKAINPUT)
    public void consumerKafkaMessage(String message) {
        log.info("kafka消费消息成功:{}", message);
    }
}
