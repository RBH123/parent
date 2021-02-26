package ruan.consumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruan.consumer.entity.Users;
import ruan.consumer.mapper.UsersDao;
import ruan.consumer.service.UsersService;
import ruan.consumer.pojo.ao.UsersAo;
import ruan.consumer.pojo.vo.UsersVo;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ruan
 * @since 2021-02-26
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public UsersVo getUsersVo(UsersAo ao) {
        Users users = new Users();
        BeanUtils.copyProperties(ao, users);
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", ao.getUserId());
        users = usersDao.selectOne(wrapper);
        UsersVo vo = new UsersVo();
        BeanUtils.copyProperties(users,vo);
        return vo;
    }
}
