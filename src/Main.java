import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        FileLib fileLib = new FileLib();
        FileIO io = new FileIO();
        DBIO dbio = new DBIO();

        io.readFilmData(fileLib);
        io.readSerieData(fileLib);

        dbio.readFilmDataDB(fileLib);
        dbio.readSerieDataDB(fileLib);

        System.out.println("Movie size: "+fileLib.getFilms().size());
        System.out.println("Serie size: "+fileLib.getSeries().size());
        System.out.println("MovieDB size: "+fileLib.getFilmsDB().size());
        System.out.println("SerieDB size: "+fileLib.getSeriesDB().size());

    }
}