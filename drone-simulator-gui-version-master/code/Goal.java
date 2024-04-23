package GUI_package;

import java.util.ConcurrentModificationException;

public class Goal extends Drone_type {

    public Goal(){

    }
    public Goal(double x, double y, double r){
        super(x, y, r);
        color = 'w';
    }
    @Override
    protected void checkDrone(DroneArena da) throws InterruptedException{
            da.checkgoal(this);
    }
    protected void adjustDrone(){

    }
    protected String getType(){
        return "Goal";
    }
    public String toData(){
        return 3+
                " "+Math.round(x_pos)+
                " "+Math.round(y_pos)+
                " "+rad+
                "\n";
    }

}
