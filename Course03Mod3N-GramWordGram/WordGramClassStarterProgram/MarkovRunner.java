
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(3);
        markovWord.setRandom(643);
        runModel(markovWord, st, 200); 
    } 
    
    public void testHashMap() {
        EfficientMarkovWord markov = new EfficientMarkovWord(6);
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');        
        runModel(markov, st, 500, 792);
        
        //markov.setTraining(st);
        //markov.setRandom(792);
        //markov.printHashMapInfo();
    }

    public void compareMethods() {
        int order = 2;
        int seed = 42;
        int length = 100;
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        long startTime = System.nanoTime();
        MarkovWord mWord = new MarkovWord(order);
        runModel(mWord, st, length, seed);
        long endTime = System.nanoTime();
        System.out.println("Time for MarkovModel: " + ((endTime - startTime) / 1000000) + " ms");
        
        startTime = System.nanoTime();
        EfficientMarkovWord emWord = new EfficientMarkovWord(order);
        runModel(emWord, st, length, seed);
        endTime = System.nanoTime();
        System.out.println("Time for EfficientMarkovModel: " + ((endTime - startTime) / 1000000 + " ms"));    
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
