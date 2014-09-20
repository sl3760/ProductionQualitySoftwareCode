package edu.nyu.pqs.stopwatch.impl;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * @author Shuo A thread-safe object that implements IStopwatch interface.It has
 *         all the stopwatch behaviors: start, lap, stop, reset, and provides a
 *         method to get all the lap times.
 */
public class Stopwatch implements IStopwatch {
	private String id;
	private long startTime;

	// a list of recorded lap times or an empty list if no times are recorded.
	private List<Long> lapTimes;

	/**
	 * The status of stopwatch. A stopwatch has three status: RUN, PAUSE and STOP.
	 * RUN represents the stopwatch is running; PAUSE represents the stopwatch is
	 * in the status after stop(); TERMINATE represents the stopwatch is reseted
	 * or never started yet.
	 * 
	 */
	private enum Status {
		RUN, PAUSE, TERMINATE
	}

	private Status status;

	// lock used to synchronize ensure thread safe
	private Object lock = new Object();

	/**
	 * Initializes a newly created Stopwatch object with its id
	 * 
	 * @param id
	 */
	Stopwatch(String id) {
		this.id = id;
		status = Status.TERMINATE;
		lapTimes = new LinkedList<Long>();
	}

	/**
	 * Returns the Id of this stopwatch
	 * 
	 * @return the Id of this stopwatch. Will never be empty or null.
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Starts the stopwatch.
	 * 
	 * @throws IllegalStateException
	 *           if called when the stopwatch is already running
	 */
	@Override
	public void start() {
		synchronized (lock) {
			if (status == Status.RUN) {
				throw new IllegalStateException("The stopwatch " + id
						+ " is already running!");
			} else if (status == Status.PAUSE) {
				startTime = System.currentTimeMillis()
						- lapTimes.remove(lapTimes.size() - 1);
			} else {
				startTime = System.currentTimeMillis();
			}
			status = Status.RUN;
		}
	}

	/**
	 * Stores the time elapsed since the last time lap() was called or since
	 * start() was called if this is the first lap.
	 * 
	 * @throws IllegalStateException
	 *           if called when the stopwatch isn't running
	 */
	@Override
	public void lap() {
		synchronized (lock) {
			if (status != Status.RUN) {
				throw new IllegalStateException("The stopwatch " + id
						+ " isn't running!");
			} else {
				long lapStopTime = System.currentTimeMillis();
				long elapsedTime = lapStopTime - startTime;
				lapTimes.add(elapsedTime);
				startTime = lapStopTime;
			}
		}
	}

	/**
	 * Stops the stopwatch (and records one final lap).
	 * 
	 * @throws IllegalStateException
	 *           if called when the stopwatch isn't running
	 */
	@Override
	public void stop() {
		synchronized (lock) {
			if (status != Status.RUN) {
				throw new IllegalStateException("The stopwatch " + id
						+ " isn't running!");
			} else {
				long stopTime = System.currentTimeMillis();
				long elapsedTime = stopTime - startTime;
				lapTimes.add(elapsedTime);
				status = Status.PAUSE;
			}
		}
	}

	/**
	 * Resets the stopwatch. If the stopwatch is running, this method stops the
	 * watch and resets it. This also clears all recorded laps.
	 */
	@Override
	public void reset() {
		synchronized (lock) {
			startTime = 0;
			lapTimes.clear();
			status = Status.TERMINATE;
		}
	}

	/**
	 * Returns a list of lap times (in milliseconds). This method can be called at
	 * any time and will not throw an exception.
	 * 
	 * @return a list of recorded lap times or an empty list if no times are
	 *         recorded.
	 */
	@Override
	public List<Long> getLapTimes() {
		return Collections.unmodifiableList(lapTimes);
	}

	/**
	 * Tests for equality between the specified object and this stopwatch. Two
	 * stopwatches are considered equal if the id of their stopwatch's are equal.
	 * 
	 * @param obj
	 *          the object to test for equality with this stopwatch
	 * @return true if the stopwatch are considered equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Stopwatch)) {
			return false;
		}
		synchronized (this) {
			Stopwatch other = (Stopwatch) obj;
			return id.equals(other.id);
		}
	}

	/**
	 * The hash code is generated using the id.
	 * 
	 * @return a hash code value for this stopwatch
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		synchronized (lock) {
			result = result * prime + id.hashCode();
			return result;
		}
	}

	/**
	 * return String format of this stopwatch. If the stopwatch does not have
	 * laps, return The stopwatch ID has not recorded laps time. If it has, return
	 * lap times and time.
	 * 
	 */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("The stopwatch ID: " + id);
		synchronized (this) {
			if (lapTimes.size() == 0) {
				result.append(" has not recorded laps time.");
			} else {
				result.append(" has " + lapTimes.size() + " laps time: \n");
				for (int i = 0; i < lapTimes.size(); i++) {
					result.append("Lap " + (i + 1) + ":  " + lapTimes.get(i).toString()
							+ "\n");
				}
			}
		}
		return result.toString();
	}
}
