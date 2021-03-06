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

public class Driver2 {
	/** use a logger instead of System.out.println */
	private static final Logger logger = Logger
			.getLogger("edu.nyu.pqs.ps4.demo.Driver2");

	/**
	 * Run the Driver2 demo application
	 * 
	 * @param args
	 *          a single argument specifying the number of threads
	 */
	public static void main(String[] args) {
		Driver2 driver1 = new Driver2();
		Driver2 driver2 = new Driver2();
		driver1.go1();
		driver2.go2();
	}

	/**
	 * Starts the Driver2 object It will get a stopwatch, set a number of lap
	 * times, reset the watch, then start the watch, set a number of lap times,
	 * stop the watch, then print out all the lap times and stopwatch information.
	 * 
	 */
	private void go1() {
		Runnable runnable = new Runnable() {
			public void run() {
				IStopwatch stopwatch = StopwatchFactory.getStopwatch("ID 1");
				stopwatch.start();
				for (int i = 0; i < 3; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException ie) { /* safely ignore this */
					}
					stopwatch.lap();
				}
				try {
					Thread.sleep(200);
					stopwatch.reset();
					Thread.sleep(100);
				} catch (InterruptedException ie) { /* safely ignore this */
				}
				;
				stopwatch.start();
				for (int i = 0; i < 3; i++) {
					try {
						Thread.sleep(200);
						stopwatch.lap();
						Thread.sleep(200);
					} catch (InterruptedException ie) { /* safely ignore this */
					}
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
	 * Starts the driver2 object It will get a stopwatch, set a number of lap
	 * times, stop the watch, start the watch, set a lap time, stop the watch and
	 * then print out all the lap times and stopwatch information.
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
				try {
					Thread.sleep(200);
					stopwatch.stop();
					Thread.sleep(500);
					stopwatch.start();
					Thread.sleep(600);
					stopwatch.lap();
					Thread.sleep(100);
				} catch (InterruptedException ie) { /* safely ignore this */
				}
				;
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
