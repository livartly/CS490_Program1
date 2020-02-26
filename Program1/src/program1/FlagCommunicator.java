package program1;

public class FlagCommunicator {
	/**
	 * True if producer is done, False otherwise.
	 */
	private boolean producerIsDone;

	/**
	 * Constructor for Flag Communicator.
	 */
	public FlagCommunicator () {
		this.producerIsDone = false;
	}

	/**
	 * @return True if produce is done. False otherwise.
	 */
	public boolean isProducerIsDone () {
		return producerIsDone;
	}

	/**
	 * Updates the flag for whether the producer is done.
	 * Only to be used by ProducerThread.
	 *
	 * @param producerIsDone Meant to be true if producer is finished producing, false otherwise.
	 */
	public synchronized void setProducerIsDone ( boolean producerIsDone ) {
		this.producerIsDone = producerIsDone;
	}
}
