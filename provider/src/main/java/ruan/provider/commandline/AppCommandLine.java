package ruan.provider.commandline;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ruan.provider.elasticsearch.ElasticsearchUtil;
import ruan.provider.message.mail.MailSend;

@Slf4j
@Component
public class AppCommandLine implements CommandLineRunner {

    @Override
    @SneakyThrows
    public void run(String... args){
    }
}
