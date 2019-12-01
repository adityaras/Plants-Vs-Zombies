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
    private Double attack_pts;
    private Double def_pts;
    private Double Health_pts;
    private Double Speed_pts;
    private ImageView Plant_View;
    private Image Plant_GIF;
    private int col;
    private int row;

    public static ArrayList<Plant> All_Plants=new ArrayList<>();

    Plant(int column,int my_row){
        this.col=column;
        this.row=my_row;
        attack_pts=30.00;
        Health_pts=100.00;
        Speed_pts=100.00;
        def_pts=100.00;

        All_Plants.add(this);
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

}


class Shooter_Plant extends Plant
{
    private Image bullet_Image;
    private ImageView Bullet_Holder;
    private static int Cost;
    private final String name="Pea_Shooter";
    private Double health_points;

    Shooter_Plant(int c, int r){
        super(c,r);
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
        Timeline Pea_shots_timeline = new Timeline(
                new KeyFrame(Duration.millis(3100), event -> Shoot_a_new_pea(Grid_pane, col, row))
        );
        Pea_shots_timeline.setCycleCount(Timeline.INDEFINITE);
        Pea_shots_timeline.play();
    }

    private void Shoot_a_new_pea(GridPane Grid_pane, int col, int row) {
        System.out.println("this is being called");
        TranslateTransition pea_shot = new TranslateTransition();
        Bullet_Holder = new ImageView(bullet_Image);
        Bullet_Holder.setVisible(true);
        Bullet_Holder.setFitWidth(20);
        Bullet_Holder.setFitHeight(20);
        System.out.println("I did it !");
        Grid_pane.add(Bullet_Holder, col, row);
        pea_shot.setNode(Bullet_Holder);
        pea_shot.setByX(1000);
        //pea_shot.setCycleCount(Timeline.INDEFINITE);
        Bullet_Holder.translateXProperty().addListener(checkIntersection);
        //Bullet_Holder.xProperty().addListener();
        pea_shot.setDuration(Duration.seconds(3));
        pea_shot.play();
    }

    private final ChangeListener<Number> checkIntersection = (ob, n, n1) -> {
        try {
            for(Zombies Zombie:Zombies.All_Zombies) {
                if (Bullet_Holder.getBoundsInParent().intersects(Zombie.getHolder().getBoundsInParent())) {
                    System.out.println("Detected");
                    Bullet_Holder.setVisible(false);
                    /*Zombies.zombie_got_hit(Zombie,this.getAttack_pts());*/
                }
            }
            //System.out.println("pea -----> "+n1.doubleValue()+"  Zombie = "+Zombie1.getBoundsInParent().getMinX());
        } catch (NullPointerException e) {
            System.out.println("__");
        } /*catch (ZombieKilledException z){
            Lawn.Grid.Pane.getChildren().remove(z.Zombie);
            Zombies.All_Zombies.remove(z.Zombie);
        }*/
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

    Token_Producing_Plant(int column, int my_row) {
        super(column, my_row);
        setPlant_GIF(new Image("sample/Plants vs Zombies Assets/sun_flower.gif"));
        Token.sun_token_counter-=Cost;
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {
        Timeline sun_token_sunflower = new Timeline(
                new KeyFrame(Duration.seconds(15), e -> {
                    set_Sun_on_SunFlower(col,row);
                })
        );

        sun_token_sunflower.setCycleCount(Animation.INDEFINITE);
        sun_token_sunflower.play();
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

    Barrier_Plant(int column, int my_row) {
        super(column, my_row);
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {

    }

    public void use_ability() {

    }

    //setters and getters

    public static void setCost(int cost) {
        Cost = cost;
    }

    public Double getExperience_points() {
        return Experience_points;
    }

    public Double getHealth_points() {
        return health_points;
    }

    public static int getCost() {
        return Cost;
    }

    public String getName() {
        return name;
    }

    public void setHealth_points(Double health_points) {
        this.health_points = health_points;
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

    Bomb_Plant(int column, int my_row) {
        super(column, my_row);
    }

    public static void setCost(int cost) {
        Cost = cost;
    }

    public Double getHealth_points() {
        return health_points;
    }

    public String getName() {
        return name;
    }

    public Double getExperience_points() {
        return Experience_points;
    }

    public void setHealth_points(Double health_points) {
        this.health_points = health_points;
    }

    public void setExperience_points(Double experience_points) {
        Experience_points = experience_points;
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {

    }
    public void use_ability()
    {

    }
}

class Special_Plant extends Plant {
    private static  int Cost=75;
    private final String name="Brainflower";

    Special_Plant(int column, int my_row) {
        super(column, my_row);
    }

    public static void setCost(int cost) {
        Cost = cost;
    }
    public String getName() {
        return name;
    }

    public static int getCost() {
        return Cost;
    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {

    }
    public void use_ability()
    {

    }
}

class Plant_Factory{
    public static Plant make_plant(int Selector,int c,int r){
        switch(Selector){
            case 1 : return new Shooter_Plant(c,r);
            case 2 : return new Token_Producing_Plant(c,r);
            case 3 : return new Barrier_Plant(c,r);
            case 4 : return new Bomb_Plant(c,r);
            case 5 : return new Special_Plant(c,r);
            default : return null;
        }
    }
}