package ruan.consumer.message.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ruan.consumer.message.config.Sink;
import ruan.consumer.pojo.MessageRecordVo;
import ruan.consumer.pojo.OrderInfoVo;

import java.util.List;

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

    @StreamListener(Sink.CREATEORDER)
    public void consumerCreateOrderMessage(String message) {
        MessageRecordVo messageRecordVo = JSON.parseObject(message, MessageRecordVo.class);
        if (messageRecordVo == null || StringUtils.isEmpty(messageRecordVo.getMessage())) {
            return;
        }
        List<OrderInfoVo> orderInfoVos = JSONArray.parseArray(messageRecordVo.getMessage(), OrderInfoVo.class);
        if(CollectionUtils.isEmpty(orderInfoVos)){
            return;
        }
        subtractInventory(orderInfoVos);
    }


    @Transactional(rollbackFor = Exception.class)
    public void subtractInventory(List<OrderInfoVo> orderInfoVos){

    }
}
