
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){              //num chars - how many characters we wanna display
        StringBuilder sb = new StringBuilder();
        if (myText == null){
            return "";
        }
        //get index of currently referred random count
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);    //this is the first character that is added
        sb.append(key);                                     //character after this is what must be predicted
        
        //loop till 1 less, as we look into 1 ahead (nullpointer exception thrown)
        for(int k=0; k < numChars - 1; k++){
            ArrayList<String> follows = getFollows(key);    //get the characters ahead of the first character we added, then every other key
            
            if(follows.size() == 0) {   //if nothings following the specified index break the loop
                break;                  //this occurs at the end of the string
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);               //pick a random character from the follows list 
            sb.append(next);                                //(characters that follow the key in the text) and append it
            
            //afterwards update the key to move forwards
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
        //list to hold the output
        ArrayList<String> output = new ArrayList<>();
        
        //loop through the entire text - get the index of the passed key, and add the characters infront of it to the output list, for 
        //all words in the document
        int pos = 0;
        for(int i=0; i<myText.length(); i++) {
            int foundIndex = myText.indexOf(key, pos);
            if(foundIndex == -1 || foundIndex+key.length() == myText.length()) break;
            output.add(Character.toString(myText.charAt(foundIndex + key.length())));
            pos = foundIndex + key.length();        //key length can vary, not always of length 1, if for instead two characters were passed
        }
        return output;
    }
}
