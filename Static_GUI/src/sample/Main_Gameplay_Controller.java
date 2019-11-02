package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Main_Gameplay_Controller implements Initializable {
    private int Selector;



    @FXML
    public ScrollPane scrollpane_GamePlay ;
    Timeline scroll_pane = new Timeline();
    Timeline scroll_pane_reset = new Timeline();
    public void scroll_pane_to_show_zombies()
    { scroll_pane.play();
        scroll_pane.setOnFinished((e)->{
        scroll_pane_reset.play();
        });
    }
    Image PeaShooter=new Image("sample/Plants vs Zombies Assets/PeashooterSeed.PNG.png");
    Image PeaShooterSelected = new Image("sample/Plants vs Zombies Assets/PeaShooterSelected.gif");
    Image SunFlower = new Image("sample/Plants vs Zombies Assets/SunflowerSeed.PNG.png");
    Image SunFlowerSelected = new Image("sample/Plants vs Zombies Assets/SunflowerSelected.gif");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroll_pane.getKeyFrames().addAll(
                new KeyFrame((new Duration(2000)), new KeyValue(scrollpane_GamePlay.hvalueProperty() ,0)),
                new KeyFrame((new Duration(4000)), new KeyValue(scrollpane_GamePlay.hvalueProperty(),scrollpane_GamePlay.getHmax()))

        );
        scroll_pane_reset.getKeyFrames().addAll(
                new KeyFrame((new Duration(1000)), new KeyValue(scrollpane_GamePlay.hvalueProperty() ,scrollpane_GamePlay.getHmax())),
                new KeyFrame((new Duration(4000)), new KeyValue(scrollpane_GamePlay.hvalueProperty(),0))

        );
    }


}
