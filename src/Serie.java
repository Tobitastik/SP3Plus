import java.util.ArrayList;

public class Serie implements MediaInterface{

    private String name;
    private String year;
    private ArrayList<String> categories;
    private double rating;
    private ArrayList<Season> seasons;
    private ArrayList<Integer> episodes;
    private String startYear;
    private String endYear;

    public Serie(String name, String year, ArrayList<String> categories, double rating, ArrayList<Season> seasons){
        this.name = name;
        this. year = year;
        this.categories = categories;
        this.rating = rating;
        this.seasons = seasons;
        this.episodes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getStartYear(){
        return startYear;
    }

    public String getEndYear(){
        return endYear;
    }

    public void setStartYear(String startYear){
        this.startYear = startYear;
    }

    public void setEndYear(String endYear){
        this.endYear = endYear;
    }

    public String getYearString(){
        if(startYear != null && endYear != null){
            return startYear +"-"+endYear;
        } else if (startYear != null){
            return startYear;
        } else
            return "";
    }

    public int getYear(){ return Integer.parseInt(startYear);}

   public ArrayList<String> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Season> getSeason() {
        return seasons;
    }

    public ArrayList<Integer> getEpisodes(){
        return episodes;
    }


    @Override
    public String display() {
        StringBuilder seasonString = new StringBuilder("[");

        for (Season s : seasons) {
            seasonString.append("Season ").append(s.getNumberOfSeasons()).append(": ");
            int seasonEpisodes = s.getEpisodes();

            seasonString.append("Episodes ").append(seasonEpisodes).append(" | ");
        }

        if (!seasons.isEmpty()) {
            seasonString.delete(seasonString.length() - 3, seasonString.length());
        }

        seasonString.append("]");

        return "Serie{" +
                "Name = " + name +
                ", Year = " + year +
                ", Categories = " + categories +
                ", Rating = " + rating +
                ", Seasons = " + seasonString.toString() + "}";
    }

}