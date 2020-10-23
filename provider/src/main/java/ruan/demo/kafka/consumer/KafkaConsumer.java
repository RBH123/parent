package ruan.demo.kafka.consumer;//package ruan.demo.kafka.consumer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import ruan.demo.rocketmq.config.Sink;
//
//@Slf4j
//@EnableBinding(Sink.class)
//public class KafkaConsumer {
//
//    @StreamListener(Sink.KAFKAINPUT)
//    public void consumer(String body){
//        log.info("kafka消费消息:{}",body);
//    }
//}
