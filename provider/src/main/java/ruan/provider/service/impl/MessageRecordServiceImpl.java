package ruan.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruan.provider.entity.MessageRecord;
import ruan.provider.mapper.MessageRecordDao;
import ruan.provider.pojo.vo.MessageRecordVo;
import ruan.provider.service.MessageRecordService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ruan
 * @since 2020-10-26
 */
@Service
public class MessageRecordServiceImpl extends ServiceImpl<MessageRecordDao, MessageRecord> implements MessageRecordService {

    @Autowired
    private MessageRecordDao messageRecordDao;

    @Override
    public boolean updateStatus(MessageRecordVo messageRecordVo) {
        MessageRecord messageRecord = new MessageRecord();
        BeanUtils.copyProperties(messageRecordVo, messageRecord);
        int row = messageRecordDao.updateById(messageRecord);
        if (row > 0) {
            return true;
        }
        return false;
    }
}
