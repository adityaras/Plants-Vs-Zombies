package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import java.io.IOException;

public class Main_Gameplay implements Runnable{
    private static Parent screen;

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
            fxmlLoader.setLocation(Game_GUI.class.getResource("Main_Gameplay.fxml"));
            return (GridPane) fxmlLoader.load();
        } catch (IOException  err) {
            System.out.println("Main_Menu_Object_Not_Created");
            err.printStackTrace();
            return null;
        }
    }
}
