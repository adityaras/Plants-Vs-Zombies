package sample;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rankings_Controller {

    public ImageView GoBack;

    private Image goback = new Image("sample/Plants vs Zombies Assets/Go back button@2x.png");
    private Image goback_pressed = new Image("sample/Plants vs Zombies Assets/Go back button@2x_pressed.png");

    @FXML
    public void gobackentered(){ GoBack.setImage(goback_pressed);}
    @FXML
    public void gobackexited(){ GoBack.setImage(goback);}
    @FXML
    public void goback()
    {
        Main_Menu_Screen.Main_Game.setOpacity(1);
        Controller.Ranking_Stage.close();
    }
}
