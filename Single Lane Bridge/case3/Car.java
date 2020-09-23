package case3;

public class Car extends Thread {

	private String type;
	private int id;
	private Bridge bridge;

	public Car(String type, int id, Bridge bridge) {

		this.type = type;
		this.id = id;
		this.bridge = bridge;

		// printing relevant information about car created
		String aCar = type + id;
		if (type.equals("Red"))
			System.out.println("Wild Car " + aCar + " appeared on the dark side of the bridge");
		else if (type.equals("Blue"))
			System.out.println("Wild Car " + aCar + " appeared on the light side of the bridge");
	}

	// getters
	public String getType() {
		return type;
	}

	public int getID() {
		return id;
	}

	// runnable divided to two sections
	// arrive: adds cars to a queue to be handled by the Bridge class
	// cross: each car calls different cross method depending on the type (red,blue)
	public void run() {
		bridge.arrive(this);

		if (type.equals("Red"))
			bridge.redCrossing();
		else if (type.equals("Blue"))
			bridge.blueCrossing();
	}

}
