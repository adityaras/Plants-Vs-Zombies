package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;

abstract class Plant implements Character
{
    private StackPane P_StackPane;
    private Double attack_pts;
    private Double def_pts;
    private Double Health_pts;
    private Double Speed_pts;
    private ImageView Plant_View;
    private Image Plant_GIF;
    private int col;
    private int row;
    protected Timeline plant_transitions_timeline;

    public static ArrayList<Plant> All_Plants=new ArrayList<>();

    Plant(int column,int my_row){
        this.col=column;
        this.row=my_row;
        attack_pts=38.0;
        Health_pts=100.00;
        Speed_pts=100.00;
        def_pts=100.00;

        All_Plants.add(this);
    }

    public static void plant_got_hit(Plant p,Double Damage) throws PlantDiedException {
        Double d=p.getHealth_pts()-Damage;
        p.setHealth_pts(d);
        //System.out.println("\n\n\nZombie_Health = "+z.getHealth_pts()+" \n\n\n");
        if(p.Health_pts<=0) throw new PlantDiedException(p);
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {
        //here nothing happens
    }

    public void setPlant_GIF(Image plant_GIF) {
        Plant_GIF = plant_GIF;
        Plant_View=new ImageView();
        Plant_View.setImage(plant_GIF);
        Plant_View.setFitWidth(40);
        Plant_View.setFitHeight(50);
    }
    public ImageView getPlant_View() { return Plant_View; }
    public Double getAttack_pts(){ return attack_pts;}
    public Double getHealth_pts() { return Health_pts; }
    public void setHealth_pts(Double health_pts) {
        Health_pts = health_pts;
    }

    public void setP_StackPane(StackPane p_StackPane) {
        P_StackPane = p_StackPane;
    }

    public StackPane getP_StackPane() {
        return P_StackPane;
    }

    public Timeline getPlant_transitions_timeline() {
        return plant_transitions_timeline;
    }
}

class Shooter_Plant extends Plant
{
    private Image bullet_Image;
    private ImageView Bullet_Holder;
    private static int Cost;
    private final String name="Pea_Shooter";
    private Double health_points;

    Shooter_Plant(int c, int r,StackPane putter){
        super(c,r);
        setP_StackPane(putter);
        setPlant_GIF( new Image("sample/Plants vs Zombies Assets/PeaShootera.gif"));
        bullet_Image=new Image("sample/Plants vs Zombies Assets/ProjectilePea.png");
        Cost=100;
        Token.sun_token_counter-=Cost;
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {
        shoot_peas(Grid_pane,col,row);
    }

    private void shoot_peas(GridPane Grid_pane, int col, int row) {
        plant_transitions_timeline = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> Shoot_a_new_pea(Grid_pane, col, row))
        );
        plant_transitions_timeline.setCycleCount(Timeline.INDEFINITE);
        plant_transitions_timeline.play();
    }

    private void Shoot_a_new_pea(GridPane Grid_pane, int col, int row) {
        //System.out.println("this is being called");
        TranslateTransition plant_transition = new TranslateTransition();
        Bullet_Holder = new ImageView(bullet_Image);
        Bullet_Holder.setVisible(true);
        Bullet_Holder.setFitWidth(20);
        Bullet_Holder.setFitHeight(20);
        //System.out.println("I did it !");
        Grid_pane.add(Bullet_Holder, col, row);
        plant_transition.setNode(Bullet_Holder);
        plant_transition.setByX(1000);
        //plant_transition.setCycleCount(Timeline.INDEFINITE);
        Bullet_Holder.translateXProperty().addListener(checkIntersection);
        //Bullet_Holder.xProperty().addListener();
        plant_transition.setDuration(Duration.seconds(3));
        plant_transition.play();
    }

