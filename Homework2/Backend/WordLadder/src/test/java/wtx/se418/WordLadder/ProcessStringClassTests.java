package wtx.se418.WordLadder;

import wtx.se418.WordLadder.processString;
import java.util.*;
import java.io.*;
import org.junit.*;

public class ProcessStringClassTests {
    private final processString ps = new processString();

    @Test
    public void testChangeStr(){
        String easy = ps.changeStr("fasy",0,'e');
        Assert.assertEquals("fasy -> easy",easy,"easy");
        Assert.assertEquals("Empty string should be returned when OUT-OF-RANGE happens.",ps.changeStr("fasy",4,'e'),"");
        Assert.assertEquals("Empty string should be returned when OUT-OF-RANGE happens.",ps.changeStr("fasy",-1,'e'),"");
    }
}