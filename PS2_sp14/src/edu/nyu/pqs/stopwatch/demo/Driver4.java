package edu.nyu.pqs.stopwatch.demo;

import java.util.List;
import java.util.logging.Logger;

import edu.nyu.pqs.stopwatch.api.IStopwatch;
import edu.nyu.pqs.stopwatch.impl.StopwatchFactory;

/**
 * This is a simple program that demonstrates just some of the functionality of
 * the IStopwatch interface and StopwatchFactory class.
 * 
 * @author Shuo
 * 
 */

public class Driver4 {
	/** use a logger instead of System.out.println */
	private static final Logger logger = Logger
			.getLogger("edu.nyu.pqs.ps4.demo.Driver4");

	/**
	 * Run the Driver4 demo application
	 * 
	 * @param args
	 *          a single argument specifying the number of threads
	 */
	public static void main(String[] args) {
		Driver4 driver1 = new Driver4();
		Driver4 driver2 = new Driver4();
		driver1.go1();
		driver2.go2();
	}

	/**
	 * Starts the Driver4 object It will throw a exception as the stopwatch stops
	 * before it is runnning.
	 * 
	 */
	private void go1() {
		Runnable runnable = new Runnable() {
			public void run() {
				IStopwatch stopwatch = StopwatchFactory.getStopwatch("ID 1");
				stopwatch.stop();
				stopwatch.start();
				for (int i = 0; i < 3; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException ie) { /* safely ignore this */
					}
					stopwatch.lap();
				}
				stopwatch.stop();
				List<Long> times = stopwatch.getLapTimes();
				logger.info(times.toString());
				logger.info(stopwatch.toString());
			}
		};
		Thread driver1Thread = new Thread(runnable);
		driver1Thread.start();
	}

	/**
	 * Starts the driver3 object It will throw exception as the id has already
	 * been taken.
	 * 
	 */
	private void go2() {
		Runnable runnable = new Runnable() {
			public void run() {
				IStopwatch stopwatch = StopwatchFactory.getStopwatch("ID 2");
				stopwatch.start();
				for (int i = 0; i < 2; i++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException ie) { /* safely ignore this */
					}
					stopwatch.lap();
				}
				stopwatch.start();
				stopwatch.stop();
				List<Long> times = stopwatch.getLapTimes();
				logger.info(times.toString());
				logger.info(stopwatch.toString());
			}
		};
		Thread driver1Thread = new Thread(runnable);
		driver1Thread.start();
	}
}
