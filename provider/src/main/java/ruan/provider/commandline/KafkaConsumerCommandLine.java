package ruan.provider.commandline;

import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ruan.provider.elasticsearch.ElasticsearchUtil;
import ruan.provider.pojo.vo.GoodsInfoVo;

@Slf4j
@Component
public class KafkaConsumerCommandLine implements CommandLineRunner {

  @Autowired private ElasticsearchUtil elasticsearchUtil;

  @Override
  @SneakyThrows
  public void run(String... args) {
    elasticsearchUtil.createIndex("goods_info",GoodsInfoVo.class,3,2);
    GoodsInfoVo goodsInfoVo = GoodsInfoVo.builder().name("华为nova6").color("天空之境")
            .createTime(Timestamp.valueOf(LocalDateTime.now())).desc("性能还行！").build();
    elasticsearchUtil.putData(Lists.newArrayList(goodsInfoVo),"goods_info");
    log.info("程序开始执行！");
  }
}
