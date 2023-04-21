package mt.volatiletest;

public class MyThread extends Thread {

	public volatile static int count;

	synchronized private static void add() {
		for (int i = 0; i < 100; i++) {
			count++;
		}
		System.out.println(count);
	}

	@Override
	public void run() {
		add();
	}

}