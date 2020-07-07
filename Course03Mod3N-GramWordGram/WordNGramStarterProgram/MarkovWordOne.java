
/**
 * The catch here is we split the text into individual words, and the rest is same as for markov characters
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    //split the string at the whitespaces so that each word has their own index in the array
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    //returns index of referred word
    public int indexOf(String[] words, String target, int start) {
        for(int i = start; i<words.length; i++) {
            if(words[i].equals(target)) return i;
        }
        return -1;
    }
    
    //gets words following the referred key word found everywhere from the entire text and returns a list of those words
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        for(int i = 0; i < myText.length; i++) {
            int index = indexOf(myText, key, pos);
            if(index+key.length() == myText.length || index == -1) {    //check to determine whether word referred is the last/ not found
                break;
            }
            follows.add(myText[index+1]);
            //pos = index + key.length(); // index+1 **cant use this due to the word length being counted
            pos = index + 1;              //rather we need only to move the index  
        }
        return follows;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index]; //get random word key
        sb.append(key);             //append it
        sb.append(" ");
        
        //get follows from that specific word
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);           //update key and append random word from follows list
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return "Markov Word of order 1";
    }
}
