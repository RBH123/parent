package ruan.provider.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ruan.common.common.ResultEnum;
import ruan.common.common.ServerException;
import ruan.provider.pojo.ao.MessageRecordAo;
import ruan.provider.pojo.vo.MessageRecordVo;
import ruan.provider.service.MessageRecordService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ruan
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/messageRecord")
public class MessageRecordController {

    @Autowired
    private MessageRecordService messageRecordService;

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public void updateMessageStatus(@RequestBody MessageRecordAo ao) throws Exception {
        if (ao == null) {
            throw new ServerException(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
        MessageRecordVo messageRecordVo = new MessageRecordVo();
        BeanUtils.copyProperties(ao, messageRecordVo);
        boolean tmp = messageRecordService.updateStatus(messageRecordVo);
    }
}
