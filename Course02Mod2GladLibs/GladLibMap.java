package CountWordsArrayList;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> map;
    private ArrayList<String> usedWords;
    
    private int wordsReplaced;
    private Random myRandom;
    
    private static String dataSourceURL = "https://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "CountWordsArrayList/data";
    
    public GladLibMap(){
        map = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        map = new HashMap<>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", "adjective", "name", "color", "timeframe", "verb", "fruit"};
        for(String string : labels){
            ArrayList<String> list = readIt(source+"/"+string+".txt");
            map.put(string,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(map.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int foundIndex = usedWords.indexOf(sub);
        
        while(foundIndex != -1) {
            sub = getSubstitute(w.substring(first+1,last));
            foundIndex = usedWords.indexOf(sub);
        }
        if(foundIndex == -1) {
            usedWords.add(sub);
            wordsReplaced++;
        }
        return prefix+sub+suffix;
    }
    
    private int totalWordsInMap() {
        int count = 0;
        for(ArrayList list: map.values()) {
            count += list.size();
        }
        return count;
    }
    
    private int totalWordsConsidered() {
        int count = 0;
        
        return count;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("https")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("https")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        wordsReplaced = 0;
        usedWords = new ArrayList<String>();
        
        FileResource fr = new FileResource("CountWordsArrayList/data/madtemplate2.txt");
        for(String line: fr.lines()) {
            System.out.println(line);
        }
        
        System.out.println("\n");
        String story = fromTemplate("CountWordsArrayList/data/madtemplate2.txt");
        System.out.println("\n");
        System.out.println("Words replaced " + wordsReplaced);
        printOut(story, 60);
        System.out.println("Total number of words to pick from " + totalWordsInMap());
    }
}
