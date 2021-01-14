package ruan.provider.handler;


import com.alibaba.fastjson.JSONArray;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * java集合和jdbc varchar互转
 */
@Slf4j
@MappedJdbcTypes({JdbcType.VARCHAR})
@MappedTypes({List.class})
public class ListTypeHandler<T> extends BaseTypeHandler<List<T>> {

    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<T> ts, JdbcType jdbcType){
        if(CollectionUtils.isEmpty(ts)){
            return;
        }
        String str = JSONArray.toJSONString(ts);
        preparedStatement.setString(i,str);
    }

    @Override
    @SneakyThrows
    public List<T> getNullableResult(ResultSet resultSet, String s){
        if(StringUtils.isEmpty(s)){
            return Collections.EMPTY_LIST;
        }
        String string = resultSet.getString(s);
        if(StringUtils.isEmpty(string)){
            return Collections.EMPTY_LIST;
        }
        List<Object> objects = JSONArray.parseArray(string, Object.class);
        return (List<T>)objects;
    }

    @Override
    @SneakyThrows
    public List<T> getNullableResult(ResultSet resultSet, int i){
        String string = resultSet.getString(i);
        if(StringUtils.isEmpty(string)){
            return Collections.EMPTY_LIST;
        }
        List<Object> objects = JSONArray.parseArray(string, Object.class);
        return (List<T>)objects;
    }

    @Override
    @SneakyThrows
    public List<T> getNullableResult(CallableStatement callableStatement, int i)
           {
        String string = callableStatement.getString(i);
        if(StringUtils.isEmpty(string)){
            return Collections.EMPTY_LIST;
        }
        List<Object> objects = JSONArray.parseArray(string, Object.class);
        return (List<T>)objects;
    }
}
