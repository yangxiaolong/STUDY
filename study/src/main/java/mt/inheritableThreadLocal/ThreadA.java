package mt.inheritableThreadLocal;

public class ThreadA extends Thread {
	
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("A " + Tools.t.get());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
