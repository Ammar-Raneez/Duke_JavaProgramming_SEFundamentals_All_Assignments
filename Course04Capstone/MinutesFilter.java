
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int minLength, maxLength;
    
    public MinutesFilter(int min, int max) {
        this.minLength = min;
        this.maxLength = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        int minutes = MovieDatabase.getMinutes(id);
        return minutes >= minLength && minutes <= maxLength;
    }
}
