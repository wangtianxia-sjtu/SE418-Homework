package wtx.se418.WordLadder;

import java.util.*;
import java.io.*;


public class bfs {
    private static dict dictionary;

    public bfs() throws IOException
    {
        dictionary = new dict();
    }
    
    private static processString ps = new processString();
    
    private Boolean checkExistence(String start,String end)
    {
        return (dictionary.contains(start) && dictionary.contains(end));
    }
    
    private Boolean checkNeighbour(String former,String itsNeighbour,Set<String> visited)
    {
        return ( (!itsNeighbour.equals(former)) && dictionary.contains(former) && !visited.contains(itsNeighbour) );
    }
    
    private String findNeighbour(String former,Set<String> visited)
    {
        for (int i=0;i<former.length();++i)
        {
            for (char c = 'a'; c<='z' ; ++c)
            {
                String itsNeighbour = ps.changeStr(former,i,c);
                if(checkNeighbour(former,itsNeighbour,visited))
                {
                    visited.add(itsNeighbour);
                    return itsNeighbour;
                }
            }
        }
        return "";
    }
    
    
    public ArrayList<String> findPath(String start,String end)
    {
        Set<String> visited = new HashSet<String>();
        Stack<String> ladder = new Stack<String>();
        Queue<Stack<String>> search = new LinkedList<Stack<String>>();
        if(!checkExistence(start,end))
        {
            return new ArrayList<String>();
        }
        visited.add(start);
        ladder.push(start);
        search.offer(ladder);
        while(!search.isEmpty())
        {
            Stack<String> partialLadder = search.poll();
            while (true)
            {
                String neighbour = findNeighbour(partialLadder.peek(),visited);
                if(neighbour.equals(""))
                {
                    break;
                }
                if(neighbour.equals(end))
                {
                    partialLadder.push(neighbour);
                    ArrayList<String> result = new ArrayList<String>(partialLadder);
                    return result;
                }
                else
                {
                    Stack<String> partialLadderCopy = (Stack<String>) partialLadder.clone();
                    partialLadderCopy.push(neighbour);
                    search.offer(partialLadderCopy);
                }
            }
        }
        return new ArrayList<String>();
    }
}