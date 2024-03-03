import java.sql.SQLOutput;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class SimpleRecursiveAction extends RecursiveAction{
    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork){
        this.simulatedWork =simulatedWork;
    }


    @Override
    protected void compute() {
        // if the task is too large we split it and execute the tasks in paralell
        //otherwise we execute it in a sequential manner

        if(simulatedWork >100){
            System.out.println("Parallel execution and split the tasks...");

            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork/2);

            /*
            action1.fork(); // action1 will be executed using the threads that are present in the forkjoinpool
            action2.fork() ;

            action1.join();
            action2.join();

            this is the same as invokeAll(action1, action2);
            */


            invokeAll(action1,action2);  // waits for both of the actions to be executed
        }
        else{
            System.out.println("The task is rather small so sequential execution is fine.. ");
            System.out.println("The size of the task: " + this.simulatedWork);
        }
    }
}
public class Main {
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(); // the level of parallisation is euql to the nr of threads we create

        SimpleRecursiveAction action = new SimpleRecursiveAction(200);
        action.invoke(); // will perform thr given task and waits for completion if necessary
    }
}