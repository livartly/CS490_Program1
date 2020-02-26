package program1;

import java.util.ArrayList;

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
	//private PriorityBlockingQueue< Node > queue;

	/**
	 * Object to be used as a mutex.
	 */
	private final Object lock;

	private ArrayList< Node > nodes;

	/**
	 * Creates the ProcessQueue with the initial capacity.
	 *
	 * @param initialCapacity The space to allocate the queue with.
	 */
	public ProcessQueue ( int initialCapacity ) {
		//this.queue = new PriorityBlockingQueue<>( initialCapacity );
		this.nodes = new ArrayList<>( initialCapacity );
		this.lock = new Object();
	}


	private void heapify ( int n, int indexOfNode ) {
		// https://www.geeksforgeeks.org/heap-sort/ helps a lot.
		int indexOfSmallestNode = indexOfNode;
		int indexOfLeftChild = 2 * indexOfNode + 1;
		int indexOfRightChild = 2 * indexOfNode + 2;

		// If left child is smaller than root
		if ( indexOfLeftChild < n &&
				     this.nodes.get( indexOfLeftChild ).compareTo( this.nodes.get( indexOfSmallestNode ) ) > 0 ) {
			indexOfSmallestNode = indexOfLeftChild;
		}

		// If right child is smaller than the smallest so far,
		if ( indexOfRightChild < n &&
				     this.nodes.get( indexOfRightChild ).compareTo( this.nodes.get( indexOfSmallestNode ) ) > 0 ) {
			indexOfSmallestNode = indexOfRightChild;
		}

		// If the index of the smallest node is not the root index,
		if ( indexOfSmallestNode != indexOfNode ) {
			// Swap them.
			Node swap = this.nodes.get( indexOfNode );
			this.nodes.set( indexOfNode, this.nodes.get( indexOfSmallestNode ) );
			this.nodes.set( indexOfSmallestNode, swap );

			// Recursively heapify the affected sub-tree
			heapify( n, indexOfSmallestNode );
		}
	}

	/**
	 * Adds the new process to the queue.
	 *
	 * @param newProcess The process to add.
	 * @return True if was successfully added, false otherwise.
	 */
	public boolean add ( Node newProcess ) {
		boolean output = false;
		synchronized ( this.lock ) {
			output = this.nodes.add( newProcess );
			// Heapify at the root.
			this.sort();
		}
		return output;
	}

	/**
	 * Removes all elements from the queue.
	 */
	public void clear () {
		synchronized ( this.lock ) {
			this.nodes.clear();
		}
	}

	public void sort () {
		synchronized ( this.lock ) {
			// Build heap (rearrange array)
			for ( int i = this.nodes.size() / 2 - 1; i >= 0; i-- )
				heapify( this.nodes.size(), i );

			// One by one extract an element from heap
			for ( int i = this.nodes.size() - 1; i >= 0; i-- ) {
				// Move current root to end
				Node temp = this.nodes.get( 0 );
				this.nodes.set( 0, this.nodes.get( i ) );
				this.nodes.set( i, temp );

				// call max heapify on the reduced heap
				heapify( i, 0 );
			}
		}
	}

	/**
	 * Removes the node at the head of the queue and returns it.
	 *
	 * @return The former head of the queue.
	 * @throws InterruptedException
	 */
	public Node removeHead () throws InterruptedException {
		synchronized ( this.lock ) {
			Node head = this.nodes.remove( 0 );
			this.sort();
			return head;
		}
	}

	/**
	 * Returns the size of the queue.
	 *
	 * @return the number of processes within the queue.
	 */
	public int size () {
		return this.nodes.size();
	}

	/**
	 * @return True if the container has nothing in it, false otherwise.
	 */
	public boolean isEmpty () {
		return this.size() == 0;
	}
}
