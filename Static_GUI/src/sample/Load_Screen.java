package sample;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class Load_Screen implements Runnable{
    private Parent screen;
    @Override
    public void run() {
    screen=showLoadScreen();
    }

    public Parent getScreen() {
        return screen;
    }

    Parent showLoadScreen()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(Game_GUI.class.getResource("Load_Screen.fxml"));
            StackPane Load_Sceen = (StackPane) fxmlLoader.load();
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), Load_Sceen);
            fadeIn.setFromValue(1);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            fadeIn.setOnFinished((e) -> {
                Login login =new Login();
                Thread logth = new Thread(login);
                logth.start();
                Game_GUI.primary_stage.setScene(new Scene(login.showLogin(),550,400));
                Game_GUI.primary_stage.show();
            });
            return Load_Sceen;
        }
        catch (IOException  err) {
            err.printStackTrace();
            return null;
        }
    }
}
