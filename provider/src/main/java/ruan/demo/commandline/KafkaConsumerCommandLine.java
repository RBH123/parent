package ruan.demo.commandline;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ruan.demo.entity.MessageRecord;
import ruan.demo.kafka.product.KafkaProduct;
import ruan.demo.mapper.MessageRecordDao;
import ruan.demo.pojo.vo.MessageRecordVo;
import ruan.demo.rocketmq.product.RocketMqProduct;
import ruan.demo.util.SnowflakesUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Component
public class KafkaConsumerCommandLine implements CommandLineRunner {

    @Autowired
    KafkaProduct kafkaProduct;
    @Autowired
    RocketMqProduct rocketMqProduct;
    @Autowired
    MessageRecordDao dao;

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public void run(String... args) {
        String message = "今天天气好晴朗";
        kafkaProduct.sendMessage(message);
        MessageRecordVo messageRecordVo = MessageRecordVo
                .builder()
                .id(SnowflakesUtil.INSTANCE().nextId())
                .createTime(Timestamp.valueOf(LocalDateTime.now())).message(message)
                .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                .status(0)
                .build();
        MessageRecord messageRecord = new MessageRecord();
        BeanUtils.copyProperties(messageRecordVo, messageRecord);
        dao.insert(messageRecord);
        rocketMqProduct.sendMessage("test-tag", "test-key", "今天有点冷！");
    }
}
