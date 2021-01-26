package ruan.gateway.entity;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import ruan.provider.pojo.vo.UserPermissionVo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements UserDetails {

    private String username;
    private String password;
    private List<UserPermissionVo> userPermissionVoList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(CollectionUtils.isEmpty(userPermissionVoList)){
            return null;
        }
        return userPermissionVoList.parallelStream().map(u->{
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(u.getPermission().toString());
            return authority;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
