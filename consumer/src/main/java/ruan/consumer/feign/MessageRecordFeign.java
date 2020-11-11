package ruan.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ruan.provider.pojo.ao.MessageRecordAo;

@FeignClient(name = "nacos-provider")
public interface MessageRecordFeign {

    @RequestMapping(value = "/updateMessageStatus", method = RequestMethod.POST)
    boolean updateMessageStatus(MessageRecordAo ao);
}
