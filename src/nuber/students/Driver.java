package nuber.students;

public class Driver extends Person {
	private Passenger passenger;
	
	public Driver(String driverName, int maxSleep) {
		super(driverName, maxSleep);
	}
	
	/**
	 * Stores the provided passenger as the driver's current passenger and then
	 * sleeps the thread for between 0-maxDelay milliseconds.
	 * 
	 * @param newPassenger Passenger to collect
	 */
	public void pickUpPassenger(Passenger newPassenger) {
		this.passenger = newPassenger;
		delay((int)(Math.random() * maxSleep));
	}

	/**
	 * Sleeps the thread for the amount of time returned by the current 
	 * passenger's getTravelTime() function
	 */
	public void driveToDestination() {
		delay(passenger.getTravelTime());
	}
	
	/**
	 * Sleeps the current thread for the specified amount of time
	 * 
	 * @param time The time in ms that the thread will sleep for
	 * @throws InterruptedException
	 */
	private void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
