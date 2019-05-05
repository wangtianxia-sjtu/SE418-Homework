package wtx.se418.WordLadder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.io.*;

@RestController
@CrossOrigin
public class WordLadderController {

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
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://word-ladder:8964/search?start="+start+"&end="+end;
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<ArrayList<String>> httpEntity = new HttpEntity<>(httpHeaders);
        ArrayList<String> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class).getBody();
        return result;
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