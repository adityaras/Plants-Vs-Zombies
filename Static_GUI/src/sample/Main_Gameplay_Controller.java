package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.PointLight;
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
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.BatchUpdateException;
import java.util.ResourceBundle;



public class Main_Gameplay_Controller implements Initializable {

    private int Selector;
    private static Boolean Set_Ownership_Flag=false;

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
    private ImageView Plant;
    private TranslateTransition zombie_mov_1;
    private TranslateTransition pea_shot;

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


        if(!Set_Ownership_Flag) {
            options_stage.initOwner(Controller.Game_Play_Stage);
            options_stage.initModality(Modality.WINDOW_MODAL);
            Set_Ownership_Flag=true;
        }

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

                        Plant = new ImageView();
                        Plant.setFitHeight(60);
                        Plant.setFitWidth(50);
                        if (Selector == 1 && !putter.getChildren().contains(Plant)) {

                            Plant.setImage(PeaShooterGIF);
                            PeaShooter_Seed.setImage(PeaShooter);


                        } else if (Selector == 2 && !putter.getChildren().contains(Plant)) {
                            Plant.setImage(SunflowerGIF);
                            Sunflower_Seed.setImage(SunFlower);
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
                                Bullet_Holder.translateXProperty().addListener(checkIntersection);
                            }


                            System.out.println(Bullet_Holder.getBoundsInParent());
                            System.out.println(Zombie1.getBoundsInParent());

                            Plant.getParent().toFront();
                            Zombie1.toFront();

                            /*Zombie1.setFitWidth(20);
                            Zombie1.setFitHeight(220);*/
                            Zombie1.translateXProperty().addListener(check_Zombie_Plant_Intersection);
                            System.out.println("Okay then");
                            //Selector = 0;
                        }
                }
            });
        }
        }
    }

    public void handle(javafx.event.ActionEvent event) {

        System.out.println("Hello this amazing function is being called");

    }


    public void Peaseedselected() {
        PeaShooter_Seed.setImage(PeaShooterSelected);
        Selector=1;
    }
    public void SunflowerSeedSelected() {
        Sunflower_Seed.setImage(SunFlowerSelected);
        Selector=2;
    }

    private void shoot_pea(ImageView Bullet_Holder) {
        //System.out.println("Yup the function is called");
        pea_shot = new TranslateTransition();
        pea_shot.setNode(Bullet_Holder);
        pea_shot.setToX(Bullet_Holder.getX()+1000);
        pea_shot.setDuration(Duration.seconds(3));
        pea_shot.setCycleCount(3);
        pea_shot.setOnFinished(event -> Shoot_pea_Again());
        pea_shot.play();
    }

    private void Shoot_pea_Again(){
        System.out.println("Trying to play this again");
        pea_shot.playFromStart();
    };

    private final ChangeListener<Number> checkIntersection = (ob, n, n1)->{
        if (Bullet_Holder.getBoundsInParent().intersects(Zombie1.getBoundsInParent())){

            //System.out.println("hello");

        }
    };

    private final ChangeListener<Number> check_Zombie_Plant_Intersection = (ob, n, n1)->{
        if (Zombie1.getBoundsInParent().intersects(Plant.getParent().getBoundsInParent())){
            System.out.println("\n\n\nDETECTED ZOMBIE\n\n\n");
            zombie_mov_1.pause();
        }
    };


    private void move_zombies(){
        zombie_mov_1=new TranslateTransition();
        zombie_mov_1.setNode(Zombie1);
        Zombie1.setFitWidth(220);
        zombie_mov_1.setByX(-1000);
        zombie_mov_1.setDuration(Duration.seconds(50));
        zombie_mov_1.play();
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

}
