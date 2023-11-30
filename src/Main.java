import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        //FileIO io = new FileIO();
        //TextUI ui = new TextUI();

        //String filePathF = "data/100bedstefilm.txt";
       // String filePathS = "data/100bedsteserier.txt";
        //String filePathA = "data/accounts.txt";
/*
        System.out.println("Film.get(20): "+Arrays.toString(io.readFilmData().get(20)));
        System.out.println("Serie.get(20): "+Arrays.toString(io.readSerieData().get(2)));
        System.out.println("accounts.size "+ io.readAccountData().size());
        System.out.println(io.readAccountData().get(2));
*/
       // FileIO io = new FileIO();

        //io.readFilmData();


        TextUI ui = new TextUI();
        FileIO io = new FileIO();
        ArrayList<Serie> series = io.readSerieData();
        ArrayList<Film> films = io.readFilmData();
        Menu menu = new Menu(films, series);
        UserMenu userMenu = new UserMenu(ui, films, series, menu);
        userMenu.chooseMenu();
        /*
        UserMenu userMenu = new UserMenu(ui, films, series);
        userMenu.chooseMenu(ui, films, series);*/
        //userMenu.searchForSerieName();

       //new Test();

   // User user = new User("Morten");
  //  ArrayList<String> watchedList = user.getWatched();
   // ArrayList<String> savedList = user.getSaved();

  //  watchedList.add("Test");
   // savedList.add("Homeward bound");

    //System.out.println(watchedList);
   // System.out.println(savedList);

    //ArrayList<User> acc = io.readUsersFromFile("data/accounts.txt");

    //io.displayUsers(ArrayList<User> user);



    }
}