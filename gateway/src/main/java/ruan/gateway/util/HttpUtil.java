package ruan.gateway.util;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtil {


    public static String getRequestJsonBody(HttpServletRequest request) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(request.getInputStream()))) {
            String body = null;
            StringBuilder result = new StringBuilder();
            while ((body = br.readLine()) != null) {
                result.append(body);
            }
            JSONObject jsonObject = JSON.parseObject(result.toString());
            return jsonObject.toJSONString();
        } catch (Exception e) {
            log.info("获取请求中的参数异常:{}", e);
        }
        return null;
    }
}
