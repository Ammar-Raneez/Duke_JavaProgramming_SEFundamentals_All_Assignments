
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne mkOne = new MarkovOne();
        mkOne.setTraining("this is a test yes this is a test.");
        
        ArrayList<String> follows = mkOne.getFollows("t");
        System.out.println(follows);
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne mkOne = new MarkovOne();
        FileResource fr = new FileResource();
        
        String st = fr.asString();
        st = st.replace('\n', ' ');
        mkOne.setTraining(st);
        
        ArrayList<String> follows = mkOne.getFollows("he");
        System.out.println(follows.size());
    }
}
