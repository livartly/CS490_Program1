package program1;

import java.time.LocalDateTime;

/**
 * Handles process identification and other items of a given process.
 */
public class Node {
	/**
	 * The id for the process.
	 */
	private int processId;

	/**
	 * The priority for this process.
	 */
	private int priority;

	/**
	 * How much time (milliseconds) is allotted for this process to be in the running state.
	 */
	private int timeSlice;

	/**
	 * The start time of when the node began execution.
	 */
	private LocalDateTime executionStart;

	/**
	 * The id for the node that was created immediately before this one.
	 */
	private static int lastId = 0;

	public Node ( int priority, int timeSlice ) {
		this.priority = priority;
		this.timeSlice = timeSlice;

		this.processId = ++ lastId;
	}

	/**
	 * Gets the process id for this node.
	 *
	 * @return the process id.
	 */
	public int getProcessId () {
		return processId;
	}

	/**
	 * Gets the priority for the node.
	 *
	 * @return the priority for the node.
	 */
	public int getPriority () {
		return priority;
	}

	/**
	 * Sets the priority for this node.
	 *
	 * @param priority The new priority.
	 */
	public void setPriority ( int priority ) {
		this.priority = priority;
	}

	/**
	 * Gets the time slice (in milliseconds) that this node can be in the running state for.
	 *
	 * @return the time (ms) that the node can execute.
	 */
	public int getTimeSlice () {
		return timeSlice;
	}

	/**
	 * Sets the time slice (in milliseconds) that this node can be in the running state for.
	 *
	 * @param timeSlice the new time slice (millseconds) for the node.
	 */
	public void setTimeSlice ( int timeSlice ) {
		this.timeSlice = timeSlice;
	}

	/**
	 * Gets the start time of the node's execution.
	 *
	 * @return The start time of the node's execution.
	 */
	public LocalDateTime getExecutionStart () {
		return executionStart;
	}

	/**
	 * Sets the node's start time to the given value.
	 *
	 * @param executionStart the new start time.
	 */
	public void setExecutionStart ( LocalDateTime executionStart ) {
		this.executionStart = executionStart;
	}

	/**
	 * @return a string containing information about the node.
	 */
	public String toString () {
		return String.format( "Process: ID %d with priority %d (started %s)",
				this.getProcessId(),
				this.getPriority(),
				Utility.formatDateTime( this.executionStart ) );
	}

	/**
	 * Runs the node's main operation for its given time slice.
	 *
	 * @throws InterruptedException
	 */
	public void run () throws InterruptedException {
		this.setExecutionStart( Utility.getCurrentTime() );
		Thread.sleep( this.timeSlice );
	}
}
