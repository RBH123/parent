package ruan.gateway.service.impl;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ruan.gateway.entity.RolePermissionRelation;
import ruan.gateway.mapper.RolePermissionRelationDao;
import ruan.gateway.service.RolePermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ruan
 * @since 2021-01-25
 */
@Service
public class RolePermissionRelationServiceImpl extends ServiceImpl<RolePermissionRelationDao, RolePermissionRelation> implements RolePermissionRelationService {

    @Autowired
    private RolePermissionRelationDao rolePermissionRelationDao;

    @Override
    public List<BigInteger> getPermissionIdByUserId(BigInteger userId) {
        List<BigInteger> permissionIdList = rolePermissionRelationDao.getPermissionIdByUserId(userId);
        if(CollectionUtils.isEmpty(permissionIdList)){
            return Collections.EMPTY_LIST;
        }
        return permissionIdList;
    }
}
