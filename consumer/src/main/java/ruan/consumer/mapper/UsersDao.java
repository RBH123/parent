package ruan.consumer.mapper;

import ruan.consumer.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 *
 * @author ruan
 * @since 2021-02-26
 */
@Mapper
public interface UsersDao extends BaseMapper<Users> {

}

