import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    private Scanner scan = new Scanner(System.in);
    //private FileIO fileIO = new FileIO();

    // Viser en besked og returnerer brugerens input som en streng
    public String getInput(String msg) {
        System.out.println(msg);
        return scan.nextLine();
    }

    // Viser en besked, beder om numerisk input og returnerer brugerens valg i form af et tal.
    public int getNumericInput(String msg) {
        System.out.println(msg);

        String input = scan.nextLine();
        int num;

        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Dette var ikke et tal. " + e.getMessage());
            num = getNumericInput(msg);
        }

        return num;
    }

    public int getDoubleInput(String msg) {
        System.out.println(msg);

        String input = scan.nextLine();
        int num;

        try {
            num = (int) Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Dette var ikke et tal. " + e.getMessage());
            num = getDoubleInput(msg);
        }

        return num;
    }

    /*
    Viser en besked, lister indholdet af en liste og returnerer brugerens valg
     */
    public String getChoice(ArrayList<String> options, String msg) {
        System.out.println(msg);
        displayMenu(options);

        String input = getInput("Vælg en mulighed:");

        // Tjek om input findes i listen, hvis ikke, kast en exception
        if (!options.contains(input)) {
            System.out.println("Findes ikke på listen");
            input = getChoice(options, msg);
        }

        return input;
    }

    // Viser menuen baseret på listen af muligheder
    public void displayMenu(ArrayList<String> options) {
        System.out.println("Menu:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
    /*
    public void displayMessage() {
        ArrayList<Film> films = fileIO.readFilmData();
        ArrayList<Serie> series = fileIO.readSerieData();

        System.out.println("Film:");

        for (Film film : films) {
            System.out.println("Name: " + film.getName());
            System.out.println("Rating: " + film.getRating());
            System.out.println("Year: " + film.getYear());
            System.out.println("Categories: " + film.getCategories());
            System.out.println();
        }

        System.out.println("Serier:");

        for (Serie serie : series) {
            System.out.println("Name: " + serie.getName());
            System.out.println("Rating: " + serie.getRating());
            System.out.println("Age: " + serie.getAge());
            System.out.println("Categories: " + serie.getCategories());
            System.out.println();
        }
    }
}*/



/* Metodekald til TextUI kan findes herunder.
import java.util.ArrayList;

public class TestTextUI {
    public static void main(String[] args) {
        // Lav et objekt af TextUI
        TextUI textUI = new TextUI();

        // Test 1: GetInput-metoden
        String userInput = textUI.getInput("Indtast en streng:");
        System.out.println("Brugerindtastning: " + userInput);

        // Test 2: GetNumericInput-metoden
        int userNumber = textUI.getNumericInput("Indtast et tal:");
        System.out.println("Brugerindtastet tal: " + userNumber);

        // Test 3: GetChoice-metoden
        ArrayList<String> menuOptions = new ArrayList<>();
        menuOptions.add("Valgmulighed 1");
        menuOptions.add("Valgmulighed 2");
        menuOptions.add("Valgmulighed 3");


        String userChoice = textUI.getChoice(menuOptions, "Vælg en mulighed:");
        System.out.println("Brugerens valg: " + userChoice);

        // Test 4: DisplayMenu-metoden
        textUI.displayMenu(menuOptions);

        System.out.println("Testene er fuldført.");
    }*/
}

