import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

import javafx.util.Pair;

public class Producer implements Runnable {
    
    private Container container;

    private String name;

    private static Integer loop = 20;

    private static Integer intervalMilliSec = 2000;

    private static final String[] startWordPool = {"code", "elite", "logic"};

    private static final String[] endWordPool = {"data", "dwarf", "pause"};

    public Producer(Container container, String name) {
        this.container = container;
        this.name = name;
    }

    @Override
    public void run() {
        Integer poolSize = startWordPool.length;
        System.out.println("This is a Producer Thread. Thread ID: " + Thread.currentThread().getId() + ". Name: " + this.name);
        try {
            for (Integer i = 0; i < loop; ++i)
            {
                Random random = new Random();
                Integer ran = random.nextInt(poolSize);
                Pair<String, String> pair = new Pair<String, String>(startWordPool[ran], endWordPool[ran]);
                Boolean res = container.insertPair(pair);
                if (!res) {
                    System.out.println("Producer Thread " + this.name + " failed to insert the request pair " + pair + ".");
                }
                else {
                    System.out.println("Producer Thread " + this.name + " successfully inserted the request pair " + pair + ".");
                }
                Thread.sleep(intervalMilliSec);
            }
        } catch (Exception e) {
            System.out.println("Producer Thread " + this.name + " has met with exception: " + e.getMessage());
        }
    }

}