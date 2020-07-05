
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String requestType;
    private String phrase;
    
    public PhraseFilter(String req, String phrase) {
        this.requestType = req;
        this.phrase = phrase;
    }
    
    public String getName(){ return "Phrase"; }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.toString().substring(qe.toString().indexOf("title =")+8);
        
        if (requestType.equals("start")) return title.startsWith(phrase); 
        if (requestType.equals("end")) return title.endsWith(phrase); 
        else return title.contains(phrase);
    }
}
