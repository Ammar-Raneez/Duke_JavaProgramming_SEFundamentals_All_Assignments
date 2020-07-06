import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    CaesarCipher[] ciphers;
    
    //constructor that initializes our ciphers array
    //taking key length size and each index having a different key
    //based on indexing on our array of keys
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    //encryption
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        //we convert the entire input into a character array so we can individually encrypt each character
        for (char c : input.toCharArray()) {
            //this value gets an index from 0 to cipher.length always due to wrapping around by the % sign
            int cipherIndex = i % ciphers.length;
            //so we simply assign the cc at this array index - cuz it has the key for it's specific index
            //and so we can use this cipher to encrypt our character at this index
            CaesarCipher thisCipher = ciphers[cipherIndex];
            //then we simply add onto the string builder and increment a counter, so that we can get the next index key for next character
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    //decryption, similar functionality, but instead call caesar cipher's decrypt method
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
}
