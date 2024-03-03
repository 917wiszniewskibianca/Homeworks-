

class Calculate extends Thread {
    private int[] array;
    private int low;
    private int high;
    private int partialSum =0;

    public Calculate (int[] array, int low, int high ){
        this.array =array;
        this.low = low;
        this.high =Math.min(array.length,high);
    }

    @Override
    public void run() {
        partialSum= 0;
        for(int i=low;i<high;++i)
            partialSum += array[i];
    }

    public int getPartialSum(){
        return this.partialSum;
    }
}

class ParallelSum{
    private Calculate[] calculates;
    private int numOfThreads;

    public ParallelSum(int numOfThreads){
        this.numOfThreads = numOfThreads;
        this.calculates = new Calculate[numOfThreads];
    }

    public int sum(int[] nums){
        int size =(int)Math.ceil(nums.length *1.0 / numOfThreads);  // number of items we have to sum up with one thread
        for(int i=0; i<numOfThreads; ++i){
              calculates[i] = new Calculate(nums, i*size,(i+1)* size);
              calculates[i].start();
        }
        try{
            for( Calculate calculate : this.calculates)
                calculate.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        int total=0;
        for( Calculate calculate : this.calculates)
            total += calculate.getPartialSum();
        return total;
    }
}
public class Main {
    public static void main(String[] args) {

        int[] nums = {1,2,3,4,5};

        int numOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("The number of processors available: " + numOfThreads);

        ParallelSum parallelSum = new ParallelSum(numOfThreads);
        System.out.println("The sum of the numbers is: " + parallelSum.sum(nums));
    }
}