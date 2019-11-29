package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class Main_Gameplay_Controller implements Initializable {

    private int Selector;
    static Stage options_stage=new Stage(StageStyle.TRANSPARENT);

    @FXML
    public ImageView Sun_token;
    @FXML
    public ImageView Zombie1;
    @FXML
    public ImageView Zombie2;
    @FXML
    public ProgressBar pb;
    @FXML
    public ImageView options_button;
    @FXML
    public ImageView Sunflower_Seed;
    @FXML
    public  ImageView PeaShooter_Seed;
    @FXML
    public GridPane Grid_Pane;
    @FXML
    public ScrollPane scrollpane_GamePlay ;

    private boolean flag=true;
    private ImageView Bullet_Holder;
    private Timeline scroll_pane = new Timeline();
    private Timeline scroll_pane_reset = new Timeline();


    public void scroll_pane_to_show_zombies()
    { if(flag)
        {scroll_pane.play();
        scroll_pane.setOnFinished((e)->{
        scroll_pane_reset.play();
        flag=false;
        });}
    }

    private Image PeaShooterGIF = new Image("sample/Plants vs Zombies Assets/PeaShootera.gif");
    private Image SunflowerGIF = new Image("sample/Plants vs Zombies Assets/sun_flower.gif");
    private Image PeaShooter=new Image("sample/Plants vs Zombies Assets/PeashooterSeed.PNG.png");
    private Image PeaShooterSelected = new Image("sample/Plants vs Zombies Assets/PeaShooterSelected.gif");
    private Image SunFlower = new Image("sample/Plants vs Zombies Assets/SunflowerSeed.PNG.png");
    private Image SunFlowerSelected = new Image("sample/Plants vs Zombies Assets/SunflowerSelected.gif");
    private Image Pea_Bullet=new Image("sample/Plants vs Zombies Assets/ProjectilePea.png");
    private Image options=new Image("sample/Plants vs Zombies Assets/options.png");
    private Image options_pressed= new Image("sample/Plants vs Zombies Assets/options_pressed.png");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        options_stage.initOwner(Controller.Game_Play_Stage);
        options_stage.initModality(Modality.WINDOW_MODAL);

        Timeline pb_timeline=new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(pb.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(300), new KeyValue(pb.progressProperty(), 1))
        );

        pb_timeline.playFromStart();
        move_zombies();
        sun_token_fall();

        scroll_pane.getKeyFrames().addAll(
                new KeyFrame((new Duration(2000)), new KeyValue(scrollpane_GamePlay.hvalueProperty() ,0)),
                new KeyFrame((new Duration(4000)), new KeyValue(scrollpane_GamePlay.hvalueProperty(),scrollpane_GamePlay.getHmax()))

        );
        scroll_pane_reset.getKeyFrames().addAll(
                new KeyFrame((new Duration(1000)), new KeyValue(scrollpane_GamePlay.hvalueProperty() ,scrollpane_GamePlay.getHmax())),
                new KeyFrame((new Duration(4000)), new KeyValue(scrollpane_GamePlay.hvalueProperty(),0))

        );
        for(int row=4;row<9;row++) {
            StackPane Image_Holder = null;
            for (int col = 0; col < 10; col++) {
                Image_Holder = new StackPane();

                Grid_Pane.add(Image_Holder, col, row);

                final int frow=row;
                final int fcol=col;

                Image_Holder.setOnMouseClicked((MouseEvent e1) -> {

                    StackPane putter=(StackPane) e1.getSource();
                    if (Selector > 0 && Selector < 6 ) {

                        ImageView Plant = new ImageView();
                        Plant.setFitHeight(60);
                        Plant.setFitWidth(50);
                        if (Selector == 1 && !putter.getChildren().contains(Plant)) {
                            Sunflower_Seed.setImage(SunFlower);
                            Plant.setImage(PeaShooterGIF);


                        } else if (Selector == 2 && !putter.getChildren().contains(Plant)) {

                            PeaShooter_Seed.setImage(PeaShooter);
                            Plant.setImage(SunflowerGIF);

                        }
                        Bullet_Holder=new ImageView();
                        Bullet_Holder.setImage(Pea_Bullet);
                        Bullet_Holder.setPreserveRatio(true);
                        Bullet_Holder.setFitWidth(20);
                        Bullet_Holder.setFitHeight(20);

                        if(putter.getChildren().isEmpty() ) {
                            putter.getChildren().addAll(Plant);

                            if (Selector == 1) {
                                Grid_Pane.add(Bullet_Holder, fcol, frow);
                                System.out.println("pea is selected !!! row is " + frow + " col is " + fcol);
                                shoot_pea(Bullet_Holder);
                            }
                            putter.toFront();
                            //Selector = 0;
                        }
                }
            });
        }
        }
    }
    public void Peaseedselected() {
        PeaShooter_Seed.setImage(PeaShooterSelected);
        Selector=1;
    }
    public void SunflowerSeedSelected() {
        Sunflower_Seed.setImage(SunFlowerSelected);
        Selector=2;
    }

    private void shoot_pea(ImageView Bullet_Holder){
        //System.out.println("Yup the function is called");
        TranslateTransition pea_shot=new TranslateTransition();
        pea_shot.setNode(Bullet_Holder);
        pea_shot.setByX(1000);
        pea_shot.setDuration(Duration.seconds(3));
        pea_shot.setCycleCount(Timeline.INDEFINITE);
        pea_shot.play();

    }

    private void move_zombies(){
        TranslateTransition zombie_mov_1=new TranslateTransition();
        zombie_mov_1.setNode(Zombie1);
        Zombie1.setFitWidth(220);
        Zombie1.setFitWidth(220);
        zombie_mov_1.setByX(-1000);
        zombie_mov_1.setDuration(Duration.seconds(100));
        zombie_mov_1.play();

        TranslateTransition zombie_mov_2=new TranslateTransition();
        Zombie2.setFitWidth(220);
        Zombie2.setFitWidth(220);
        zombie_mov_2.setNode(Zombie2);
        zombie_mov_2.setByX(-1000);
        zombie_mov_2.setDuration(Duration.seconds(100));
        zombie_mov_2.play();
    }

    private void sun_token_fall(){
        TranslateTransition sun_token_fell=new TranslateTransition();
        Sun_token.setFitWidth(30);
        Sun_token.setFitHeight(30);
        sun_token_fell.setNode(Sun_token);
        sun_token_fell.setByY(300);
        sun_token_fell.setDuration(Duration.seconds(30));
        sun_token_fell.play();
    }
    @FXML
    public void options_entered(){
        options_button.setImage(options_pressed);
    }
    @FXML
    public void options_exited(){
        options_button.setImage(options);
    }
    @FXML
    public void options_clicked() throws IOException {
        Controller.Game_Play_Stage.setOpacity(0.5);
        options_stage.setTitle("choose one of your game");
        FXMLLoader fxmlloader =  new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("Pause_menu.fxml"));
        Pane options_menu = (Pane) fxmlloader.load();
        options_stage.setScene(new Scene(options_menu, Color.TRANSPARENT));
        options_stage.showAndWait();

    }

    @FXML
    public static void close_my_stage(){
        Controller.Game_Play_Stage.close();
    }
}
