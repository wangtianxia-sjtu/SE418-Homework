package wtx.se418.WordLadder;

import wtx.se418.WordLadder.dict;
import java.util.*;
import java.io.*;
import org.junit.*;

public class DictClassTests {
    private dict dictionary;

    @Before
    public void before() throws Exception {
        dictionary = new dict();
    }

    @Test
    public void testContains() throws Exception {
        Boolean answer_false = dictionary.contains("wangtianxia");
        Boolean answer_true = dictionary.contains("name");
        Assert.assertEquals("wangtianxia should not be found!", answer_false, false);
        Assert.assertEquals("name should be found!", answer_true, true);
    }
}