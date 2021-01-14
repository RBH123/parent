package ruan.provider.config;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import ruan.provider.common.DynamicDataSourceContextHolder;

public class MultiDataSource extends AbstractRoutingDataSource {

    @Override
    public DataSource determineTargetDataSource(){
        return super.determineTargetDataSource();
    }

    @Override
    public Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSource();
    }


    public void setDefaultDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }

    public boolean containDataSource(Object dataSourceKey){
        return DynamicDataSourceContextHolder.containDataSourceKey(dataSourceKey);
    }


    public void setDataSource(Map<String,DataSource> dataSource){
        Map<Object,Object> map = Maps.newHashMap();
        map.putAll(dataSource);
        super.setTargetDataSources(map);
        DynamicDataSourceContextHolder.setTragetDataSource(Lists.newArrayList(dataSource.keySet()));
    }
}
