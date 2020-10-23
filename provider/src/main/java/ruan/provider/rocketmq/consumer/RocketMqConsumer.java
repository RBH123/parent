package ruan.provider.rocketmq.consumer;//package ruan.demo.rocketmq.consumer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import ruan.demo.rocketmq.config.Sink;
//
//@Slf4j
//@EnableBinding({Sink.class})
//public class RocketMqConsumer {
//
//    @StreamListener(target = Sink.INPUT)
//    public void consumer(String message) {
//        log.info("rocketmq消费消息:{}", message);
//    }
//}
