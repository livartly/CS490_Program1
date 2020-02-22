package program1;

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
	 * Gets the process id for this node.
	 *
	 * @return the process id.
	 */
	public int getProcessId () {
		return processId;
	}

	/**
	 * Sets the process id for this node.
	 *
	 * @param processId The new process id.
	 */
	public void setProcessId ( int processId ) {
		this.processId = processId;
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
}
