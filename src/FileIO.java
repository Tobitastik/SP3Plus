import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileIO {

    TextUI ui = new TextUI();

    public void readFilmData(FileLib filmLibrary) {
        File fileF = new File("data/100bedstefilm.txt");

        try (Scanner scanner = new Scanner(fileF)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tempDataF = line.split(";");

                String name = tempDataF[0];
                int year = Integer.parseInt(tempDataF[1].trim());
                String[] categories = tempDataF[2].trim().split(",");
                String ratingString = tempDataF[3].replace(",", ".");
                double rating = Double.parseDouble(ratingString);

                Film film = new Film(name, year, new ArrayList<>(Arrays.asList(categories)), rating);
                filmLibrary.addFilm(film);
            }
        } catch (FileNotFoundException e) {
            ui.getInput("File not found (Film)");
        }
    }


    public void readSerieData(FileLib serieLibrary) {
        File fileS = new File("data/100bedsteserier.txt");

        try (Scanner scanner = new Scanner(fileS)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tempDataS = line.split(";");

                String name = tempDataS[0];
                String yearString = tempDataS[1].trim();
                String[] categories = tempDataS[2].trim().split(",");
                String ratingString = tempDataS[3].replace(",", ".");
                double rating = Double.parseDouble(ratingString);
                String[] seasonArray = tempDataS[4].trim().split(",");

                ArrayList<String> seasonsList = new ArrayList<>(Arrays.asList(seasonArray));
                ArrayList<Season> seasons = new ArrayList<>();

                for (String seasonInfo : seasonsList) {
                    String[] seasonParts = seasonInfo.split("-");
                    String numberOfSeasons = seasonParts[0];
                    int numberOfEpisodes = Integer.parseInt(seasonParts[1]);
                    Season season = new Season(numberOfSeasons, numberOfEpisodes);
                    seasons.add(season);
                }

                Serie serie = new Serie(name, yearString, new ArrayList<>(Arrays.asList(categories)), rating, seasons);
                setStartAndEndYear(serie, yearString);

                serieLibrary.addSerie(serie);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found (Serie)");
        }
    }


    private void setStartAndEndYear(Serie serie, String yearString) {
        if (yearString.contains("-")) {
            String[] yearRange = yearString.split("-");
            serie.setStartYear(yearRange[0].trim());
            if (yearRange.length > 1 && !yearRange[1].trim().equals("")) {
                serie.setEndYear(yearRange[1].trim());
            } else {
                serie.setEndYear("2023");
            }
        } else {
            serie.setStartYear(yearString);
            serie.setEndYear(yearString);
        }
    }

    public void writeUsersToFile(ArrayList<User> users, String path) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            for (User user : users) {
                writer.write(user.getUsername());
                writer.write(";");

                writer.write("Watched: ");

                writer.write("Watched Movies:");
                for (Film film : user.getFilmWatched()) {
                    writer.write(film.getName());
                    writer.write(",");
                }

                writer.write(";Saved Movies:");
                for (Film film : user.getFilmSaved()) {
                    writer.write(film.getName());
                    writer.write(",");
                }

                writer.write(";Watched Series");
                for (Serie serie : user.getSerieWatched()) {
                    writer.write(serie.getName());
                    writer.write(",");
                }

                writer.write(";Saved Series");
                for (Serie serie : user.getSerieSaved()) {
                    writer.write(serie.getName());
                    writer.write(",");
                }
                writer.write(",");
                writer.newLine();
            }

            System.out.println("Account updated");

        } catch (IOException e) {
            System.out.println("Error updating accounts");
        }
    }

    public Film createFilm(String filmName) {
        return new Film(filmName, 2023, new ArrayList<>(), 0.0);
    }

    public Serie createSerie(String serieName) {

        return new Serie(serieName, "Year", new ArrayList<>(), 0.0, new ArrayList<>());
    }

    public ArrayList<User> readUsersFromFile() {
        return readUsersFromFile("data/accounts.txt");
    }


    //readUsersFromFile virker ikke som den skal
    public ArrayList<User> readUsersFromFile(String path) {
        ArrayList<User> users = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String userLine = scanner.nextLine();
                String[] tempUser = userLine.split(";");


                if (tempUser.length >= 2) {
                    String username = tempUser[0];


                    String watchedMoviesSection = tempUser[1].substring("Watched Movies:".length());
                    String[] watchedMoviesData = watchedMoviesSection.split(",");
                    ArrayList<Film> watchedMovies = new ArrayList<>();
                    for (String movieName : watchedMoviesData) {
                        Film movie = createFilm(movieName.trim());
                        if (movie != null) {
                            watchedMovies.add(movie);
                        }
                    }

                    User newUser = new User(username);

                    users.add(newUser);
                } else {

                    System.out.println("Warning: Invalid user line - " + userLine);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found (" + path + ")");
        }

        return users;
    }

    public void displayUsers(ArrayList<User> users){
        System.out.println("Users:");
        for(User user : users){
            System.out.println(user.getUsername());
        }
    }

}