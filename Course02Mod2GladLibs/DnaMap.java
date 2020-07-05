package CountWordsArrayList;


/**
 * Write a description of DnaMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class DnaMap {
    private HashMap<String, Integer> map;
    
    public DnaMap() {
        map = new HashMap<>();
    }
    
    public void buildCodonMap(int start, String dna) {
        map.clear();
        int count = 0;
        
        for(int i=start; i<dna.length()-2; i+=3) {
            String codon = dna.substring(i, i+3);
            
            if(!map.keySet().contains(codon)) {
                map.put(codon, 1);
                count++;
            } else {
                map.put(codon, map.get(codon)+1);
            }
        }
        
        System.out.println("Reading frame starting with " + start + ", results in " + count + " unique codons");
    }
    
    public String getMostCommonCodon() {
        String codon = "";
        int maxCount = 0;
        for(String string: map.keySet()) {
            if(map.get(string)>maxCount) {
                maxCount = map.get(string);
                codon = string;
            }
        }
        return "Most common codon is " + codon + ", occurences " + maxCount;
    }
    
    public void printCodonCounts(int start, int end) {
        for(String string: map.keySet()) {
            if(map.get(string) >= start && map.get(string) <= end) {
                System.out.println("Codon " + string + " Count " + map.get(string));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String string = fr.asString().toUpperCase();
        string = string.trim();
        
        int start = 0;
        buildCodonMap(start, string);
        String common = getMostCommonCodon();
        System.out.println(common);
        printCodonCounts(1, 7);
    }
}
