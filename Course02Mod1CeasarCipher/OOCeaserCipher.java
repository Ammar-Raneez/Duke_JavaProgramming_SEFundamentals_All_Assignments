
/**
 * Write a description of OOCeaserCipher here.
 * CeaserCipher using OOP Concepts of epic organization
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class OOCeaserCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    //all necessary instance fields
    
    //initialize all instance fields upon object creation
    public OOCeaserCipher(int key) {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        this.mainKey = key;
    }
    
    //same method, that encrypts the text
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx != -1){
                if(Character.isUpperCase(currChar)) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                } else {
                    char newChar = shiftedAlphabet.charAt(idx);
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        
        return encrypted.toString();
    }
    
    //method that does the work, pass in the key that is 26-mainKey that
    //was found - with unknown key
    public String breakCaesarCipher(String input) {
        CaesarCipher cc = new CaesarCipher();
        int foundKey = cc.getKey(input);
        System.out.println(foundKey);
        
        OOCeaserCipher oocc = new OOCeaserCipher(26-foundKey);
        String decrypted =  oocc.encrypt(input);
        return decrypted;
    }
    
    //same method that decrypts the text passing 26-mainKey - with known key
    public String decrypt(String input) {
        OOCeaserCipher ceaserCipher = new OOCeaserCipher(26-mainKey);
        return ceaserCipher.encrypt(input);
    }
    
    public void simpleTests() {
        //FileResource fr = new FileResource();
        //String input = fr.asString();
        OOCeaserCipher cc = new OOCeaserCipher(15);
        String encrypted = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(encrypted);
        String decrypted = cc.breakCaesarCipher(encrypted);
        System.out.println(decrypted);
    }
}
