package mt.inheritableThreadLocal;

public class Run {
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println("Main " + Tools.t.get());
			try {
				Thread.sleep(100);
				ThreadA a = new ThreadA();
				a.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
