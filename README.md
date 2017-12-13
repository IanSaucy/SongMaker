# SongMaker
A program to make random songs

*If you pulled the RandomSongs.txt from this repo before 12/13 @ 2PM there is an issue*

This program makes random song sets from a set of text files that have different types of words and names in them.
The number of albums per artist and songs per album is randomly set between two variables.
If you want to change the per artist album count or per album song count change the variable at the top of the SongMaker class.

The program will generate and overwrite the existing randomSong.txt file every run. You can either clone and run yourself or simply
download the randomSongs.txt file and use it(contains 10k songs). 

If you want to change what data gets written to the txt file, change the toString() method in the Song class. 

Part of the generated data is random paths to album art and the song location. These locations are setup to have these assetes in the root
java directory(root of your project). The idea is that you could have some songs and album data as example data that are randomy picked for each album. If you want to change the the root diretory, just change the makePath() and or makePathToAlbumCover().


Process for generating random songs:

1) run loadRnadomData() method
2) run makeArtistSet() method
3) run writeFile() method

or simply run the Driver.java as it contains a main method with these steps setup already.

Done! You know have a list of songs in text file format!
