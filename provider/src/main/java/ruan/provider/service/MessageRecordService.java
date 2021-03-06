package ruan.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ruan.provider.entity.MessageRecord;
import ruan.provider.pojo.vo.MessageRecordVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruan
 * @since 2020-10-26
 */
public interface MessageRecordService extends IService<MessageRecord> {
    boolean updateStatus(MessageRecordVo messageRecordVo);
}
