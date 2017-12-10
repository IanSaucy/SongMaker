# SongMaker
A program to make random songs

This program makes random song sets from a set of text files that have different types and words and names in them.
If you want to change the per artist album count or per album song count change the variable at the top of the SongMaker class.

The program will generate and overwrite the existing randomSong.txt file at every write. You can either clone and run yourself or simply
download the randomSongs.txt file and use it(contains 10k songs). 

If you want to change what data gets written to the txt file, change the toString() method in the Song class. 

Process for generating random songs:

1) run loadRnadomData() method
2) run makeArtistSet() method
3) run writeFile() method

Done! You know have a list of songs in text file format!
