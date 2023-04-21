package mt.myreentrantlock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author yangxiaolong
 */
public class MyReentrantLock {

    abstract static class Sync extends AbstractQueuedSynchronizer {

        protected boolean skipFair() {
            throw new UnsupportedOperationException();
        }

        protected boolean skipCheckQueuedPredecessors() {
            throw new UnsupportedOperationException();
        }

        boolean initialTryLock() {
            Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                //add skipFair
                if (skipFair() && compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (getExclusiveOwnerThread() == current) {
                if (++c < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(c);
                return true;
            }
            return false;
        }

        protected final boolean tryAcquire(int acquires) {
            //add skipCheckQueuedPredecessors
            if (getState() == 0 && skipCheckQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
    }

    static final class NonfairSync extends Sync {
        private static final long serialVersionUID = 7316153563782823691L;

        @Override
        protected boolean skipFair() {
            return true;
        }

        @Override
        protected boolean skipCheckQueuedPredecessors() {
            return true;
        }

    }

    static final class FairSync extends Sync {
        private static final long serialVersionUID = -3000897897090466540L;

        @Override
        protected boolean skipFair() {
            return !hasQueuedThreads();
        }

        @Override
        protected boolean skipCheckQueuedPredecessors() {
            return !hasQueuedPredecessors();
        }

    }
}
