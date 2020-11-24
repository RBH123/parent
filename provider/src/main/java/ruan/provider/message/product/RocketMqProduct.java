package ruan.provider.message.product;

import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ruan.provider.message.config.Source;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Component
@EnableBinding({Source.class})
public class RocketMqProduct {

    @Resource
    private Source source;

    public boolean sendMessage(String tag, String key, String body) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(RocketMQHeaders.TAGS, tag);
        headers.put(RocketMQHeaders.KEYS, key);
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        Message message = MessageBuilder.createMessage(body, messageHeaders);
        boolean send = source.rocketMqOutput().send(message);
        return send;
    }
}
