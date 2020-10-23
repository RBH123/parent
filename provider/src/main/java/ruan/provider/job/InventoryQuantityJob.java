package ruan.provider.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import ruan.provider.constant.RedisKeyConstant;
import ruan.provider.entity.GoodsInfo;
import ruan.provider.mapper.GoodsInfoDao;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@ElasticSimpleJob("1 * * * * ?")
public class InventoryQuantityJob implements SimpleJob {

    @Autowired
    GoodsInfoDao goodsInfoDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("开始执行定时任务执行定时任务inventoryCountJob");
        long startTime = System.currentTimeMillis();
        List<GoodsInfo> allGoodsInventory = goodsInfoDao.getAllGoodsInventory();
        Optional.ofNullable(allGoodsInventory).ifPresent(a -> a.parallelStream().forEach(g -> {
            String key = RedisKeyConstant.INVENTORY_COUNT.concat(g.getId().toString());
            redisTemplate.opsForValue().set(key, g.getInventoryCount());
        }));
        log.info("定时任务执行完成inventoryCountJob，耗时:{}", System.currentTimeMillis() - startTime);
    }
}
