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

        System.out.println("Movie size: "+fileLib.getFilms().size());
        System.out.println("Serie size: "+fileLib.getSeries().size());
        System.out.println("MovieDB size: "+fileLib.getFilmsDB().size());
        System.out.println("SerieDB size: "+fileLib.getSeriesDB().size());

       /* Menu menu = new Menu(fileLib.getFilms(), fileLib.getSeries());
        UserMenu userMenu = new UserMenu(ui, fileLib.getFilms(), fileLib.getSeries(), menu);
        userMenu.chooseMenu();*/

        int choice = ui.getNumericInput("Vil du gøre brug af FileIO eller DBIO? Tryk 1 for FileIO og 2 for DBIO:");
            switch (choice){
                case 1:
                    Menu menu = new Menu(fileLib.getFilms(), fileLib.getSeries());
                    UserMenu userMenu = new UserMenu(ui, fileLib.getFilms(), fileLib.getSeries(), menu);
                    userMenu.chooseMenu();
                    break;
                case 2:
                    Menu menuDB = new Menu(fileLib.getFilmsDB(), fileLib.getSeriesDB());
                    UserMenu userMenuDB = new UserMenu(ui, fileLib.getFilmsDB(), fileLib.getSeriesDB(), menuDB);
                    userMenuDB.chooseMenu();
                    break;
                default:
                    System.out.println("Invalid input");
            }



        /*Menu menu = new Menu(fileLib.getFilmsDB(), fileLib.getSeriesDB());
        UserMenu userMenu = new UserMenu(ui, fileLib.getFilmsDB(), fileLib.getSeriesDB(), menu);
        userMenu.chooseMenu();*/


    }

}