package sample;

abstract class Plant implements Character
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