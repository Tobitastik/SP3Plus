import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserMenu {
    private TextUI ui;
    private Media media;
    private ArrayList<Serie> series;
    private ArrayList<Film> readFilmData = new ArrayList<>();
    private Menu menu;
    public User user;
    FileLib fl = new FileLib();
    private static DBIO dbio = new DBIO();

    public UserMenu(TextUI ui, ArrayList<Film> films, ArrayList<Serie> series, Menu menu) {
        this.ui = ui;
        this.series = series;
        this.readFilmData = films;
        this.media = new Media(ui);
        this.menu = menu;
    }


    public void chooseMenu() {
        int choice;
/*
        int a = ui.getNumericInput("1 for Database 2 for Files");
        switch (a) {
            case 1:

            break;
        }


 */

        int b = ui.getNumericInput("1 for Film 2 for Serie");
        switch (b) {
            case 1:
                Runner();
                break;
            case 2:
                displayMenu();
                choice = ui.getNumericInput("Chose your search method");
                switch (choice) {
                    case 1:
                        searchForSerieName();
                        break;
                    case 2:
                        searchForSerieYear();
                        break;
                    case 3:
                        searchForCategories();
                        break;
                    case 4:
                        searchForRating();
                        break;
                    case 5:
                        System.out.println("Work in progress");
                        //searchForSeason();
                        break;
                    case 0:
                        System.out.println("Exiting menu");
                        break;
                    default:
                        System.out.println("Invalid choice, try again please");
                        break;
                }
                break;

            default:
                System.out.println("Please press 1 or 2");
                break;
        }
    }


    private void displayMenu() {
        System.out.println("1. Search by name");
        System.out.println("2. Search by year");
        System.out.println("3. Search by categories");
        System.out.println("4. Search by rating");
        System.out.println("5. Search by season");
    }

    private void searchForSerieName() {
        String searchName = ui.getInput("Enter name search:");
        ArrayList<Serie> searchResults = new ArrayList<>();

        for (Serie serie : series) {
            if (serie.getName().toLowerCase().contains(searchName.toLowerCase())) {
                searchResults.add(serie);
            }
        }
        displaySearchResults(searchResults);

        if (!searchResults.isEmpty()) {
            int selectedSerieIndex = ui.getNumericInput("Chose serie based on the number");

            if (selectedSerieIndex >= 1 && selectedSerieIndex <= searchResults.size()) {
                Serie selectedSerie = searchResults.get(selectedSerieIndex - 1);
                System.out.println(selectedSerie.getName());
                media.playSerieOrSave();

            } else {
                System.out.println("Please enter valid number");
            }
        }
    }

    private void searchForSerieYear() {
        int searchYear = ui.getNumericInput("Enter year:");
        ArrayList<Serie> searchResults = new ArrayList<>();

        for (Serie serie : series) {
            String yearString = serie.getYear();
            if (yearString.contains("-")) {
                String[] yearRange = yearString.split("-");
                int startYear = Integer.parseInt(yearRange[0].trim());

                if (yearRange.length > 1 && !yearRange[1].trim().equals("")) {
                    int endYear = Integer.parseInt(yearRange[1].trim());
                    if (searchYear >= startYear && searchYear <= endYear) {
                        searchResults.add(serie);
                    }
                } else {

                    searchResults.add(serie);
                }
            } else {
                if (yearString.contains(Integer.toString(searchYear))) {
                    searchResults.add(serie);
                }
            }
        }

        displaySearchResults(searchResults);

        if (!searchResults.isEmpty()) {
            int selectedSerieIndex = ui.getNumericInput("Chose serie based on the number");

            if (selectedSerieIndex >= 1 && selectedSerieIndex <= searchResults.size()) {
                Serie selectedSerie = searchResults.get(selectedSerieIndex - 1);
                System.out.println(selectedSerie.getName());
                media.playSerieOrSave();

            } else {
                System.out.println("Please enter valid number");
            }
        }
    }

    private void searchForCategories() {
        String searchCategory = ui.getInput("Enter category search:");
        ArrayList<Serie> searchResults = new ArrayList<>();

        for (Serie serie : series) {
            for (String category : serie.getCategories()) {
                if (category.toLowerCase().contains(searchCategory.toLowerCase())) {
                    searchResults.add(serie);
                    break;
                }
            }
        }

        displaySearchResults(searchResults);

        if (!searchResults.isEmpty()) {
            int selectedSerieIndex = ui.getNumericInput("Choose serie based on the number");

            if (selectedSerieIndex >= 1 && selectedSerieIndex <= searchResults.size()) {
                Serie selectedSerie = searchResults.get(selectedSerieIndex - 1);
                System.out.println(selectedSerie.getName());
                media.playSerieOrSave();
            } else {
                System.out.println("Please enter a valid number");
            }
        }
    }

    private void searchForRating() {
        double searchRating = ui.getDoubleInput("Enter minimum rating:");

        ArrayList<Serie> searchResults = new ArrayList<>();

        for (Serie serie : series) {
            if (serie.getRating() >= searchRating) {
                searchResults.add(serie);
            }
        }

        // MÅSKE ÆNDRE TIL AT DEN KUN SØGER EFTER DET RATING INPUT SOM MAN INDSKRIVER I KONSOLLEN

        displaySearchResults(searchResults);

        if (!searchResults.isEmpty()) {
            int selectedSerieIndex = ui.getNumericInput("Choose serie based on the number");

            if (selectedSerieIndex >= 1 && selectedSerieIndex <= searchResults.size()) {
                Serie selectedSerie = searchResults.get(selectedSerieIndex - 1);
                System.out.println(selectedSerie.getRating());
            } else {
                System.out.println("Please enter a valid number");
            }
        } else {
            System.out.println("No series found with a rating equal to or higher than " + searchRating);
        }


    }



    /*
   private void searchForSeason() {
        int searchSeason = ui.getNumericInput("Enter season search:");
        ArrayList<Serie> searchResults = new ArrayList<>();

        for (Serie serie : series) {
            for (Season season : serie.getSeason()) {
                int seasonNumber;
                try {
                    seasonNumber = Integer.parseInt(season.getNumberOfSeasons());
                } catch (NumberFormatException e) {
                    continue;
                }

                if (seasonNumber == searchSeason) {
                    searchResults.add(serie);
                    break;
                }
            }
        }

        displaySearchResults(searchResults);

        if (!searchResults.isEmpty()) {
            int selectedSerieIndex = ui.getNumericInput("Choose serie based on the number");

            if (selectedSerieIndex >= 1 && selectedSerieIndex <= searchResults.size()) {
                Serie selectedSerie = searchResults.get(selectedSerieIndex - 1);
                System.out.println(selectedSerie.getName());
                media.playSerieOrSave();
            } else {
                System.out.println("Please enter a valid number");
            }
        }
    }*/

    private void displaySearchResults(ArrayList<? extends MediaInterface> searchResults) {
        int index = 0;
        if (searchResults.isEmpty()) {
            System.out.println("No media match found");
        } else {
            System.out.println("Search results:");
            for (MediaInterface media : searchResults) {
                System.out.println(index + 1);
                System.out.println(media.display());
                index++;
                System.out.println();
            }
        }
    }

    public void Runner() {
        readFilmData = fl.getFilms(); // Kalder på readFilmData Arraylisten for filmene fra FileIO klassen
        input();

    }

    /*
        private void MovieList() // listen over alle filmene
        {
            String a = "\nmovie list:\n";
            for (Film films : readFilmData) {
                a = a.concat(films.toString() + "\n");
            }
            ui.getInput(a);
        }
     */

    private void input() // input af tekst i console om hvad man vil søge efter, samt om man finder en film/serie af det man søger efter
    {
        Scanner scanner = new Scanner(System.in);


        FilmCollection filmCollection = new FilmCollection(readFilmData);

        System.out.println("Enter the type you will search for (Files: Name/Year/Category/Rating): ");
        String searching = scanner.nextLine();

        if ("Name".equalsIgnoreCase(searching)) {
            System.out.println("Enter Name: ");
            String searchName = scanner.nextLine();
            filmCollection.searchByName(searchName);
        } else if ("Year".equalsIgnoreCase(searching)) {
            System.out.println("Enter Year: ");
            int searchAge = scanner.nextInt();
            filmCollection.searchByAge(searchAge);
        } else if ("Rating".equalsIgnoreCase(searching)) {
            System.out.println("Enter Rating: ");
            double searchRating = scanner.nextDouble();
            filmCollection.searchByRating(searchRating);
        } else if ("Category".equalsIgnoreCase(searching)) {
            System.out.println("Enter Category: ");
            String searchCat = scanner.nextLine();
            filmCollection.searchByCat(searchCat);
        } else {
            System.out.println("Invalid");

        }
        scanner.close();
    }

    static class FilmCollection { // arraylist af film i FilmCollection klassen
        private final ArrayList<Film> readFilmData;

        public FilmCollection(ArrayList<Film> readFilmData) { // initializer readFilmData Arraylisten
            this.readFilmData = readFilmData;

        }


        public void searchByAge(int searchAge) // søger for udgivelsesdatoen af filmene
        {

            TextUI ui = new TextUI();

            boolean found = false;

            System.out.println("\nResults for search: " + searchAge);

            for (Film film : readFilmData) {
                if (film.getYear() == searchAge) {
                    System.out.println(film);
                    found = true;
                }
            }

            if (!found) {
                String userInput = ui.getInput("No films found: " + searchAge);
                System.out.println(userInput);
            }
        }

        public void searchByCat(String searchCategory) // søger efter categori af filmene
        {

            TextUI ui = new TextUI();

            boolean found = false;

            System.out.println("\nResults for search: " + searchCategory);

            for (Film film : readFilmData) {
                if (film.getCategories().contains(searchCategory)) {
                    System.out.println(film);
                    found = true;
                }
            }


            if (!found) {
                String userInput = ui.getInput("No films found: " + searchCategory);
                System.out.println(userInput);
            }
        }

        public void searchByRating(double searchRating) // søger for rating af filmene
        {

            TextUI ui = new TextUI();

            boolean found = false;

            System.out.println("\nResults for search: " + searchRating);


            for (Film film : readFilmData) {
                if (film.getRating() == searchRating) {
                    System.out.println(film);
                    found = true;
                }
            }


            if (!found) {
                String userInput = ui.getInput("No films found: " + searchRating);
                System.out.println(userInput);
            }
        }


        public void searchByName(String searchName) // bruges til at søge efter navn af filmene
        {
            TextUI ui = new TextUI();

            String lowerCaseSearchName = searchName.toLowerCase();

            boolean found = false;

            System.out.println("\nResults for search: " + searchName);


            for (Film film : readFilmData) {
                if (film.getName().toLowerCase().contains(lowerCaseSearchName)) {
                    System.out.println(film);
                    found = true;
                }
            }


            if (!found) {

                String userInput = ui.getInput("No films found");
                System.out.println(userInput + searchName);
            }
        }


    }


}
