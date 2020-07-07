
/**
 * Use a hashmap to increase efficiency
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        this.myRandom = new Random();
        this.myOrder = order;
        this.map = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    //split the string at the whitespaces so that each word has their own index in the array
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    public int indexOf(String[] words, WordGram target, int start) {
        ArrayList<WordGram> wordGram = new ArrayList<>();
        for(int i = 0; i<words.length - myOrder; i++) {
            wordGram.add(new WordGram(words, i, myOrder));
        }
        
        for(int i=start; i<words.length; i++) {
            if(i >= wordGram.size()) return -1;
            if(target.equals(wordGram.get(i))) return i;
        }
        return -1;
    }
        
    public void buildMap() {
        ArrayList<String> follows = new ArrayList<>();
        WordGram wg = new WordGram(myText, 0, myOrder);
        String nextWord = null;
        
        for(int i=0; i<myText.length; i++) {
            if(i+myOrder > myText.length) {
                nextWord = "";
                follows = new ArrayList<>();
                map.put(wg, follows);
            } else {
                wg = new WordGram(myText, i, myOrder);
                int index = indexOf(myText, wg, i);
                nextWord = myText[index + myOrder];
            }
            
            //same functionality here to get follows list
            if(!map.containsKey(wg)) {
                follows = new ArrayList<String>();
                follows.add(nextWord);
                map.put(wg, follows);
            } else {
                follows = map.get(wg);
                follows.add(nextWord);
                map.put(wg, follows);
            } 
        }
    }

    public ArrayList<String> getFollows(WordGram wg) {
        ArrayList<String> follows = new ArrayList<>();
        if(map.containsKey(wg)) {
            follows = map.get(wg);
        }
        return follows;
    }
    
    public void printHashMapInfo() {
        int maxSize = 0;
        
        for(WordGram key : map.keySet()) {
            int size = map.get(key).size();
            if(size > maxSize) {
                maxSize = size;
            }
        }
        System.out.println("Number of keys: " + map.size());
        System.out.println("Max num of following a key: " + maxSize);  
        
        for(WordGram key: map.keySet()) {
            if(map.get(key).size() == maxSize) {
                System.out.println("Key - " + key + " - has size equal to max size with values - " + map.get(key));
            }
        }
    }
    
    //method not used
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);            
        sb.append(" ");
        
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);  
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return "Efficient Markov Word of order " + myOrder;
    }
}
