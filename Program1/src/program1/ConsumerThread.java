package program1;

import java.time.LocalDateTime;

/**
 * Consumes tasks and allows them to execute.
 * Requests nodes from the process queue, simulates execution for each process,
 * reports stats on the process and its task, etc.
 */
public class ConsumerThread implements Runnable {

	/**
	 * The id of the previous consumer thread.
	 */
	private static int lastId = 0;
	/**
	 * The time in milliseconds to wait while idling.
	 */
	private final long IDLE_WAIT_IN_MILLISECONDS = 33;
	/**
	 * The queue of processes to run.
	 */
	private ProcessQueue processQueue;
	/**
	 * The id of the consumer thread.
	 */
	private int id;
	/**
	 * True if the thread is to continue consuming.
	 */
	private boolean isRunning;

	/**
	 * A variable to improve time reporting information about the thread.
	 */
	private String tabsPrepend;

	/**
	 * Communicates flags between threads.
	 */
	private FlagCommunicator flags;

	/**
	 * The total number of nodes consumed by this consumer.
	 */
	private int totalConsumed;

	/**
	 * Creates a Consumer Thread with the given shared queue.
	 *
	 * @param queue The queue to share with all other threads.
	 * @param fc    The flags that are to be communicated between threads.
	 */
	public ConsumerThread ( ProcessQueue queue, FlagCommunicator fc ) {
		this.processQueue = queue;
		this.id = ++ lastId;
		this.flags = fc;
		this.totalConsumed = 0;
		this.isRunning = false;

		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < this.id; i++ ) {
			sb.append( '\t' );
		}

		this.tabsPrepend = sb.toString();


	}

	/**
	 * @return the id of the consumer thread.
	 */
	public int getId () {
		return id;
	}

	/**
	 * @return the total number of nodes consumed by this consumer.
	 */
	public int getTotalConsumed () {
		return totalConsumed;
	}

	/**
	 * Requests a node from {@link ProcessQueue} if there is one. If the queue is empty, waits.
	 *
	 * @return the requested node.
	 */
	public Node requestNode () {
		report( "is requesting a new node." );

		while ( this.processQueue.isEmpty() ) {

			report( "cannot find new node." );

			if ( flags.isProducerIsDone() ) {
				report( "thinks there won't be any more nodes to request." );
				this.isRunning = false;
				return null;
			} else {
				idle();
			}
		}

		try {
			return this.processQueue.removeHead();
		} catch ( InterruptedException e ) {
			report( "was interrupted." );
			return null;
		}
	}

	/**
	 * @return the flags shared between threads.
	 */
	public FlagCommunicator getFlags () {
		return flags;
	}

	/**
	 * Reports the given message to the screen.
	 *
	 * @param message The message to send out to.
	 */
	private void report ( String message ) {
		System.out.println( String.format( "%sConsumer %d %s", this.tabsPrepend, this.getId(), message ) );
	}

	/**
	 * Waits for the given time in ms.
	 */
	private void idle () {
		try {
			report( "is idling..." );
			Thread.sleep( IDLE_WAIT_IN_MILLISECONDS );
		} catch ( InterruptedException e ) {
			report( "was interrupted." );
		}
	}

	/**
	 * Consumes the processes in the queue while there are some to get.
	 */
	@Override
	public void run () {
		this.isRunning = true;
		while ( this.isRunning ) {
			try {
				Node nodeToProcess = this.requestNode();

				if ( nodeToProcess == null ) {
					continue;
				}

				nodeToProcess.run();

				LocalDateTime finishedProcessingTime = Utility.getCurrentTime();

				String nodeStatistics = nodeToProcess.toString();

				report( String.format( "finished %s at %s", nodeStatistics, Utility.formatDateTime( finishedProcessingTime ) ) );

				this.totalConsumed++;

			} catch ( InterruptedException ex ) {
				report( "was interrupted." );
			}
		}

		report( "is done." );
		report( String.format( "consumed %d nodes.", this.totalConsumed ) );

	}
}