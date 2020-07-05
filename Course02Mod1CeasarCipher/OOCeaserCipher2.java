
/**
 * Write a description of OOCeaserCipher2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class OOCeaserCipher2 {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;
    
    public OOCeaserCipher2(int key1, int key2) {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        this.shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2); 
        this.key1 = key1;
        this.key2 = key2;
    }
    
    public String encryptTwoKeys(String input) {
        OOCeaserCipher oocc1 = new OOCeaserCipher(key1);
        OOCeaserCipher oocc2 = new OOCeaserCipher(key2);
        
        StringBuilder string = new StringBuilder(input);
        
        for(int i=0; i<string.length(); i++) {
            if(i%2==0) {
                String newString = oocc1.encrypt("" + string.charAt(i));
                string.setCharAt(i, newString.charAt(0));
            } else {
                String newString = oocc2.encrypt("" + string.charAt(i));
                string.setCharAt(i, newString.charAt(0));
            }
        }
        return string.toString();
    }
    
    public String breakCaesarCipherTwoKeys(String input) {
       CaesarCipher cc = new CaesarCipher();
       StringBuilder decrypted = new StringBuilder(input);
        
       String firstHalf = cc.halfOfString(input, 0);
       String secondHalf = cc.halfOfString(input, 1);
        
       int firstKey = cc.getKey(firstHalf);
       int secondKey = cc.getKey(secondHalf);
        
       String decryptedFirstHalf = cc.encrypt(firstHalf, 26-firstKey);
       String decryptedSecondHalf = cc.encrypt(secondHalf, 26-secondKey);
        
       for (int i=0; i < decryptedFirstHalf.length(); i++){
           decrypted.setCharAt((2*i), decryptedFirstHalf.charAt(i));
       }
           
       for (int i=0; i < decryptedSecondHalf.length(); i++){
           decrypted.setCharAt((2*i)+1, decryptedSecondHalf.charAt(i));
       }
           
       System.out.println("Two Keys Found - "  + "Key 1 - " + firstKey + ", Key 2 - " + secondKey);
       
       return decrypted.toString();
    }
    
    public void simpleTests() {
        //FileResource fr = new FileResource();
        OOCeaserCipher2 oocc2 = new OOCeaserCipher2(21, 3);
        String encrypted = oocc2.encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(encrypted);
        String decrypted = oocc2.breakCaesarCipherTwoKeys(encrypted);
        System.out.println(decrypted);
    }
}
