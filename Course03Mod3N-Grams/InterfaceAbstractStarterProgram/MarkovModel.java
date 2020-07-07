
/**
 * Write a description of MarkovModel applicable for any order, as before
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int numChar;

    public MarkovModel(int numChar) {
        myRandom = new Random();
        this.numChar = numChar;
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
        return "MarkovModel of order" + numChar;
    }
}
