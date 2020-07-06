/**
 * Write a description of class MagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MagnitudeComparator implements Comparator<QuakeEntry> {
    //Comparator interface implemented, passing the QuakeEntry type (Comparator<T> actual)
    /** Comparator takes a generic type so that for any data type it'd work.
      * We need this for our QuakeEntry data type, so we implement it, passing QuakeEntry, in order to make it work for out data type
      * The compare() method in the Comparator interface compares two different instances of classes, called from another 
      * object - in our case, the Double object calls the compare() method
      * while the compareTo() method compares THIS object with the other (a.compareTo(b) - means compare a with b)
      */
     
    //compare method implemented overrun. This method now returns a value between -1 and 1 depending on whether the values passed in the
    //compare() method are equal or not
    @Override
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
    }
    
}
