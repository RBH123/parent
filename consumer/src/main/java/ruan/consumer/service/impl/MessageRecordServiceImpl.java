package ruan.consumer.service.impl;

import ruan.consumer.entity.MessageRecord;
import ruan.consumer.mapper.MessageRecordDao;
import ruan.common.service.MessageRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ruan
 * @since 2020-10-26
 */
@Service
public class MessageRecordServiceImpl extends ServiceImpl<MessageRecordDao, MessageRecord> implements MessageRecordService {

}
