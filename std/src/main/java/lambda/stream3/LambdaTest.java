package lambda.stream3;

/**
 * @auther yangxiaolong
 * @create 2024/9/1
 */
public class LambdaTest {

    Runnable r1 = () -> System.out.println(this + " r1");

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this + " r2");
        }
    };

    public static void main(String[] args) {
        LambdaTest test = new LambdaTest();
        Thread t1 = new Thread(test.r1);
        t1.start();

        Thread t2 = new Thread(test.r2);
        t2.start();
    }

}
