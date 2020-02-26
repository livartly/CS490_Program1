// Trystan May & Liv Weaver
// CS490 Program1
// 3/9/2020
// This program simulates the creation and execution of prioritized processes
package program1;

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

		new Thread( consume1 ).start();
		new Thread( consume2 ).start();

		new Thread( producer ).start();
	}

}
