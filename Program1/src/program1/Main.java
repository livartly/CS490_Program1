// Trystan May & Liv Weaver
// CS490 Program1
// 3/9/2020
// This program simulates the creation and execution of prioritized processes
package program1;

public class Main {

	public static void main ( String[] args ) {
		// Create new process queue
		ProcessQueue queue = new ProcessQueue( 75 );

		// Create two consumer threads
		ConsumerThread consume1 = new ConsumerThread( queue );
		ConsumerThread consume2 = new ConsumerThread( queue );

		// Create producer thread
		ProducerThread producer = new ProducerThread( queue );

		new Thread( consume1 ).start();
		new Thread( consume2 ).start();

		new Thread( producer ).start();
	}

}
