package ruan.gateway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruan.gateway.common.ServerException;
import ruan.gateway.constant.GlobalEnum;
import ruan.gateway.entity.Users;
import ruan.gateway.mapper.UsersDao;
import ruan.gateway.service.UsersService;
import ruan.gateway.util.ObjectUtil;
import ruan.gateway.util.SnowflakesUtil;
import ruan.provider.pojo.ao.UsersAo;
import ruan.provider.pojo.vo.UsersVo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
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
        if (ao.getUserId() != null) {
            Users users = usersDao.getUserByUserId(ao.getUserId());
            if (users == null) {
                return null;
            }
            UsersVo vo = new UsersVo();
            BeanUtils.copyProperties(users, vo);
            return vo;
        } else {
            Map<String, Object> conditionMap = ObjectUtil.beanToMap(ao);
            List<Users> usersList = usersDao.selectByMap(conditionMap);
            if (CollectionUtils.isEmpty(usersList)) {
                return null;
            }
            return usersList.parallelStream().map(u -> {
                UsersVo vo = new UsersVo();
                BeanUtils.copyProperties(u, vo);
                return vo;
            }).collect(Collectors.toList());
        }
    }

    @Override
    @SneakyThrows
    public boolean register(UsersVo vo) {
        Users users = new Users();
        BeanUtils.copyProperties(vo, users);
        QueryWrapper<Users> query = new QueryWrapper<>();
        query.eq("username", vo.getUsername());
        query.eq("status", GlobalEnum.ZERO.getCode());
        Integer count = this.baseMapper.selectCount(query);
        if (count != null && count > 0) {
            throw new ServerException("昵称已经被注册，请重新输入！");
        }
        users.setUserId(SnowflakesUtil.INSTANCE().nextId());
        users.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        int insert = this.baseMapper.insert(users);
        return insert > 0;
    }
}
