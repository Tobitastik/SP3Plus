import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        DBIO dbio = new DBIO();
        ArrayList<Film> films = dbio.readFilmDataDB();
        ArrayList<Serie> series = dbio.readSerieDataDB();

        System.out.println("Size of movie arraylist: "+films.size());
        System.out.println("Size of serie arraylist: "+series.size());

        System.out.println("Test serie 2: "+series.get(0).display());
        System.out.println("Test film 2: "+films.get(2).display());

    }
}