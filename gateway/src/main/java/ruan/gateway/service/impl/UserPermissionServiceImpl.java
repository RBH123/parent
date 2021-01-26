package ruan.gateway.service.impl;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ruan.gateway.entity.UserPermission;
import ruan.gateway.mapper.UserPermissionDao;
import ruan.gateway.service.RolePermissionRelationService;
import ruan.gateway.service.UserPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ruan.provider.pojo.vo.UserPermissionVo;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ruan
 * @since 2021-01-25
 */
@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionDao, UserPermission> implements UserPermissionService {

    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    @Override
    public List<UserPermissionVo> getPermissionsByUserId(BigInteger userId) {
        List<BigInteger> permissionIdList = rolePermissionRelationService.getPermissionIdByUserId(userId);
        if(CollectionUtils.isEmpty(permissionIdList)){
            return Collections.EMPTY_LIST;
        }
        List<UserPermission> userPermissions = this.baseMapper.getUserPermissionsByIdList(permissionIdList);
        if(CollectionUtils.isEmpty(userPermissions)){
            return Collections.EMPTY_LIST;
        }
        return userPermissions.parallelStream().map(u->{
            UserPermissionVo vo = new UserPermissionVo();
            BeanUtils.copyProperties(u,vo);
            return vo;
        }).collect(Collectors.toList());
    }
}
