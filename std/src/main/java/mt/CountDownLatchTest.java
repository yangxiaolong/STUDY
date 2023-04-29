package mt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxiaolong024
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " begin");
            try {
                TimeUnit.SECONDS.sleep(50L);
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + " countDown");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        try {
            latch.await(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " continue");
    }

}
