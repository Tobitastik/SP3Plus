import java.util.ArrayList;

public class Serie implements MediaInterface{

    private String name;
    private String year;
    private ArrayList<String> categories;
    private double rating;
    private ArrayList<Season> season;
    private ArrayList<Integer> episodes;


    private String startYear;
    private String endYear;

    public Serie(String name, String year, ArrayList<String> categories, double rating, ArrayList<Season> season){

        this.name = name;
        this. year = year;
        this.categories = categories;
        this.rating = rating;
        this.season = season;
        this.episodes = new ArrayList<>();

        for(Season s : season){
            episodes.addAll(s.getEpisodes());
        }

    }

    public String getName() {
        return name;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getYear() {
        if(startYear != null && endYear != null) {
           return startYear + "-"+ endYear;
        } else if(startYear != null){
           return startYear;
        } else{
           return"";
        }

    }


    public ArrayList<String> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Season> getSeason() {

        return season;
    }

    public ArrayList<Integer> getEpisodes(){
        return episodes;
    }


    @Override
    public String display() {
        StringBuilder seasonString = new StringBuilder("[");

        for (Season s : season) {
            seasonString.append("Season").append(s.getNumberOfSeasons()).append(": ");
            ArrayList<Integer> seasonEpisodes = s.getEpisodes();

            for (int episode : seasonEpisodes) {
                seasonString.append("Episode").append(episode).append(", ");
            }


            if (!seasonEpisodes.isEmpty()) {
                seasonString.delete(seasonString.length() - 2, seasonString.length());
            }

            seasonString.append(" | ");
        }


        if (!season.isEmpty()) {
            seasonString.delete(seasonString.length() - 3, seasonString.length());
        }

        seasonString.append("]");

        return "Serie{" +
                "Name = " + name +
                ", Year = " + year +
                ", Categories = " + categories +
                ", Rating = " + rating +
                ", Season = " + seasonString.toString() + "}";
    }

}