package v.o.i.d.ref;

import java.lang.ref.WeakReference;


public class ThreadTest {

	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// ignore
		}
	}
	

	static class Task implements Runnable {
		@Override
		public void run() {
			System.out.println("in");
			sleep(10000);
			System.out.println("out");
		}

	}


	public static void main(String[] args) {
		Thread t = new Thread(new Task());
		WeakReference tr = new WeakReference(t);
		WeakReference wr = new WeakReference(new Object());

		System.out.println("start");
		t.start(); t = null;

		sleep(500);
//		sleep(15000);

		System.out.println(wr.get());
		System.out.println(tr.get());
		System.out.println("gc");
		System.gc();
		System.gc();
		System.gc();
		System.out.println(wr.get());
		System.out.println(tr.get());
		System.out.println("end");

	}


}
