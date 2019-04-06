package wtx.se418.WordLadder;

import java.util.*;
import java.io.*;
import org.springframework.core.io.ClassPathResource;

public class dict {
    private static Set<String> dictionary = new TreeSet<String>();

    private void readFileByLine(InputStream input){
        try {
            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String strLine = null;
            while(null != (strLine = bufferedReader.readLine())){
                dictionary.add(strLine);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public dict()  throws IOException
    {
        ClassPathResource source = new ClassPathResource("static/dictionary.txt");
        InputStream input = source.getInputStream();
        readFileByLine(input);
        System.out.println("Word Count: " + dictionary.size());
    }
    
    public dict(InputStream input){
        readFileByLine(input);
        System.out.println("Word Count: " + dictionary.size());
    }
    
    public Boolean contains(String word){
        return dictionary.contains(word);
    }
}
