import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class PrintIntegers extends RecursiveAction{

    private int[] array;

    public PrintIntegers (int[] array){
        this.array = array;

    }


    @Override
    protected void compute() {
        if(array.length >=2 ){

            int[] array1 = new int[(int)Math.ceil(array.length*1.0/2)];
            for(int i = 0;i<array1.length;i++)
                array1[i] = array[i];

            int[] array2 = new int[array.length/2];
            for(int i = 0;i<array2.length;i++)
                array2[i] = array[array1.length + i];

            PrintIntegers action1 = new PrintIntegers(array1);
            PrintIntegers action2 = new PrintIntegers(array2);

            invokeAll(action1,action2);

        }
        else {
            System.out.print(" ");
            System.out.print(array[0]);
            System.out.print(" ");
        }
    }
}



public class Main {
    public static void main(String[] args) {

        int[] array = {1,2,3,4,5,6,7,8,9};
        ForkJoinPool pool = new ForkJoinPool(); // the level of parallisation is euql to the nr of threads we create

        PrintIntegers action = new PrintIntegers(array);
        action.invoke(); // will perform thr given task and waits for completion if necessary
    }
}