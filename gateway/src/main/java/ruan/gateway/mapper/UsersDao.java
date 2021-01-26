package ruan.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.math.BigInteger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ruan.gateway.entity.Users;

/**
 * Mapper 接口
 *
 * @author ruan
 * @since 2021-01-25
 */
@Mapper
public interface UsersDao extends BaseMapper<Users> {

    Users getUserByUserId(@Param("userId") BigInteger userId);
}

