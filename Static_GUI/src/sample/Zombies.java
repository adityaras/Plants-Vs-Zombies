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

abstract class Zombies implements Character
{
    private Double attack_pts;
    private Double def_pts;
    private Double Health_pts;
    private Double Speed_pts;
    protected ImageView holder=new ImageView();
    protected Timeline zt=new Timeline();
    public static ArrayList<Zombies> All_Zombies = new ArrayList<>();

    public Double getSpeed_pts() {
        return Speed_pts;
    }

    public void setSpeed_pts(Double speed_pts) {
        Speed_pts = speed_pts;
    }

    public Double getAttack_pts() {
        return attack_pts;
    }

    public Double getDef_pts() {
        return def_pts;
    }

    public Double getHealth_pts() {
        return Health_pts;
    }

    public void setAttack_pts(Double attack_pts) {
        this.attack_pts = attack_pts;
    }

    public void setDef_pts(Double def_pts) {
        this.def_pts = def_pts;
    }

    public void setHealth_pts(Double health_pts) {
        Health_pts = health_pts;
    }

}

class fast_zombie extends Zombies {
    private final String name="Bolt";

    public String getName() {
        return name;
    }

    fast_zombie(GridPane gridPane)
    {

        super.holder.setImage(new Image("sample/Plants vs Zombies Assets/FastZombie.gif"));
        super.holder.setOnMouseClicked(event ->
        {
            gridPane.getChildren().remove(super.holder);
        });
        super.holder.setFitWidth(60);
        super.holder.setFitHeight(45);
        Random rno=new Random();
        int col=rno.nextInt(3)+12;
        int row=rno.nextInt(5)+4;
        gridPane.add(super.holder,col,row);

        super.holder.toFront();
        super.zt.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0), new KeyValue(super.holder.translateXProperty(),1000)),
                new KeyFrame(Duration.seconds(60), new KeyValue(super.holder.translateXProperty(),-1000 ))
        );
        super.zt.play();
    }
    @Override
    public void action() {

    }
    public void use_ability()
    {}
}
class Normal_zombie extends Zombies{
    private final String name="Clark";

    public String getName() {
        return name;
    }

    Normal_zombie(GridPane gridPane)
    {

        super.holder.setImage(new Image("sample/Plants vs Zombies Assets/zombie_normal.gif"));
        super.holder.setOnMouseClicked(event ->
        {
            gridPane.getChildren().remove(super.holder);
        });
        super.holder.setFitWidth(50);
        super.holder.setFitHeight(45);
        Random rno=new Random();
        int col=rno.nextInt(3)+12;
        int row=rno.nextInt(5)+4;
        gridPane.add(super.holder,col,row);
        super.holder.toFront();
        super.zt.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0), new KeyValue(super.holder.translateXProperty(),1000)),
                new KeyFrame(Duration.seconds(80), new KeyValue(super.holder.translateXProperty(),-1000 ))
        );
        super.zt.play();
    }
    @Override
    public void action() {

    }
    public void use_ability()
    {}
}
class Cone_zombie extends Zombies{

    private final String name="CAPtain Zombie";
    public String getName() {
        return name;
    }
    Cone_zombie(GridPane gridPane)
    {

        super.holder.setImage(new Image("sample/Plants vs Zombies Assets/Conehead_Zombie.gif"));
        super.holder.setOnMouseClicked(event ->
        {
            gridPane.getChildren().remove(super.holder);
        });
        super.holder.setFitWidth(85);
        super.holder.setFitHeight(45);
        Random rno=new Random();
        int col=rno.nextInt(3)+12;
        int row=rno.nextInt(5)+4;
        gridPane.add(super.holder,col,row);
        super.holder.toFront();
        super.zt.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0), new KeyValue(super.holder.translateXProperty(),1000)),
                new KeyFrame(Duration.seconds(80), new KeyValue(super.holder.translateXProperty(),-1000 ))
        );
        super.zt.play();
    }
    public void use_ability()
    {}
    @Override
    public void action() {

    }
}
class Boss_Zombie extends Zombies{
    private final String name="Steve Mobs";
    public String getName() {
        return name;
    }
    Boss_Zombie(GridPane gridPane)
    {

        super.holder.setImage(new Image("sample/Plants vs Zombies Assets/BossZombies.gif"));
        super.holder.setOnMouseClicked(event ->
        {
            gridPane.getChildren().remove(super.holder);
        });
        super.holder.setFitWidth(80);
        super.holder.setFitHeight(45);
        Random rno=new Random();
        int col=rno.nextInt(3)+12;
        int row=rno.nextInt(5)+4;
        gridPane.add(super.holder,col,row);
        super.holder.toFront();
        super.zt.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0), new KeyValue(super.holder.translateXProperty(),1000)),
                new KeyFrame(Duration.seconds(80), new KeyValue(super.holder.translateXProperty(),-1000 ))
        );
        super.zt.play();
    }

    public void use_ability()
    {}

    @Override
    public void action() {

    }
}
class Football_Zombie extends Zombies{
    private final String name="Fooooooooooootball";
    public String getName() {
        return name;
    }
    Football_Zombie(GridPane gridPane)
    {

        super.holder.setImage(new Image("sample/Plants vs Zombies Assets/zombie_football.gif"));
        super.holder.setOnMouseClicked(event ->
        {
            gridPane.getChildren().remove(super.holder);
        });
        super.holder.setFitWidth(50);
        super.holder.setFitHeight(45);
        Random rno=new Random();
        int col=rno.nextInt(3)+12;
        int row=rno.nextInt(5)+4;
        gridPane.add(super.holder,col,row);
        super.holder.toFront();
        super.zt.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0), new KeyValue(super.holder.translateXProperty(),1000)),
                new KeyFrame(Duration.seconds(80), new KeyValue(super.holder.translateXProperty(),-1000 ))
        );
        super.zt.play();
    }

    public void use_ability()
    {}
    @Override
    public void action() {

    }
}

class Zombie_Factory
{
    public Zombies create_zombie(GridPane gridPane)
    {
        Random rno=new Random();
        int type=rno.nextInt(5)+1;
        if (type == 1 )
        {
            return new Normal_zombie(gridPane);
        }
        else if (type == 2)
        {
            return new fast_zombie(gridPane);
        }
        else if (type == 3)
        {
            return new Cone_zombie(gridPane);
        }
        else if (type == 4)
        {
            return new Football_Zombie(gridPane);
        }
        else if (type == 5)
        {
            return new Boss_Zombie(gridPane);
        }
        else
        {
            return null;
        }
    }
}