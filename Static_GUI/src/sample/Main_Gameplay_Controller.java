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

    private Image PeaShooterGIF = new Image("sample/Plants vs Zombies Assets/PeaShootera.gif");
    private Image SunflowerGIF = new Image("sample/Plants vs Zombies Assets/sun_flower.gif");
    private Image PeaShooter = new Image("sample/Plants vs Zombies Assets/PeashooterSeed.PNG.png");
    private Image PeaShooterSelected = new Image("sample/Plants vs Zombies Assets/PeaShooterSelected.gif");
    private Image SunFlower = new Image("sample/Plants vs Zombies Assets/SunflowerSeed.PNG.png");
    private Image SunFlowerSelected = new Image("sample/Plants vs Zombies Assets/SunflowerSelected.gif");
    private Image Pea_Bullet = new Image("sample/Plants vs Zombies Assets/ProjectilePea.png");
    private Image options = new Image("sample/Plants vs Zombies Assets/options.png");
    private Image options_pressed = new Image("sample/Plants vs Zombies Assets/options_pressed.png");

    private boolean flag = true;
    public static ImageView Bullet_Holder;
    private ImageView Sun_Token_Holder;
    private Timeline scroll_pane = new Timeline();
    private Timeline scroll_pane_reset = new Timeline();

    private static TranslateTransition zombie_mov_1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Place_Plants();

        if (!Set_Ownership_Flag) {
            options_stage.initOwner(Controller.Game_Play_Stage);
            options_stage.initModality(Modality.WINDOW_MODAL);
            Set_Ownership_Flag = true;
        }   //setting the modality and ownership of options stage.

        Play_Progress_Bar();
        Play_Scroll_Animation();
        Drop_Sun_token();
        move_zombies();

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

        //separate Timeline to Update Monitor
        Timeline sun_token_monitor = new Timeline(
                new KeyFrame(Duration.ZERO, event -> setSun_token_monitor()),
                new KeyFrame(Duration.millis(1), event -> setSun_token_monitor())
        );

        sun_token_monitor.setCycleCount(Animation.INDEFINITE);
        sun_token_monitor.play();
    }

    private final ChangeListener<Number> checkIntersection = (ob, n, n1) -> {
        try {
            if (Bullet_Holder.getBoundsInParent().intersects(Zombie1.getBoundsInParent())) {
                System.out.println("Detected");
                Bullet_Holder.setVisible(false);
            }
            //System.out.println("pea -----> "+n1.doubleValue()+"  Zombie = "+Zombie1.getBoundsInParent().getMinX());
        } catch (NullPointerException e) {
            System.out.println("__");
        }
    };

    private final ChangeListener<Number> check_Zombie_Plant_Intersection = (ob, n, n1) -> {
        if (My_Plant == null) {
            System.out.println("Plant is null");
            return;
        }
        if (Zombie1 == null) {
            System.out.println("Zombie is null");
            return;
        }
        if (Zombie1.getBoundsInParent().intersects(My_Plant.getParent().getBoundsInParent())) {
            System.out.println("\n\n\nDETECTED ZOMBIE\n\n\n");
            zombie_mov_1.pause();
        }
    };

    private void move_zombies() {
        Zombie_Factory factory = new Zombie_Factory();
        Timeline zombie_mover = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> factory.create_zombie(Grid_Pane)),
                new KeyFrame(Duration.seconds(8), e -> factory.create_zombie(Grid_Pane))
        );
        zombie_mover.setCycleCount(Animation.INDEFINITE);
        zombie_mover.play();
    }

    public void Place_Plants() {

        Lawn.make_lawn(Grid_Pane);

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
        if (Token.sun_token_counter >= Shooter_Plant.getCost()) {
            PeaShooter_Seed.setImage(PeaShooterSelected);
            Lawn.Grid.setSelector(1);
        }
    }

    @FXML
    public void SunflowerSeedSelected() {
        if (Token.sun_token_counter >= Token_Producing_Plant.getCost()) {
            Sunflower_Seed.setImage(SunFlowerSelected);
            Lawn.Grid.setSelector(2);
        }
    }

    public void All_Deselect(){
        //deselect all the plants , i.e set all seeds to normal

    }

    public void scroll_pane_to_show_zombies() {
        if (flag) {
            scroll_pane.play();
            scroll_pane.setOnFinished((e) -> {
                scroll_pane_reset.play();
                flag = false;
            });
        }
    }
}