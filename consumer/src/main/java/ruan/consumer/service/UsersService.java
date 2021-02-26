package ruan.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ruan.consumer.entity.Users;
import ruan.consumer.pojo.ao.UsersAo;
import ruan.consumer.pojo.vo.UsersVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ruan
 * @since 2021-02-26
 */
public interface UsersService extends IService<Users> {

    UsersVo getUsersVo(UsersAo ao);
}
