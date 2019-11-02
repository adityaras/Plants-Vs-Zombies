package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Main_Gameplay_Controller implements Initializable {
    private int Selector;

    @FXML
    public ImageView Sunflower_Seed;
    @FXML
    public  ImageView PeaShooter_Seed;
    @FXML
    public GridPane Grid_Pane;
    @FXML
    public ScrollPane scrollpane_GamePlay ;
    private boolean flag=true;
    public ImageView Bullet_Holder;
    Timeline scroll_pane = new Timeline();
    Timeline scroll_pane_reset = new Timeline();


    Image Pea_Bullet=new Image("sample/Plants vs Zombies Assets/ProjectilePea.png");
    public void scroll_pane_to_show_zombies()
    { if(flag)
        {scroll_pane.play();
        scroll_pane.setOnFinished((e)->{
        scroll_pane_reset.play();
        flag=false;
        });}
    }
    Image PeaShooterGIF = new Image("sample/Plants vs Zombies Assets/PeaShootera.gif");
    Image SunflowerGIF = new Image("sample/Plants vs Zombies Assets/sun_flower.gif");
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
        for(int row=0;row<5;row++) {
            StackPane Image_Holder = null;
            for (int col = 0; col < 10; col++) {
                Image_Holder = new StackPane();

                Grid_Pane.add(Image_Holder, col, row);

            Image_Holder.setOnMouseClicked((MouseEvent e1) -> {

                StackPane putter=(StackPane) e1.getSource();
                if (Selector > 0 && Selector < 6 ) {


                    ImageView Plant = new ImageView();
                    Plant.setFitHeight(60);
                    Plant.setFitWidth(50);
                    if (Selector == 1 && !putter.getChildren().contains(Plant)) {
                        Selector=0;

                        Plant.setImage(PeaShooterGIF);
                        PeaShooter_Seed.setImage(PeaShooter);
                        //Bullet_Holder.setImage(Pea_Bullet);
                        //Bullet_Holder.setVisible(false);
                        /*putter.getChildren().add(Bullet_Holder);*/
                        //Bullet.play();
                        //Bullet_Holder.setVisible(true);
                    } else if (Selector == 2 && !putter.getChildren().contains(Plant)) {
                        Plant.setImage(SunflowerGIF);
                        Selector=0;
                        Sunflower_Seed.setImage(SunFlower);
                    }
                    putter.getChildren().addAll(Plant);

                }
            });

        }
        }
    }
    public void Peaseedselected()
    {
        PeaShooter_Seed.setImage(PeaShooterSelected);
        Selector=1;
    }
    public void SunflowerSeedSelected()
    {
        Sunflower_Seed.setImage(SunFlowerSelected);
        Selector=2;
    }


}
