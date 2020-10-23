package ruan.demo.service.impl;

import ruan.demo.entity.MessageRecord;
import ruan.demo.mapper.MessageRecordDao;
import ruan.demo.service.MessageRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ruan
 * @since 2020-10-20
 */
@Service
public class MessageRecordServiceImpl extends ServiceImpl<MessageRecordDao, MessageRecord> implements MessageRecordService {

}
