package ruan.gateway.service;

import java.math.BigInteger;
import ruan.gateway.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import ruan.provider.pojo.ao.UsersAo;
import ruan.provider.pojo.vo.UsersVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruan
 * @since 2021-01-25
 */
public interface UsersService extends IService<Users> {

    Object getUsersByUserId(UsersAo ao);
}
