package mt;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxiaolong024
 */
public class SemaphoreTest {

    /*
     * 6部车来抢3个车位
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//三个车位
        for (int i = 1; i <= 6; i++) {//6部车
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, i + "").start();
        }
    }

}
