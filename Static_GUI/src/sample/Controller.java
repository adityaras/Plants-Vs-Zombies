package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;


public class Controller {
    public static Stage Exit_Stage =new Stage(StageStyle.TRANSPARENT);;
    public ImageView Play;
    public ImageView Load;
    public ImageView Exit;
    public ImageView Settings;
    public ImageView Ranking;
    Image play = new Image("sample/Plants vs Zombies Assets/Actual Button.png");
    Image load = new Image("sample/Plants vs Zombies Assets/load button.png");
    Image exit = new Image("sample/Plants vs Zombies Assets/Exit button.png");
    Image Setting = new Image("sample/Plants vs Zombies Assets/Settings button.png");
    Image ranking = new Image("sample/Plants vs Zombies Assets/Ranking BG.png");
    Image playpressed = new Image("sample/Plants vs Zombies Assets/Actual Button_Pressed.png");
    Image loadpressed = new Image("sample/Plants vs Zombies Assets/load button_pressed.png");
    Image exitpressed = new Image("sample/Plants vs Zombies Assets/Exit button_pressed.png");
    Image Settingpressed = new Image("sample/Plants vs Zombies Assets/Settings button_pressed.png");
    Image Rankingpressed = new Image("sample/Plants vs Zombies Assets/Ranking BG_pressed.png");

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
    public void playgame()
    {

    }
    @FXML
    public void savegame()
    {

    }
    @FXML
    public void leaderboard()
    {

    }
    @FXML
    public void settings()
    {

    }
    @FXML
    public void exit() throws IOException {


        Exit_Stage.setTitle("Exit Prompt");

        FXMLLoader fxmlloader =  new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("Exit.fxml"));
        AnchorPane Exit_Pane = (AnchorPane) fxmlloader.load();
        Exit_Stage.setScene(new Scene(Exit_Pane, Color.TRANSPARENT));
        Exit_Stage.showAndWait();
    }


}
