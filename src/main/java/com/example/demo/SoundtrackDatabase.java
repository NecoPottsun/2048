package com.example.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all the soundtracks of the game
 * @author Thanh Mai Duyen Trang
 */
public class SoundtrackDatabase {
    private List<String> soundtrackList = new ArrayList<>();
    private static SoundtrackDatabase instance = null;
    private int currentPlayIndex = 0;

    /**
     * The constructor of SoundtrackDatabase
     */
    private SoundtrackDatabase(){
        soundtrackList.add("music/soundtrack1.mp3");
        soundtrackList.add("music/soundtrack2.mp3");
        soundtrackList.add("music/soundtrack3.mp3");
    }
    /**
     * <code>getInstance()</code>help this SoundtrackDatabase follows the Singleton, because it only need to be
     *  created once
     * @return SoundtrackDatabase
     * */
    public static SoundtrackDatabase getInstance(){
        if(instance == null)
            instance= new SoundtrackDatabase();
        return instance;
    }

    /**
     * Gets position of the current soundtrack playing inside the soundtrack list of the SoundtrackDatabase
     * @return the position of the current soundtrack playing
     */
    public int getCurrentPlayIndex() {
        return currentPlayIndex;
    }

    /**
     * Sets the position of the current soundtrack playing of the SoundtrackDatabase
     * @param currentPlayIndex the position of the song that is currently playing
     */
    public void setCurrentPlayIndex(int currentPlayIndex) {
        this.currentPlayIndex = currentPlayIndex;
    }

    /**
     * Gets the list of soundtracks initialized in the SoundtrackDatabase
     * @return list of soundtracks
     */
    public List<String> getSoundtrackList() {
        return soundtrackList;
    }

    /**
     * Gets the soundtrack at position i inside the soundtrackList of the SoundtrackDatabase
     * @param i the position of the soundtrack that wanted to play
     * @return the path of the soundtrack at position i
     */
    public String getSoundtrackAtPosition(int i){
        currentPlayIndex = i;
        return soundtrackList.get(i);
    }

    /**
     * Gets the soundtrack whose position is in the next position of the current soundtrack
     * @return the path of the soundtrack in the next position
     */
    public String nextSoundtrack(){
        currentPlayIndex++;
        int playAtIndex = checkCurrentPlayIndex(currentPlayIndex);
        return soundtrackList.get(playAtIndex);
    }

    /**
     * Gets the soundtrack whose position is in the previous position of the current soundtrack
     * @return the path of the soundtrack in the previous position
     */
    public String previousSoundtrack(){
        currentPlayIndex--;
        int playAtIndex = checkCurrentPlayIndex(currentPlayIndex);
        return soundtrackList.get(playAtIndex);
    }

    /**
     *Gets the current soundtrack position in the Soundtrack List of the SoundtrackDatabase
     *.This method is to handle the current soundtrack position if smaller
     * than 0 or exceed the size of this soundtrack list
     *
     * @param currentPlayIndex the index of the soundtrack that wanted to play
     * @return the new position inside the List
     */
    public int checkCurrentPlayIndex(int currentPlayIndex){
        if(currentPlayIndex >= soundtrackList.size()){
            currentPlayIndex = soundtrackList.size() - currentPlayIndex;
        }
        else if(currentPlayIndex < 0){
            currentPlayIndex = soundtrackList.size() + currentPlayIndex;
        }
        return currentPlayIndex;
    }

    /**
     * Adds a new soundtrack to the Soundtrack List of the SoundtrackDatabase
     * @param path the path of the soundtrack that wanted to add
     */
    public void addSoundtrack(String path){
        soundtrackList.add(path);
    }
    /**
     * Removes an existed soundtrack inside the Soundtrack List of the SoundtrackDatabase
     * @param path the path of the soundtrack that wanted to delete
     */
    public void deleteSoundtrack(String path){
        for(String soundtrackPath : soundtrackList){
            if(soundtrackPath.equals(path)){
                soundtrackList.remove(soundtrackPath);
            }
        }
    }

    /**
     * Prints all the elements inside the soundtrack list
     */
    public void printSoundtrackList(){
        for(String soundtrack : soundtrackList){
            System.out.println(soundtrack);
        }
    }


}
