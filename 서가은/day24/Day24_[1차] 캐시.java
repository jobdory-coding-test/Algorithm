import java.util.*;
import java.io.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> index = new ArrayList<>(cacheSize);
        int cSize = cities.length;
        int indexSize = 0;
        if(cacheSize==0){
            return cSize*5;
        }
        
        for(String city:cities){
            city = city.toUpperCase();    
            
            if(index.contains(city)==true){
                //cache hit -> 1
                answer+=1;    
                index.remove(city);
                index.add(city);
            }
            else if(indexSize<cacheSize){
                //cache miss -> 5
                answer+=5;
                index.add(city);
                indexSize++;
                
            }
            else if(indexSize==cacheSize){
                //cache miss -> 5
                answer+=5;
                index.remove(0);
                index.add(city);
            }
            
        }
        return answer;
    }
}