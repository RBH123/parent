package ruan.provider.controller;


import java.math.BigInteger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ruan.provider.anno.DynamicDataSource;
import ruan.provider.pojo.vo.FinanceVo;
import ruan.provider.service.FinanceService;
import ruan.provider.util.FileUtil;

/**
 * <p>
 * 融资表 前端控制器
 * </p>
 *
 * @author ruan
 * @since 2021-01-12
 */
@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @DynamicDataSource(value = "slave")
    @RequestMapping(value = "/getFinanceById",method = RequestMethod.POST)
    public void getFinanceById(){
        FinanceVo finance = financeService.getFinanceById(BigInteger.valueOf(3865163402805137408L));
        System.out.println(finance);
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public void upload(HttpServletRequest request){
        FileUtil.multiFileUpload(request);
    }
}
