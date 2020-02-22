package program1;

// Producer Thread creates and adds processes to the heap
// Thread wakes up at random periodic intervals and adds new processes to heap
// Thread adds a total of 75 processes throughout its lifetime
public class ProducerThread {
    /**
     * The counter for the amount of nodes created.
     */
    private int nodeCount = 0;
    /**
     * The counter for the amount of times producer thread has awoken.
     */
    private int wakeUpCount = 0;

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
     */
    public int createNode () {
        Node node = new Node();
        node.setPriority(Utility.getRandomNumber(1,1000));
        node.setProcessId(Utility.getRandomNumber(1,1000));
        node.setTimeSlice(Utility.getRandomNumber(1,1000));
        nodeCount++;
        return 0;
    }
}