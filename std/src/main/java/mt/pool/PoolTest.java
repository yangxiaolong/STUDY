package mt.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yangxiaolong
 */
public class PoolTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            list.add(i);
        }

        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(
                2, 4, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 6; i++) {
            MyThread myThread = new MyThread("name" + i);
            executor.execute(myThread);
        }
    }

    static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println(r);
        }

    }


}
