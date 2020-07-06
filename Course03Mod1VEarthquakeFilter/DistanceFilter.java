 


/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location location;
    private double maxDistance;
    
    public DistanceFilter(Location location, double distance) {
        this.location = location;
        this.maxDistance = distance;
    }
    
    public String getName(){ return "Distance"; }
    
    //returns t/f based on whether the distance from a specific location to another is less than a maxDistance
    public boolean satisfies(QuakeEntry qe) {
        return location.distanceTo(qe.getLocation()) < maxDistance;
    }
}
