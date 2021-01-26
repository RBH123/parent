package ruan.gateway.service;

import java.math.BigInteger;
import java.util.List;
import ruan.gateway.entity.UserPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import ruan.provider.pojo.vo.UserPermissionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruan
 * @since 2021-01-25
 */
public interface UserPermissionService extends IService<UserPermission> {
    List<UserPermissionVo> getPermissionsByUserId(BigInteger userId);
}
