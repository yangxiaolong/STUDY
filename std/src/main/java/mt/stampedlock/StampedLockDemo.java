package mt.stampedlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author yangxiaolong024
 */
public class StampedLockDemo {

    private int num = 0;
    private final StampedLock sl = new StampedLock();

    public int getNum() {
        //使用乐观锁
        long stamp = sl.tryOptimisticRead();
        //执行业务逻辑,拿数据
        int remNum = num;
        System.out.println("现在是乐观锁读取到所的数据" + num + Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //检查,判断是否有写锁在操作,如果有写锁的话,
        //可能会造成读取的数据不一致,因此要切换到普通的读锁模式
        if (!sl.validate(stamp)) {
            //切换到普通的读锁模式
            System.out.println("乐观锁读取数据,发现有写锁,改用 悲观锁===" + Thread.currentThread().getName());
            stamp = sl.readLock();
            try {
                remNum = num;
            } finally {
                System.out.println("乐观锁读取数据,发现有写锁,释放 悲观锁===" + Thread.currentThread().getName());
                sl.unlockRead(stamp);
            }
        }
        System.out.println("返回读取到的数据" + num + Thread.currentThread().getName());
        return remNum;
    }

    public void setNum() {
        //使用写锁,需要接受返回的stamp,解锁的时候要用
        long stamp = sl.writeLock();
        System.out.println("现在是 获取 写锁===" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            num++;
        } finally {
            System.out.println("现在是 释放 写锁===" + Thread.currentThread().getName());
            sl.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) {
        StampedLockDemo demo = new StampedLockDemo();
        new Thread(demo::getNum).start();
        new Thread(demo::setNum).start();
        new Thread(demo::setNum).start();
        new Thread(demo::getNum).start();
    }

}
