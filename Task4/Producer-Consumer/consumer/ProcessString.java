package consumer;

import java.util.*;
import java.io.*;

public class ProcessString {
    public String changeStr(String formerStr,int pos, char newStr)
    {
        if (pos<0 || pos >= formerStr.length())
            return "";
        return formerStr.substring(0,pos) + newStr +formerStr.substring(pos+1);
    }
}