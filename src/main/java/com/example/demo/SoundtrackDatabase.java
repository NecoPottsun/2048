package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class SoundtrackDatabase {
    private List<String> soundtrackList = new ArrayList<>();
    private static SoundtrackDatabase instance = null;
    private int currentPlayIndex = 0;
    private SoundtrackDatabase(){
        soundtrackList.add("music/soundtrack1.mp3");
        soundtrackList.add("music/soundtrack2.mp3");
        soundtrackList.add("music/soundtrack3.mp3");
    }
    public static SoundtrackDatabase getInstance(){
        if(instance == null)
            instance= new SoundtrackDatabase();
        return instance;
    }

    public int getCurrentPlayIndex() {
        return currentPlayIndex;
    }

    public void setCurrentPlayIndex(int currentPlayIndex) {
        this.currentPlayIndex = currentPlayIndex;
    }

    public List<String> getSoundtrackList() {
        return soundtrackList;
    }
    public String getSoundtrackAtPosition(int i){
        currentPlayIndex = i;
        return soundtrackList.get(i);
    }
    public String nextSoundtrack(){
        currentPlayIndex++;
        int playAtIndex = checkCurrenPlayIndex();
        return soundtrackList.get(playAtIndex);
    }
    public String previousSoundtrack(){
        currentPlayIndex--;
        int playAtIndex = checkCurrenPlayIndex();
        return soundtrackList.get(playAtIndex);
    }

    public int checkCurrenPlayIndex(){
        if(currentPlayIndex >= soundtrackList.size()){
            currentPlayIndex = soundtrackList.size() - currentPlayIndex;
        }
        else if(currentPlayIndex < 0){
            currentPlayIndex = soundtrackList.size() + currentPlayIndex;
        }
        return currentPlayIndex;
    }

    public void addSoundtrack(String path){
        soundtrackList.add(path);
    }
    public void deleteSoundtrack(String path){
        for(String soundtrackPath : soundtrackList){
            if(soundtrackPath.equals(path)){
                soundtrackList.remove(soundtrackPath);
            }
        }
    }

}
