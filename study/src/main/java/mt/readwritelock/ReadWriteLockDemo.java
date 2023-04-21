package mt.readwritelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yangxiaolong024
 */
public class ReadWriteLockDemo {

    private int num;
    private volatile boolean flag = false;//是其他线程修改的状态位
    private final ReadWriteLock rw = new ReentrantReadWriteLock();
    Lock r = rw.readLock();
    Lock w = rw.writeLock();

    public void getNum() {
        r.lock();
        System.out.println(Thread.currentThread().getName() + "开始读取数据" + System.currentTimeMillis() / 1000);
        try {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "读取数据结束" + System.currentTimeMillis() / 1000);
            r.unlock();
        }
    }

    public void setNum() {
        w.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "===写入数据开始" + System.currentTimeMillis() / 1000);
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;
        } finally {
            System.out.println(Thread.currentThread().getName() + "===写入数据结束" + System.currentTimeMillis() / 1000);
            w.unlock();
        }
    }

    /**
     * 演示写锁的降级
     */
    public void setNum2() {
        w.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "===写入数据开始" + System.currentTimeMillis() / 1000);
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;//第一步业务功能
            r.lock();//保证写锁还没释放就悄悄的拿到读锁,以便进行第二步,阻止其他的线程拿到写锁
            System.out.println(Thread.currentThread().getName() + "写锁降级,获取读锁成功");
        } finally {
            System.out.println(Thread.currentThread().getName() + "===写入数据结束" + System.currentTimeMillis() / 1000);
            w.unlock();
        }

        try {
            if (flag) {
                //第二步业务功能
                System.out.println("执行业务逻辑,写锁降级为读锁");
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "写锁降级,释放读锁成功");
            r.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();

        //1.读读不互斥
        /*new Thread(demo::getNum, "A").start();
        new Thread(demo::getNum, "B").start();
        new Thread(demo::getNum, "C").start();
        new Thread(demo::getNum, "D").start();*/
        //2.读写互斥
        /*new Thread(demo::getNum, "A").start();
        new Thread(demo::setNum, "B").start();
        new Thread(demo::getNum, "C").start();
        new Thread(demo::getNum, "D").start();*/
        //3.写锁降级
        new Thread(demo::getNum, "A").start();
        new Thread(demo::setNum2, "B").start();
        new Thread(() -> demo.flag = true, "C").start();
        new Thread(demo::getNum, "D").start();
    }

}
