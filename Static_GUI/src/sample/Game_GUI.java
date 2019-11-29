package sample;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class Game_GUI extends Application {

    public static Stage primary_stage=new Stage(StageStyle.TRANSPARENT);

    @Override
    public void start(Stage primary_stage){
        Game_GUI.primary_stage.setTitle("Plants Vs. Zombies");
        Game_GUI.primary_stage.setScene(new Scene(showPreLoadScreen(),600,337, Color.TRANSPARENT));
        Game_GUI.primary_stage.show();
        Platform.setImplicitExit(false);


    }

    private Parent showPreLoadScreen()
    {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Game_GUI.class.getResource("Pre_Load_Screen.fxml"));
            StackPane Pre_Load_Screen = (StackPane) fxmlLoader.load();
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(4), Pre_Load_Screen);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), Pre_Load_Screen);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            fadeIn.play();
            playSound();
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });
            fadeOut.setOnFinished((e) -> {
                Load_Screen l =new Load_Screen();
                Game_GUI.primary_stage.setScene(new Scene(l.showLoadScreen(),550,400));
                Game_GUI.primary_stage.show();
            });

            return Pre_Load_Screen;
        }
        catch (IOException err) {
            err.printStackTrace();
            return null;
        }
    }
    public void playSound() {
        /*try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("D:\\Codes\\PVZ_BANEA\\AP-PROJECT\\Static_GUI\\src\\sample\\Plants vs Zombies Assets\\BackGround.wav").getAbsoluteFile());
            //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sample/Plants vs Zombies Assets/BackGround.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error In Sound File Location, Try giving full Path");
            ex.printStackTrace();
        }*/
    }
    public static void main(String[] args) {
        launch(args);
    }
}
