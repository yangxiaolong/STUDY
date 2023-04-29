package mt.recycleprint.simpleversion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//A打印5次,B打印10次,C打印15次	来10轮
public class SharedResource {

    // Condition 实现分组唤醒需要唤醒的线程们,可以精确唤醒

    private int number = 1;// A:1 B:2 C:3

    private final Lock lock = new ReentrantLock();
    // 一把锁里面有3把钥匙
    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();
    private final Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                c1.await();
            }
            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "	" + i);
            }
            // 通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // 判断
            while (number != 2) {
                c2.await();
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "	" + i);
            }
            // 通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            // 判断
            while (number != 3) {
                c3.await();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "	" + i);
            }
            // 通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 线程操作资源类
        SharedResource resource = new SharedResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print15();
            }
        }, "C").start();
    }

}
