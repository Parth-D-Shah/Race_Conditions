
/**
 * Lab 9: Thread Barrier
 * Barrier Class
 *
 * Processes join the barrier and are held until barrierSize processes have
 * joined.
 *
 *
 */
public class Barrier {

    /**
     * Size of the barrier, which is the minimum number of processes to proceed.
     */
    private int barrierSize;
    private int count = 0; //count starts at 0 as Theads start at 1 and u need Barrier+1
    private int queueLength = 0;

    /**
     * Create a barrier of a given size
     *
     * @param size
     */
    public Barrier(int size) {
        barrierSize = size;
        System.out.println("Barrier size = " + barrierSize);
    }

    /**
     * Processes join at barrier and either wait or are allowed past.
     *
     * @param p The process joining
     */
    public synchronized void joinBarrier(Process p) throws InterruptedException { //synchronized so only 1 thread at a time can access this critical region(race conditons)
        int pos = count;
        System.out.println(p.getName() + " waiting on barrier");

        count++; //increment count
        queueLength++; //increment queueLength
        //System.out.println("The pos is: "+pos);
        ////System.out.println("The count before 1st while is: "+count);
        ////System.out.println("The queueLengtht before 1st while is: "+queueLength);
        while(count <= barrierSize)
        { //make them wait for barrierSize+1 process to arrive
          wait(); //Causes the current thread to wait until another thread invokes the notifyAll() method for this object
        }
        notifyAll(); //throw exception because of this /notifyAll wakes up the other Threads that can now compete to access
        while(count - queueLength != pos)
        { //ensures a FIFO
          //System.out.println(pos);
          wait();
        }
        //System.out.println("The count after 2nd while is: "+count);
        //System.out.println("The queueLengtht after 2nd while is: "+queueLength);
        queueLength--; //because process leaves queue is shorter
        notifyAll(); //notifys process left
        System.out.println(p.getName() + " passed the barrier");
    }
}
