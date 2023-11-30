
import java.util.ArrayList;

public class Menu {
    private TextUI ui = new TextUI();
    private ArrayList<User> users = new ArrayList<>();
    private String s = "";
    private ArrayList<Film> films;
    private ArrayList<Serie> series;
    private UserMenu userMenu;

    public Menu(ArrayList<Film> films, ArrayList<Serie> series) {
        this.films = films;
        this.series = series;
        loadUsersFromFile();
        displayMenu();
        userMenu = new UserMenu(ui, films, series, this);
        userMenu.chooseMenu();

    }

    private void loadUsersFromFile(){
        FileIO io = new FileIO();
        users = io.readUsersFromFile();
    }

    public void displayMenu() {
        FileIO io = new FileIO();

        do {
            s = ui.getInput("Press Y to login or N to make a new user");

            if (s != null) {
                switch (s.toLowerCase()) {
                    case "y":
                        newLogin();
                        break;
                    case "n":

                        createUser();
                        break;
                    default:
                        System.out.println("Invalid input please try again");
                }
            } else {
                System.out.println("Error: need input");
            }
        } while (!s.toLowerCase().equals("y") && !s.toLowerCase().equals("n"));

    }

    private void createUser() {
        String newUsername = ui.getInput("Enter new username");
        User newUser = new User(newUsername);
        users.add(newUser);
        System.out.println("User " + newUser.getUsername() + " created");

        FileIO io = new FileIO();
        io.writeUsersToFile(users, "data/accounts.txt");
    }


    private void newLogin() {

        FileIO io = new FileIO();
        ArrayList<User> usersFromFile = io.readUsersFromFile();

        if (usersFromFile.isEmpty()) {
            System.out.println("No users found. Please create a new user.");
            createUser();
            return;
        }

        io.displayUsers(usersFromFile);

        int choice = ui.getNumericInput("Choose your account");

        switch (choice) {
            case 1:
                if (choice <= usersFromFile.size()) {
                    User selectedUser = usersFromFile.get(choice - 1);
                    System.out.println("Welcome " + selectedUser.getUsername());

                    users.add(selectedUser);

                } else {
                    System.out.println("Invalid choice, Please choose another account");
                }
                break;
            default:
                System.out.println("Invalid choice, Please choose another account");
                break;
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}