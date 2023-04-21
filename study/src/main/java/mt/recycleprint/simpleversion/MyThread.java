package mt.recycleprint.simpleversion;

public class MyThread extends Thread {

	public MyService service;

	public MyThread(MyService service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.testMethod();
	}
}
