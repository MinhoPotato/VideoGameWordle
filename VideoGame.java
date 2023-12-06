import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the basics for everything!
 */
public class VideoGame
{
    private String title;
    private String genre;
    private String subGenre;
    private String publisher;
    private int unitsSold;
    private String rating;
    private ArrayList<String> ratings = new ArrayList<>(Arrays.asList("E", "E10", "T", "M", "A", "RP"));
    
    public VideoGame(String t, String g, String sg, String p, int u, String r) throws Exception {
        if(ratings.indexOf(r) == -1 || u <= 0){
            throw new Exception("Adding Error");
            //the rating must be in our ratings list and units sold must be positive
        }
        else{
            title = t;
            genre = g;
            subGenre = sg;
            publisher = p;
            unitsSold = u;
            rating = r;
        }
        //specificed constructor
    }
    
    //get methods
    
    public String getTitle(){
        return title;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public String getSubGenre(){
        return subGenre;
    }
    
    public String getPublisher(){
        return publisher;
    }
    
    public int getUnitsSold(){
        return unitsSold;
    }
    
    public String getRating(){
        return rating;
    }
    
    //compare method. This one is fun
    public String compare(VideoGame other){
        //this compare method is going to return a string
        //0 if the game we are comparing to is larger than us, for example starts with E and we start with A
        //2 for vice versa
        //1 if it is the same. 
        
        String comparison = "";
        if(this == other){
            return "111111";
            //if they are all the same, we simply return 6 ones
        }
        
        if(title == other.getTitle()){
            comparison += "1";
            //if the title is the same, we add one to comparison
        }
        else{
            int min = Math.min(title.length(), other.getTitle().length());
            for(int i = 0; i < min; i++){
                if(title.charAt(i) < other.getTitle().charAt(i)){
                    comparison += "2";
                    break;
                    //if we are A and it is E, we add 0
                }
                else if(title.charAt(i) > other.getTitle().charAt(i)){
                    comparison += "0";
                    break;
                    //if we are E and it is A we add 2
                }
            }
        }
        
        if(genre == other.getGenre()){
            comparison += "1";
            //if the title is the same, we add one to comparison
        }
        else{
            int min = Math.min(genre.length(), other.getGenre().length());
            for(int i = 0; i < min; i++){
                if(genre.charAt(i) < other.getGenre().charAt(i)){
                    comparison += "2";
                    break;
                    //if we are A and it is E, we add 0
                }
                else if(genre.charAt(i) > other.getGenre().charAt(i)){
                    comparison += "0";
                    break;
                    //if we are E and it is A we add 2
                }
            }
        }
        
        if(subGenre == other.getSubGenre()){
            comparison += "1";
            //if the title is the same, we add one to comparison
        }
        else{
            int min = Math.min(subGenre.length(), other.getSubGenre().length());
            for(int i = 0; i < min; i++){
                if(subGenre.charAt(i) < other.getSubGenre().charAt(i)){
                    comparison += "2";
                    break;
                    //if we are A and it is E, we add 0
                }
                else if(subGenre.charAt(i) > other.getSubGenre().charAt(i)){
                    comparison += "0";
                    break;
                    //if we are E and it is A we add 2
                }
            }
        }
        
        if(publisher == other.getPublisher()){
            comparison += "1";
            //if the title is the same, we add one to comparison
        }
        else{
            int min = Math.min(publisher.length(), other.getPublisher().length());
            for(int i = 0; i < min; i++){
                if(publisher.charAt(i) < other.getPublisher().charAt(i)){
                    comparison += "2";
                    break;
                    //if we are A and it is E, we add 0
                }
                else if(publisher.charAt(i) > other.getPublisher().charAt(i)){
                    comparison += "0";
                    break;
                    //if we are E and it is A we add 2
                }
            }
        }
        
        //the units sold is the easy one LOL
        if(unitsSold == other.getUnitsSold()){
            comparison += "1";
            //if same add one
        }
        else if(unitsSold < other.getUnitsSold()){
            comparison += "0";
        }
        else{
            comparison += "2";
        }
        
        //finally, rating. which is more complicated
        if(ratings.indexOf(rating) < ratings.indexOf(other.getRating())){
            comparison += "0";
        }
        else if(ratings.indexOf(rating) > ratings.indexOf(other.getRating())){
            comparison += "2";
        }
        else{
            comparison += "1";
        }
        
        return comparison;
    }
    
    public String toString(){
        return title + " " + genre + " " + subGenre + " " + publisher + " " + unitsSold + " " + rating;
    }
}
