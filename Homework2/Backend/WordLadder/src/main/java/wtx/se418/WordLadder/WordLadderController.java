package wtx.se418.WordLadder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;
import java.io.*;

@RestController
@CrossOrigin
public class WordLadderController {
    private static bfs finder;
    
    public WordLadderController() throws IOException {
        finder = new bfs();
    }

    @RequestMapping("/success")
    public int SuccessLogin()
    {
        return 1;
    }

    @RequestMapping("/unauthorized")
    public ErrorMessage Unauthorized()
    {
        return new ErrorMessage("Permission Denied");
    }

    @RequestMapping("/failure")
    public int FailureLogin()
    {
        return 0;
    }

    @RequestMapping("/search")
    public ArrayList<String> LadderSearcher(@RequestParam(value="start", defaultValue="code") String start, @RequestParam(value="end", defaultValue="data") String end)
    {
        return finder.findPath(start,end);
    }
}

class ErrorMessage {

    private final String error;

    public ErrorMessage(String content) {
        this.error = content;
    }

    public String getError() {
        return error;
    }

}