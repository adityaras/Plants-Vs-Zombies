package sample;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Game_GUI extends Application {
    public static Stage primary_stage;
    @Override
    public void start(Stage primary_stage){
        primary_stage.initStyle(StageStyle.TRANSPARENT);
        primary_stage.setTitle("Plants Vs. Zombies");
        primary_stage.setScene(new Scene(showPreLoadScreen(),600,337, Color.TRANSPARENT));
        Game_GUI.primary_stage = primary_stage;
        Game_GUI.primary_stage.show();
        Platform.setImplicitExit(false);
    }
    public Parent showPreLoadScreen()
    {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Game_GUI.class.getResource("Pre_Load_Screen.fxml"));
            StackPane Pre_Load_Screen = (StackPane) fxmlLoader.load();
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(4), Pre_Load_Screen);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), Pre_Load_Screen);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            fadeIn.play();
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });
            fadeOut.setOnFinished((e) -> {
                Load_Screen l =new Load_Screen();
                Thread load_screen = new Thread(l);
                load_screen.start();
                load_screen.run();
                Game_GUI.primary_stage.setScene(new Scene(l.showLoadScreen(),550,400));
                Game_GUI.primary_stage.show();
            });

            return Pre_Load_Screen;
        } catch (IOException err) {
            err.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
