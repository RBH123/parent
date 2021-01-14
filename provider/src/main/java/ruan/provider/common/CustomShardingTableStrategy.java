package ruan.provider.common;


import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import java.math.BigInteger;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;

public class CustomShardingTableStrategy implements PreciseShardingAlgorithm<BigInteger> {

    /**
     * 分库，用
     * @param collection
     * @param preciseShardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<BigInteger> preciseShardingValue) {
        String logicTableName = preciseShardingValue.getLogicTableName();
        if(StringUtils.isNotBlank(logicTableName) && logicTableName.equalsIgnoreCase("order_detail")){
            for (String str : collection) {
                BigInteger value = preciseShardingValue.getValue();
                BigInteger size = BigInteger.valueOf(collection.size());
                if(BigInteger.ZERO.compareTo(size)  >= 0){
                    break;
                }
                if(str.endsWith(value.mod(size).toString())){
                    return str;
                }
            }
        }
        return collection.toArray()[0].toString();
    }
}
