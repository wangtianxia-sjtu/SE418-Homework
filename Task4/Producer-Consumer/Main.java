import consumer.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Bfs bfs = new Bfs("consumer/dictionary.txt");
        List<String> test = bfs.findPath("code", "data");
        System.out.println(test.get(0));
        System.out.println("Hello World!");
    }
}

