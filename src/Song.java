import java.io.Serializable;
import java.lang.String;

import java.util.Objects;

public class Song implements Serializable {

    protected String title, artist, album, pathToFile, pathToAlbumCover;
    protected int length, playCount;
    protected boolean isThumbed;

    public Song(String title, String artist, String album, String pathToFile, int length, int playCount, boolean isThumbed) {
        this.title = new String(title);
        this.artist = new String(artist);
        this.album = new String(album);
        this.pathToFile = new String(pathToFile);
        this.length = length;
        this.playCount = playCount;
        this.isThumbed = isThumbed;
    }

    public Song(String title, String artist, String album, String pathToFile) {
        this.title = new String(title);
        this.artist = new String(artist);
        this.album = new String(album);
        this.pathToFile = new String(pathToFile);


    }

    public Song() {

    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getLength() {
        return length;
    }

    public int getPlayCount() {
        return playCount;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public String getPathToAlbumCover() {
        return pathToAlbumCover;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public boolean isThumbed() {
        return isThumbed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public void setThumbed(boolean thumbed) {
        isThumbed = thumbed;
    }

    public void setPathToAlbumCover(String path) {
        pathToAlbumCover = path;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        //Don't check playCount and thumb since these should not fail a .equal comparison
        return length == song.length &&
                Objects.equals(title, song.title) &&
                Objects.equals(artist, song.artist) &&
                Objects.equals(album, song.album);
    }

    @Override
    public int hashCode() {

        //Hash should not include file path since these could be different for the same song
        return Objects.hash(title, artist, album, length, playCount, isThumbed);
    }

    @Override
    public String toString() {
        String objString = "";
        objString += artist;
        objString += ",";
        objString += album;
        objString += ",";
        objString += title;
        objString += ",";
        objString += playCount;
        objString += ",";
        objString += isThumbed;
        objString += ",";
        objString += length;
        objString += ",";
        objString += pathToFile;
        objString += ",";
        objString += pathToAlbumCover;
        return objString;
    }
}
