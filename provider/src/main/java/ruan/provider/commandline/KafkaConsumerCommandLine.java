package ruan.provider.commandline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumerCommandLine implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("程序开始执行！");
    }
}
