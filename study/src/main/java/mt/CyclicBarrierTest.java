package mt;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("线程组执行结束"));
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i, cyclicBarrier)).start();
        }
        // CyclicBarrier 可以重复利用，
        // 这个是CountDownLatch做不到的
        for (int i = 11; i < 16; i++) {
            new Thread(new readNum(i, cyclicBarrier)).start();
        }
    }

    static class readNum implements Runnable {
        private final int id;
        private final CyclicBarrier cyc;

        public readNum(int id, CyclicBarrier cyc) {
            this.id = id;
            this.cyc = cyc;
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id:" + id);
                try {
                    cyc.await();
                    System.out.println("线程组任务" + id + "结束，其他任务继续");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
