package ruan.provider.config;



import com.google.common.collect.Lists;
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


    public void setDataSource(Map<Object,Object> dataSource){
        super.setTargetDataSources(dataSource);
        DynamicDataSourceContextHolder.setTragetDataSource(Lists.newArrayList(dataSource.keySet()));
    }
}
