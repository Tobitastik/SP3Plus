import java.util.ArrayList;

public class FileLib {
    private ArrayList<Film> filmsDB;
    private ArrayList<Serie> seriesDB;

    public FileLib() {
        this.filmsDB = new ArrayList<>();
        this.seriesDB = new ArrayList<>();
    }

    public ArrayList<Film> getFilmsDB() {
        return filmsDB;
    }

    public ArrayList<Serie> getSeriesDB() {
        return seriesDB;
    }

    public void addFilmDB(Film film) {
        filmsDB.add(film);
    }

    public void addSerieDB(Serie serie) {
        seriesDB.add(serie);
    }
}
