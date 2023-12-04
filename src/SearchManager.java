import java.util.ArrayList;

public class SearchManager {
    FileIO fileIO;
    DBIO dbIO;
    Menu menu;
    private TextUI textUI;
    FileLib fileLib = new FileLib();
    private ArrayList<Serie> series;
    private ArrayList<Film> films;


    public SearchManager(FileIO fileIO, DBIO dbIO, TextUI textUI, FileLib fileLib) {
        this.fileIO = fileIO;
        this.dbIO = dbIO;
        this.textUI = textUI;
    }

    public void performSearch(boolean useFileIO) {
        if (useFileIO) {
            searchInFile();
        } else {
            searchInDB();
        }
    }

    private void searchInFile() {
        //ArrayList<Film> films = fileIO.readFilmData();
        //ArrayList<Serie> series = fileIO.readSerieData();

        // Implementer logik for at søge i films og series fra FileIO
        String searchKeyword = textUI.getInput("Indtast søgeord:");
        Menu menu = new Menu(fileLib.getFilms(), fileLib.getSeries());
        UserMenu userMenu = new UserMenu(textUI, fileLib.getFilms(), fileLib.getSeries(), menu);
        userMenu.chooseMenu();

        // Søg i film
        for (Film film : films) {
            if (film.getName().toLowerCase().contains(searchKeyword.toLowerCase())) {
                System.out.println("Film fundet: " + film.getName());
                // Gør noget med filmen, f.eks. vis detaljer eller gem den i en liste
            }
        }

        // Søg i serier
        for (Serie serie : series) {
            if (serie.getName().toLowerCase().contains(searchKeyword.toLowerCase())) {
                System.out.println("Serie fundet: " + serie.getName());
                // Gør noget med serien, f.eks. vis detaljer eller gem den i en liste
            }
        }
    }

    private void searchInDB() {
        // Access data from FileLib (adjust if necessary based on your implementation)
        ArrayList<Film> films = fileLib.getFilmsDB();
        ArrayList<Serie> series = fileLib.getSeriesDB();

        // Debugging statements
        System.out.println("Films in database:");
        System.out.println(films);  // Print the films ArrayList
        if (films != null) {
            for (Film film : films) {
                System.out.println(film.getName());
            }
        } else {
            System.out.println("Films ArrayList is null.");
        }

        System.out.println("Series in database:");
        System.out.println(series);  // Print the series ArrayList
       /* if (series != null) {
            for (Serie serie : series) {
                System.out.println(serie.getName());
            }*/
            if(series.isEmpty()){
                System.out.println("it's empty");

        } else {
            System.out.println("Series ArrayList is null.");
        }

        // Call chooseMenu from UserMenu class
        UserMenu userMenu = new UserMenu(textUI, films, series, menu);
        userMenu.chooseMenu();
    }


}
