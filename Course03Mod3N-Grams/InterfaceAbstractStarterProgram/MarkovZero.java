/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel{
    public MarkovZero() {
        myRandom = new Random();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        //MarkovZero - simply take a random character from the string and add it to the output
        for(int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "MarkovModel of order 0";
    }
}
