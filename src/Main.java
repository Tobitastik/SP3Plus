import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        FileLib fileLib = new FileLib();
        FileIO io = new FileIO();
        DBIO dbio = new DBIO();
        TextUI ui = new TextUI();

        io.readFilmData(fileLib);
        io.readSerieData(fileLib);

        dbio.readFilmDataDB(fileLib);
        dbio.readSerieDataDB(fileLib);

        /*System.out.println("Movie size: "+fileLib.getFilms().size());
        System.out.println("Serie size: "+fileLib.getSeries().size());
        System.out.println("MovieDB size: "+fileLib.getFilmsDB().size());
        System.out.println("SerieDB size: "+fileLib.getSeriesDB().size());*/

        /*Menu menu = new Menu(fileLib.getFilms(), fileLib.getSeries());
        UserMenu userMenu = new UserMenu(ui, fileLib.getFilms(), fileLib.getSeries(), menu);
        userMenu.chooseMenu();
        Menu menu = new Menu(fileLib.getFilmsDB(), fileLib.getSeriesDB());
        UserMenu userMenu = new UserMenu(ui, fileLib.getFilmsDB(), fileLib.getSeriesDB(), menu);
        userMenu.chooseMenu();*/
        SearchManager searchManager = new SearchManager(io, dbio, ui, fileLib);
        Menu menu = new Menu(fileLib.getFilms(), fileLib.getSeries());
        UserMenu userMenu = new UserMenu(ui, fileLib.getFilms(), fileLib.getSeries(), menu);


        System.out.println("Movie size: "+fileLib.getFilms().size());
        System.out.println("Serie size: "+fileLib.getSeries().size());
        System.out.println("MovieDB size: "+fileLib.getFilmsDB().size());
        System.out.println("SerieDB size: "+fileLib.getSeriesDB().size());

        userMenu.chooseIOProvider();
    }
}