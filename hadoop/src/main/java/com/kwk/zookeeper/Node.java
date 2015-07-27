package com.kwk.zookeeper;

import com.google.common.base.Throwables;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Node {
    private static final Logger logger = LoggerFactory.getLogger(Node.class);
    private static final int SESSION_TIMEOUT = 300000;
    public static final String NODE_KEY = "/zk001";

    private ZooKeeper zooKeeper;


    public Node(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public static void main(String[] args) throws InterruptedException {
        ZooKeeper zooKeeper = null;
        try {
            Watcher watcher = new Watcher() {
                public void process(WatchedEvent event) {
                    logger.info("process : " + event.getType());
                }
            };
            //connectionInfo "localhost:2181,localhost:2182,localhost:2183"
            zooKeeper = new ZooKeeper("192.168.11.130:2181", SESSION_TIMEOUT, watcher);
        } catch (IOException e) {
            Throwables.propagate(e);
        }

        try {
            test(zooKeeper);
        } finally {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        }
    }

    private static void test(ZooKeeper zooKeeper) {
        Node node = new Node(zooKeeper);
//        if (!node.exist()) {
//            node.create();
//        }
//
//        node.getChildTest();
//        logger.info("get Data: {}", node.getData());
//        node.setData("info2");
//        logger.info("get Data2: {}", node.getData());
//        node.delete();

        node.testExistsWatch1();
        node.testExistsWatch2();
    }

    public void create() {
        String result = null;
        try {
            result = zooKeeper.create(NODE_KEY, "zk001data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Throwables.propagate(e);
        }
        logger.info("create result : {}", result);
    }

    public void delete() {
        try {
            zooKeeper.delete(NODE_KEY, -1);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Throwables.propagate(e);
        }
    }

    public boolean exist() {
        Stat stat = null;
        try {
            stat = zooKeeper.exists(NODE_KEY, false);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Throwables.propagate(e);
        }
        if (stat == null) {
            return false;
        } else {
            logger.info("exists result : {}", stat.getCzxid());
            return true;
        }
    }

    public static boolean existEx(ZooKeeper zooKeeper, String key) {
        Stat stat = null;
        try {
            stat = zooKeeper.exists(key, false);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Throwables.propagate(e);
        }

        if (stat == null) {
            return false;
        } else {
            logger.info("exists result : {}", stat.getCzxid());
            return true;
        }
    }

    public String getData() {
        String result = null;
        try {
            byte[] bytes = zooKeeper.getData(NODE_KEY, null, null);
            result = new String(bytes);
            logger.info("get data : {}", result);
            return result;
        } catch (Throwable e) {
            logger.error(e.getMessage());
            Throwables.propagate(e);
        }
        return "";
    }

    public void setData(String nodeInfo) {
        Stat stat = null;
        try {
            stat = zooKeeper.setData(NODE_KEY, nodeInfo.getBytes(), -1);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Throwables.propagate(e);
        }
        logger.info("exists result : {}", stat.getVersion());
    }

    public void getChildTest() {
        try {
            zooKeeper.create("/001", "001".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            zooKeeper.create("/002", "002".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

            List<String> list = zooKeeper.getChildren("/", true);
            for (String node : list) {
                logger.info("node {}", node);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            Throwables.propagate(e);
        }
    }

    /**
     * 判断节点是否存在,
     * 设置是否监控这个目录节点，这里的 watcher 是在创建 ZooKeeper实例时指定的 watcher
     * <br>------------------------------<br>
     */
    public void testExistsWatch1() {
        Stat stat = null;
        try {
            if (!Node.existEx(zooKeeper, "/zk001")) {
                zooKeeper.create("/zk001", "zk001".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            stat = zooKeeper.exists("/zk001", true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Throwables.propagate(e);
        }

        if (stat == null) {
            throw new IllegalStateException("stat == null");
        }

        try {
            zooKeeper.setData("/zk001", "testExistsWatch1".getBytes(), -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            zooKeeper.delete("/zk001", -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断节点是否存在,
     * 设置监控这个目录节点的 Watcher
     * <br>------------------------------<br>
     */
    public void testExistsWatch2() {
        Stat stat = null;
        try {
            if (!Node.existEx(zooKeeper, "/zk002")) {
                zooKeeper.create("/zk002", "zk002".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            stat = zooKeeper.exists("/zk002", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    logger.info("testExistsWatch2  watch : {}", event.getType());
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            Throwables.propagate(e);
        }

        if (stat == null) {
            throw new IllegalStateException("stat == null");
        }

        // 触发watch中的process方法   NodeDataChanged
        try {
            zooKeeper.setData("/zk002", "testExistsWatch2".getBytes(), -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 不会触发watch 只会触发一次
        try {
            zooKeeper.setData("/zk002", "testExistsWatch3".getBytes(), -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 不会触发watch 只会触发一次
        try {
            zooKeeper.delete("/zk002", -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
