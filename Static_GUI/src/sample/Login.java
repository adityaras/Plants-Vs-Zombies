package sample;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Login implements Runnable{
    private Parent screen;
    @Override
    public void run() {
        screen=showLogin();
    }


    public Parent getScreen() {
        return screen;
    }


    public Parent showLogin()
    {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Game_GUI.class.getResource("Login.fxml"));
            Pane Login = fxmlLoader.load();
            return Login;
        } catch (IOException  err) {
            err.printStackTrace();
            return null;
        }
    }
}
