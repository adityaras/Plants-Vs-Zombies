package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;



class Zombies implements Character
{
    private Double attack_pts;
    private Double def_pts;
    private Double Health_pts;
    private Double Speed_pts;
    ImageView holder=new ImageView();
    Timeline zt=new Timeline();
    static ArrayList<Zombies> All_Zombies = new ArrayList<>();


    public void set_image(Image image,int h,int w){
        holder.setImage(image);
        holder.setFitHeight(h);
        holder.setFitWidth(w);
    }

    public void place_and_move_zombie(){
        Random rno=new Random();
        int col=rno.nextInt(3)+12;
        int row=rno.nextInt(5)+4;
        if(Lawn.Grid.Pane==null) System.out.println("Pane is null");
        if(holder==null) System.out.println("Holder is null");
        Lawn.Grid.Pane.add(holder,col,row);

        holder.toFront();
        zt.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0), new KeyValue(holder.translateXProperty(),1000)),
                new KeyFrame(Duration.seconds(60), new KeyValue(holder.translateXProperty(),-1000 ))
        );
        zt.play();
    }

    Zombies(){
        All_Zombies.add(this);
        Health_pts=100.0;
        def_pts=100.0;
        Speed_pts=100.0;
        attack_pts=100.0;

    }

    public void action(GridPane Grid_pane, int col, int row){

    }


    public static void zombie_got_hit(Zombies z,Double Damage) throws ZombieKilledException {
        Double d=Damage-z.getHealth_pts();
        z.setHealth_pts(d);
        System.out.println("\n\n\nHIT\n\n\n");
        /*if(z.Health_pts<=0) throw new ZombieKilledException(z);*/
    }

    public ImageView getHolder() { return holder; }

    public Double getHealth_pts() { return Health_pts; }
    public void setHealth_pts(Double health_pts) { Health_pts = health_pts; }
}

class fast_zombie extends Zombies {
    fast_zombie(GridPane gridPane)
    {

        set_image(new Image("sample/Plants vs Zombies Assets/FastZombie.gif"),45,60);
        place_and_move_zombie();
    }
}

class Normal_zombie extends Zombies{
    Normal_zombie(GridPane gridPane) {
        set_image(new Image("sample/Plants vs Zombies Assets/zombie_normal.gif"),45,50);
        place_and_move_zombie();
    }
}

class Cone_zombie extends Zombies{
    Cone_zombie(GridPane gridPane) {
        set_image(new Image("sample/Plants vs Zombies Assets/Conehead_Zombie.gif"),45,85);
        place_and_move_zombie();
    }
}

class Boss_Zombie extends Zombies{

    Boss_Zombie(GridPane gridPane) {
        set_image(new Image("sample/Plants vs Zombies Assets/BossZombies.gif"),45,80);
        place_and_move_zombie();
    }
}

class Football_Zombie extends Zombies{

    Football_Zombie(GridPane gridPane) {
        set_image(new Image("sample/Plants vs Zombies Assets/zombie_football.gif"),45,50);
        place_and_move_zombie();
    }
}

class Zombie_Factory {
    public Zombies create_zombie(GridPane gridPane) {

        Random rno=new Random();
        int type=rno.nextInt(5)+1;
        if (type == 1 ){ return new Normal_zombie(gridPane); }
        else if (type == 2) { return new fast_zombie(gridPane); }
        else if (type == 3) { return new Cone_zombie(gridPane); }
        else if (type == 4) { return new Football_Zombie(gridPane); }
        else if (type == 5) { return new Boss_Zombie(gridPane); }
        else { return null; }
    }
}