public class Season {
    private String numberOfSeasons;
    private int episodes;

    public Season(String numberOfSeasons, int episodes){
        this.numberOfSeasons = numberOfSeasons;
        this.episodes = episodes;
    }

    public String getNumberOfSeasons(){
        return numberOfSeasons;
    }

    public int getEpisodes(){
        return episodes;
    }

}
