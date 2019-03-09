package wtx.se418.WordLadder;

import java.util.*;
import java.io.*;
import org.springframework.core.io.ClassPathResource;

public class dict {
    private static Set<String> dictionary = new TreeSet<String>();
    
    private void readFileByLine(String strFile){
        try {
            File file = new File(strFile);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
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
        readFileByLine(source.getFile().getAbsolutePath());
        System.out.println("Word Count: " + dictionary.size());
    }
    
    public dict(String dictName){
        readFileByLine(dictName);
        System.out.println("Word Count: " + dictionary.size());
    }
    
    public Boolean contains(String word){
        return dictionary.contains(word);
    }
}
