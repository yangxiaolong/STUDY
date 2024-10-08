package com.example.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestConnect {

    CuratorFramework client;

    @Before
    public void testConnect() {
        //重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);
        //2.第二种方式
        //CuratorFrameworkFactory.builder();
        client = CuratorFrameworkFactory.builder()
                .connectString("centos01:2181,centos02:2181,centos03:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(retryPolicy)
                .namespace("test")
                .build();
        //开启连接
        client.start();
    }

    /**
     * 创建节点：create 持久 临时 顺序 数据
     * 1. 基本创建 ：create().forPath("")
     * 2. 创建节点 带有数据:create().forPath("",data)
     * 3. 设置节点的类型：create().withMode().forPath("",data)
     * 4. 创建多级节点  /app1/p1 ：create().creatingParentsIfNeeded().forPath("",data)
     */
    @Test
    public void testCreate() throws Exception {
        //2. 创建节点 带有数据
        //如果创建节点，没有指定数据，则默认将当前客户端的ip作为数据存储
        String path = client.create().forPath("/app2", "hehe".getBytes(StandardCharsets.UTF_8));
        System.out.println(path);
    }

    @Test
    public void testCreate2() throws Exception {
        //1. 基本创建
        //如果创建节点，没有指定数据，则默认将当前客户端的ip作为数据存储
        String path = client.create().forPath("/app1");
        System.out.println(path);
    }

    @Test
    public void testCreate3() throws Exception {
        //3. 设置节点的类型
        //默认类型：持久化
        String path = client.create().withMode(CreateMode.EPHEMERAL).forPath("/app3", "app3".getBytes(StandardCharsets.UTF_8));
        System.out.println(path);
    }

    @Test
    public void testCreate4() throws Exception {
        //4. 创建多级节点  /app1/p1
        //creatingParentsIfNeeded():如果父节点不存在，则创建父节点
        String path = client.create().creatingParentsIfNeeded().forPath("/app4/p1");
        System.out.println(path);
    }

    /**
     * 查询节点：
     * 1. 查询数据：get: getData().forPath()
     * 2. 查询子节点： ls: getChildren().forPath()
     * 3. 查询节点状态信息：ls -s:getData().storingStatIn(状态对象).forPath()
     */
    @Test
    public void testGet1() throws Exception {
        //1. 查询数据：get
        byte[] data = client.getData().forPath("/app2");
        System.out.println(new String(data));
    }

    @Test
    public void testGet2() throws Exception {
        // 2. 查询子节点： ls
        List<String> path = client.getChildren().forPath("/");
        System.out.println(path);
    }

    @Test
    public void testGet3() throws Exception {
        Stat status = new Stat();
        System.out.println(status);
        //3. 查询节点状态信息：ls -s
        client.getData().storingStatIn(status).forPath("/app1");
        System.out.println(status);
    }

    /**
     * 修改数据
     * 1. 基本修改数据：setData().forPath()
     * 2. 根据版本修改: setData().withVersion().forPath()
     * * version 是通过查询出来的。目的就是为了让其他客户端或者线程不干扰我。
     */
    @Test
    public void testSet() throws Exception {
        client.setData().forPath("/app1", "test".getBytes());
    }

    @Test
    public void testSetForVersion() throws Exception {
        Stat status = new Stat();
        //3. 查询节点状态信息：ls -s
        client.getData().storingStatIn(status).forPath("/app1");
        int version = status.getVersion();//查询出来的 3
        System.out.println(version);
        client.setData().withVersion(version).forPath("/app1", "hehe".getBytes());
    }

    /**
     * 删除节点： delete deleteall
     * 1. 删除单个节点:delete().forPath("/app1");
     * 2. 删除带有子节点的节点:delete().deletingChildrenIfNeeded().forPath("/app1");
     * 3. 必须成功的删除:为了防止网络抖动。本质就是重试。  client.delete().guaranteed().forPath("/app2");
     * 4. 回调：inBackground
     */
    @Test
    public void testDelete() throws Exception {
        // 1. 删除单个节点
        client.delete().forPath("/app1");
    }

    @Test
    public void testDelete2() throws Exception {
        //2. 删除带有子节点的节点
        client.delete().deletingChildrenIfNeeded().forPath("/app4");
    }

    @Test
    public void testDelete3() throws Exception {
        //3. 必须成功的删除
        client.delete().guaranteed().forPath("/app2");
    }

    @Test
    public void testDelete4() throws Exception {
        //4. 回调
        client.delete().guaranteed().inBackground((client, event) -> {
            System.out.println("我被删除了~");
            System.out.println(event);
        }).forPath("/app1");
    }

}
