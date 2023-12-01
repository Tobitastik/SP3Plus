import java.util.ArrayList;

public class FileLib {

    private ArrayList<Film> films;
    private ArrayList<Serie> series;

    public FileLib() {
        this.films = new ArrayList<>();
        this.series = new ArrayList<>();
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

}
