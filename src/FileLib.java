import java.util.ArrayList;

public class FileLib{
    private ArrayList<Film> films;
    private ArrayList<Serie> series;
    private ArrayList<Film> filmsDB;
    private ArrayList<Serie> seriesDB;

    public FileLib() {
        this.films = new ArrayList<>();
        this.series = new ArrayList<>();
        this.filmsDB = new ArrayList<>();
        this.seriesDB = new ArrayList<>();
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public void addSerie(Serie serie) {
        series.add(serie);
    }

    public ArrayList<Film> getFilmsDB(){ return filmsDB; }

    public ArrayList<Serie> getSeriesDB(){ return seriesDB; }

    public void addFilmDB(Film film) {filmsDB.add(film); }
    public void addSerieDB(Serie serie) {seriesDB.add(serie);}
}
