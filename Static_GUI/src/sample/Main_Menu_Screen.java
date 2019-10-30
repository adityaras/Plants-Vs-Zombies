package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main_Menu_Screen implements Runnable{
    private Parent screen;
    public static Stage Main_Game;

    public Main_Menu_Screen(){
        Main_Game=new Stage(StageStyle.UNIFIED);
    }

    @Override
    public void run() {
        screen=showMain_Menu_Screen();
    }

    public Parent getScreen() {
        return screen;
    }
    public Parent showMain_Menu_Screen()
    {


        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(Game_GUI.class.getResource("Main_Menu_Screen.fxml"));
            return (Pane) fxmlLoader.load();
        } catch (IOException  err) {
            System.out.println("Main_Menu_Object_Not_Created");
            err.printStackTrace();
            return null;
        }
    }
}
