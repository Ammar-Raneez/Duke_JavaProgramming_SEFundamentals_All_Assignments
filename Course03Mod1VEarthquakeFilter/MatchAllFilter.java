
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        this.filters = new ArrayList<Filter>();
    }
    
    public String getName(){
        StringBuilder output = new StringBuilder();
        
        for(Filter f: filters) {
            output.append(f.getName() + " ");
        }
        return output.toString();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        boolean result = true;
        
        for(Filter f: filters) {
            if(!f.satisfies(qe)) return false;
        }
        return true;
    }
}
