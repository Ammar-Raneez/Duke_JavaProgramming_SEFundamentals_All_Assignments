/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String lastWord1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ") + 1);
        String lastWord2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ") + 1);
        
        int titleCompare = lastWord1.compareTo(lastWord2); //compare THIS to the other (lastWord2 with lastWord2)
        
        if(titleCompare != 0) return titleCompare;
        return Double.compare(q1.getMagnitude(), q2.getMagnitude());    //compare two objects - called from another object
    }
}
    