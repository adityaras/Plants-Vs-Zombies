package sample;

import java.io.Serializable;
import java.util.ArrayList;
class BackEnd
{
    public static void main1(String args[])
    {
        Game_menu game=new Game_menu();
    }
    public void serialize()
    {

    }
    public void desearalize()
    {

    }
}
interface Character extends Scene_Elements {
    public void move();
    public void action();
}

class Narrator implements Scene_Elements
{
    public void speak()
    {

    }
}
class Shovel implements Character {
public void dig()
{

}

    @Override
    public void move() {

    }

    @Override
    public void action() {

    }
}
class Tile implements Scene_Elements
{
    private Boolean ispit=false;
    private Character contained;

    public Boolean getIspit() {
        return ispit;
    }

    public Character getContained() {
        return contained;
    }

    public void setContained(Character contained) {
        this.contained = contained;
    }

    public void setIspit(Boolean ispit) {
        this.ispit = ispit;
    }
}



class Sun extends Weather
{
    public Sun_Token produce_sun_tokens()
    {
        Sun_Token token=new Sun_Token();
        return token;
    }
}
class Clouds extends Weather
{
    public Shade_Token produce_shade_tokens()
    {
        Shade_Token token=new Shade_Token();
        return token;
    }
}
class Rainy_Clouds extends Weather
{
    public Rain_Token produce_rain_tokens()
    {
        Rain_Token token=new Rain_Token();
        return token;
    }
}


abstract class Plants implements Character
{
private Double attack_pts;
private Double def_pts;
private Double Health_pts;
private Double Speed_pts;

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


class Player implements Serializable, Comparable
{
    private Game game;
    private static final long serialVersionUID = 4L;
    private String name;
    private Character selected_character;
    Player()
    {
        game=new Game();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
    public void play_game(int audio_vol,int fx_vol) throws GamePausedException,GameWinnerException,PlantDiedException,ZombieKilledException {
        game.setMusic_lvl(audio_vol);
        try {
            throw new GamePausedException();
        } catch (GamePausedException e) {


        }
        try {
            throw new GameWinnerException();
        } catch (GameWinnerException e)
        {

        }
        try {
            throw new PlantDiedException();
        } catch (PlantDiedException e)
        {

        }
        try {
            throw new ZombieKilledException();
        }
        catch (ZombieKilledException e)
        {

    }}
    public void place_character()
    {

    }

}
class Game implements Serializable
{
    private int sound_lvl=50;
    private int music_lvl=50;
    private int level;
    private int time;
    private int no_of_sun_tokens;
    private Weather current_weather;
    Tile lawn[][]=new Tile[6][10];//Change when you decide
    public void place_character()
    {

    }

    public void setMusic_lvl(int music_lvl) {
        this.music_lvl = music_lvl;
    }

    public void setSound_lvl(int sound_lvl) {
        this.sound_lvl = sound_lvl;
    }

    public void create_zombies()
    {

    }
    public void playgame()
    {

    }

    public int getTime() {
        return time;
    }

    public int getSound_lvl() {
        return sound_lvl;
    }

    public int getMusic_lvl() {
        return music_lvl;
    }

    public int getLevel() {
        return level;
    }

    public int getNo_of_sun_tokens() {
        return no_of_sun_tokens;
    }

    public Tile[][] getLawn() {
        return lawn;
    }

    public Weather getCurrent_weather() {
        return current_weather;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setCurrent_weather(Weather current_weather) {
        this.current_weather = current_weather;
    }

    public void setLawn(Tile[][] lawn) {
        this.lawn = lawn;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setNo_of_sun_tokens(int no_of_sun_tokens) {
        this.no_of_sun_tokens = no_of_sun_tokens;
    }

}

class Lawnmower implements Character
{
    private Boolean iftouched;
    private Boolean ifused;

    public Boolean getIftouched() {
        return iftouched;
    }

    public void setIftouched(Boolean iftouched) {
        this.iftouched = iftouched;
    }

    public Boolean getIfused() {
        return ifused;
    }

    public void setIfused(Boolean ifused) {
        this.ifused = ifused;
    }

    @Override
    public void move() {

    }

    @Override
    public void action() {

    }
}
class Shooter_Plants extends Plants
{
    private Bullet bullet;
    private  int Cost;
    private final String name="Pea_Shooter";
    private Double health_points;
    private Double Experience_points;
    public  void setCost(int cost) {
        Cost = cost;
    }
    @Override
    public void move() {

    }

    @Override
    public void action() {

    }
    public void use_ability()
    {

    }

    public String getName() {
        return name;
    }

    public  int getCost() {
        return Cost;
    }


    public Double getHealth_pts() {
        return getHealth_pts();
    }

    public Double getExperience_points() {
        return Experience_points;
    }


    public void setHealth_pts(Double health_pts) {
        this.health_points=health_pts;
    }

    public void setExperience_points(Double experience_points) {
        Experience_points = experience_points;
    }
    class Bullet implements Scene_Elements
    {
        private Double Attack_Val;

        public void setAttack_Val(Double attack_Val) {
            Attack_Val = attack_Val;
        }
    }
}
class Sunlight_Producing_Plants extends Plants
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
    public void move() {

    }

    @Override
    public void action() {

    }
    public void use_ability()
    {

    }
}
class Barrier_Plants extends Plants
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
    public void move() {

    }

    @Override
    public void action() {

    }
    public void use_ability()
    {

    }
}
class Bomb_Plants extends Plants
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
    public void move() {

    }

    @Override
    public void action() {

    }
    public void use_ability()
    {

    }
}

class Special_Plant extends Plants
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
    public void move() {

    }

    @Override
    public void action() {

    }
    public void use_ability()
    {

    }
}

class Game_menu{
    private ArrayList<Player> saved_players=new ArrayList<Player>();
    private Settings my_settings;

    public ArrayList<Player> getSaved_players() {
        return saved_players;
    }

    public void setMy_settings(Settings my_settings) {
        this.my_settings = my_settings;
    }

    public Settings getMy_settings() {
        return my_settings;
    }

    public void setSaved_players(ArrayList<Player> saved_players) {
        this.saved_players = saved_players;
    }

    public void start_new_game() {
        try {
            Player new_player = new Player();
            new_player.play_game(my_settings.getMusic_lvl(), my_settings.getSound_lvl());
        }
        catch (Exception e)
        {

        }
    }
    public void load_game()
    {
        System.out.println(saved_players);

    }
    public void exit_game()
    {

    }
    public void view_hall_of_fame()
    {

    }
    public void change_settings()
    {

    }
     class Settings
    {
        private final String about="About Game";
        private final String credits="Developer Information";
        private int sound_lvl;
        private int music_lvl;

        public String getAbout() {
            return about;
        }

        public String getCredits() {
            return credits;
        }

        public void setSound_lvl(int sound_lvl) {
            this.sound_lvl = sound_lvl;
        }

        public void setMusic_lvl(int music_lvl) {
            this.music_lvl = music_lvl;
        }


        public void reset_data()
        {

        }
        public void display_setting_menu()
        {

        }
        public void print_about()
        {

        }
        public void print_credits()
        {

        }
        public void change_audio()
        {

        }

        public int getMusic_lvl() {
            return music_lvl;
        }

        public int getSound_lvl() {
            return sound_lvl;
        }
    }

}

class MyExceptions extends Exception
{

}
class GameWinnerException extends MyExceptions
{

}
class PlantDiedException extends MyExceptions
{

}
class ZombieKilledException extends MyExceptions
{

}
class GamePausedException extends MyExceptions
{

}
