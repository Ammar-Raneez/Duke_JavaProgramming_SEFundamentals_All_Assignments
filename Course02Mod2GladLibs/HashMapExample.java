package CountWordsArrayList;


/**
 * Write a description of HashMapExample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class HashMapExample {
    public void countWords() {
        FileResource fr = new FileResource();
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String w: fr.words()) {
            w = w.toLowerCase();
            if(!map.keySet().contains(w)) {
                map.put(w, 1);
            } else {
                map.put(w, map.get(w)+1);
            }
        }
        
        for(String word: map.keySet()) {
            int occurences = map.get(word);
            if(occurences>500) System.out.println(word + " " + occurences);
        }
    }
}
