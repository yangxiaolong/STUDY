package mt.volatiletest;

public class Run {

    public static void main(String[] args) {
        MyThread[] my = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            my[i] = new MyThread();

        }
        for (int i = 0; i < 100; i++) {
            my[i].start();
        }
    }

}
