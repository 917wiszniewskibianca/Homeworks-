import java.sql.SQLOutput;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        try {
            // these can be inserted by different threads ; it is a synchronised container so its safe
            // so we have to wait 2 seconds for the first message , 10 seconds for the second message and 4,5 seconds for third mesage
            queue.put(new DelayedWorker("This is the first message.. ",2000));
            queue.put(new DelayedWorker("This is the second message.. ",10000));
            queue.put(new DelayedWorker("This is the third message.. ",4500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while(!queue.isEmpty()){
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class DelayedWorker implements Delayed{
    private long duration;
    private String message;

    public DelayedWorker (String message , long duration){
        this.message = message;
        this.duration = System.currentTimeMillis() + duration;
    }
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /// this is the method that can compare objects
    // -1 , +1 or 0 asta poate sa dea return
    @Override
    public int compareTo(Delayed other) {
        if (duration < ((DelayedWorker ) other ).getDuration())
            return -1 ;
        if (duration < ((DelayedWorker ) other ).getDuration())
            return +1 ;

        return 0;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayedWorker{" +
                " message='" + message + '\'' +
                '}';
    }
}