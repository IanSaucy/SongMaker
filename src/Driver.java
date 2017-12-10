import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        RandomSongMaker songFactory  = new RandomSongMaker();
        //call to load data
        songFactory.loadRandomData();
        //Called to create random songs
        songFactory.makeArtistSet();
        //Called to write everything to a file
        songFactory.writeFile();

    }
}
