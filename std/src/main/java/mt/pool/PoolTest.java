package mt.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxiaolong
 */
public class PoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 20; i++) {
            System.out.println(1);
            MyThread myThread = new MyThread("name" + i);
            executor.execute(myThread);
        }
    }

}
