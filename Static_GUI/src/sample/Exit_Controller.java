
package sample;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Exit_Controller {
    public ImageView OK;
    public ImageView goback;

    Image ok = new Image("sample/Plants vs Zombies Assets/Exit_screen_ok_normal.png");
    Image cross = new Image("sample/Plants vs Zombies Assets/exitscreen_cross_normal.png");
    Image okpressed = new Image("sample/Plants vs Zombies Assets/exitscreen_ok.png");
    Image crosspressed = new Image("sample/Plants vs Zombies Assets/exitscreen_cross.png");


    @FXML
    public void okenetered()
    {
        OK.setImage(okpressed);
    }
    @FXML
    public void playexited()
    {
        OK.setImage(ok);
    }
    @FXML
    public void crossentered()
    {
        goback.setImage(crosspressed);
    }
    @FXML
    public void crossexited()
    {
        goback.setImage(cross);
    }

    @FXML
    public void playgoback()
    {

    }
    @FXML
    public void exit()
    {
    System.exit(0);
    }


}

