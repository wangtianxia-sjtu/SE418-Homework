import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import javafx.util.Pair;

public class Container {

    private LinkedBlockingDeque<Pair<String, String>> request;

    private static double overloadRate = 0.25;

    private static Integer timeoutMilliSec = 200;

    private Integer containerSize;

    public Container(Integer size) {
        containerSize = size;
        request = new LinkedBlockingDeque<Pair<String, String>>(size);
    }

    public Pair<String, String> getPair() {
        if (request.remainingCapacity() <= (Integer) containerSize * overloadRate) {
            try {
                return request.takeLast();
            } catch (InterruptedException e) {
                return null;
            }
        }
        else
        {
            try {
                return request.takeFirst();
            } catch(InterruptedException e) {
                return null;
            }
        }
    }

    public Boolean insertPair(Pair<String, String> pair) {
        if (request.remainingCapacity() == 0) {
            // no space available
            System.out.println("Running out of capacity...");
            try {
                request.pollFirst(timeoutMilliSec, TimeUnit.MILLISECONDS);
                Boolean res = request.offerLast(pair, timeoutMilliSec, TimeUnit.MILLISECONDS);
                return res;
            }
            catch (Exception e) {
                return false;
            }

        }
        else {
            try {
                Boolean res = request.offerLast(pair, timeoutMilliSec, TimeUnit.MILLISECONDS);
                return res;
            }
            catch (Exception e) {
                return false;
            }
        }
    }
}