import java.util.Random;

/**
 * Lab 9: Thread Barrier
 *
 * Main class
 *
 */
public class Main {

    private static int threadCount = 20 ;
    private static final int barrierSize = 10 ;
    private static final int sleepTime = 500 ;

    /**
    * Main program
    *    Create a barrier
    *    Create multiple instances of Process and run them in threads.
    *	 threadCount should be provided as command line argument when you run Main
    * @param args
    */
    public static void main(String[] args){
        try
        { //execption handling for command line arguements
          threadCount = Integer.parseInt(args[0]);
        }
        catch(Exception e){
          System.out.println("error");
          System.exit(0);
        }
        // Create a random source for randomly setting the sleep time of the
        //  process instances
        Random r = new Random() ;

        // Print out the number of threads
        System.out.println("Number of threads = " + threadCount);

        // Create the barrier
        Barrier barrier = new Barrier(barrierSize); //create Barrier object here of size barrierSize
        for(int i=1;i <=threadCount;i++){ //i start at 1 cause its threads.
          new Thread(new Process(barrier,i,r.nextInt(sleepTime))).start(); //create a unbound Thread which creates a unbound Process, Process takes the Barrier,thred number and length of time to sleep as args. .start() is java syntax to get Thread working
        }
        // Create and start the process threads
        // There are threadCount threads

        // Add code here.
    }
}
