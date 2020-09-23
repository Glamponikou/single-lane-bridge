package case3;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Bridge bridge = new Bridge();
		ArrayList<Car> cars = new ArrayList<Car>();
		int redCounter = 0, blueCounter = 0;
		int numOfCars = 1; // variable must be initialized

		// parsing argument
		try {
			numOfCars = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("Wrong type of argumnets passed");
			System.exit(0);
		}

		// randomly generating blue and red cars and adding them to an arraylist
		for (int i = 0; i < numOfCars; i++) {

			int rand = new Random().nextInt(2);

			if (rand == 0) {
				cars.add(new Car("Red", redCounter, bridge));
				redCounter++;
			} else if (rand == 1) {
				cars.add(new Car("Blue", blueCounter, bridge));
				blueCounter++;
			}

			Thread.sleep(100);
		}

		// printing relevant information
		System.out.println("\n--Total Number of Cars for this Simulation--\n");
		System.out.println("Blue: " + blueCounter);
		System.out.println("Red: " + redCounter + "\n");

		// starting threads
		for (Car car : cars)
			car.start();

		// waiting threads to end
		for (Car car : cars)
			car.join();

		// last print
		System.out.println("Looks like noone else is around");

	}
}
