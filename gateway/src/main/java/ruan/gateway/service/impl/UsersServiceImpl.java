package ruan.gateway.service.impl;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ruan.gateway.entity.Users;
import ruan.gateway.mapper.UsersDao;
import ruan.gateway.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ruan.gateway.util.ObjectUtil;
import ruan.provider.pojo.ao.UsersAo;
import ruan.provider.pojo.vo.UsersVo;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ruan
 * @since 2021-01-25
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public Object getUsersByUserId(UsersAo ao) {
        if(ao.getUserId() != null){
            Users users = usersDao.getUserByUserId(ao.getUserId());
            if(users == null){
                return null;
            }
            UsersVo vo = new UsersVo();
            BeanUtils.copyProperties(users,vo);
            return vo;
        }else {
            Map<String, Object> conditionMap = ObjectUtil.beanToMap(ao);
            List<Users> usersList = usersDao.selectByMap(conditionMap);
            if(CollectionUtils.isEmpty(usersList)){
                return null;
            }
            return usersList.parallelStream().map(u->{
                UsersVo vo = new UsersVo();
                BeanUtils.copyProperties(u,vo);
                return vo;
            }).collect(Collectors.toList());
        }
    }
}
