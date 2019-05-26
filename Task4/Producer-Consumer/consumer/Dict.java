package consumer;

import java.util.*;
import java.io.*;

public class Dict {
    private static Set<String> dictionary = new TreeSet<String>();

    private void readFileByLine(String strFile){
        try {
            File file = new File(strFile);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String strLine = null;
            while(null != (strLine = bufferedReader.readLine())){
                dictionary.add(strLine);
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Dict(){
        readFileByLine("dictionary.txt");
        System.out.println("Word Count: " + dictionary.size());
    }

    public Dict(String dictName){
        readFileByLine(dictName);
        System.out.println("Word Count: " + dictionary.size());
    }

    public Boolean contains(String word){
        return dictionary.contains(word);
    }
}

