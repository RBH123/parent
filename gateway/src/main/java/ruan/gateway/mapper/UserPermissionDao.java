package ruan.gateway.mapper;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import ruan.gateway.entity.UserPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 *
 * @author ruan
 * @since 2021-01-25
 */
@Mapper
public interface UserPermissionDao extends BaseMapper<UserPermission> {

    List<UserPermission> getUserPermissionsByIdList(List<BigInteger> permissionIdList);
}

