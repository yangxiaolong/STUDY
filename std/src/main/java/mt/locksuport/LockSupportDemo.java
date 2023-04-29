package mt.locksuport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangxiaolong024
 */
public class LockSupportDemo {

    static final Object object = new Object();
    static final Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        //        synchronizedLock();
        //        lockAwaitSignal();
        Thread a = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + "come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " " + "被 唤醒");
        }, "A");
        a.start();

        new Thread(() -> {
            LockSupport.unpark(a);
            //LockSupport.unpark(a);//unpark() 最多一个证
            System.out.println(Thread.currentThread().getName() + " " + "通知");
        }, "B").start();
    }

    private static void lockAwaitSignal() {
        new Thread(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " " + "come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " " + "被 唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " " + "通知");
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }

    private static void synchronizedLock() {
        new Thread(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " " + "come in");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + "被唤醒");
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (object) {
                object.notify();
                System.out.println(Thread.currentThread().getName() + " " + "通知");
            }
        }, "B").start();
    }

}
