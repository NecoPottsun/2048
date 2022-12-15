package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.apache.poi.ss.formula.functions.T;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

/**
 * To control the MusicPane, handle the interactions between the user
 * with the system in the Music Pane
 * @author Thanh Mai Duyen Trang
 */
public class MusicPaneController implements Initializable {

    @FXML
    private Label songNameLabel;
    @FXML
    private ProgressBar soundtrackProgressBar;
    @FXML
    private Slider volumnSlider;

    private static double volume = 50;

    private static String songName = "";

    private static MediaPlayer mediaPlayer;

    private Boolean running = false;
    private Timeline timeline;
    /**
     * Create all the initial values, do the very first stuffs
     * when a Music Pane is created
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        running = true;
        setSoundtrackProgressBarTimer();

        songNameLabel.setText(songName);
        volumnSlider.setValue(volume);


        volumnSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                volume = (int)volumnSlider.getValue();
                changeVolume(volume);
            }
        });

    }

    /**
     * Gets the current playing song name of this MusicPane
     * @return the name of the song that is playing
     * <p>
     *     Default value: " "
     * </p>
     */
    public static String getSongName() {
        return songName;
    }

    /**
     * Sets the current playing song name of this MusicPane
     * @param songName the name that wanted to change
     */
    public static void setSongName(String songName) {
        MusicPaneController.songName = songName;
    }

    /**
     * Gets the playing status of this MusicPane
     * @return <code>True</code> if it is playing;
     *          <code>False</code> if it is pausing;
     * <p>
     *     Default value: false
     * </p>
     */
    public Boolean getRunning() {
        return running;
    }

    /**
     * Sets the playing status of this MusicPane
     * @param running the running status of this MusicPane
     */
    public void setRunning(Boolean running) {
        this.running = running;
    }

    /**
     * Gets the Media Player of this MusicPane
     * @return the MediaPlayer of this MusicPane
     */
    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Sets the Media Player of this MusicPane
     * @param mediaPlayer the Media Player that wanted to set
     */
    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        MusicPaneController.mediaPlayer = mediaPlayer;
    }

    /**
     * Gets the volume of the Media Player of this MusicPane
     * @return
     * <p>
     *     Default value: 50
     * </p>
     */
    public static double getVolume() {
        return volume;
    }



    /**
     * Pauses the soundtrack which is playing in the MusicPane
     * @param event the event listened when the Pause button inside the MusicPane is clicked
     */
    @FXML
    protected void pauseSoundtrackButtonOnAction(ActionEvent event){
        System.out.println("pause clicked");
        if(mediaPlayer.getStatus().equals(MediaPlayer.Status.PAUSED)){
            mediaPlayer.play();
            running = true;
        }
        else{
            mediaPlayer.pause();
            running = false;
        }
    }
    /**
     * Plays the next soundtrack in the MusicPane
     * @param event the event listened when the Next song button inside the MusicPane is clicked
     */
    @FXML
    protected void nextSoundtrackButtonOnAction(ActionEvent event){
        System.out.println("next song clicked");
        String nextSoundtrack = SoundtrackDatabase.getInstance().nextSoundtrack();

        songNameLabel.setText(getTitleSong(nextSoundtrack));
        if(timeline != null){
            timeline.stop();
        }
        mediaPlayer.stop();
        playSoundtrack(nextSoundtrack);
        setSoundtrackProgressBarTimer();

    }
    /**
     * Plays the previous soundtrack in the MusicPane
     * @param event the event listened when the Previous song button inside the MusicPane is clicked
     */
    @FXML
    protected void previousSoundtrackButtonOnAction(ActionEvent event){
        System.out.println("previous song clicked");
        String previousSoundtrack = SoundtrackDatabase.getInstance().previousSoundtrack();
        songNameLabel.setText(getTitleSong(previousSoundtrack));
        System.out.println(previousSoundtrack);
        if(timeline != null){
            timeline.stop();
        }
        mediaPlayer.stop();
        playSoundtrack(previousSoundtrack);
        setSoundtrackProgressBarTimer();


    }

    /**
     * Adjusts the volume of the media player of this MusicPaneController
     * @param volume the volume that wanted to change
     *
     */
    private void changeVolume(double volume){
        this.volume = volume;
        mediaPlayer.setVolume(volume/100);
    }

    /**
     * Sets the soundtrack progress bar inside this Music Pane
     */
    public void setSoundtrackProgressBarTimer(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1),e ->{
            double current = MusicPaneController.getMediaPlayer().getCurrentTime().toSeconds();
            double end = MusicPaneController.getMediaPlayer().getTotalDuration().toSeconds();
            double playingTime = current/end;
            soundtrackProgressBar.setProgress(playingTime);
            if(!running){
                timeline.pause();
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Plays the soundtrack with a particular path of the Music Pane
     * @param path the path of the soundtrack
     */
    public static void playSoundtrack(String path){
        URL soundTrackFile1 = Main.class.getResource(path);
        System.out.println(soundTrackFile1);
        Media media = new Media(soundTrackFile1.toString());
        //get title name
        songName = getTitleSong(path);
        setSongName(songName);
        System.out.println(songName);

  //      songNameLabel.setText(media.getSource());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        System.out.println(songName + " is playing...");
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });


    }

    /**
     * Gets the title of a song with its path
     * @param path the path of the song
     * @return the title of the song
     */
    public static String getTitleSong(String path){
        return path.substring(path.toString().lastIndexOf('/')+1, path.toString().length());
    }




}
