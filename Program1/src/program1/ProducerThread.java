package program1;

// Producer Thread creates and adds processes to the heap
// Thread wakes up at random periodic intervals and adds new processes to heap
// Thread adds a total of 75 processes throughout its lifetime
public class ProducerThread implements Runnable {
    /**
     * The maximum number of nodes to produce.
     */
    private final int MAX_NUM_OF_NODES_TO_PRODUCE = 75;
    /**
     * The time to wait in idle.
     */
    private final int IDLE_TIME_IN_MILLISECONDS = 66;
    /**
     * The counter for the amount of nodes created.
     */
    private int nodeCount;
    /**
     * The counter for the amount of times producer thread has awoken.
     */
    private int wakeUpCount;
    /**
     * The queue for the processes to produce to.
     */
    private ProcessQueue processQueue;

    /**
     * Creates a new producer thread with the given shared resource.
     *
     * @param processQueue The queue that holds all processes to add to.
     */
    public ProducerThread ( ProcessQueue processQueue ) {
        this.processQueue = processQueue;

        this.wakeUpCount = 0;
        this.nodeCount = 0;
    }

    /**
     * Gets the amount of nodes created.
     *
     * @return the node count.
     */
    public int getNodeCount () {
        return nodeCount;
    }

    /**
     * Gets the amount of times producer has awoken.
     *
     * @return the wake up count.
     */
    public int wakeUpCount () {
        return wakeUpCount;
    }

    /**
     * Creates new instance of node.
     * Generates random numbers to set priority, process id, and time slice
     *
     * @return the created node.
     */
    public Node createNode () {
        Node node = new Node( Utility.getRandomNumber( 1, 100 ), Utility.getRandomNumber( 10, 30 ) );
        nodeCount++;
        return node;
    }

    /**
     * @return true if the number of nodes generated is >= to the max number of nodes the producer can make. False otherwise.
     */
    public boolean isFinished () {
        return this.nodeCount >= this.MAX_NUM_OF_NODES_TO_PRODUCE;
    }

    /**
     * Gets the remaining nodes to produce, based off of the max number of nodes and the current count of nodes generated.
     *
     * @return
     */
    private int getRemainingNodesToProduce () {
        return this.MAX_NUM_OF_NODES_TO_PRODUCE - this.nodeCount;
    }

    /**
     * Gets a random number of nodes to produce.
     * <p>
     * Handles clamping, thus it's in its own function.
     *
     * @return a clamped random number of nodes to produce.
     */
    private int getRandomNumOfNodesToProduce () {
        int possibility = Utility.getRandomNumber( 15, this.MAX_NUM_OF_NODES_TO_PRODUCE / 4 );
        int remainingCount = getRemainingNodesToProduce();
        if ( possibility > remainingCount ) {
            possibility = remainingCount;
        }
        return possibility;
    }

    /**
     * Waits for the given time in ms.
     */
    private void idle () {
        try {
            Thread.sleep( IDLE_TIME_IN_MILLISECONDS );
        } catch ( InterruptedException e ) {
            System.out.println( "Producer was interrupted!" );
        }
    }

    /**
     * Adds nodes to the {@link #processQueue} as it runs.
     */
    @Override
    public void run () {

        while ( ! isFinished() ) {
            int nodesToProduce = getRandomNumOfNodesToProduce();
            for ( int i = 0; i < nodesToProduce; i++ ) {
                Node producedNode = createNode();
                this.processQueue.add( producedNode );
            }
            System.out.println( String.format( "Producer has produced ~%d nodes.", nodesToProduce ) );
            System.out.println( "Producer is idling..." );
            idle();
        }

        System.out.println( "Producer has completed its tasks." );
        // TODO: Notify everyone when done.
    }
}