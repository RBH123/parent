package ruan.common.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class IpAddressConvert extends ClassicConverter {

    @Override
    @SneakyThrows
    public String convert(ILoggingEvent iLoggingEvent) {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        return hostAddress;
    }
}
