package program1;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Contains all processes that are to be run within the system.
 * Is a shared resource so mutual exclusion must be practiced.
 */
public class ProcessQueue {
	/**
	 * Queue to handle processes.
	 * <p>
	 * ONLY TO BE USED FOR STARTING IMPLEMENTATION.
	 * MEANING, THIS MUST BE REPLACED EVENTUALLY.
	 */
	private PriorityBlockingQueue< Node > queue;

	/**
	 * Creates the ProcessQueue with the initial capacity.
	 *
	 * @param initialCapacity The space to allocate the queue with.
	 */
	public ProcessQueue ( int initialCapacity ) {
		this.queue = new PriorityBlockingQueue<>( initialCapacity );
	}

	/**
	 * Adds the new process to the queue.
	 *
	 * @param newProcess The process to add.
	 * @return True if was successfully added, false otherwise.
	 */
	public boolean add ( Node newProcess ) {
		return this.queue.add( newProcess );
	}

	/**
	 * Removes all elements from the queue.
	 */
	public void clear () {
		this.queue.clear();
	}

	/**
	 * Checks if the queue contains the given node.
	 *
	 * @param n The node to check for.
	 * @return True if the queue contains this node, false otherwise.
	 */
	public boolean contains ( Node n ) {
		return this.queue.contains( n );
	}

	/**
	 * NOT IMPLEMENTED YET.
	 * <p>
	 * Checks if there is a process within the queue with the given id.
	 *
	 * @param processId The id of the process to check for.
	 * @return True if there exists a process with the given id within the queue, false otherwise.
	 */
	public boolean contains ( int processId ) {
		return false;
	}

	/**
	 * Returns the node at the head of the queue, but does not remove it.
	 *
	 * @return The node at the head of the queue.
	 */
	public Node peek () {
		return this.queue.peek();
	}

	/**
	 * Removes the node at the head of the queue and returns it.
	 *
	 * @return The former head of the queue.
	 * @throws InterruptedException
	 */
	public Node removeHead () throws InterruptedException {
		return this.queue.take();
	}

	/**
	 * Returns the size of the queue.
	 *
	 * @return the number of processes within the queue.
	 */
	public int size () {
		return this.queue.size();
	}

	public boolean isEmpty () {
		return this.size() == 0;
	}
}
