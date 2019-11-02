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

class MyImageView{
    static Image tile_selected=new Image("sample/Plants vs Zombies Assets/Tile_Selected.png");
    static Image tile_not_selected=new Image("sample/Plants vs Zombies Assets/Tile_not_selected.png");
    static Image pea_shooter=new Image("sample/Plants vs Zombies Assets/PeaShootera.gif");
    static Image sunflower=new Image("sample/Plants vs Zombies Assets/sunflower.gif");
    static Image selected_plant;
    ImageView my_image_container;

    @FXML
    public void credits_entered()
    { my_image_container.setImage(tile_selected); }
    @FXML
    public void credits_exited()
    { my_image_container.setImage(tile_not_selected); }
    @FXML
    public void credits_clicked() throws IOException
    {

    }

}

public class Test_Main_Game_Controller {


}
