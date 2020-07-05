package CountWordsArrayList;


/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear(); 
        myFreqs.clear();
        FileResource resource = new FileResource();
        
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            //if word isnt present index of returns -1, and so we add the new found word along with a counter
            if (index == -1){
                myWords.add(s);         //adding the word and count to respective list
                myFreqs.add(1);
            }
            //else increment the index the word is
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    //in the end the words and count lists are matched by the same index values
    public void tester(){
        findUnique();
        System.out.println("Number of unique unique words: "+myWords.size());
        int index = findIndexOfMax();
        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
    }
   
    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
