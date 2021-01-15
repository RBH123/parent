package ruan.provider.config;

import java.io.File;
import javax.servlet.MultipartConfigElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@Slf4j
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        try {
            File classpath = ResourceUtils.getFile("classpath:");
            String absolutePath = classpath.getAbsolutePath();
            absolutePath = absolutePath.concat("\\tmp");
            File tmpFile = new File(absolutePath);
            if(!tmpFile.exists()){
                tmpFile.mkdir();
            }
            factory.setLocation(absolutePath);
        }catch (Exception e){
            log.error("配置multipart异常:{}",e);
        }
        return factory.createMultipartConfig();
    }
}
