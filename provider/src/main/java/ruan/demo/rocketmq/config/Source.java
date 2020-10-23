package ruan.demo.rocketmq.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface Source {

    String OUTPUT = "output";

    String KAFKAOUTPUT = "kafkaoutput";

    /**
     * @return stream消息发送通道
     */
    @Output(OUTPUT)
    MessageChannel rocketMqOutput();

    /**
     *
     * @return kafka消息发送通道
     */
    @Output(KAFKAOUTPUT)
    MessageChannel kafkaOutput();
}
