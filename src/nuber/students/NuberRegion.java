package nuber.students;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * A single Nuber region that operates independently of other regions, other than getting 
 * drivers from bookings from the central dispatch.
 * 
 * A region has a maxSimultaneousJobs setting that defines the maximum number of bookings 
 * that can be active with a driver at any time. For passengers booked that exceed that 
 * active count, the booking is accepted, but must wait until a position is available, and 
 * a driver is available.
 * 
 * Bookings do NOT have to be completed in FIFO order.
 * 
 * @author james
 *
 */
public class NuberRegion {
	
	private NuberDispatch dispatch;
	private String regionName;
	private ExecutorService service;
	private boolean isActive;
	private ThreadPoolExecutor pool;
	
	/**
	 * Creates a new Nuber region
	 * 
	 * @param dispatch The central dispatch to use for obtaining drivers, and logging events
	 * @param regionName The regions name, unique for the dispatch instance
	 * @param maxSimultaneousJobs The maximum number of simultaneous bookings the region is allowed to process
	 */
	public NuberRegion(NuberDispatch dispatch, String regionName, int maxSimultaneousJobs) {
		this.dispatch = dispatch;
		this.regionName = regionName;
		this.service = Executors.newFixedThreadPool(maxSimultaneousJobs);
		this.pool = (ThreadPoolExecutor) service;
		this.isActive = true;
	}
	
	/**
	 * Creates a booking for given passenger, and adds the booking to the 
	 * collection of jobs to process. Once the region has a position available, and a driver is available, 
	 * the booking should commence automatically. 
	 * 
	 * If the region has been told to shutdown, this function should return null, and log a message to the 
	 * console that the booking was rejected.
	 * 
	 * @param waitingPassenger
	 * @return a Future that will provide the final BookingResult object from the completed booking
	 */
	public Future<BookingResult> bookPassenger(Passenger waitingPassenger) {
		System.out.println("\n\nbefore submit");
		System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize());
	    System.out.println("Current threads in pool: " + pool.getPoolSize());
	    System.out.println("Currently executing threads: " + pool.getActiveCount());
	    System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount());
	    System.out.println("Waiting jobs: " + pool.getQueue().size());
		
		if(isActive) {
			dispatch.incrementBookingsAwaitingDriver();
			Future<BookingResult> future = service.submit(new Booking(this.dispatch, waitingPassenger));
			
			System.out.println("\n\nafter submit");
			System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize());
		    System.out.println("Current threads in pool: " + pool.getPoolSize());
		    System.out.println("Currently executing threads: " + pool.getActiveCount());
		    System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount());
		    System.out.println("Waiting jobs: " + pool.getQueue().size());
			
			return future;
		}
		else {
			System.out.println("Booking rejected as this region is shutdown");
			return null;
		}
	}
	
	/**
	 * Called by dispatch to tell the region to complete its existing bookings and stop accepting any new bookings
	 */
	public void shutdown() {
		service.shutdown();
	}
		
}
