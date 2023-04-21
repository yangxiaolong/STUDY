package mt.recycleprint.optimistic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangxiaolong
 */
public class ResourceLock {

    private String running;//运行中线程的名称

    private final Lock lock;

    public ResourceLock(String running) {
        this.running = running;
        lock = new ReentrantLock();
    }

    public Condition newCondition() {
        return lock.newCondition();
    }

    public String getRunning() {
        return running;
    }

    public void setRunning(String running) {
        this.running = running;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

}
