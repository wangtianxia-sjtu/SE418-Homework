import consumer.*;
import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final Integer MAXLENGTH = 20;
    public static void main(String[] args) throws Exception {
        Bfs bfs = new Bfs("consumer/dictionary.txt");
        Consumer.setBfs(bfs);
        Container container = new Container(MAXLENGTH);
        Producer producer0 = new Producer(container, "P0");
        Producer producer1 = new Producer(container, "P1");
        Producer producer2 = new Producer(container, "P2");
        Consumer consumer0 = new Consumer(container, "C0");
        Consumer consumer1 = new Consumer(container, "C1");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(consumer0);
        cachedThreadPool.execute(consumer1);
        cachedThreadPool.execute(producer0);
        cachedThreadPool.execute(producer1);
        cachedThreadPool.execute(producer2);
        Thread.sleep(10000);
        return;
    }
}

