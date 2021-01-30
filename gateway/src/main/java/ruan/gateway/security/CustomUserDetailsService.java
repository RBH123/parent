package ruan.gateway.security;


import java.util.List;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ruan.gateway.entity.UserInfo;
import ruan.gateway.service.UserPermissionService;
import ruan.gateway.service.UsersService;
import ruan.provider.pojo.ao.UsersAo;
import ruan.provider.pojo.vo.UserPermissionVo;
import ruan.provider.pojo.vo.UsersVo;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UserPermissionService userPermissionService;

    @Override
    @SneakyThrows
    public UserInfo loadUserByUsername(String username) {
        UsersAo ao = UsersAo.builder().username(username).build();
        Object users = usersService.getUsersByUserId(ao);
        if (users == null) {
            return null;
        }
        UsersVo vo = null;
        if (users instanceof UsersVo) {
            vo = (UsersVo) users;
        } else if (users instanceof List) {
            vo = ((List<UsersVo>) users).get(0);
        }
        List<UserPermissionVo> permissionVos = userPermissionService
                .getPermissionsByUserId(vo.getUserId());
        return UserInfo.builder().userId(vo.getUserId()).username(vo.getUsername()).password(vo.getPassword())
                .userPermissionVoList(permissionVos).status(vo.getStatus()).build();
    }
}
