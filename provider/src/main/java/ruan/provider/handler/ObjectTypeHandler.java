package ruan.provider.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({Object.class})
public class ObjectTypeHandler<T extends Object> extends BaseTypeHandler<T> {

    private Class<T> clazz;

    public ObjectTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o,
            JdbcType jdbcType) throws SQLException {

    }

    @Override
    @SneakyThrows
    public T getNullableResult(ResultSet resultSet, String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        String string = resultSet.getString(s);
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        T t = JSON.parseObject(string, clazz);
        return t;
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        T t = JSONObject.parseObject(string, clazz);
        return t;
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i)
            throws SQLException {
        String string = callableStatement.getString(i);
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        T t = JSONObject.parseObject(string, clazz);
        return t;
    }
}
