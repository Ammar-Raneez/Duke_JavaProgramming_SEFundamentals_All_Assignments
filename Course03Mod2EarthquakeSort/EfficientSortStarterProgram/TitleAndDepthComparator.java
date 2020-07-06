
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int titleCompare = q1.getInfo().compareTo(q2.getInfo());
        if(titleCompare != 0) return titleCompare;
        return Double.compare(q1.getDepth(), q2.getDepth());
    }
}
