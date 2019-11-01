
package sample;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Instructions_Controller {
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
        Controller.Settings_Stage.setOpacity(1);
        Settings_Controller.Instruction_stage.close();
    }
}

