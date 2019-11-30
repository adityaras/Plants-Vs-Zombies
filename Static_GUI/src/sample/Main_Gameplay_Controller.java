package sample;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
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


    @FXML
    public ImageView Zombie1;
    @FXML
    public ImageView Zombie2;
    @FXML
    public ImageView My_Plant;
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
    public static ImageView Bullet_Holder;
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
                new KeyFrame(Duration.seconds(10), e -> token_factory.create_token(Grid_Pane, 1)),
                new KeyFrame(Duration.seconds(20), e -> token_factory.create_token(Grid_Pane, 1)),
                new KeyFrame(Duration.seconds(30), e -> token_factory.create_token(Grid_Pane, 1))
        );

        sun_token.setCycleCount(Animation.INDEFINITE);
        sun_token.play();

        //separate Timeline to Update Monitor
        Timeline sun_token_monitor = new Timeline(
                new KeyFrame(Duration.ZERO, event -> setSun_token_monitor()),
                new KeyFrame(Duration.millis(1), event -> setSun_token_monitor())
        );

        sun_token_monitor.setCycleCount(Animation.INDEFINITE);
        sun_token_monitor.play();
    }

    private void shoot_peas(GridPane Grid_pane,int col, int row) {
        Timeline Pea_shots_timeline = new Timeline(
                new KeyFrame(Duration.millis(3100), event -> Shoot_a_new_pea(Grid_pane,col,row))
        );
        Pea_shots_timeline.setCycleCount(Timeline.INDEFINITE);
        Pea_shots_timeline.play();
    }

    private void Shoot_a_new_pea(GridPane Grid_pane,int col, int row){
        System.out.println("this is being called");
        TranslateTransition pea_shot=new TranslateTransition();
        Bullet_Holder=new ImageView(Pea_Bullet);
        Bullet_Holder.setVisible(true);
        Bullet_Holder.setFitWidth(20);
        Bullet_Holder.setFitHeight(20);
        System.out.println("I did it !");
        Grid_pane.add(Bullet_Holder,col,row);
        pea_shot.setNode(Bullet_Holder);
        pea_shot.setByX(1000);
        //pea_shot.setCycleCount(Timeline.INDEFINITE);
        Bullet_Holder.translateXProperty().addListener(checkIntersection);
        //Bullet_Holder.xProperty().addListener();
        pea_shot.setDuration(Duration.seconds(3));
        pea_shot.play();
    }

    private final ChangeListener<Number> checkIntersection = (ob, n, n1)->{
        try{
            if(Bullet_Holder.getBoundsInParent().intersects(Zombie1.getBoundsInParent())){
                System.out.println("Detected");
                Bullet_Holder.setVisible(false);
            }
            //System.out.println("pea -----> "+n1.doubleValue()+"  Zombie = "+Zombie1.getBoundsInParent().getMinX());
        }
        catch (NullPointerException e){
            System.out.println("______");
        }
    };

    private final ChangeListener<Number> check_Zombie_Plant_Intersection = (ob, n, n1)->{
        if(My_Plant==null){
            System.out.println("Plant is null");
            return;
        }
        if(Zombie1==null){
            System.out.println("Zombie is null");
            return;
        }
        if (Zombie1.getBoundsInParent().intersects(My_Plant.getParent().getBoundsInParent())){
            System.out.println("\n\n\nDETECTED ZOMBIE\n\n\n");
            zombie_mov_1.pause();
        }
    };

    private void move_zombies() {

        /*
        zombie_mov_1 = new TranslateTransition();
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

        Zombie1.translateXProperty().addListener(check_Zombie_Plant_Intersection);
        */

        Zombie_Factory factory=new Zombie_Factory();
        Timeline zombie_mover = new Timeline(
                new KeyFrame(Duration.seconds(5),e -> factory.create_zombie(Grid_Pane)),
                new KeyFrame(Duration.seconds(8),e -> factory.create_zombie(Grid_Pane))
        );
        zombie_mover.setCycleCount(Animation.INDEFINITE);
        zombie_mover.play();
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

                    //selector is set through injected functions in the fxml

                    if (Selector > 0 && Selector < 6) {

                        My_Plant = new ImageView();
                        My_Plant.setFitHeight(60);
                        My_Plant.setFitWidth(50);

                        if (Selector == 1 && !putter.getChildren().contains(My_Plant)) {
                            Token.sun_token_counter -= 100;
                            My_Plant.setImage(PeaShooterGIF);
                            PeaShooter_Seed.setImage(PeaShooter);

                        } else if (Selector == 2 && !putter.getChildren().contains(My_Plant)) {
                            My_Plant.setImage(SunflowerGIF);
                            Sunflower_Seed.setImage(SunFlower);
                            Token.sun_token_counter -= 50;

                        }


                        if (putter.getChildren().isEmpty()) {

                            putter.getChildren().addAll(My_Plant); //adding the selected plant to Stack_pane

                            if (Selector == 1) {
                                shoot_peas(Grid_Pane,fcol,frow);
                                putter.toFront();

                            }else if (Selector == 2) {

                                Timeline sun_token_sunflower = new Timeline(
                                        new KeyFrame(Duration.seconds(45), e -> {
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

    @FXML
    public void Peaseedselected() {
        if (Token.sun_token_counter >= 100) {
            PeaShooter_Seed.setImage(PeaShooterSelected);
            Selector = 1;

        }
    }

    @FXML
    public void SunflowerSeedSelected() {
        if (Token.sun_token_counter >= 50) {
            Sunflower_Seed.setImage(SunFlowerSelected);
            Selector = 2;
        }
    }



}