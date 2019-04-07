package wtx.se418.WordLadder;

import wtx.se418.WordLadder.bfs;
import java.util.*;
import java.io.*;
import org.junit.*;

public class BfsClassTests {
    private bfs BfsSearcher;

    @Before
    public void before() throws Exception {
        BfsSearcher = new bfs();
    }

    @Test
    public void testFindPath0() throws Exception {
        ArrayList<String> answer = BfsSearcher.findPath("code","data");
        Assert.assertEquals("code -> data: The ladder is 5 in size.",answer.size(),5);
    }

    @Test
    public void testFindPath1() throws Exception {
        ArrayList<String> answer = BfsSearcher.findPath("strong","beauty");
        Assert.assertEquals("strong -> beauty: The ladder does not exist.",answer.size(),0);
    }
}