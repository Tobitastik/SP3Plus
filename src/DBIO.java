import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class DBIO {

    private static final String JDBC_URL = "jdbc:mysql://localhost/my_streaming";
    private static final String USER = "root";
    private static final String PASSWORD = "b4U}]ADKqGcD86";

    private static Connection connection;


    public static Connection connect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            System.out.println("Connected to the database.");
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readFilmDataDB(FileLib filmLibrary) {
        Connection conn = null;
        PreparedStatement stmt = null;


        try {
            conn = connect();

            String sql = "SELECT name, year, category, rating FROM movie";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                int year = rs.getInt("Year");
                String[] categoriesArray = rs.getString("Category").split(",");
                double rating = rs.getDouble("Rating");

                ArrayList<String> categories = new ArrayList<>(Arrays.asList(categoriesArray));

                Film film = new Film(name, year, categories, rating);
                filmLibrary.addFilmDB(film);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            disconnect();
        }
    }


    public void readSerieDataDB(FileLib serieLibrary){
        Connection conn = null;
        PreparedStatement stmt = null;


        try {
            conn = connect();

            String sql = "SELECT name, year, category, rating, season FROM serie";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                String year = rs.getString("Year");
                String[] categoriesArray = rs.getString("Category").split(",");
                double rating = rs.getDouble("Rating");
                String[] seasonsArray = rs.getString("Season").split("\\.");

                ArrayList<String> categories = new ArrayList<>(Arrays.asList(categoriesArray));
                ArrayList<Season> seasons = new ArrayList<>();

                for (String seasonInfo : seasonsArray) {
                    String[] seasonParts = seasonInfo.split("-");
                    String numberOfSeasons = seasonParts[0];
                    int numberOfEpisodes = Integer.parseInt(seasonParts[1]);
                    Season season = new Season(numberOfSeasons, numberOfEpisodes);
                    seasons.add(season);
                }

                Serie serie = new Serie(name, year, categories, rating, seasons);
                serieLibrary.addSerieDB(serie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            disconnect();
        }
    }




    public void displayNames() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = connect();

            String sql = "SELECT Name FROM serie";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                String name = rs.getString("Name");
                System.out.println(name);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            disconnect();
        }
    }

}