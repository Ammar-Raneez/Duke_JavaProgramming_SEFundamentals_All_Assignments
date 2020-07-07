
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.util.*;

public class MarkovRunner {
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);                         //removing trailing and leading whitespaces from the text
        markov.setRandom(1024);
        
        for(int k=0; k < 3; k++){ //run three times
            String text = markov.getRandomText(500);    //call the getRandomText passing 500(do it for 500 characters)
            printOut(text);
        }
    }
    
    public void runMarkovOne() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is a test yes this is a test";
        
        MarkovOne markov = new MarkovOne();        
        markov.setTraining(st);                         
        markov.setRandom(365);
        
        //ArrayList<String> a = markov.getFollows("es");
        //System.out.println(a);
        
        for(int k=0; k < 3; k++){ 
            String text = markov.getRandomText(500);    
            printOut(text);
        }    
    }
    
    public void runMarkovFour() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is a test yes this is a test";
        
        MarkovFour markov = new MarkovFour();        
        markov.setTraining(st);                         
        markov.setRandom(715);
        
        //ArrayList<String> a = markov.getFollows("es");
        //System.out.println(a);
        
        for(int k=0; k < 3; k++){ 
            String text = markov.getRandomText(500);    
            printOut(text);
        }    
    }

    public void runMarkovModel() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is a test yes this is a test";
        
        MarkovModel markov = new MarkovModel(7);        
        markov.setTraining(st);                         
        markov.setRandom(953);
        
        //ArrayList<String> a = markov.getFollows("es");
        //System.out.println(a);
        
        for(int k=0; k < 3; k++){ 
            String text = markov.getRandomText(500);    
            printOut(text);
        }
    }
        
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

}
