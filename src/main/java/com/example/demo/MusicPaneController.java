package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

public class MusicPaneController implements Initializable {

    @FXML
    private Label songNameLabel;
    @FXML
    private ProgressBar soundtrackProgressBar;
    @FXML
    private Slider volumnSlider;

    private static double volumn = 50;

    private static String path;
    private static String songName = "";

    private static MediaPlayer mediaPlayer;

    private Boolean running = false;
    private Timer timer;
    private TimerTask task;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Running = " + running);
        if(running == true){
            System.out.println("no null nha");
            timer.cancel();
        }
        else if(!running){
            System.out.println("begin ne");
            beginTimer();
        }
        songNameLabel.setText(songName);
        volumnSlider.setValue(volumn);
        volumnSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                volumn = (int)volumnSlider.getValue();
                System.out.println(volumn);
                changeVolumn(volumn);
            }
        });

    }

    public static String getSongName() {
        return songName;
    }

    public static void setSongName(String songName) {
        MusicPaneController.songName = songName;
    }

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
    @FXML
    protected void nextSoundtrackButtonOnAction(ActionEvent event){
        System.out.println("next song clicked");
        String nextSoundtrack = SoundtrackDatabase.getInstance().nextSoundtrack();
        songNameLabel.setText(getTitleSong(nextSoundtrack));
        System.out.println(nextSoundtrack);
        if(timer != null){
            timer.cancel();
        }
        mediaPlayer.stop();
        playSoundtrack(nextSoundtrack);
        beginTimer();
    }

    @FXML
    protected void previousSoundtrackButtonOnAction(ActionEvent event){
        System.out.println("previous song clicked");
        String previousSoundtrack = SoundtrackDatabase.getInstance().previousSoundtrack();
        songNameLabel.setText(getTitleSong(previousSoundtrack));
        System.out.println(previousSoundtrack);
        if(timer != null){
            timer.cancel();
        }
        mediaPlayer.stop();
        playSoundtrack(previousSoundtrack);
        beginTimer();

    }

    private void changeVolumn(double volumn){
        this.volumn = volumn;
        mediaPlayer.setVolume(volumn/100);
    }

    public void beginTimer(){
        timer = new Timer();
        task = new TimerTask(){

            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = mediaPlayer.getTotalDuration().toSeconds();
                System.out.println(current/end);
                soundtrackProgressBar.setProgress(current/end);
                if(current/end == 1){

                }

            }
        };
        timer.scheduleAtFixedRate(task,1000,1000);
    }
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

    public static String getTitleSong(String path){
        return path.substring(path.toString().lastIndexOf('/')+1, path.toString().length());
    }




}
