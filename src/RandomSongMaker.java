

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@SuppressWarnings("Duplicates")
public class RandomSongMaker {


    //Create array list for data
    private ArrayList<String> firstNames, lastNames, actionWords, business, buzzWords, colors, frequencyWords, languages, adjectives;
    private ArrayList<String> bandTitle = new ArrayList<>();
    private ArrayList<String> albumTitle = new ArrayList<>();
    private ArrayList<String> songTitle = new ArrayList<>();
    private ArrayList<Song> randomSongs = new ArrayList<>();

    private final String OUTPUT_FILE_NAME = "randomSongs.txt";

    //Number of songs, artist, and albums to create
    private final int MAX_ALBUM_COUNT = 10;
    private final int MIN_ALBUM_COUNT = 1;
    private final int MAX_SONG_COUNT = 25;
    private final int MIN_SONG_COUNT = 3;
    private final int MAX_PLAY_COUNT = 50;
    private final int MAX_LENGTH = 36000;
    private final int MIN_LENTH = 9000;
    //This is approx because number of albums and songs is random and this value each time a new artist is check
    //Thus, it will be an extra ~5-25 songs depending on the run
    private final int DESIRED_SONG_COUNT = 10000;

    public RandomSongMaker() {

        //Init arrayList
        lastNames = new ArrayList<>();
        firstNames = new ArrayList<>();
        actionWords = new ArrayList<>();
        business = new ArrayList<>();
        buzzWords = new ArrayList<>();
        colors = new ArrayList<>();
        frequencyWords = new ArrayList<>();
        languages = new ArrayList<>();
        adjectives = new ArrayList<>();
    }


