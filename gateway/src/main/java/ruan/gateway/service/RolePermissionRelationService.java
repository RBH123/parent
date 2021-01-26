package ruan.gateway.service;

import java.math.BigInteger;
import java.util.List;
import ruan.gateway.entity.RolePermissionRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruan
 * @since 2021-01-25
 */
public interface RolePermissionRelationService extends IService<RolePermissionRelation> {

    List<BigInteger> getPermissionIdByUserId(BigInteger userId);
}
