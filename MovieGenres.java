package moviegenres;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MovieGenres {

    public static void main(String[] args) throws IOException { 
        
        // creates arraylist 
        ArrayList<Movie> movies = new ArrayList<>();
        HashMap<String, Integer> genreCount = new HashMap<>();
        HashMap<Integer, Integer> yearCount = new HashMap<>();

        // reads the csv file
        File file = new File("movies.csv");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine();
        String line = reader.readLine();
        
        while(line != null) {
            // Splits the csv file
            String[] arr = line.split(",");

            // title is at index 
            String name = arr[1];

            // for the entries that have a comma within there title
            if(name.charAt(0) == '\"') {
                int counter = 2;
                while(name.indexOf("\"") == name.lastIndexOf("\"")) {
                    name += arr[counter++];
                }
            }

            // year is encased within () 
            int year = Integer.parseInt(name.substring(name.lastIndexOf("(") + 1, name.lastIndexOf(")")));
            
            // removes the year that is in the titles
            name = name.substring(0, name.lastIndexOf("(")).replace("\"", "");
            
            // genres is stored in the last index of the array
            String genre = arr[arr.length - 1];

            // splits genres by '|'
            String[] genres = genre.split("\\|");

            // adds each genres into the hashmap and increments the value by 1
            for(String s : genres) {
                genreCount.put(s, genreCount.getOrDefault(s, 0) + 1);
            }

            // adds each year into the hashmap and increments the value by 1
            yearCount.put(year, yearCount.getOrDefault(year, 0) + 1);

            Movie movie = new Movie(name, year, genres);
            movies.add(movie);
            
            line = reader.readLine();
        }

        // closes the bufferedreader
        reader.close();

        // prints out the movie genre counts
        System.out.println("Movies Genres: ");
        genreCount.keySet().stream().forEach((genre) -> {
            System.out.println(genre + " has " + genreCount.get(genre) + " movies");
        });

        // prints out the movie year releases
        System.out.println("Movie Year Releases: ");
        yearCount.keySet().stream().forEach((year) -> {
            System.out.println(year + " had " + yearCount.get(year) + " movies come out");
        });

    }

}

class Movie {

    // instances variables
    private String title;
    private int releaseYear;
    private String[] genres;

    // constructor
    public Movie(String title, int releaseYear, String[] genres) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genres = genres;
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }


}



    
   