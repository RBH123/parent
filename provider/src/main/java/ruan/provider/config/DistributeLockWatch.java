package ruan.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class DistributeLockWatch implements Watcher {

    private CountDownLatch countDownLatch;

    public DistributeLockWatch() {
    }

    public DistributeLockWatch(CountDownLatch downLatch) {
        this.countDownLatch = downLatch;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.EventType type = watchedEvent.getType();
        if (type == Event.EventType.NodeDeleted) {
            countDownLatch.countDown();
        }
    }
}
