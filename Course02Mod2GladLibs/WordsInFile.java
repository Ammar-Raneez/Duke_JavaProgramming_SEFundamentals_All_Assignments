package CountWordsArrayList;


/**
 * Write a description of WordsInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFile {
    private HashMap<String, ArrayList<String>> map;
    
    public WordsInFile() {
        map = new HashMap<>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        
        for(String word: fr.words()) {
            if(map.keySet().contains(word)) {
                if(!map.get(word).contains(f.getName()))
                    map.get(word).add(f.getName());
            } else {
                ArrayList<String> fileName = new ArrayList<>();
                fileName.add(f.getName());
                map.put(word, fileName);
            }
        }
    }
    
    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxFiles = 0;
        for(String key: map.keySet()) {
            if(map.get(key).size() > maxFiles) {
                maxFiles = map.get(key).size();
            }
        }
        return maxFiles;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<>();
        int count = 0;
        
        for(String string: map.keySet()) {
            if(map.get(string).size() == number) {
                words.add(string);
                count++;
            }
        }
        System.out.println("There are " + count + " words in " + number + " files.");
        return words;
    }
    
    private void printFilesIn(String word) {
        for(String string: map.keySet()) {
            if(string.equals(word)) {
                ArrayList<String> files = map.get(string);
                for(String file: files) {
                    System.out.println(file);
                }
            }
        }
    }
    
    public void tester() {
        buildWordFileMap();
        //ArrayList wordsInNumFiles = wordsInNumFiles(4);
        
        //for (int i=0; i < wordsInNumFiles.size(); i++){
        //   System.out.println(wordsInNumFiles.get(i));
        //}
        
        System.out.println("\nMaximum number of words in file is = " +maxNumber());
        System.out.println("\n");
        
        printFilesIn("tree");
        
        //for (String s :map.keySet() ){
        //    System.out.println(s + map.get(s));
        //}
    }
}
