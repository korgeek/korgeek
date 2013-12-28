package korgeek.util;

public class InfinityThread extends Thread {

	public void run() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException localInterruptedException) {
		}
	}

	public void startNJoin() {
		start();
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
