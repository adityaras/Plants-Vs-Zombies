package sample;

import com.sun.org.apache.bcel.internal.generic.LADD;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

//Singleton Pattern Implemented

public class Lawn {

    private static int Selector;
    private static Plant Active_Plant;

    public GridPane Pane;
    public static Lawn Grid;

    public static void make_lawn(GridPane Grid_Pane){

        if(Lawn.Grid==null) Lawn.Grid=new Lawn(Grid_Pane);
    }

    public void setSelector(int selector) {
        Selector = selector;
    }


    public Lawn(GridPane Grid_Pane){
        Pane=Grid_Pane;
        for (int row = 4; row < 9; row++) {
            StackPane Image_Holder = null;
            for (int col = 0; col < 10; col++) {
                Image_Holder = new StackPane();

                Grid_Pane.add(Image_Holder, col, row);

                final int frow = row;
                final int fcol = col;

                Image_Holder.setOnMouseClicked((MouseEvent e1) -> {

                    StackPane putter = (StackPane) e1.getSource();

                    //selector is set through injected functions in the fxml

                    if (Selector > 0 && Selector < 6) {

                        if (putter.getChildren().isEmpty()) {

                            //setting which one is the Active_Plant
                            Active_Plant=Plant_Factory.make_plant(Selector,fcol,frow);

                            putter.getChildren().addAll(Active_Plant.getPlant_View()); //adding the selected plant to Stack_pane
                            Active_Plant.action(Grid_Pane, fcol,frow);

                            Selector = 0;
                        }
                    }
                });
            }
        }
    }
}