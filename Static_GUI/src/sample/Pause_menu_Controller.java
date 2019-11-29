package sample;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pause_menu_Controller   {

    public ImageView GoBack;
    public ImageView Main_menu_button;

    private Image goback = new Image("sample/Plants vs Zombies Assets/Go back button@2x.png");
    private Image goback_pressed = new Image("sample/Plants vs Zombies Assets/Go back button@2x_pressed.png");
    private Image main_menu=new Image("sample/Plants vs Zombies Assets/Main Menu.png");
    private Image main_menu_pressed= new Image("sample/Plants vs Zombies Assets/Main_Menu_Pressed.png");

    @FXML
    public void go_back_entered(){ GoBack.setImage(goback_pressed);}
    @FXML
    public void go_back_exited(){ GoBack.setImage(goback);}
    @FXML
    public void go_back()
    {
        Main_Gameplay_Controller.options_stage.close();
        Controller.Game_Play_Stage.setOpacity(1);

    }

    @FXML
    public void main_menu_button_entered(){ Main_menu_button.setImage(main_menu_pressed);}
    @FXML
    public void main_menu__button_exited(){ Main_menu_button.setImage(main_menu);}
    @FXML
    public void Main_menu_button_played()
    {
        Main_Menu_Screen.Main_Game.setOpacity(1);
        //System.out.println("hello");
        Controller.Game_Play_Stage.setOpacity(1);
        Controller.Game_Play_Stage.close();
        //System.out.println("The above statement did not execute Did not execute");
        //Main_Gameplay_Controller.close_my_stage();
        Main_Gameplay_Controller.options_stage.close();
    }
}
