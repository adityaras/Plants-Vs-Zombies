package sample;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import java.util.Random;

public class Token implements Scene_Elements {
    public static Integer sun_token_counter=500;
}

class Sun_Token extends Token
{

    private ImageView holder;
    private Image Sun_Token = new Image("sample/Plants vs Zombies Assets/sun.gif");
    private Timeline st = new Timeline();
    Sun_Token(GridPane gridPane)
    {
        holder=new ImageView();
        holder.setImage(Sun_Token);
        holder.setOnMouseClicked(event ->
        {
            gridPane.getChildren().remove(holder);
            super.sun_token_counter+=50;
        });
        holder.setFitWidth(30);
        holder.setFitHeight(30);
        Random rno=new Random();
        int col=rno.nextInt(9)+1;
        int end=rno.nextInt(145)+200;
        gridPane.add(holder,col,0);
        holder.toFront();
        st.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(20), new KeyValue(holder.translateYProperty(),0)),
                new KeyFrame(Duration.seconds(27), new KeyValue(holder.translateYProperty(),end ))
        );

        st.play();

    }
}
class Shade_Token extends Token
{

}
class Rain_Token extends Token
{

}

class Token_Factory
        {
            public Token create_token(GridPane gridPane,int type) {
                if (type==1){
                    return new Sun_Token(gridPane);
                } else if (type == 2) {

                    return new Shade_Token();
                }
                else if (type == 3){

                return new Rain_Token();
            }
                return null;
        }
        }