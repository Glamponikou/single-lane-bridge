package case1;

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

	// runnable divided to three sections
	// enter: tracks time entered
	// cross: waits random amount of seconds
	// exit: tracks time exited
	public void run() {
		bridge.enter(this);
		bridge.cross(this);
		bridge.exit(this);
	}

}
