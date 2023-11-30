import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Media {
    private User user;
    private TextUI textUI = new TextUI();

    public Media(TextUI textUI){
        this.textUI = textUI;
    }
    public String playFilmOrSave() {
        System.out.println("1. Play");
        System.out.println("2. Save");

        int choice = textUI.getNumericInput("Vælg en mulighed:");

        if (choice == 1) {
            return "Play";
        } else if (choice == 2) {
            return "Save";
        } else {
            return "Ugyldigt valg";
        }
    }

    public void playSerieOrSave() {
        System.out.println("1. Play");
        System.out.println("2. Save");

        int choice = textUI.getNumericInput("Choose an option:");

        if (choice == 1) {
            int season = textUI.getNumericInput("Choose season:");
            int episode = textUI.getNumericInput("Choose episode:");

            System.out.println("Play Serie - Season " + season + ", Episode " + episode);

            Serie watchedSerie = new Serie("Watched Serie", "Year", new ArrayList<>(), 0, new ArrayList<>());
            Season watchedSeason = new Season(Integer.toString(season), new ArrayList<>(Arrays.asList(episode)));
            watchedSerie.getSeason().add(watchedSeason);

            System.out.println("Debug: User information - " + user);

            if (user != null) {

                String serieName = watchedSerie.getName();



                FileIO io = new FileIO();
                io.writeUsersToFile(new ArrayList<>(Arrays.asList(user)), "data/accounts.txt");
            } else {
                System.out.println("User not found. Cannot add watched series.");
            }

        } else if (choice == 2) {
            System.out.println("Save");
        } else {
            System.out.println("Invalid choice");
        }
    }


    //gemmes i tilfælde af den anden ikke virker spoiler it doesn't
   /* public void playSerieOrSave() {
        System.out.println("1. Play");
        System.out.println("2. Save");

        int choice = textUI.getNumericInput("Vælg en mulighed:");

        if (choice == 1) {
            int season = textUI.getNumericInput("Vælg sæson:");
            int episode = textUI.getNumericInput("Vælg episode:");

            System.out.println("Play Serie - Sæson " + season + ", Episode " + episode);

            Serie watchedSerie = new Serie("Watched Serie", "Year", new ArrayList<>(), 0, new ArrayList<>());
            Season watchedSeason = new Season(Integer.toString(season), new ArrayList<>(Arrays.asList(episode)));
            watchedSerie.getSeason().add(watchedSeason);

            User user = this.user;
            if (user != null){
                user.getSerieWatched().add(watchedSerie);
            }
            user.getSerieWatched().add(watchedSerie);

            FileIO io = new FileIO();
            io.writeUsersToFile(new ArrayList<>(Arrays.asList(user)), "data/accounts.txt");

        } else if (choice == 2) {
            System.out.println("Save");
        } else {
            System.out.println("Ugyldigt valg");
        }
    }*/
}