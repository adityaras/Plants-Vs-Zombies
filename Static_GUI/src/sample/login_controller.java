package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;

public class login_controller {
    @FXML
    public void login_enter_username()
    {
        Main_Menu_Screen mm = new Main_Menu_Screen();
        Main_Menu_Screen.Main_Game.setTitle("Plants Vs Zombies");
        Scene screen=new Scene(mm.showMain_Menu_Screen());
        Main_Menu_Screen.Main_Game.setScene(screen);
        Main_Menu_Screen.Main_Game.show();
        Game_GUI.primary_stage.close();

    }
}
