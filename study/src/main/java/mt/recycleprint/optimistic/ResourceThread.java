package mt.recycleprint.optimistic;

import java.util.concurrent.locks.Condition;

public class ResourceThread implements Runnable {

    private final String nextRunning;//下一个线程的名称
    private final int printTime;//打印次数
    private final Condition condition;
    private final ResourceLock resourceLock;
    private ResourceThread nextThread;//下一个运行的线程

    public ResourceThread(String nextRunning, int printTime, ResourceLock resourceLock) {
        this.nextRunning = nextRunning;
        this.printTime = printTime;
        this.condition = resourceLock.newCondition();
        this.resourceLock = resourceLock;
    }

    public void setNextThread(ResourceThread nextThread) {
        this.nextThread = nextThread;
    }

    @Override
    public void run() {
//        for (int j = 0; j < 10; j++) {
        resourceLock.lock();

        try {
            // 判断
            while (!resourceLock.getRunning().equals(nextRunning)) {
                condition.await();
            }
            // 干活
            for (int i = 1; i <= printTime; i++) {
                System.out.println(resourceLock.getRunning() + "		" + i);
            }
            // 通知
            resourceLock.setRunning(nextThread.nextRunning);
            nextThread.condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resourceLock.unlock();
        }
    }
//    }
}