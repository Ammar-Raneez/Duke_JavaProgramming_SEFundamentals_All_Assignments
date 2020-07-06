/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        this.names = new ArrayList<String>();
        this.counts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int index = names.indexOf(person);
        if(index == -1) {
            names.add(person);
            counts.add(1);
        } else {
            int count = counts.get(index);
            counts.set(index, count+1);
        }
    }
    
    public void findAllCharacters() {
        this.names.clear();
        this.counts.clear();
        
        FileResource fr = new FileResource();
        
        for(String line: fr.lines()) {
            line = line.trim();
            int index = line.indexOf(".");
            //System.out.println(index);
            //System.out.println(line);
            if(index != -1) {
                String name = line.substring(0, index);
                update(name);
            }
        }
    }
    
    //characters who have a number of parts within a range
    public void characterWithNumParts(int num1, int num2) {
        for(int i=0; i<names.size(); i++) {
             if(counts.get(i) >= num1 && counts.get(i) <= num2) {
                 System.out.println(names.get(i) + " " + counts.get(i));
             }
        }
    }
    public void test() {
        findAllCharacters();
        characterWithNumParts(10, 15);
    }
    
    public void tester() {
         findAllCharacters();
         
         for(int i=0; i<names.size(); i++) {
             if(counts.get(i) <100) {
                 System.out.println(names.get(i) + " " + counts.get(i));                 
             }
         }
    }
}
