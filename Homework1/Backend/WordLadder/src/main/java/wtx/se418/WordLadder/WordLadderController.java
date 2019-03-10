package wtx.se418.WordLadder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;
import java.io.*;

@RestController
@CrossOrigin("http://localhost:8080")
public class WordLadderController {
    private static bfs finder;
    
    public WordLadderController() throws IOException {
        finder = new bfs();
    }

    @RequestMapping("/search")
    public ArrayList<String> LadderSearcher(@RequestParam(value="start", defaultValue="code") String start, @RequestParam(value="end", defaultValue="data") String end)
    {
        return finder.findPath(start,end);
    }
}