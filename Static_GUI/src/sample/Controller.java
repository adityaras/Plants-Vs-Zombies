package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private static Boolean Set_Ownership_Flag=false;

    static Stage Exit_Stage =new Stage(StageStyle.TRANSPARENT);
    static  Stage Ranking_Stage = new Stage(StageStyle.TRANSPARENT);
    static Stage Settings_Stage = new Stage(StageStyle.TRANSPARENT);
    static Stage Loading_Stage = new Stage(StageStyle.TRANSPARENT);;
    static Stage Game_Play_Stage = new Stage(StageStyle.TRANSPARENT);

    public ImageView Play;
    public ImageView Load;
    public ImageView Exit;
    public ImageView Settings;
    public ImageView Ranking;

    private Image play = new Image("sample/Plants vs Zombies Assets/Actual Button.png");
    private Image load = new Image("sample/Plants vs Zombies Assets/load button.png");
    private Image exit = new Image("sample/Plants vs Zombies Assets/Exit button.png");
    private Image Setting = new Image("sample/Plants vs Zombies Assets/Settings button.png");
    private Image ranking = new Image("sample/Plants vs Zombies Assets/Ranking BG.png");
    private Image playpressed = new Image("sample/Plants vs Zombies Assets/Actual Button_Pressed.png");
    private Image loadpressed = new Image("sample/Plants vs Zombies Assets/load button_pressed.png");
    private Image exitpressed = new Image("sample/Plants vs Zombies Assets/Exit button_pressed.png");
    private Image Settingpressed = new Image("sample/Plants vs Zombies Assets/Settings button_pressed.png");
    private Image Rankingpressed = new Image("sample/Plants vs Zombies Assets/Ranking BG_pressed.png");



    @FXML
    public void playentered()
    {
        Play.setImage(playpressed);
    }
    @FXML
    public void playexited()
    {
        Play.setImage(play);
    }
    @FXML
    public void loadentered()
    {
        Load.setImage(loadpressed);
    }
    @FXML
    public void loadexited()
    {
        Load.setImage(load);
    }
    @FXML
    public void settingentered()
    {
        Settings.setImage(Settingpressed);
    }
    @FXML
    public void settingexited()
    {
        Settings.setImage(Setting);
    }
    @FXML
    public void rankingentered()
    {
        Ranking.setImage(Rankingpressed);
    }
    @FXML
    public void rankingexited()
    {
        Ranking.setImage(ranking);
    }
    @FXML
    public void exitentered()
    {
        Exit.setImage(exitpressed);
    }
    @FXML
    public void exitexited()
    {
        Exit.setImage(exit);
    }

    @FXML
    public void playgame() throws IOException {
        Main_Menu_Screen.Main_Game.setOpacity(0.5);
        Game_Play_Stage.setTitle("Plants Vs Zombies");
        FXMLLoader fxmlloader =  new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("Main_GamePlay.fxml"));
        ScrollPane game = (ScrollPane) fxmlloader.load();
        Game_Play_Stage.setScene(new Scene(game, Color.TRANSPARENT));
        Game_Play_Stage.showAndWait();

    }
    @FXML
    public void load_game() throws IOException {
        Main_Menu_Screen.Main_Game.setOpacity(0.5);
        Loading_Stage.setTitle("choose one of your game");
        FXMLLoader fxmlloader =  new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("load_game.fxml"));
        Pane load_game = (Pane) fxmlloader.load();
        Loading_Stage.setScene(new Scene(load_game, Color.TRANSPARENT));
        Loading_Stage.showAndWait();
    }
    @FXML
    public void leaderboard() throws IOException {
        Main_Menu_Screen.Main_Game.setOpacity(0.5);
        Ranking_Stage.setTitle("Top 3 Rankers");
        FXMLLoader fxmlloader =  new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("Rankings.fxml"));
        Pane leaderboard = (Pane) fxmlloader.load();
        Ranking_Stage.setScene(new Scene(leaderboard, Color.TRANSPARENT));
        Ranking_Stage.showAndWait();
    }
    @FXML
    public void settings() throws IOException {
        Main_Menu_Screen.Main_Game.setOpacity(0.5);


        Settings_Stage.setTitle("Settings Menu");
        FXMLLoader fxmlloader =  new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("Settings.fxml"));
        Pane settings_screen = (Pane) fxmlloader.load();
        Settings_Stage.setScene(new Scene(settings_screen, Color.TRANSPARENT));
        Settings_Stage.showAndWait();
    }
    @FXML
    public void exit() throws IOException {
        Main_Menu_Screen.Main_Game.setOpacity(0.5);
        Exit_Stage.setTitle("Exit Prompt");
        FXMLLoader fxmlloader =  new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("Exit.fxml"));
        AnchorPane Exit_Pane = (AnchorPane) fxmlloader.load();
        Exit_Stage.setScene(new Scene(Exit_Pane, Color.TRANSPARENT));
        Exit_Stage.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!Set_Ownership_Flag) {
            Settings_Stage.initOwner(Main_Menu_Screen.Main_Game);
            Settings_Stage.initModality(Modality.WINDOW_MODAL);
            Exit_Stage.initOwner(Main_Menu_Screen.Main_Game);
            Exit_Stage.initModality(Modality.WINDOW_MODAL);
            Ranking_Stage.initOwner(Main_Menu_Screen.Main_Game);
            Ranking_Stage.initModality(Modality.WINDOW_MODAL);
            Game_Play_Stage.initOwner(Main_Menu_Screen.Main_Game);
            Game_Play_Stage.initModality(Modality.WINDOW_MODAL);
            Loading_Stage.initOwner(Main_Menu_Screen.Main_Game);
            Loading_Stage.initModality(Modality.WINDOW_MODAL);

            Set_Ownership_Flag=true;
        }
    }
}
