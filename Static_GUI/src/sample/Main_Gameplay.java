package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;

public class Main_Gameplay{
    private static Parent screen;


    public Parent getScreen() {
        return screen;
    }
    public Parent showMainGameplay_Screen()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Game_GUI.class.getResource("Main_Gameplay.fxml"));
           return (ScrollPane) fxmlLoader.load();
           // return screen;
        } catch (IOException  err) {
            System.out.println("Main_GamePlay_Object_Not_Created");
            err.printStackTrace();
            return null;
        }
    }
}
