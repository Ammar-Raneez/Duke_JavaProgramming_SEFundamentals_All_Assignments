import edu.duke.*;
import java.io.File;
/**
 * Write a description of UrlSearch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UrlSearch {
    public static void main(String[] args){
        //urlresource, locate specific urls from a list of em
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for(String s : ur.words()) {
            s = s.toLowerCase();
            int pos = s.indexOf("youtube");
            if( pos != -1) {
                //int ht = s.indexOf("\"");
                //int end = s.lastIndexOf("\"");
                //String string = s.substring(ht, end);
                //System.out.println(string);
                int beg = s.lastIndexOf("\"",pos);
                int end = s.indexOf("\"", pos+1);
                System.out.println(s.substring(beg+1,end));  
            }
        }
    }
}
