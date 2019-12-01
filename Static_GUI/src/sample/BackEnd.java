package sample;

import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.util.ArrayList;
class BackEnd
{
    public static void main1(String args[])
    {
        Game_menu game=new Game_menu();
    }

    public void serialize() {

    }
    public void desearalize() {

    }
}

interface Character extends Scene_Elements
{
    public void action(GridPane Grid_pane, int col, int row);
}

class Narrator implements Scene_Elements {
    public void speak()
    {

    }
}

class Shovel implements Character {
    public void dig()
    {

    }

    @Override
    public void action(GridPane Grid_pane, int col, int row) {

    }
}


class Sun extends Weather
{
    public Sun_Token produce_sun_tokens(GridPane gridPane)
    {
        Sun_Token token=new Sun_Token(gridPane);
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
    public void play_game(int audio_vol,int fx_vol) {
        game.setMusic_lvl(audio_vol);

    }
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
    //Tile lawn[][]=new Tile[6][10];//Change when you decide
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

   /* public Tile[][] getLawn() {
        return lawn;
    }*/

    public Weather getCurrent_weather() {
        return current_weather;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setCurrent_weather(Weather current_weather) {
        this.current_weather = current_weather;
    }

    /*public void setLawn(Tile[][] lawn) {
        this.lawn = lawn;
    }*/

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
    public void action(GridPane Grid_pane, int col, int row) {

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

