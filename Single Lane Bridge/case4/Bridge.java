package case4;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;

public class Bridge {

	private float timeEntered, timeExited, timePassed; // variables for time tracking
	private Queue<Car> redQueue = new LinkedList<>(); // queue that holds red cars
	private Queue<Car> blueQueue = new LinkedList<>(); // queue that holds blue cars
	private boolean blueFlag = false; // flag. When true blue cars are crossing the bridge
	private boolean redFlag = true; // flag. When true red cars are crossing the bridge

	public Bridge() {
	}

	// adds cars to the appropriate queue depending on the type (red,blue)
	public void arrive(Car car) {
		String aCar = car.getType() + car.getID();

		if (car.getType().equals("Red")) {
			redQueue.add(car);
			System.out.println("Car " + aCar + " added to the red waiting list");
		} else if (car.getType().equals("Blue")) {
			blueQueue.add(car);
			System.out.println("Car " + aCar + " added to the blue waiting list");

		}
	}

	// uses intrinsic lock of object Bridge
	// counts the size difference of the two queues
	// checks if blue cars have the lock and the size difference is greater than two
	// (the number two was chosen randomly)
	// pulls first blue car from queue
	// waits random amount of seconds
	// reverses flags
	// notifies all threads
	public synchronized void blueCrossing() {
		int carsWaiting = Math.abs(blueQueue.size() - redQueue.size());
		while (redFlag && carsWaiting < 2)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		Car car = blueQueue.poll();
		waitSomeTime(car);

		blueFlag = false;
		redFlag = true;
		notifyAll();
	}

	// uses intrinsic lock of object Bridge
	// counts the size difference of the two queues
	// checks if red cars have the lock and the size difference is greater than two
	// (the number two was chosen randomly)
	// pulls first red car from queue
	// waits random amount of seconds
	// reverses flags
	// notifies all threads
	public synchronized void redCrossing() {
		int carsWaiting = Math.abs(blueQueue.size() - redQueue.size());
		while (blueFlag && carsWaiting < 2)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		Car car = redQueue.poll();
		waitSomeTime(car);

		redFlag = false;
		blueFlag = true;
		notifyAll();
	}

	public void waitSomeTime(Car car) {

		int duration = (int) (Math.random() * 5000);
		DecimalFormat df = new DecimalFormat("#.#");
		String aCar = car.getType() + car.getID();

		timeEntered = System.nanoTime();
		System.out.println("Car " + aCar + " arrived on bridge");

		System.out.println("Car " + aCar + " is crossing the bridge. This might take a while");

		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		timeExited = System.nanoTime();
		timePassed = (float) ((timeExited - timeEntered) / 1000000000);

		System.out.println("Car " + aCar + " took " + df.format(timePassed) + " seconds to pass the bridge");

	}

}
