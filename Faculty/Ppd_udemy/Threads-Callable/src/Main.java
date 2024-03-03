import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Processor implements Callable<String> {
    private int id;

    public Processor (int id){
          this.id= id;
    }

    @Override
    public String call() throws Exception {
      Thread.sleep(2000);
      return "Id " + id;
    }
}


public class Main {
    public static void main(String[] args) {

        List<Future<String>> list = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; ++i) {
           Future<String> future = service.submit(new Processor(i+1));
           list.add(future);

        }
        for( Future<String> f : list){
            try {
                System.out.println(f.get());
            } catch (InterruptedException  | ExecutionException e ) {
                e.printStackTrace();

            }
        }

    }
}