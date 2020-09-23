package case1;

import java.text.DecimalFormat;

public class Bridge {

	int carsOnBridge = 0; // monitors how many cars are on the bridge. If value > 1 -> crash

	float timeEntered, timeExited, timePassed; // variables for time tracking

	public Bridge() {

	}

	// tracks time entered
	// increments the variable carsOnBridge
	public void enter(Car car) {

		String aCar = car.getType() + car.getID();

		carsOnBridge++;
		timeEntered = System.nanoTime();
		System.out.println("Car " + aCar + " arrived on bridge");
	}

	// checks if more than one cars are on the bridge simultaneously
	// puts thread to sleep for random amount of seconds
	public void cross(Car car) {

		int duration = (int) (Math.random() * 10000);
		String aCar = car.getType() + car.getID();

		if (isCarOnBridge()) {
			System.out.println("Car " + aCar + " crashed");
			System.exit(1);
		}

		System.out.println(aCar + " is crossing the bridge. This might take a while");

		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// tracks time exited
	// decrements the variable carsOnBridge
	public void exit(Car car) {

		DecimalFormat df1 = new DecimalFormat("#.#"); // makes print more beautiful
		String aCar = car.getType() + car.getID(); // just a shortcut

		timeExited = System.nanoTime();
		timePassed = (float) ((timeExited - timeEntered) / 1000000000);

		System.out.println("Car " + aCar + " took " + df1.format(timePassed) + " seconds to pass the bridge");
		carsOnBridge--;
	}

	public boolean isCarOnBridge() {
		return carsOnBridge > 2;
	}

}
