package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main_Menu_Screen implements Runnable{
    private Parent screen;
    public Stage primary_stage;


    @Override
    public void run() {
        screen=showMain_Menu_Screen();
    }

    public Parent getScreen() {
        return screen;
    }
    public void set_stage( Stage my_stage)
    {
        this.primary_stage=my_stage;
    }
    public Parent showMain_Menu_Screen()
    {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Game_GUI.class.getResource("Main_Menu_Screen.fxml"));
            StackPane Main_Menu_Screen =  fxmlLoader.load();
            return Main_Menu_Screen;
        } catch (IOException  err) {
            System.out.println();
            err.printStackTrace();
            return null;
        }
    }
}