    private final ChangeListener<Number> checkIntersection = (ob, n, n1) -> {
        try {
            for(Zombies Zombie:Zombies.All_Zombies) {
                //System.out.println("In the for loop");
                if(Bullet_Holder.isVisible()) {
                    if (Bullet_Holder.getBoundsInParent().intersects(Zombie.getHolder().getBoundsInParent())) {
                        //System.out.println("\n\n\n\nDetected\n\n\n\n\n");
                        Bullet_Holder.setVisible(false);
                        Zombies.zombie_got_hit(Zombie, this.getAttack_pts());
                    }
                }
            }
            //System.out.println("pea -----> "+n1.doubleValue()+"  Zombie = "+Zombie1.getBoundsInParent().getMinX());
        } catch (NullPointerException e) {
            System.out.println("__");
        } catch (ZombieKilledException z){

            Lawn.Grid.Pane.getChildren().remove(z.Zombie.holder);
            Zombies.All_Zombies.remove(z.Zombie);
        }
    };

    //setters and getters

    public  void setCost(int cost) {
        Cost = cost;
    }
    public static int getCost() { return Cost; }
}

class Token_Producing_Plant extends Plant
{
    private static  int Cost=25;
    private final String name="Sunflower";
    private Double health_points;
    private Double Experience_points;
    private ImageView Sun_Token_Holder;

    Token_Producing_Plant(int column, int my_row,StackPane putter) {
        super(column, my_row);
        setP_StackPane(putter);
        setPlant_GIF(new Image("sample/Plants vs Zombies Assets/sun_flower.gif"));
        Token.sun_token_counter-=Cost;
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {
        Timeline Plant_transitions_timeline = new Timeline(
                new KeyFrame(Duration.seconds(15), e -> {
                    set_Sun_on_SunFlower(col,row);
                })
        );

        Plant_transitions_timeline.setCycleCount(Animation.INDEFINITE);
        Plant_transitions_timeline.play();
    }

    public void set_Sun_on_SunFlower(int c,int r) {
        Sun_Token_Holder = new ImageView();
        Sun_Token_Holder.setImage(new Image("sample/Plants vs Zombies Assets/sun.gif"));
        Sun_Token_Holder.setPreserveRatio(true);
        Sun_Token_Holder.setFitHeight(35);
        Sun_Token_Holder.setX(50);
        Sun_Token_Holder.setFitWidth(35);

        Lawn.Grid.Pane.add(Sun_Token_Holder,c,r);
        Sun_Token_Holder.toFront();

        Sun_Token_Holder.setOnMouseClicked(event ->
        {
            Lawn.Grid.Pane.getChildren().remove(Sun_Token_Holder);
            Token.sun_token_counter += 50;
        });

    }

    //setters and getters
    static int getCost() { return Cost; }
}

class Barrier_Plant extends Plant {
    private static int Cost = 30;
    private final String name = "WallNut";
    private Double health_points;
    private Double Experience_points;

    Barrier_Plant(int column, int my_row,StackPane putter) {
        super(column, my_row);
        setP_StackPane(putter);
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {

    }

    public void setExperience_points(Double experience_points) {
        Experience_points = experience_points;
    }
}

class Bomb_Plant extends Plant {

    private static  int Cost=25;
    private final String name="Chilli Bomb";
    private Double health_points;
    private Double Experience_points;

    Bomb_Plant(int column, int my_row,StackPane putter) {
        super(column, my_row);
        setP_StackPane(putter);
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {

    }
}

class Special_Plant extends Plant {
    private static  int Cost=75;

    Special_Plant(int column, int my_row,StackPane putter) {
        super(column, my_row);
        setP_StackPane(putter);
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {

    }
}

class Plant_Factory{
    public static Plant make_plant(int Selector,int c,int r,StackPane putter){
        switch(Selector){
            case 1 : return new Shooter_Plant(c,r,putter);
            case 2 : return new Token_Producing_Plant(c,r,putter);
            case 3 : return new Barrier_Plant(c,r,putter);
            case 4 : return new Bomb_Plant(c,r,putter);
            case 5 : return new Special_Plant(c,r,putter);
            default : return null;
        }
    }
}