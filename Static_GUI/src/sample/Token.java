package sample;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.util.Random;

public class Token implements Scene_Elements {
}

class Sun_Token extends Token
{

    private ImageView holder;
    private Image Sun_Token = new Image("sample/Plants vs Zombies Assets/sun.gif");
    private Timeline st = new Timeline();
    Sun_Token(GridPane gridPane)
    {
        holder.setImage(Sun_Token);
        holder.setOnMouseClicked((MouseEvent event) ->{
            System.out.println("noddling");

        });
        Random rno=new Random();
        int col=rno.nextInt(9)+1;
        int end=rno.nextInt(500)+300;
        gridPane.add(holder,col,0);
        holder.toFront();
        st.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(holder.translateYProperty(),0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(holder.translateYProperty(),end ))
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