package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.StageStyle;


public class Controller {
    @FXML
    public void login_enter_username()
    {
        Main_Menu_Screen mm = new Main_Menu_Screen();
        Thread Main_Menu = new Thread(mm);
        Main_Menu.start();
        Game_GUI.primary_stage.close();
        Main_Menu_Screen.Main_Game.setScene(new Scene(mm.showMain_Menu_Screen()));
        Main_Menu_Screen.Main_Game.show();

    }


}