    public boolean writeFile(){
        try{
            File outputFile = new File(OUTPUT_FILE_NAME);
            FileWriter writer = new FileWriter(outputFile.getAbsoluteFile());
            BufferedWriter buffWriter = new BufferedWriter(writer);
            for (Song song : randomSongs) {
                buffWriter.write(song.toString());
                buffWriter.newLine();
            }
            buffWriter.close();


        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        return true;

    }
    public void loadRandomData() {
        //A class to load all arrayList with songs.
        try (Stream<String> stream = Files.lines(Paths.get("firstNames.txt"))) {
            stream.forEach(s -> firstNames.add(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (Stream<String> stream = Files.lines(Paths.get("lastNames.txt"))) {
            stream.forEach(s -> lastNames.add(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (Stream<String> stream = Files.lines(Paths.get("actionWords.txt"))) {
            stream.forEach(s -> actionWords.add(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (Stream<String> stream = Files.lines(Paths.get("business.txt"))) {
            stream.forEach(s -> business.add(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (Stream<String> stream = Files.lines(Paths.get("buzzWords.txt"))) {
            stream.forEach(s -> buzzWords.add(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (Stream<String> stream = Files.lines(Paths.get("colors.txt"))) {
            stream.forEach(s -> colors.add(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (Stream<String> stream = Files.lines(Paths.get("frequencyWords.txt"))) {
            stream.forEach(s -> frequencyWords.add(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (Stream<String> stream = Files.lines(Paths.get("languages.txt"))) {
            stream.forEach(s -> languages.add(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (Stream<String> stream = Files.lines(Paths.get("adjectives.txt"))) {
            stream.forEach(s -> adjectives.add(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void makeArtistSet() {
        //Create songs and save to a text file
        //Create artists as they're out top level item
        while(randomSongs.size() < DESIRED_SONG_COUNT){
            String artist = makeArtist();
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(MIN_ALBUM_COUNT, MAX_ALBUM_COUNT) ; j++) {
                String album = makeAlbum();
                for (int k = 0; k < ThreadLocalRandom.current().nextInt(MIN_SONG_COUNT, MAX_SONG_COUNT); k++) {
                    String songTitle = makeSong();
                    Song songObj = new Song();
                    songObj.setArtist(artist);
                    songObj.setAlbum(album);
                    songObj.setTitle(songTitle);
                    songObj.setPathToFile(makePath());
                    songObj.setPlayCount(makePlaycount());
                    songObj.setThumbed(makeThumb());
                    songObj.setLength(makeLength());
                    songObj.setPathToAlbumCover(makePathToAlbumCover());

                    //Add song to arrayList
                    randomSongs.add(songObj);
                }
            }
        }

    }

    public ArrayList<Song> getRandomSongsList(){
        return randomSongs;
    }

    public String makePathToAlbumCover(){
        String path = "";
        String base = "albumCover";
        int choice = ThreadLocalRandom.current().nextInt(1, 25);
        path += base;
        path += choice;
        path += ".jpg";
        return path;
    }

    public String makeArtist() {
        String artist = "";
        //We will make 3 "styles" of artist names
        int styleChoice = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        if (styleChoice == 1) {
            // Frequency + Color + language
            artist += frequencyWords.get(ThreadLocalRandom.current().nextInt(0, frequencyWords.size()));
            artist += " ";
            artist += colors.get(ThreadLocalRandom.current().nextInt(0, colors.size()));
            artist += " ";
            artist += languages.get(ThreadLocalRandom.current().nextInt(0, languages.size()));
        }
        if (styleChoice == 2) {
            // Adjectives + color + Last names
            artist += adjectives.get(ThreadLocalRandom.current().nextInt(0, adjectives.size()));
            artist += " ";
            artist += colors.get(ThreadLocalRandom.current().nextInt(0, colors.size()));
            artist += " ";
            artist += lastNames.get(ThreadLocalRandom.current().nextInt(0, lastNames.size()));
        }
        if (styleChoice == 3) {
            //Language + Color
            artist += languages.get(ThreadLocalRandom.current().nextInt(0, languages.size()));
            artist += " ";
            artist += colors.get(ThreadLocalRandom.current().nextInt(0, colors.size()));

        }
        return artist;

    }

    public String makeAlbum() {
        String album = "";
        //We will make 3 "styles" of album names
        int styleChoice = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        if (styleChoice == 1) {
            album += frequencyWords.get(ThreadLocalRandom.current().nextInt(0, frequencyWords.size()));
            album += " ";
            album += adjectives.get(ThreadLocalRandom.current().nextInt(0, adjectives.size()));
            album += " ";
            album += colors.get(ThreadLocalRandom.current().nextInt(0, colors.size()));
        }
        if (styleChoice == 2) {
            album += actionWords.get(ThreadLocalRandom.current().nextInt(0, actionWords.size()));
            album += " ";
            album += adjectives.get(ThreadLocalRandom.current().nextInt(0, adjectives.size()));
            album += " ";
            album += firstNames.get(ThreadLocalRandom.current().nextInt(0, firstNames.size()));
        }
        if (styleChoice == 3) {
            album += buzzWords.get(ThreadLocalRandom.current().nextInt(0, buzzWords.size()));

        }
        return album;


    }

    public String makeSong() {
        String song = "";
        //We will make 5 "styles" of song names
        int styleChoice = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        if (styleChoice == 1) {
            song += firstNames.get(ThreadLocalRandom.current().nextInt(0, firstNames.size()));
        }
        if (styleChoice == 2) {
            song += adjectives.get(ThreadLocalRandom.current().nextInt(0, adjectives.size()));
            song += " ";
            song += firstNames.get(ThreadLocalRandom.current().nextInt(0, firstNames.size()));
        }
        if (styleChoice == 3) {
            song += adjectives.get(ThreadLocalRandom.current().nextInt(0, adjectives.size()));
            song += " ";
            song += colors.get(ThreadLocalRandom.current().nextInt(0, colors.size()));

        }
        if (styleChoice == 4) {
            song += actionWords.get(ThreadLocalRandom.current().nextInt(0, actionWords.size()));
            song += " ";
            song += adjectives.get(ThreadLocalRandom.current().nextInt(0, adjectives.size()));
            song += " ";
            song += lastNames.get(ThreadLocalRandom.current().nextInt(0, lastNames.size()));

        }
        if (styleChoice == 5) {
            song += lastNames.get(ThreadLocalRandom.current().nextInt(0, lastNames.size()));
            song += " ";
            song += firstNames.get(ThreadLocalRandom.current().nextInt(0, firstNames.size()));

        }


        return song;
    }

    public String makePath(){
        String path = "";
        //We will make 5 paths
        int styleChoice = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        if (styleChoice == 1) {
            path = "song1.mp3";
        }
        if (styleChoice == 2) {
            path = "song2.mp3";
        }
        if (styleChoice == 3) {
            path = "song3.mp3";

        }
        if (styleChoice == 4) {
            path = "song4.mp3";

        }
        if (styleChoice == 5) {
            path = "song5.mp3";

        }
        return path;
    }

    public Boolean makeThumb(){
        boolean thumb = false;
        //1/4 times it will be thumbed
        int chance = ThreadLocalRandom.current().nextInt(1, 4 +1);
        if (chance == 1){
            thumb = true;
        }else{
            thumb = false;
        }

        return thumb;
    }

    public int makePlaycount(){
        int playCount = 0;
        playCount = ThreadLocalRandom.current().nextInt(0, MAX_PLAY_COUNT);
        return playCount;
    }

    public int makeLength(){
        int length = 9;
        length = ThreadLocalRandom.current().nextInt(MIN_LENTH, MAX_LENGTH);
        return length;
    }


}

