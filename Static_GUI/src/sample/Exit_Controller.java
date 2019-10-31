
package sample;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Exit_Controller {
    @FXML
    public ImageView OK;
    @FXML
    public ImageView goback;

    private Image ok = new Image("sample/Plants vs Zombies Assets/Exit_screen_ok_normal.png");
    private Image cross = new Image("sample/Plants vs Zombies Assets/exitscreen_cross_normal.png");
    private Image okpressed = new Image("sample/Plants vs Zombies Assets/Exit_screen_ok.png");
    private Image crosspressed = new Image("sample/Plants vs Zombies Assets/exitscreen_cross.png");

    @FXML
    public void okenetered()
    { OK.setImage(okpressed); }
    @FXML
    public void playexited()
    { OK.setImage(ok); }
    @FXML
    public void exit()
    {
        System.exit(0);
    }

    @FXML
    public void crossentered()
    { goback.setImage(crosspressed); }
    @FXML
    public void crossexited()
    { goback.setImage(cross); }
    @FXML
    public void play_cross()
    { Main_Menu_Screen.Main_Game.setOpacity(1);
      Controller.Exit_Stage.close(); }

}

