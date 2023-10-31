package nuber.students;

import java.util.HashMap;

public class AssignmentDriver {

	
	public static void main(String[] args) throws Exception {

		//turn this or off to enable/disable output from the dispatch's logEvent function
		//use the logEvent function to print out debug output when required.
		boolean logEvents = true;
		
		HashMap<String, Integer> testRegions = new HashMap<String, Integer>();
		testRegions.put("Test Region1", 3);
		testRegions.put("Test Region2", 100);
		
		NuberDispatch dispatch = new NuberDispatch(testRegions, logEvents);
		
		Passenger testPassenger1 = new Passenger("Alex", 100);
		Passenger testPassenger2 = new Passenger("Alex", 100);
		Passenger testPassenger3 = new Passenger("Alex", 100);
		Passenger testPassenger4 = new Passenger("Alex", 100);
		Passenger testPassenger5 = new Passenger("Alex", 100);
		
		dispatch.bookPassenger(testPassenger1, "Test Region1");
		dispatch.bookPassenger(testPassenger2, "Test Region1");
		dispatch.bookPassenger(testPassenger3, "Test Region1");
		dispatch.bookPassenger(testPassenger4, "Test Region1");
		Thread.sleep(10000);
		dispatch.bookPassenger(testPassenger5, "Test Region1");
		
		Thread.sleep(30000);
		dispatch.shutdown();

		
//		/**
//		 * This driver has a number of different sections that you can uncomment as you progress through the assignment
//		 * Once you have completed all parts, you should be able to run this entire function uncommented successfully
//		 */
//
//		Passenger testPassenger = new Passenger("Alex", 100);
//
//		Driver testDriver = new Driver("Barbara", 100);
//		try {
//			//should store the passenger, and then sleep the thread for as long as the driver's random timeout takes
//			testDriver.pickUpPassenger(testPassenger);
//
//			//should sleep the thread for as long as the passenger's random timeout takes
//			testDriver.driveToDestination();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		//test creating a dispatch object
//		NuberDispatch dispatch = new NuberDispatch(testRegions, logEvents);
//		
//		//create two new bookings
//		Booking b1 = new Booking(dispatch, testPassenger);
//		Booking b2 = new Booking(dispatch, testPassenger);
//		
//		//test creating a new region
//		NuberRegion region = new NuberRegion(dispatch, "Test Region", 10);
//
//		//test adding a driver to dispatch
//		dispatch.addDriver(testDriver);
//		
//		//test booking a single passenger
//		dispatch.bookPassenger(testPassenger, "Test Region");
//
//		//shutdown the dispatch when it's done
//		dispatch.shutdown();

		
		
		
		
		//create NuberDispatch for given regions and max simultaneous jobs per region
		//once you have the above running, you should be able to uncomment the Simulations below to start to put everything together
		
		HashMap<String, Integer> regions = new HashMap<String, Integer>();
		regions.put("North", 50);
		regions.put("South", 50);
		
		//new Simulation(regions, 1, 10, 1000, logEvents);
		//new Simulation(regions, 5, 10, 1000, logEvents);
		//new Simulation(regions, 10, 10, 1000, logEvents);
		//new Simulation(regions, 10, 100, 1000, logEvents);
		//new Simulation(regions, 1, 50, 1000, logEvents);
	}

}
