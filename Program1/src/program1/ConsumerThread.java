package program1;

import java.time.LocalDateTime;

// Consumer thread requests nodes from the process queue
// Simulate execution by sleeping for node slice duration
// Report node's process ID, priority value, time when completed
// Print report to screen
public class ConsumerThread implements Runnable {

	private static int lastId = 0;
	private final long IDLE_WAIT_IN_MILLISECONDS = 33;
	private ProcessQueue processQueue;
	private int id;
	private boolean isRunning;

	private String tabsPrepend;

	public ConsumerThread ( ProcessQueue queue ) {
		this.processQueue = queue;
		this.id = ++ lastId;
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
	 * Requests a node from {@link ProcessQueue} if there is one. If the queue is empty, waits.
	 *
	 * @return the requested node.
	 */
	public Node requestNode () {
		report( "is requesting a new node." );
		while ( this.processQueue.isEmpty() ) {
			report( "cannot find new node." );
			idle();
		}

		try {
			return this.processQueue.removeHead();
		} catch ( InterruptedException e ) {
			report( "was interrupted." );
			return null;
		}
	}

	private void report ( String message ) {
		System.out.println( String.format( "%sConsumer %d %s", this.tabsPrepend, this.getId(), message ) );
	}

	private void idle () {
		try {
			System.out.println( String.format( "Consumer %d is idle...", this.getId() ) );
			Thread.sleep( IDLE_WAIT_IN_MILLISECONDS );
		} catch ( InterruptedException e ) {
			report( "was interrupted." );
		}
	}

	@Override
	public void run () {
		this.isRunning = true;
		while ( this.isRunning ) {
			try {
				Node nodeToProcess = this.requestNode();

				nodeToProcess.run();

				LocalDateTime finishedProcessingTime = Utility.getCurrentTime();

				String nodeStatistics = nodeToProcess.toString();

				report( String.format( "finished %s at %s", nodeStatistics, Utility.formatDateTime( finishedProcessingTime ) ) );

			} catch ( InterruptedException ex ) {
				report( "was interrupted." );
			}
		}

	}
}