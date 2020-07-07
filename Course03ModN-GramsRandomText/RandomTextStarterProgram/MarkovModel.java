
/**
 * A generalized version
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private int numChar;
    private String myText;
    private Random myRandom;

    //we take an int value that specifies the Markov order
    public MarkovModel(int numChar) {
        myRandom = new Random();
        this.numChar = numChar;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        if (myText == null){
            return "";
        }
        
        int index = myRandom.nextInt(myText.length() - numChar);    //pass in the parameter here instead
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
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> output = new ArrayList<>();
       
        int pos = 0;
        for(int i=0; i<myText.length(); i++) {
            int foundIndex = myText.indexOf(key, pos);
            if(foundIndex == -1 || foundIndex+key.length() == myText.length()) break;
            output.add(Character.toString(myText.charAt(foundIndex + key.length())));
            pos = foundIndex + key.length();        
        }
        return output;
    }
}
