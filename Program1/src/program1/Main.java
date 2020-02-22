// Trystan May & Liv Weaver
// CS490 Program1
// 3/9/2020
// This program simulates the creation and execution of prioritized processes
package program1;

public class Main {

	public static void main ( String[] args ) {
		System.out.println( "Hello World!" );

		// Create new process queue
		ProcessQueue queue = new ProcessQueue( 75 );

		// Create two consumer threads
		ConsumerThread consume1 = new ConsumerThread( queue );
		ConsumerThread consume2 = new ConsumerThread( queue );

		// Create producer thread
		ProducerThread producer = new ProducerThread();
	}

}
