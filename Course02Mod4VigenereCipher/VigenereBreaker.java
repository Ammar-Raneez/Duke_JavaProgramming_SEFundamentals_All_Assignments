import java.util.*;
import edu.duke.*;
import java.io.*;

/**
 * Follows the same procedure as caesar cipher, but uses the array of keys to decrypt each individual character based on array key indexing
 */

public class VigenereBreaker {
    //
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder string = new StringBuilder();
        
        for(int i=whichSlice; i<message.length(); i+= totalSlices) {
            string.append(message.charAt(i));
        }
        
        return string.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker ccr = new CaesarCracker(mostCommon);
        
        for(int i=0; i<klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = ccr.getKey(slice);
        }
        
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> set = new HashSet<>();
        
        for(String string: fr.lines()) {
            string = string.toLowerCase();
            set.add(string);
        }
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int count = 0;
        
        for(String word: words) {
            word = word.toLowerCase();
            if(dictionary.contains(word)) count++;
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCount = 0;
        String decrypted = "";
        char mostCommon = mostCommonCharIn(dictionary);
        
        for(int i=1; i<=100; i++) {
            int[] keys = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            String string = vc.decrypt(encrypted);
            int a = countWords(string, dictionary);

            if(a > maxCount) {
                maxCount  = a;
            }
        }
        
        for (int j = 1; j <= 100; j++){
            int[] key = tryKeyLength(encrypted, j, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String s = vc.decrypt(encrypted);
            int a = countWords(s, dictionary);
            
            if (a == maxCount){
                decrypted = s;
            }
        }
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        //most common character in a language
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(String string: dictionary) {
            string = string.toLowerCase();
            for(char c: string.toCharArray()) {
                if(!map.keySet().contains(c)) map.put(c, 1);
                else map.put(c, map.get(c)+1);
                
            }
        }
        
        int maxOccurence = 0;
        for(char c: map.keySet()) {
            if(map.get(c) > maxOccurence) maxOccurence = map.get(c);
        }
        
        for(char c: map.keySet()) {
            if(map.get(c) == maxOccurence) return c;
        }
        return 'e';
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        
        for(String language: languages.keySet()) {
            String string = breakForLanguage(encrypted, languages.get(language));
            int count = countWords(string, languages.get(language));
            if(count>maxCount) maxCount = count;
        }
        
        for(String language: languages.keySet()) {
            String string = breakForLanguage(encrypted, languages.get(language));
            int count = countWords(string, languages.get(language));
            if(count == maxCount) System.out.println(string + " " + language);
        }
    }
    
    public void breakVigenere () {
        FileResource messageFr = new FileResource();
        String s = messageFr.asString();
        int[] keys = tryKeyLength(s, 5, 'e');
      
        VigenereCipher vc = new VigenereCipher(keys);
        //System.out.println(vc.decrypt(s));
        
        DirectoryResource dictionaryDr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        for(File f: dictionaryDr.selectedFiles()) {
            FileResource fr2 = new FileResource(f);
            languages.put(f.getName(), readDictionary(fr2));
        }
        breakForAllLangs(s, languages);
        
        //System.out.println(keys);
        //String decrypted = breakForLanguage(s, dictionary);
        //System.out.println(decrypted);
    }
}
