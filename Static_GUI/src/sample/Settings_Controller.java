
package sample;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Settings_Controller {
    public static Stage Instruction_stage= new Stage(StageStyle.TRANSPARENT);
    public static Stage Credits_stage= new Stage(StageStyle.TRANSPARENT);

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
    public void instruction_clicked() throws IOException {
        Controller.Settings_Stage.setOpacity(0.5);
        //Instruction_stage.hide();
        Instruction_stage.setTitle("Instructions Menu");
        FXMLLoader fxmlloader =  new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("Instructions.fxml"));
        Pane instruction_screen = (Pane) fxmlloader.load();
        Instruction_stage.setScene(new Scene(instruction_screen, Color.TRANSPARENT));
        Instruction_stage.showAndWait();
    }

    @FXML
    public void credits_entered()
    { credits.setImage(credits_pressed); }
    @FXML
    public void credits_exited()
    { credits.setImage(credits_button); }
    @FXML
    public void credits_clicked() throws IOException
    {   Controller.Settings_Stage.setOpacity(0.5);
        //Instruction_stage.hide();
        Credits_stage.setTitle("Credits Menu");
        FXMLLoader fxmlloader =  new FXMLLoader();
        fxmlloader.setLocation(Game_GUI.class.getResource("Credits.fxml"));
        Pane credits_screen = (Pane) fxmlloader.load();
        Credits_stage.setScene(new Scene(credits_screen, Color.TRANSPARENT));
        Credits_stage.showAndWait();
    }

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

