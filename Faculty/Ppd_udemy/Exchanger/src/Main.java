import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Exchanger<Integer>  exchanger = new Exchanger<>();

        FirstThread t1 = new FirstThread(exchanger);
        SecondThread t2 = new SecondThread(exchanger);

        new Thread(t1).start();
        new Thread(t2).start();
    }
}

class FirstThread implements Runnable{
    private int counter;
    private Exchanger<Integer> exchanger;


    public FirstThread(Exchanger<Integer> exhanger) {
        this.exchanger = exhanger;
    }

    @Override
    public void run() {
         while(true) {
             counter++;
             System.out.println("First thread implemented the counter " + counter);
             try {
                 counter = exchanger.exchange(counter);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
             try {
                 Thread.sleep(2000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }
    }
}

class SecondThread implements Runnable{
    private int counter;
    private Exchanger<Integer> exchanger;


    public SecondThread(Exchanger<Integer> exhanger) {
        this.exchanger = exhanger;
    }

    @Override
    public void run() {
        while(true) {
            counter--;
            System.out.println("Second thread decremented the counter " + counter);
            try {
                counter = exchanger.exchange(counter);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

// o sa avem 1 -1 si dupa 1 -1
// primul thread ia counter ul care e 0 si il fce 1
// in aceelasi timp threadul 2 ia counterul care e 0 si il face -1
// dupa tot fac exchaneg de aceste numere