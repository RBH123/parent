package ruan.provider.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Author: rocky
 * Date: 2020/11/17 16:28
 * Project: parent
 * Package: ruan.provider.config
 */
public class ProjectPathConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return System.getProperty("user.dir");
    }
}
