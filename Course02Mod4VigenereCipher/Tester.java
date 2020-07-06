/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    public void testCaesar() {
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource();
        String string = fr.asString();
        String output = cc.encrypt(string);
        String dec = cc.decrypt(output);
        System.out.println(output);
        System.out.println(dec);
    }
    
    public void testCaesarBreaker() {
        //CaesarCracker cc = new CaesarCracker();
        //FileResource fr = new FileResource();
        //String decrypt = cc.decrypt(fr.asString());
        //System.out.println(decrypt);
        
        CaesarCracker cc2 = new CaesarCracker('a');
        FileResource fr2 = new FileResource();
        String decrypt2 = cc2.decrypt(fr2.asString());
        System.out.println(decrypt2);
    }
}
