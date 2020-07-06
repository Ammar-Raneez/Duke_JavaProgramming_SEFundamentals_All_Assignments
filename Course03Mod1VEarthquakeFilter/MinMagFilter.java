
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//implements the Filter interface and overrides the satisfies method
public class MinMagFilter implements Filter{
    private double magMin; 

    public MinMagFilter(double min) { 
        magMin = min;
    } 

    public String getName() {return "Min-Mag";}
    
    //this specific satisfies method returns t/f based on whether the magnitude is greater than a minimum magnitude specified
    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 

}
