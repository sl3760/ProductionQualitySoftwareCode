package edu.nyu.pqs.stopwatch.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 * 
 */
public class StopwatchFactory {

	private static final CopyOnWriteArrayList<IStopwatch> stopwatchList = new CopyOnWriteArrayList<IStopwatch>();
	private static final CopyOnWriteArraySet<String> idSet = new CopyOnWriteArraySet<String>();
	private static final Object lock = new Object();

	/**
	 * Creates and returns a new IStopwatch object
	 * 
	 * @param id
	 *          The identifier of the new object
	 * @return The new IStopwatch object
	 * @throws IllegalArgumentException
	 *           if <code>id</code> is empty, null, or already taken.
	 */
	public static IStopwatch getStopwatch(String id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null!");
		}
		if (id.isEmpty()) {
			throw new IllegalArgumentException("id cannot be empty!");
		}
		synchronized (lock) {
			if (idSet.contains(id)) {
				throw new IllegalArgumentException("id has been already taken!");
			} else {
				idSet.add(id);
				Stopwatch stopwatch = new Stopwatch(id);
				stopwatchList.add(stopwatch);
				return stopwatch;
			}
		}
	}

	/**
	 * Returns a list of all created stopwatches
	 * 
	 * @return a List of al creates IStopwatch objects. Returns an empty list if
	 *         no IStopwatches have been created.
	 */
	public static List<IStopwatch> getStopwatches() {
		return stopwatchList.subList(0, stopwatchList.size());
	}
}
