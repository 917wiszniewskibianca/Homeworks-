import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
        FirstWorker first = new FirstWorker(queue);
        SecondWorker second = new SecondWorker(queue);

        new Thread(first).start();
        new Thread(second).start();

    }
}

class FirstWorker implements Runnable{
    private BlockingQueue<Person> queue;

    public FirstWorker(BlockingQueue<Person> queue){
        this.queue=queue;
    }


    @Override
    public void run() {
        try{
        queue.put(new Person(12,"Adam"));
        queue.put(new Person(22,"Irina"));
        queue.put(new Person(23,"Ionut"));
        Thread.sleep(2000);
        queue.put(new Person(40,"Andreea"));
        Thread.sleep(1000);
        queue.put(new Person(34,"Vero"));
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}

class SecondWorker implements Runnable{
    private BlockingQueue<Person> queue;

    public SecondWorker(BlockingQueue<Person> queue){
        this.queue=queue;
    }


    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            System.out.println(queue.take());  // the item it will return will be the one with the highest priority
            Thread.sleep(1000);
            System.out.println(queue.take());
            Thread.sleep(5000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());


        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}

// if we want to define out own object to compare over
class Person implements Comparable<Person> {

    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name= name ;
    }

    @Override
    public int compareTo(Person person) {
        return name.compareTo(person.getName());

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

