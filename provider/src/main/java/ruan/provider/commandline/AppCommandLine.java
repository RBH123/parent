package ruan.provider.commandline;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ruan.provider.elasticsearch.ElasticsearchUtil;
import ruan.provider.message.mail.MailSend;
import ruan.provider.util.FileUtil;

@Slf4j
@Component
public class AppCommandLine implements CommandLineRunner {

    @Autowired
    private ElasticsearchUtil elasticsearchUtil;
    @Autowired
    private MailSend mailSend;

    @Override
    @SneakyThrows
    public void run(String... args){
        FileUtil.uploadFile("D:\\WePE.txt");
    }
}
