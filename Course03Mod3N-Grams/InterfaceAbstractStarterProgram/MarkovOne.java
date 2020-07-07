
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovOne extends AbstractMarkovModel{
    //the abstract class extended has all the duplicated methods, as well as
    //the interface implementation, therefore its not necessary to implement it again
    public MarkovOne() {
        myRandom = new Random();
    }

    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        if (myText == null){
            return "";
        }
        //get index of currently referred random count
        //get character infront of that specific index
        //append that character onto our stringbuider
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);        //this is the first character that is added
        sb.append(key);                                         //character after this is what must be predicted
        
        //loop till 1 less, as we look into 1 ahead (nullpointer exception thrown)
        for(int k=0; k < numChars - 1; k++){
            ArrayList<String> follows = getFollows(key);        //get the characters ahead of the first character we added, then every other key
            
            if(follows.size() == 0) {   //if nothings following the specified index break the loop
                break;                  //this occurs at the end of the string
            }
            //otherwise, get the next random index and the character from the
            //follows list at that index and append to stringbuilder
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);           //pick a random character from the follows list and appends it
            sb.append(next);
            
            //afterwards update the key to move forwards
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "MarkovModel of order 1";
    }
}
