import java.util.ArrayList;
import java.util.Scanner;

public class UserMenu {
    private TextUI textUI;
    private ArrayList<Serie> series;
    private ArrayList<Film> readFilmData;
    private ArrayList<Serie> readSerieData;
    private SearchManager searchManager;
    private FileLib fileLib;

    public UserMenu(TextUI ui, ArrayList<Film> films, ArrayList<Serie> series, SearchManager searchManager, FileLib fileLib) {
        this.textUI = ui;
        this.series = series;
        this.readFilmData = films;
        this.searchManager = searchManager;
        this.fileLib = fileLib;
    }

    public void chooseIOProvider() {
        int choice = textUI.getNumericInput("Vil du gøre brug af FileIO eller DBIO? Tryk 1 for FileIO og 2 for DBIO:");

        if (choice == 1) {
            new FileIO().readFilmData(fileLib);
            new FileIO().readSerieData(fileLib);
        } else if (choice == 2) {
            // Implementer din DBIO-logik her, hvis nødvendigt
        } else {
            System.out.println("Ugyldigt valg. Prøv igen.");
            chooseIOProvider();
        }
    }

    public void chooseMenu() {
        int b = textUI.getNumericInput("1 for Film 2 for Serie");
        switch (b) {
            case 1:
                Runner();
                break;
            case 2:
                displayMenu();
                int choice = textUI.getNumericInput("Chose your search method");
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
                        searchForSeason();
                        break;
                    case 0:
                        System.out.println("Exiting menu");
                        break;
                    default:
                        System.out.println("Invalid choice, please try again");
                        break;
                }
                break;
            default:
                System.out.println("Invalid choice, please try again");
                chooseMenu();
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
        String searchName = textUI.getInput("Enter name search:");
        ArrayList<Serie> searchResults = new ArrayList<>();

        for (Serie serie : series) {
            if (serie.getName().toLowerCase().contains(searchName.toLowerCase())) {
                searchResults.add(serie);
            }
        }
        displaySearchResults(searchResults);

        if (!searchResults.isEmpty()) {
            int selectedSerieIndex = textUI.getNumericInput("Chose serie based on the number");

            if (selectedSerieIndex >= 1 && selectedSerieIndex <= searchResults.size()) {
                Serie selectedSerie = searchResults.get(selectedSerieIndex - 1);
                System.out.println(selectedSerie.getName());
                //media.playSerieOrSave();

            } else {
                System.out.println("Please enter valid number");
            }
        }
    }

    private void searchForSerieYear() {
        int searchYear = textUI.getNumericInput("Enter year:");
        ArrayList<Serie> searchResults = new ArrayList<>();

        for (Serie serie : series) {
            String yearString = serie.getYearString();
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
            int selectedSerieIndex = textUI.getNumericInput("Chose serie based on the number");

            if (selectedSerieIndex >= 1 && selectedSerieIndex <= searchResults.size()) {
                Serie selectedSerie = searchResults.get(selectedSerieIndex - 1);
                System.out.println(selectedSerie.getName());
                //media.playSerieOrSave();

            } else {
                System.out.println("Please enter valid number");
            }
        }
    }

    private void searchForCategories() {
        String searchCategory = textUI.getInput("Enter category search:");
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
            int selectedSerieIndex = textUI.getNumericInput("Choose serie based on the number");

            if (selectedSerieIndex >= 1 && selectedSerieIndex <= searchResults.size()) {
                Serie selectedSerie = searchResults.get(selectedSerieIndex - 1);
                System.out.println(selectedSerie.getName());
                //media.playSerieOrSave();
            } else {
                System.out.println("Please enter a valid number");
            }
        }
    }

    private void searchForRating() {
        double searchRating = textUI.getDoubleInput("Enter minimum rating:");

        ArrayList<Serie> searchResults = new ArrayList<>();

        for (Serie serie : series) {
            if (serie.getRating() >= searchRating) {
                searchResults.add(serie);
            }
        }


        displaySearchResults(searchResults);

        if (!searchResults.isEmpty()) {
            int selectedSerieIndex = textUI.getNumericInput("Choose serie based on the number");

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



    private void searchForSeason() {
        int searchSeason = textUI.getNumericInput("Enter season search:");
        ArrayList<Serie> searchResults = new ArrayList<>();

        for (Serie serie : series) {
            for (Season season : serie.getSeason()) {
                int seasonNumber;
                try {
                    seasonNumber = Integer.parseInt(season.getNumberOfSeasons());
                } catch (NumberFormatException e) {
                    continue;
                }

                if (searchSeason == seasonNumber) {
                    searchResults.add(serie);
                    break;
                }
            }
        }

        displaySearchResults(searchResults);

        if (!searchResults.isEmpty()) {
            int selectedSerieIndex = textUI.getNumericInput("Choose serie based on the number");

            if (selectedSerieIndex >= 1 && selectedSerieIndex <= searchResults.size()) {
                Serie selectedSerie = searchResults.get(selectedSerieIndex - 1);
                System.out.println(selectedSerie.getName());
                //media.playSerieOrSave();
            } else {
                System.out.println("Please enter a valid number");
            }
        }
    }

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
        input();

    }


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