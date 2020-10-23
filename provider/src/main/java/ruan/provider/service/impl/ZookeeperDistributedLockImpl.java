package ruan.provider.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruan.provider.common.ServerException;
import ruan.provider.config.DistributeLockWatch;
import ruan.provider.constant.DistributedConstant;
import ruan.provider.service.ZookeeperDistributedLock;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class ZookeeperDistributedLockImpl implements ZookeeperDistributedLock {

    @Autowired
    private ZooKeeper zooKeeper;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private DistributeLockWatch watcher = new DistributeLockWatch(countDownLatch);

    @SneakyThrows
    private String acquireLock(String nodePath) {
        String lockPrefix = DistributedConstant.LOCK_PREFIX;
        List<String> children = zooKeeper.getChildren(lockPrefix, false);
        String znodePath = nodePath.replace(lockPrefix, "").replace("/", "");
        if (CollectionUtils.isEmpty(children)) {
            throw new ServerException("当前父节点下没有子节点创建成功！");
        }
        if (children.indexOf(znodePath) < 0) {
            throw new ServerException("zookeeper创建节点异常！");
        }
        Collections.sort(children);
        int currentNodeIndex = children.indexOf(znodePath);
        if (currentNodeIndex == 0) {
            return nodePath;
        }
        String preZnodePath = lockPrefix.concat("/").concat(children.get(--currentNodeIndex));
        Stat exists = zooKeeper.exists(preZnodePath, watcher);
        if (exists == null) {
            return acquireLock(nodePath);
        }
        countDownLatch.await();
        return acquireLock(nodePath);
    }

    @Override
    @SneakyThrows
    public boolean releaseLock(String nodePath) {
        Stat exists = zooKeeper.exists(nodePath, false);
        if (exists == null) {
            return true;
        }
        zooKeeper.delete(nodePath, exists.getVersion());
        return true;
    }

    @SneakyThrows
    @Override
    public String tryLock(String key) {
        String lockPrefix = DistributedConstant.LOCK_PREFIX;
        Stat parentNodeExist = zooKeeper.exists(lockPrefix, false);
        if (parentNodeExist == null) {
            try {
                zooKeeper.create(lockPrefix, lockPrefix.replace("/", "").getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } catch (KeeperException.NodeExistsException e) {
            }
        }
        key = lockPrefix.concat(key.contains("/") ? key : "/".concat(key));
        String nodePath = zooKeeper.create(key, key.replace("/", "").getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        return acquireLock(nodePath);
    }
}
