import java.util.ArrayList;

public class Season {
    private String numberOfSeasons;
    private ArrayList<Integer> episodes;

    public Season(String numberOfSeasons, ArrayList<Integer> episodes){
        this.numberOfSeasons = numberOfSeasons;
        this.episodes = episodes;
    }

    public String getNumberOfSeasons(){
        return numberOfSeasons;
    }

    public ArrayList<Integer> getEpisodes(){
        return episodes;
    }

    public void addEpisode(int episode){
        this.episodes.add(episode);
    }
}
