import java.util.ArrayList;

import consumer.Bfs;
import javafx.util.Pair;

public class Consumer implements Runnable {
    
    private Container container;

    private String name;

    private static Bfs bfs;

    public static void setBfs (Bfs bfs_) {
        bfs = bfs_;
    }

    public Consumer (Container container, String name) {
        this.container = container;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("This is Consumer Thread " + name +".");
        while(true) {
            System.out.println("Consumer Thread " + name + " is waiting for request...");
            Pair<String, String> pair = null;
            while (pair == null) {
                pair = container.getPair();
            }
            System.out.println("Consumer Thread " + name + " has received request " + pair + ".");
            ArrayList<String> ans = bfs.findPath(pair.getKey(), pair.getValue());
            System.out.println("Consumer Thread " + name + " has finished the request " + pair + ". " + "The answer is " + ans + ".");
        }
    }
}