package sample;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class Main_Gameplay_Controller implements Initializable {

    private int Selector = 0;
    private static Boolean Set_Ownership_Flag = false;

    static Stage options_stage = new Stage(StageStyle.TRANSPARENT);

    private Double temp_X;

    @FXML
    public ImageView Zombie1;
    @FXML
    public ImageView Zombie2;
    @FXML
    public ImageView Plant;
    @FXML
    public ProgressBar pb;
    @FXML
    public ImageView options_button;
    @FXML
    public ImageView Sunflower_Seed;
    @FXML
    public ImageView PeaShooter_Seed;
    @FXML
    public GridPane Grid_Pane;
    @FXML
    public ScrollPane scrollpane_GamePlay;
    @FXML
    public Text sun_token_monitor;

    private boolean flag = true;
    private ImageView Bullet_Holder;
    private ImageView Sun_Token_Holder;
    private Timeline scroll_pane = new Timeline();
    private Timeline scroll_pane_reset = new Timeline();


    public void scroll_pane_to_show_zombies() {
        if (flag) {
            scroll_pane.play();
            scroll_pane.setOnFinished((e) -> {
                scroll_pane_reset.play();
                flag = false;
            });
        }
    }

    private Image PeaShooterGIF = new Image("sample/Plants vs Zombies Assets/PeaShootera.gif");
    private Image SunflowerGIF = new Image("sample/Plants vs Zombies Assets/sun_flower.gif");
    private Image PeaShooter = new Image("sample/Plants vs Zombies Assets/PeashooterSeed.PNG.png");
    private Image PeaShooterSelected = new Image("sample/Plants vs Zombies Assets/PeaShooterSelected.gif");
    private Image SunFlower = new Image("sample/Plants vs Zombies Assets/SunflowerSeed.PNG.png");
    private Image SunFlowerSelected = new Image("sample/Plants vs Zombies Assets/SunflowerSelected.gif");
    private Image Pea_Bullet = new Image("sample/Plants vs Zombies Assets/ProjectilePea.png");
    private Image options = new Image("sample/Plants vs Zombies Assets/options.png");
    private Image options_pressed = new Image("sample/Plants vs Zombies Assets/options_pressed.png");

    private static TranslateTransition zombie_mov_1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (!Set_Ownership_Flag) {
            options_stage.initOwner(Controller.Game_Play_Stage);
            options_stage.initModality(Modality.WINDOW_MODAL);
            Set_Ownership_Flag = true;
        }   //setting the modality and ownership of options stage.

        Play_Progress_Bar();
        Play_Scroll_Animation();
        move_zombies();
        Drop_Sun_token();
        Place_Plants();

    }

    public void Peaseedselected() {
        if (Token.sun_token_counter >= 100) {
            PeaShooter_Seed.setImage(PeaShooterSelected);
            Selector = 1;

        }
    }

    public void SunflowerSeedSelected() {
        if (Token.sun_token_counter >= 50) {
            Sunflower_Seed.setImage(SunFlowerSelected);
            Selector = 2;
        }
    }

    public void set_Sun_on_SunFlower(StackPane putter) {
        Sun_Token_Holder = new ImageView();
        Sun_Token_Holder.setImage(new Image("sample/Plants vs Zombies Assets/sun.gif"));
        Sun_Token_Holder.setPreserveRatio(true);
        Sun_Token_Holder.setFitHeight(35);
        Sun_Token_Holder.setX(50);
        Sun_Token_Holder.setFitWidth(35);
        if (!putter.getChildren().contains(Sun_Token_Holder)) {
            putter.getChildren().add(Sun_Token_Holder);
            Sun_Token_Holder.toFront();
        }

        Sun_Token_Holder.setOnMouseClicked(event ->
        {
            putter.getChildren().remove(Sun_Token_Holder);
            Token.sun_token_counter += 50;
        });

    }

    public void setSun_token_monitor() {
        sun_token_monitor.setText(Token.sun_token_counter.toString());
    }

    public void Drop_Sun_token() {
        Token_Factory token_factory = new Token_Factory();
        Timeline sun_token = new Timeline(
                new KeyFrame(Duration.seconds(40), e -> token_factory.create_token(Grid_Pane, 1)),
                new KeyFrame(Duration.seconds(80), e -> token_factory.create_token(Grid_Pane, 1)),
                new KeyFrame(Duration.seconds(120), e -> token_factory.create_token(Grid_Pane, 1))
        );

        sun_token.setCycleCount(Animation.INDEFINITE);
        sun_token.play();

        //seperate Timeline to Update Monitor
        Timeline sun_token_monitor = new Timeline(
                new KeyFrame(Duration.ZERO, event -> setSun_token_monitor()),
                new KeyFrame(Duration.millis(1), event -> setSun_token_monitor())
        );

        sun_token_monitor.setCycleCount(Animation.INDEFINITE);
        sun_token_monitor.play();
    }

    private void shoot_pea(ImageView Bullet_Holder) {
        //System.out.println("Yup the function is called");
        TranslateTransition pea_shot = new TranslateTransition();
        Bullet_Holder.setX(temp_X);
        pea_shot.setNode(Bullet_Holder);
        pea_shot.setToX(temp_X+1000);
        pea_shot.setDuration(Duration.seconds(3));
        //pea_shot.setCycleCount(Timeline.INDEFINITE);


        Timeline pea_shot_Timeline=new Timeline(
                new KeyFrame(Duration.millis(0), e-> Play_pea_shot(pea_shot)),
                new KeyFrame(Duration.millis(2990), e-> Set_Bullet_visible(pea_shot))
        );

        Bullet_Holder.translateXProperty().addListener(checkIntersection);

        pea_shot_Timeline.setCycleCount(Timeline.INDEFINITE);
        pea_shot_Timeline.play();

    }


    //the two functions below are made for testing purposes
    private void Set_Bullet_visible(TranslateTransition pea_shot){
        Bullet_Holder.setVisible(true);
        System.out.println("I DID IT");
        Play_pea_shot(pea_shot);
    }

    private  void Play_pea_shot(TranslateTransition pea_shot){
        Bullet_Holder.toFront();
        Bullet_Holder.setX(temp_X);
        pea_shot.setNode(Bullet_Holder);
        pea_shot.setToX(temp_X+1000);
        pea_shot.setDuration(Duration.seconds(3));
        pea_shot.play();
        System.out.println("I played");
    }



    private final ChangeListener<Number> checkIntersection = (ob, n, n1)->{
        if (Bullet_Holder.getBoundsInParent().intersects(Zombie1.getBoundsInParent())){
            if(Bullet_Holder.isVisible()) {
                System.out.println("Zombie Hit By Pea");
                //Bullet_Holder.setVisible(false);
            }

        }
    };

    /*private final ChangeListener<Number> check_Zombie_Plant_Intersection = (ob, n, n1)->{
        if (Zombie1.getBoundsInParent().intersects(Plant.getParent().getBoundsInParent())){
            System.out.println("\n\n\nDETECTED ZOMBIE\n\n\n");
            zombie_mov_1.pause();
        }
    };*/

    private void move_zombies() {
        TranslateTransition zombie_mov_1 = new TranslateTransition();
        zombie_mov_1.setNode(Zombie1);
        Zombie1.setFitWidth(220);
        Zombie1.setFitWidth(220);
        zombie_mov_1.setByX(-1000);
        zombie_mov_1.setDuration(Duration.seconds(100));
        zombie_mov_1.play();

        TranslateTransition zombie_mov_2 = new TranslateTransition();
        Zombie2.setFitWidth(220);
        Zombie2.setFitWidth(220);
        zombie_mov_2.setNode(Zombie2);
        zombie_mov_2.setByX(-1000);
        zombie_mov_2.setDuration(Duration.seconds(100));
        zombie_mov_2.play();

        //Zombie1.translateXProperty().addListener(check_Zombie_Plant_Intersection);
    }

    public void Place_Plants() {
        for (int row = 4; row < 9; row++) {
            StackPane Image_Holder = null;
            for (int col = 0; col < 10; col++) {
                Image_Holder = new StackPane();

                Grid_Pane.add(Image_Holder, col, row);

                final int frow = row;
                final int fcol = col;

                Image_Holder.setOnMouseClicked((MouseEvent e1) -> {
                    StackPane putter = (StackPane) e1.getSource();
                    if (Selector > 0 && Selector < 6) {

                        Plant = new ImageView();
                        Plant.setFitHeight(60);
                        Plant.setFitWidth(50);
                        if (Selector == 1 && !putter.getChildren().contains(Plant)) {
                            Token.sun_token_counter -= 100;
                            Plant.setImage(PeaShooterGIF);
                            PeaShooter_Seed.setImage(PeaShooter);

                        } else if (Selector == 2 && !putter.getChildren().contains(Plant)) {
                            Plant.setImage(SunflowerGIF);
                            Sunflower_Seed.setImage(SunFlower);
                            Token.sun_token_counter -= 50;

                        }


                        if (putter.getChildren().isEmpty()) {
                            putter.getChildren().addAll(Plant);

                            if (Selector == 1) {

                                Bullet_Holder = new ImageView();
                                Bullet_Holder.setImage(Pea_Bullet);
                                Bullet_Holder.setPreserveRatio(true);
                                Bullet_Holder.setFitWidth(20);
                                Bullet_Holder.setFitHeight(20);
                                Grid_Pane.add(Bullet_Holder, fcol, frow);
                                System.out.println("pea is selected !!! row is " + frow + " col is " + fcol);

                                temp_X=Bullet_Holder.getX(); //for experimenting, this can be an attribute of the plant class

                                shoot_pea(Bullet_Holder);
                                putter.toFront();
                            } else if (Selector == 2) {
                                Timeline sun_token_sunflower = new Timeline(
                                        new KeyFrame(Duration.seconds(15), e -> {
                                            set_Sun_on_SunFlower(putter);
                                        })
                                );

                                sun_token_sunflower.setCycleCount(Animation.INDEFINITE);
                                sun_token_sunflower.play();
                            }

                            Selector = 0;
                        }
                    }
                });
            }
        }
    }

    public void Play_Progress_Bar() {
        Timeline pb_timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(pb.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(300), new KeyValue(pb.progressProperty(), 1))
        );

        pb_timeline.playFromStart();
    }

    public void Play_Scroll_Animation() {
        scroll_pane.getKeyFrames().addAll(
                new KeyFrame((new Duration(2000)), new KeyValue(scrollpane_GamePlay.hvalueProperty(), 0)),
                new KeyFrame((new Duration(4000)), new KeyValue(scrollpane_GamePlay.hvalueProperty(), scrollpane_GamePlay.getHmax()))

        );
        scroll_pane_reset.getKeyFrames().addAll(
                new KeyFrame((new Duration(1000)), new KeyValue(scrollpane_GamePlay.hvalueProperty(), scrollpane_GamePlay.getHmax())),
                new KeyFrame((new Duration(4000)), new KeyValue(scrollpane_GamePlay.hvalueProperty(), 0))

        );

        scroll_pane_to_show_zombies();
    }

    @FXML
    public void options_entered() {
        options_button.setImage(options_pressed);
    }

    @FXML
    public void options_exited() {
        options_button.setImage(options);
    }

    @FXML
    public void options_clicked() throws IOException {
        Controller.Game_Play_Stage.setOpacity(0.5);
        options_stage.setTitle("choose one of your game");
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("Pause_menu.fxml"));
        Pane options_menu = (Pane) fxmlloader.load();
        options_stage.setScene(new Scene(options_menu, Color.TRANSPARENT));
        options_stage.showAndWait();

    }

}
