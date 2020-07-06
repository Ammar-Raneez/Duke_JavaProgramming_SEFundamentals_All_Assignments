
/**
 * Filter interface that has a satisfies method, that returns t/f based on the passed QuakeEntry
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe); 
    public String getName();
}
