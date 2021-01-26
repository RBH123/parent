package ruan.gateway.service.impl;

import ruan.gateway.entity.UserRole;
import ruan.gateway.mapper.UserRoleDao;
import ruan.gateway.service.UserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}
