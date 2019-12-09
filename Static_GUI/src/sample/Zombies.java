package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
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
    private TranslateTransition Zombie_Moves;
    private Timeline eating;
    ImageView holder=new ImageView();
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

        Lawn.Grid.Pane.add(holder,col,row);

        holder.toFront();

        Zombie_Moves=new TranslateTransition();
        Zombie_Moves.setNode(holder);
        Zombie_Moves.setDuration(Duration.seconds(Speed_pts));
        Zombie_Moves.setByX(-1000);
        holder.translateXProperty().addListener(check_Zombie_Plant_Intersection);
        Zombie_Moves.play();
        /*zt.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0), new KeyValue(holder.translateXProperty(),1000)),
                new KeyFrame(Duration.seconds(Speed_pts), new KeyValue(holder.translateXProperty(),-1000 ))
        );
        zt.play();*/
    }

    private void eat_plant(Plant p){
        eating=new Timeline(
                new KeyFrame(Duration.millis(300),e-> {
                    try {
                        Plant.plant_got_hit(p,this.getAttack_pts());
                        System.out.println("Plant Health - "+p.getHealth_pts());
                    } catch (PlantDiedException ex) {
                        System.out.println("Remove_this_Bitch__  "+ex.plant);

                        System.out.println(ex.plant.getPlant_transitions_timeline());
                        if(ex.plant.getPlant_transitions_timeline()!=null) ex.plant.getPlant_transitions_timeline().stop();

                        ex.plant.getP_StackPane().getChildren().remove(p.getPlant_View());
                        Plant.All_Plants.remove(ex.plant);
                        Zombie_Moves.play();
                        eating.stop();

                    }catch (NullPointerException ne){
                        Zombie_Moves.play();
                    }
                })
        );

        eating.setCycleCount(Timeline.INDEFINITE);
        eating.play();
        eating.setOnFinished(event -> {
            Zombie_Moves.play();
        });
    }


    private final ChangeListener<Number> check_Zombie_Plant_Intersection = (ob, n, n1) -> {
        try{
            for(Plant p: Plant.All_Plants) {
                if(p==null) System.out.println("Pea is NUll");
                if (this.getHolder().getBoundsInParent().intersects(p.getPlant_View().getParent().getBoundsInParent())) {
                    System.out.println("\n\n\nDETECTED ZOMBIE\n\n\nSTART EATING\n\n\n");
                    System.out.println("Plant Health - "+p.getHealth_pts());
                    Zombie_Moves.pause();
                    eat_plant(p);
                }
            }

        }catch (NullPointerException e){
            System.out.println("_____________________");
            Zombie_Moves.play();
        }
    };

    Zombies(){
        All_Zombies.add(this);
        Health_pts=100.0;
        def_pts=100.0;
        Speed_pts=40.0;
        attack_pts=5.0;

    }

    public void action(GridPane Grid_pane, int col, int row){

    }


    public static void zombie_got_hit(Zombies z,Double Damage) throws ZombieKilledException {
        Double d=z.getHealth_pts()-Damage;
        z.setHealth_pts(d);
        //System.out.println("\n\n\nZombie_Health = "+z.getHealth_pts()+" \n\n\n");
        if(z.Health_pts<=0) throw new ZombieKilledException(z);
    }

    public ImageView getHolder() { return holder; }

    public Double getHealth_pts() { return Health_pts; }
    public void setHealth_pts(Double health_pts) { Health_pts = health_pts; }

    public void setSpeed_pts(Double speed_pts) { Speed_pts = speed_pts; }
    public Double getAttack_pts() {
        return attack_pts;
    }
}

class fast_zombie extends Zombies {
    fast_zombie(GridPane gridPane)
    {

        set_image(new Image("sample/Plants vs Zombies Assets/FastZombie.gif"),45,60);
        place_and_move_zombie();
        setSpeed_pts(40.0);
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