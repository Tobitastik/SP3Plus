import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    private Scanner scan = new Scanner(System.in);

    public String getInput(String msg) {
        System.out.println(msg);
        return scan.nextLine();
    }

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

    public double getDoubleInput(String msg) {
        System.out.println(msg);

        String input = scan.nextLine();
        double num;  // Ændret til double

        try {
            num = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Dette var ikke et tal. " + e.getMessage());
            num = getDoubleInput(msg);
        }

        return num;
    }

    public String getChoice(ArrayList<String> options, String msg) {
        System.out.println(msg);
        displayMenu(options);

        String input = getInput("Vælg en mulighed:");

        if (!options.contains(input)) {
            System.out.println("Findes ikke på listen");
            input = getChoice(options, msg);
        }

        return input;
    }

    public void displayMenu(ArrayList<String> options) {
        System.out.println("Menu:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}
