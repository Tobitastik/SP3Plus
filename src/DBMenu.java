import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class DBMenu {
    public static DBIO dbio = new DBIO();
    public ArrayList<Film> readFilmDataDB;

    public void DBRunner() {
        readFilmDataDB = dbio.readFilmDataDB();
        input();
    }

    private void input() // input af tekst i console om hvad man vil søge efter, samt om man finder en film/serie af det man søger efter
    {
        Scanner scanner = new Scanner(System.in);


        FilmCollection filmCollection = new FilmCollection(readFilmDataDB);

        System.out.println("Enter the type you will search for (Database: Name/Year/Category/Rating): ");
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
        private final ArrayList<Film> readFilmDataDB;

        public FilmCollection(ArrayList<Film> readFilmDataDB) { // initializer readFilmData Arraylisten
            this.readFilmDataDB = readFilmDataDB;

        }

        TextUI ui = new TextUI();

        public void searchByName(String searchName) {


            String lowerCaseSearchName = searchName.toLowerCase();

            boolean found = false;

            System.out.println("\nResults for search: " + searchName);

            try {
                Connection connection = dbio.connect();

                String query = "SELECT * FROM movie WHERE title LIKE '%" + searchName + "%'";

                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    while (resultSet.next()) {
                        String title = resultSet.getString("title");
                        int year = resultSet.getInt("year");
                        String genre = resultSet.getString("genre");
                        double rating = resultSet.getDouble("rating");

                        System.out.println("Title: " + title + ", Year: " + year + ", Genre: " + genre + ", Rating: " + rating);
                        found = true;
                    }
                }

                dbio.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!found) {
                String userInput = ui.getInput("No films found");
                System.out.println(userInput + searchName);
            }
        }

        public void searchByAge(int searchAge) {

            boolean found = false;

            System.out.println("\nResults for search: " + searchAge);
            String query = "SELECT * FROM movie WHERE year LIKE '%" + searchAge + "%'";

            try {
                Connection connection = dbio.connect();

                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    while (resultSet.next()) {
                        String title = resultSet.getString("title");
                        int year = resultSet.getInt("year");
                        String genre = resultSet.getString("genre");
                        double rating = resultSet.getDouble("rating");

                        System.out.println("Title: " + title + ", Year: " + year + ", Genre: " + genre + ", Rating: " + rating);
                        found = true;
                    }
                }

                dbio.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!found) {
                String userInput = ui.getInput("No films found: " + searchAge);
                System.out.println(userInput);
            }
        }

        public void searchByCat(String searchCategory) {

            boolean found = false;

            System.out.println("\nResults for search: " + searchCategory);
            String query = "SELECT * FROM movie WHERE genre LIKE '%" + searchCategory + "%'";

            try {
                Connection connection = dbio.connect();

                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    while (resultSet.next()) {
                        String title = resultSet.getString("title");
                        int year = resultSet.getInt("year");
                        String genre = resultSet.getString("genre");
                        double rating = resultSet.getDouble("rating");

                        System.out.println("Title: " + title + ", Year: " + year + ", Genre: " + genre + ", Rating: " + rating);
                        found = true;
                    }
                }

                dbio.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!found) {
                String userInput = ui.getInput("No films found: " + searchCategory);
                System.out.println(userInput);
            }
        }

        public void searchByRating(double searchRating) {

            boolean found = false;

            System.out.println("\nResults for search: " + searchRating);
            String query = "SELECT * FROM movie WHERE rating LIKE '%" + searchRating + "%'";

            try {
                Connection connection = dbio.connect();

                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    while (resultSet.next()) {
                        String title = resultSet.getString("title");
                        int year = resultSet.getInt("year");
                        String genre = resultSet.getString("genre");
                        double rating = resultSet.getDouble("rating");

                        System.out.println("Title: " + title + ", Year: " + year + ", Genre: " + genre + ", Rating: " + rating);
                        found = true;
                    }
                }

                dbio.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!found) {
                String userInput = ui.getInput("No films found: " + searchRating);
                System.out.println(userInput);
            }
        }
    }
}