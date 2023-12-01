import java.util.ArrayList;



public class Main {

    public static void main(String[] args) {

        FileLib fileLib = new FileLib();
        FileIO io = new FileIO();
        TextUI ui = new TextUI();

        io.readFilmData(fileLib);
        io.readSerieData(fileLib);
/*
        DBIO dbio = new DBIO();
        ArrayList<Film> films = dbio.readFilmDataDB();
        ArrayList<Serie> series = dbio.readSerieDataDB();

        System.out.println("Size of movie arraylist: "+films.size());
        System.out.println("Size of serie arraylist: "+series.size());

        System.out.println("Test serie 0: "+series.get(0).display());
        System.out.println("Test film 2: "+films.get(2).display());

        System.out.println("Movie size: "+fileLib.getFilms().size());
        System.out.println("Serie size: "+fileLib.getSeries().size());


 */

        io.readFilmData(fileLib);
        io.readSerieData(fileLib);

        Menu menu = new Menu(fileLib.getFilms(), fileLib.getSeries());
        UserMenu userMenu = new UserMenu(ui, fileLib.getFilms(), fileLib.getSeries(), menu);
        userMenu.chooseMenu();
        /*
        DBMenu dbMenu = new DBMenu();
        dbMenu.DBRunner();


         */



    }
}