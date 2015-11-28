package com.kwk.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class Curator {
    private static String path = "/cyw/c";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = null;
        client = createSimple("192.168.11.130:2181");
        client.start();
        createNode(client);
        leaderElection(client);

        System.out.println("Press enter/return to quit\n");
        new BufferedReader(new InputStreamReader(System.in)).readLine();
        CloseableUtils.closeQuietly(client);
    }

    private static void createNode(CuratorFramework client) throws Exception {

        if (client.checkExists().forPath(path) == null) {
            client.create().creatingParentsIfNeeded().forPath(path, "中国".getBytes());
        }
    }

    public static CuratorFramework createSimple(String connectionString) {
        // these are reasonable arguments for the ExponentialBackoffRetry.
        // The first retry will wait 1 second - the second will wait up to 2 seconds - the
        // third will wait up to 4 seconds.
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // The simplest way to get a CuratorFramework instance. This will use default values.
        // The only required arguments are the connection string and the retry policy
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
    }

    public static void leaderElection(CuratorFramework client) {
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter()
        {
            public void takeLeadership(CuratorFramework client) throws Exception
            {
                Thread.sleep(3000);
                System.out.println("leader:" + new Date());
                // this callback will get called when you are the leader
                // do whatever leader work you need to and only exit
                // this method when you want to relinquish leadership
            }
        };

        LeaderSelector selector = new LeaderSelector(client, path, listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();
    }
}
