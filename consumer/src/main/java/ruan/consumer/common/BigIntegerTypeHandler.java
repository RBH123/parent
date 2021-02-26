package ruan.consumer.common;

import lombok.SneakyThrows;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Author: rocky
 * Date: 2021/2/26 16:06
 * Project: parent1
 * Package: ruan.consumer.common
 */
@MappedJdbcTypes(JdbcType.BIGINT)
@MappedTypes(BigInteger.class)
public class BigIntegerTypeHandler implements TypeHandler<BigInteger> {

    @Override
    @SneakyThrows
    public void setParameter(PreparedStatement ps, int i, BigInteger parameter, JdbcType jdbcType) {
        ps.setLong(i, parameter.longValue());
    }

    @Override
    @SneakyThrows
    public BigInteger getResult(ResultSet rs, String columnName) {
        long aLong = rs.getLong(columnName);
        return BigInteger.valueOf(aLong);
    }

    @Override
    @SneakyThrows
    public BigInteger getResult(ResultSet rs, int columnIndex) {
        long aLong = rs.getLong(columnIndex);
        return BigInteger.valueOf(aLong);
    }

    @Override
    @SneakyThrows
    public BigInteger getResult(CallableStatement cs, int columnIndex) {
        long aLong = cs.getLong(columnIndex);
        return BigInteger.valueOf(aLong);
    }
}
