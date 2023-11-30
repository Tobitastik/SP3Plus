import java.util.ArrayList;

public class Film implements MediaInterface {

    private String name;
    private int year;
    private ArrayList<String> categories;
    private double rating;

    public Film(String name, int year, ArrayList<String> catagories, double rating){
        this.name = name;
        this. year = year;
        this.categories = catagories;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String display(){
        return "Movie{"+
               "Name = "+name+
               ", Year = "+year+
               ", Categories = " +categories+
               ", Rating = " + rating + "}";

    }
}
