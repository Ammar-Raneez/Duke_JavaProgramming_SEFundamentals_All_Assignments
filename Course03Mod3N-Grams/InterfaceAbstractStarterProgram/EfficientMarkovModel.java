
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

//hashmaps to increase efficiency
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int numChar;
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int numChar) {
        myRandom = new Random();
        this.numChar = numChar;
        this.map = new HashMap<>();
    }
    
    public void buildMap() {
        ArrayList<String> follows;
        for(int i=0; i<myText.length()-1; i++) {
            String nextChar = null;
            //same as b4, if we've reached the end of the text, break loop
            if((i+numChar) >= myText.length()) {
                nextChar = "";
            } else {
                //get current character, and infront
                String key = myText.substring(i, i+numChar);
                nextChar = Character.toString(myText.charAt(i+numChar));
                
                //if the key isnt in the map, create a new list for the nextChar
                //and add that into the map, else simply update the list
                if(!map.containsKey(key)) {
                    follows = new ArrayList<>();
                    follows.add(nextChar);
                    map.put(key, follows);
                } else {
                    follows = map.get(key);
                    follows.add(nextChar);
                    map.put(key, follows);
                }
            }
        }
    }
    
    //get follows is as simple as finding it upon a entered hashmap key - preventing unnecessary look ups
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        if(map.containsKey(key)) follows = map.get(key);
        return follows;
    }

    public void printHashMapInfo() {       
        int maxSize = 0; String keyValue = ""; int keyCount = 0;
        for(String key: map.keySet()) {
            keyCount++;
            if(map.get(key).size() > maxSize) {
                maxSize = map.get(key).size();
                keyValue = key;
            }
        }
        
        for(String key: map.keySet()) {
            if(map.get(key).size() == maxSize) {
                System.out.println(key + " has a size equal to the max size.");
            }
        }
        
        System.out.println("Number of keys - " + keyCount);
        System.out.println("Maximum size of largest value is " + keyValue + " of size " + maxSize);
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        if (myText == null){
            return "";
        }
        
        int index = myRandom.nextInt(myText.length() - numChar);
        String key = myText.substring(index, index + numChar);        
        sb.append(key);                                         
        
        for(int k=0; k < numChars - numChar; k++){
            ArrayList<String> follows = getFollows(key);       
            
            if(follows.size() == 0) {   
                break;                  
            }

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);         
            sb.append(next);
            
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "Efficient Markov Model of order " + numChar;
    }
}

