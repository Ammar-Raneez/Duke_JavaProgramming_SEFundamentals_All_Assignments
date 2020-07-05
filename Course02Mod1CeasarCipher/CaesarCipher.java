
/**
 * Write a description of CeasarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                if(Character.isUpperCase(currChar)) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                } else {
                    char newChar = shiftedAlphabet.charAt(idx);
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public void testEncrypt() {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        int key = 17;
        
        String encrypted = encrypt("First Legion", key);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println("Key is - " + key + "\n" + encrypted);
        System.out.println(decrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        //takes stringbuilder and modifies it, with a different key 
        //depending on index
        StringBuilder string = new StringBuilder(input);
        
        for(int i=0; i<string.length(); i++) {
            if(i%2==0) {
                String newString = encrypt("" + string.charAt(i), key1);
                string.setCharAt(i, newString.charAt(0));
            } else {
                String newString = encrypt("" + string.charAt(i), key2);
                string.setCharAt(i, newString.charAt(0));
            }
        }
        return string.toString();
    }
    
    public void testTwoKeys() {
        String result = encryptTwoKeys("First Legion", 23, 17);
        System.out.println(result);
    }
    
    
    
    public void decryptEyeball(String encrypted) {
        //human/ eyeball decryption - scans through all 26 keys to see which one it is
        for(int i=0; i<26; i++) {
            String decrypted = encrypt(encrypted, i);
            System.out.println(decrypted);
        }
    }
    
    public void testDecryptEyeball() {
        String encrypted = encrypt("First Legion", 17);
        System.out.println(encrypted + "\n");
        decryptEyeball(encrypted);
    }
    
    
    
    
    
    //next method - based on the frequency of the most occuring letter in English - 'e'
    public int[] countLetters(String encrypted) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] letters = new int[26];
        
        for(int i=0; i<encrypted.length(); i++) {
            char ch = Character.toUpperCase(encrypted.charAt(i));
            int index = alphabet.indexOf(ch);
            if(index != -1) letters[index]++;
        }
        return letters;
        //loops through our encrypted message and keeps track of total number of occurences for each letter
    }
    
    //splits the string into two parts based on start position
    public String halfOfString(String message, int start) {
        String string = "";
        
        for(int i=start; i<message.length(); i+=2) {
            string += message.charAt(i);
        }
        
        return string;
    }
    
    //returns the letter that occured the most in the cnrypted message
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for(int i=0; i<vals.length; i++) {
            if(vals[i] > vals[maxDex]) maxDex = i;
        }
        return maxDex;
    }
    
    //get key used for both instances
    public int getKey(String s) {
        int[] frequencies = countLetters(s);
        int maxIndex = maxIndex(frequencies);
        int decryptKey = maxIndex - 4;
        if(maxIndex<4) decryptKey = 26 - (4-maxIndex);
        return decryptKey;
    }
    
    //simply call all methods for each separate message
    public void decryptTwoKeys(String encrypted) {
       StringBuilder decrypted = new StringBuilder(encrypted);
        
       String firstHalf = halfOfString(encrypted, 0);
       String secondHalf = halfOfString(encrypted, 1);
        
       int firstKey = getKey(firstHalf);
       int secondKey = getKey(secondHalf);
        
       String decryptedFirstHalf = encrypt(firstHalf, 26-firstKey);
       String decryptedSecondHalf = encrypt(secondHalf, 26-secondKey);
        
       for (int i=0; i < decryptedFirstHalf.length(); i++){
           decrypted.setCharAt((2*i), decryptedFirstHalf.charAt(i));
       }
           
       for (int i=0; i < decryptedSecondHalf.length(); i++){
           decrypted.setCharAt((2*i)+1, decryptedSecondHalf.charAt(i));
       }
           
       System.out.println("Two Keys Found - "  + "Key 1 - " + firstKey + ", Key 2 - " + secondKey);
       System.out.println(decrypted.toString());    
    }
    
    public void testTwoDecrypt() {
        FileResource fr = new FileResource();
        String string = fr.asString();
        decryptTwoKeys(string);
    }
    
    
    //does the decrypting
    public String decryptNormal(String encrypted) {
        int[] frequencies = countLetters(encrypted);
        //after getting the maxIndex, the value is where we assume the 
        //'e' was shifted in the alphabet (we are looking for the most occuring
        //letter, in our case letter 'e')
        int maxIndex = maxIndex(frequencies);   //*this is basically what leads to us getting the encrypted key (ans-4)*//
        //we get the distance from our index to actual letter 'e'
        //letter 'e' has index 4, so we can get the distance it has 
        //shifted in this way, this is all based on our assumption that letter 'e' has highest frequency
        int distanceKey = maxIndex - 4;  
        //*for instance, from example "elephent" the maxIndex is 21 and distance is 17*//
        //*thing ofc only works if 'e' is the most used in the actual msg*//
        //*assuming this we get the most used in the encrypted msg, in our case it's 'v', get index and subtract original 'e' location*//
        
        //wrap around if max index is less than "e's" actual position - 4
        if(maxIndex < 4) {
            distanceKey = 26 - (4-maxIndex);
        }
        //so we have the assumed key used to encrypt
        //therefore 26-encrypt key = decrypt key
        return encrypt(encrypted, 26 - distanceKey);
    }
    public void testDecrypt() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        String string =  encrypt(input, 17);
        System.out.println(string);
        int[] letters = countLetters(string);
        System.out.print("Frequencies: ");
        for(int val: letters) System.out.print(val);
        System.out.println();
        System.out.print("Max Index: ");
        int max = maxIndex(letters); System.out.println(max);
        String decrypted = decryptNormal(string);
        System.out.print("Decrypted: ");
        System.out.println(decrypted);
    }
}
