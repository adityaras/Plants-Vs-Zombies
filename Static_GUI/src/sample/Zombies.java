package sample;

import javax.swing.text.html.ImageView;

abstract class Zombies implements Character
{
    private Double attack_pts;
    private Double def_pts;
    private Double Health_pts;
    private Double Speed_pts;
    private ImageView holder;

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

    public void use_ability()
    {}

    @Override
    public void action() {

    }
}
class Jump_Zombie extends Zombies{
    private final String name="TRAMPoline";
    public String getName() {
        return name;
    }

    public void use_ability()
    {}

    @Override
    public void action() {

    }
}
class Wolf_Zombies extends Zombies{
    private final String name="Woooooooooooolf";
    public String getName() {
        return name;
    }

    public void use_ability()
    {}
    @Override
    public void action() {

    }
}
