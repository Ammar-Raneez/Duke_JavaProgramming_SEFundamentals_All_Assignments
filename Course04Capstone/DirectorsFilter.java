
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    String directors;
    
    public DirectorsFilter(String directors) {
        this.directors = directors;
    }
    
    @Override
    //returns t/f depending on whether the passed director has directed the movie or not
    public boolean satisfies(String id) {
        String[] allDirectors = directors.split(",");
        
        for(String dir: allDirectors) {
            if(MovieDatabase.getDirector(id).contains(dir)) return true;
        }
        return false;
    }
}
