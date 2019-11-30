package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class Plant implements Character
{
    private Double attack_pts;
    private Double def_pts;
    private Double Health_pts;
    private Double Speed_pts;
    private ImageView Plant_View;
    private Image Plant_GIF;
    private int Cost;

    Plant(){
        attack_pts=100.00;
        Health_pts=100.00;
        Speed_pts=100.00;
        def_pts=100.00;
    }

    @Override
    public void action() {

    }

    public void setPlant_GIF(Image plant_GIF) {
        Plant_GIF = plant_GIF;
        Plant_View.setImage(plant_GIF);
    }
}


class Shooter_Plant extends Plant
{
    private Image bullet_Image;
    private ImageView bullet_view;
    private  int Cost;
    private final String name="Pea_Shooter";
    private Double health_points;

    Shooter_Plant(){
        super();
        super.setPlant_GIF( new Image("sample/Plants vs Zombies Assets/PeaShooter.gif"));

        Cost=100;
    }

    public  void setCost(int cost) {
        Cost = cost;
    }

    @Override
    public void action() {

    }
    public void use_ability()
    {

    }


}
class Sunlight_Producing_Plant extends Plant
{
    private static  int Cost=25;
    private final String name="Sunflower";
    private Double health_points;
    private Double Experience_points;
    public static void setCost(int cost) {
        Cost = cost;
    }
    public Double getHealth_pts() {
        return health_points;
    }

    public Double getExperience_points() {
        return Experience_points;
    }

    public void setExperience_points(Double experience_points) {
        Experience_points = experience_points;
    }

    public void setHealth_points(Double health_points) {
        this.health_points = health_points;
    }

    @Override
    public void action() {

    }
    public void use_ability()
    {

    }
}
class Barrier_Plant extends Plant
{
    private static  int Cost=30;
    private final String name="WallNut";
    private Double health_points;
    private Double Experience_points;
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

    @Override
    public void action() {

    }
    public void use_ability()
    {

    }
}
class Bomb_Plant extends Plant
{

    private static  int Cost=25;
    private final String name="Chilli Bomb";
    private Double health_points;
    private Double Experience_points;

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
    public void action() {

    }
    public void use_ability()
    {

    }
}

class Special_Plant extends Plant
{
    private static  int Cost=75;
    private final String name="Brainflower";
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
    public void action() {

    }
    public void use_ability()
    {

    }
}