
package sample;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Settings_Controller {
    @FXML
    private ImageView instructions;
    @FXML
    private ImageView credits;
    @FXML
    private ImageView go_back;
    Image credits_button = new Image("sample/Plants vs Zombies Assets/Credits Button.png");
    Image instructions_button = new Image("sample/Plants vs Zombies Assets/Instructions Button.png");
    Image instructions_pressed = new Image("sample/Plants vs Zombies Assets/INstruction_pressed.png");
    Image credits_pressed = new Image("sample/Plants vs Zombies Assets/credits_pressed.png");
    Image go_back_button = new Image("sample/Plants vs Zombies Assets/Go back button.png");
    Image go_back_pressed = new Image("sample/Plants vs Zombies Assets/Go back button@2x_pressed.png");

    @FXML
    public void instruction_entered()
    { instructions.setImage(instructions_pressed); }
    @FXML
    public void instruction_exited()
    { instructions.setImage(instructions_button); }
    @FXML
    public void instruction_clicked()
    {  }

    @FXML
    public void credits_entered()
    { credits.setImage(credits_pressed); }
    @FXML
    public void credits_exited()
    { credits.setImage(credits_button); }
    @FXML
    public void credits_clicked()
    {  }

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
        Controller.Settings_Stage.close();
    }
}

