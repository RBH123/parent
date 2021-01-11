package ruan.provider.common;


import com.google.common.collect.Lists;
import java.util.List;

public class DynamicDataSourceContextHolder {

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    private static List<Object> dataSourceKeyList = Lists.newArrayList();

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void setDataSource(String dataSource){
        contextHolder.set(dataSource);
    }

    public static void emptyDataSource(){
        contextHolder.remove();
    }

    public static void setTragetDataSource(List<Object> dataSourceList){
        dataSourceKeyList.addAll(dataSourceList);
    }
}
