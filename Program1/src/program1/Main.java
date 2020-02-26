// Trystan May & Liv Weaver
// CS490 Program1
// 3/9/2020
// This program simulates the creation and execution of prioritized processes
package program1;

/**
 * The main functionality for the program.
 */
public class Main {


	public static void main ( String[] args ) {
		// Create new process queue
		ProcessQueue queue = new ProcessQueue( 75 );

		FlagCommunicator flags = new FlagCommunicator();

		// Create two consumer threads
		ConsumerThread consume1 = new ConsumerThread( queue, flags );
		ConsumerThread consume2 = new ConsumerThread( queue, flags );

		// Create producer thread
		ProducerThread producer = new ProducerThread( queue, flags );

		Thread consumer1 = new Thread( consume1 );
		Thread consumer2 = new Thread( consume2 );

		consumer1.start();
		consumer2.start();

		Thread producerThread = new Thread( producer );

		producerThread.start();

		try {
			producerThread.join();
			consumer1.join();
			consumer2.join();
		} catch ( InterruptedException ex ) {
			System.out.println( "Threads were interrupted while joining!" );
		}

		System.out.println( String.format( "Producer produced %d nodes to process.", producer.getNodeCount() ) );
		System.out.println( String.format( "There are %d remaining nodes in the processes queue.", queue.size() ) );
	}

}
