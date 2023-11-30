import java.util.ArrayList;
import java.util.Calendar;

public class User {
    private String username;
    private ArrayList<Film> filmWatched;
    private ArrayList<Film> filmSaved;
    private ArrayList<Serie> serieWatched;
    private ArrayList<Serie> serieSaved;


    public User(String username){
        this.username = username;
        this.filmWatched = new ArrayList<>();
        this.filmSaved = new ArrayList<>();
        this.serieWatched = new ArrayList<>();
        this.serieSaved = new ArrayList<>();

    }

    public String getUsername(){
        return username;
    }

    public ArrayList<Film> getFilmWatched() {
        return filmWatched;
    }

    public ArrayList<Film> getFilmSaved() {
        return filmSaved;
    }

    public ArrayList<Serie> getSerieWatched() {
        return serieWatched;
    }

    public ArrayList<Serie> getSerieSaved() {
        return serieSaved;
    }

    public void addToSavedSerie(Serie serie){
        serieSaved.add(serie);
    }

    public void addToWatchedSerie(Serie serie){
        serieWatched.add(serie);
    }
}
