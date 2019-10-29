package sample;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Load_Screen implements Runnable{
    private Parent screen;
    private Stage primary_stage;
    @Override
    public void run() {
    screen=showLoadScreen();
    }
    public void set_stage( Stage my_stage)
    {
        this.primary_stage=my_stage;
    }

    public Parent getScreen() {
        return screen;
    }


    public Parent showLoadScreen()
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
                System.out.println("Ya1y");
                Main_Menu_Screen mm =new Main_Menu_Screen();
                System.out.println("Ya2y");
                Thread Main_menu_screen = new Thread(mm);
                System.out.println("Ya3y");
                //mm.set_stage(primary_stage);
                Main_menu_screen.start();
                System.out.println("Ya4y");
                Game_GUI.primary_stage.setScene(new Scene(mm.showMain_Menu_Screen(),600 ,400));
                System.out.println("Ya5y");
                Game_GUI.primary_stage.show();
                System.out.println("Ya6y");

            });
            return Load_Sceen;
        } catch (IOException  err) {
            err.printStackTrace();
            return null;
        }
    }
}
