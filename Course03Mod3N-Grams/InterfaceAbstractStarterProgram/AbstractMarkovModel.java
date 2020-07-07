
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

//Abstract classes, have both only method signatures n definitions
public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    //these three methods are the same everywhere, so its fine to define them n inherit
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
 
    //the following method is different, therefore define only the signature and define it after inheriting
    abstract public String getRandomText(int numChars);

    //the following method is also duplicated
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        
        for(int i = 0; i < myText.length(); i++) {
            int index = myText.indexOf(key, pos);       //get index of every key update "pos" to start searching the key again but this time ahead than b4
            
            //if the next char is past the end break
            if(index+key.length() == myText.length() || index == -1) {
                break;
            }
            //else add the character infront
            follows.add(Character.toString(myText.charAt(index + key.length())));
            pos = index + key.length(); // index+1

        }
        return follows;
    }
}
