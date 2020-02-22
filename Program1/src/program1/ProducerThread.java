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
     * The counter for the amount of nodes created.
     */
    private int nodeCount = 0;
    /**
     * The counter for the amount of times producer thread has awoken.
     */
    private int wakeUpCount = 0;
    /**
     * The queue for the processes to produce to.
     */
    private ProcessQueue processQueue;

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

    public boolean isFinished () {
        return this.nodeCount >= this.MAX_NUM_OF_NODES_TO_PRODUCE;
    }

    private int getRemainingNodesToProduce () {
        return this.MAX_NUM_OF_NODES_TO_PRODUCE - this.nodeCount;
    }

    private int getRandomNumOfNodesToProduce () {
        int possibility = Utility.getRandomNumber( 15, this.MAX_NUM_OF_NODES_TO_PRODUCE / 4 );
        int remainingCount = getRemainingNodesToProduce();
        if ( possibility > remainingCount ) {
            possibility = remainingCount;
        }
        return possibility;
    }

    @Override
    public void run () {

        while ( ! isFinished() ) {

        }

    }
}