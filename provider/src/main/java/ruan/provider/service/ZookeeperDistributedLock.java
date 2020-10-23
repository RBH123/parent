package ruan.provider.service;

public interface ZookeeperDistributedLock {

    String tryLock(String key);

    boolean releaseLock(String key);
}
