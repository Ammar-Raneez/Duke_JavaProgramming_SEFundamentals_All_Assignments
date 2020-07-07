
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        this.myRandom = new Random();
        this.myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    //split the string at the whitespaces so that each word has their own index in the array
    public void setTraining(String text){
        myText = text.split("\\s+");
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
    
    //gets words following the referred key word and returns a list of those words
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        
        for(int i = 0; i < myText.length; i++) {
            int index = indexOf(myText, kGram, pos);
            if((index+kGram.length() >= myText.length - myOrder) || index == -1) {    //check to determine whether word referred is the last/ not found
                break;
            }
            follows.add(myText[index+myOrder]);
            pos = index + myOrder;             
        }
        return follows;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);             //append it
        sb.append(" ");
        
        //get follows from that specific word
        for(int i=0; i < numWords-myOrder; i++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);           //update key and append random word from follows list
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return "Markov Word of order " + myOrder;
    }
}
