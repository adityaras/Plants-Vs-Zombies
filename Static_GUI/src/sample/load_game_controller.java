
package sample;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class load_game_controller {

    @FXML
    public ImageView go_back;

    Image go_back_button = new Image("sample/Plants vs Zombies Assets/Go back button.png");
    Image go_back_pressed = new Image("sample/Plants vs Zombies Assets/Go back button@2x_pressed.png");

    @FXML
    public void go_back_entered()
    { go_back.setImage(go_back_pressed); }
    @FXML
    public void go_back_exited()
    { go_back.setImage(go_back_button);}
    @FXML
    public void play_go_back()
    {
        Main_Menu_Screen.Main_Game.setOpacity(1);
        System.out.println("hello");
        Controller.Loading_Stage.close();
    }

}

