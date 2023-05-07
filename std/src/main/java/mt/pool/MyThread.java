package mt.pool;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxiaolong
 */
public class MyThread implements Runnable {
    private final String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        System.out.println(name + " begin");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " end");
    }
}
