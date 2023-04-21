package mt.deadLock;

public class Run {
	public static void main(String[] args) {
		try {
			DeadThread t1 = new DeadThread();
			t1.setUsername("a");
			Thread thread1 = new Thread(t1);
			thread1.start();
			Thread.sleep(100);
			t1.setUsername("b");
			Thread thread2 = new Thread(t1);
			thread2.start();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
