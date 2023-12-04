import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileIO {
    public void readFilmData(FileLib fileLib) {
        try (BufferedReader reader = new BufferedReader(new FileReader("filmData.txt"))) {
            ArrayList<Film> films = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                // Tjek om der er nok dele i linjen, ellers fortsæt til næste iteration
                if (parts.length != 4) {
                    System.out.println("Ugyldig linje i filen: " + line);
                    continue;
                }

                // Opret en Film-objekt baseret på data fra filen
                Film film = new Film(parts[0], Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]));

                // Tilføj filmen til listen
                films.add(film);
            }

            // Opdater FileLib med filmene
            fileLib.setFilms(films);
        } catch (IOException e) {
            System.err.println("Fejl under læsning af filmdata: " + e.getMessage());
        }
    }

    public void readSerieData(FileLib fileLib) {
        try (BufferedReader reader = new BufferedReader(new FileReader("serieData.txt"))) {
            ArrayList<Serie> series = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                // Tjek om der er nok dele i linjen, ellers fortsæt til næste iteration
                if (parts.length != 5) {
                    System.out.println("Ugyldig linje i filen: " + line);
                    continue;
                }

                // Opret en Serie-objekt baseret på data fra filen
                Serie serie = new Serie(parts[0], parts[1], parseCategories(parts[2]), Double.parseDouble(parts[3]), parseSeasons(parts[4]));

                // Tilføj serien til listen
                series.add(serie);
            }

            // Opdater FileLib med serierne
            fileLib.setSeries(series);
        } catch (IOException e) {
            System.err.println("Fejl under læsning af seriedata: " + e.getMessage());
        }
    }

    private ArrayList<String> parseCategories(String categoriesString) {
        // Implementer logik til at opdele kategorier fra en streng
        // Eksempel: return new ArrayList<>(Arrays.asList(categoriesString.split(",")));
        return new ArrayList<>(Arrays.asList(categoriesString.split(",")));
    }

    private ArrayList<Season> parseSeasons(String seasonsString) {
        // Implementer logik til at opdele sæsoner fra en streng
        // Eksempel: return new ArrayList<>(Arrays.asList(seasonsString.split(",")));
        return new ArrayList<>(Arrays.asList(seasonsString.split(",")));
    }
}
