package case2;

import java.text.DecimalFormat;
import java.util.concurrent.locks.ReentrantLock;

public class Bridge {

	private float timeEntered, timeExited, timePassed; // variables for time tracking
	private ReentrantLock bridgeBusy = new ReentrantLock(); // lock

	public Bridge() {

	}

	// tracks time entered
	public void enter(Car car) {

		String aCar = car.getType() + car.getID();

		// CRITICAL SECTIONS STARTS HERE!
		bridgeBusy.lock();
		timeEntered = System.nanoTime();
		System.out.println("Car " + aCar + " arrived on bridge");
	}

	// puts thread to sleep for random amount of seconds
	public void cross(Car car) {

		int duration = (int) (Math.random() * 5000);
		String aCar = car.getType() + car.getID();

		System.out.println(aCar + " is crossing the bridge. This might take a while");

		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// tracks time exited
	public void exit(Car car) {

		DecimalFormat df = new DecimalFormat("#.#"); // makes print more beautiful
		String aCar = car.getType() + car.getID(); // just a shortcut

		timeExited = System.nanoTime();
		timePassed = (float) ((timeExited - timeEntered) / 1000000000);

		System.out.println("Car " + aCar + " took " + df.format(timePassed) + " seconds to pass the bridge");

		bridgeBusy.unlock();
		// CRITICAL SECTIONS ENDS HERE!
	}

}
