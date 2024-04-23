package GUI_package;

import java.util.ArrayList;
import java.util.Random;


public class DroneArena {
    static ArrayList<Drone_type> manyDroneTypes; //Drone class contain in arraylist
	private static int x_size;
	private static int y_size;
	static Random rand_Gen;
	//static Random rand_Gen_Y;
	
	//Drone d;
	DroneArena(){
		this(500,400);
	}
	
	
	
	DroneArena(int x, int y){
		x_size = x;
		y_size = y;
		rand_Gen = new Random();

		setManyDrones(new ArrayList<Drone_type>()); //make the array list
		
		manyDroneTypes.add(new Drone(20, 20, 10, 45, 2));
		manyDroneTypes.add(new Avoider(x/2, y/2, 10, 45, 2));
		manyDroneTypes.add(new Obstacle(x/3, y/4, 15));
		manyDroneTypes.add(new Goal(x-10, y-10, 10));
	}
	
	//functions
	public ArrayList<String> toData() {
		ArrayList<String>res = new ArrayList<String>();
		
		for (Drone_type d : manyDroneTypes) {
			res.add(d.toData());
		}
		return res;
	}
	
	//make element change
	public static void setManyDrones(ArrayList<Drone_type> droneslist) {
		//setManyDrones(Droneslist);
		manyDroneTypes = droneslist;
	}
	
	//getter function
	public ArrayList<Drone_type> getManyDrones() {
		return manyDroneTypes;
	}

	public boolean isHere(int x, int y) {
		for (Drone_type d: manyDroneTypes) {
			if(x==d.getX_pos() && y == d.getY_pos()) {
				return true;
			}
			
		}
		
			return false;
		
	}
	
	/**
	 * Checks if thr Drone can move to given coordinates
	 * @param potential_x -- the given x coordinates
	 * @param potential_y -- the given y coordinates
	 * 
	 * 			make sure there is no drone in the coordinates.
	 * 			check is the moving posistion is out of arena bounds
	 * 			also check the x and y is positive number.
	 * @see DroneAt() - returned true if there  a drone
	 * 
	 * @return boolean true to allows movement, false to denied
	 */
	public boolean canMoveHere(double potential_x, double potential_y) {
		
		if (hasDroneAt(potential_x, potential_y) == true || potential_x >= x_size || potential_y >=y_size || potential_x <= 0 || potential_y <= 0) {
			// if can't go there
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * calls by DroneInterface myArena.moveAllDrones(myArena);
	 * 
	 * @param a
	public void moveAllDrones(DroneArena a, ConsoleCanvas cc) {
		for (Drone d: manyDrones) {
			d.tryToMove(a, cc);
		}
	}
	 */


	/**
	 * @param potential_x
	 * @param potential_y
	 * @see isHere from drone
	 * @return true if there is a drone
	 */
	public static boolean hasDroneAt(double potential_x, double potential_y) {
	boolean torf = false;
		for (Drone_type a : manyDroneTypes) {
			if (a.isHere(potential_x, potential_y) == true) {
				torf = true;
				break;
		}}
		return torf;
	}


	
	
	public int getterXsize() {
		return x_size;
	}
	public int getterYsize() {
		return y_size;
	}




	public void drawArena(GUICanvas cc) {
		// TODO Auto-generated method stub
		for (Drone_type d: manyDroneTypes)
			d.drawDrone(cc);
	}
	public void checkDrones() throws InterruptedException {
		for (Drone_type d: manyDroneTypes)
			d.checkDrone(this);
	}
	public void adjustDrones() {
		for(Drone_type d: manyDroneTypes)
			d.adjustDrone();
	}
	/*
	 *		// Obstacle - blocker 
	public void setObstacle(double x, double y) {
		for (Drone d: manyDrones)
			if(d instanceof Obstacle)
				d.setXY(x, y);
	}
	 */
	public ArrayList<String> describeAll(){
		ArrayList<String> ans = new ArrayList<String>();
		for (Drone_type d: manyDroneTypes)
			ans.add(d.toString());
		return ans;
	}
	public double CheckAvoiderAngle(double x, double y, double rad, double ang, int id) {
		double ans = ang;
		//change direction when hitting walls
		// Avoider
		if (ang < 10 && x > x_size - rad * 2) {
			//y
			//turn right if angle 0 and near top
			ans = 90;
		} else if (ang > 80 & ang < 100 && y > y_size - rad * 2) {
			//x
			//turn if angle 90 and near right
			ans = 180;
		} else if (ang > 170 && ang < 190 && x < rad * 2) {
			//y
			//turn if angle 180 and near bottom
			ans = 270;

		} else if (ang > 260 && y < rad * 2) {
			//x
			//turn if angle 270 and near left
			ans = 0;
		}
		// change direction when hitting others
		for (Drone_type b : manyDroneTypes) //check others drone b for hit
			if (b.uniq_id != id && b.hitting(x, y, rad)) {
				// if hit .. do
				int dir = rand_Gen.nextInt(4);
				if (dir == 0)
					ans = 0;
				if (dir == 1)
					ans = 90;
				if (dir == 2)
					ans = 180;
				if (dir == 3)
					ans = 270;
				if (checkHit(b)) { //check the hitting target
					if (b instanceof Avoider)
						((Avoider) b).dAngle = ((Avoider) b).dAngle;
					if (b instanceof Drone)
						((Drone) b).dAngle = ((Drone) b).dAngle;


				}
			}
		return ans;
	}

	public double CheckDroneAngle(double x, double y, double rad, double ang, int id) {
			double ans = ang;
			if(x<rad*2||x >getterXsize()-rad) {
				ans = 180 - ans;
			}
			if (y < rad*2||y > getterYsize()-rad) {
				ans = -ans ;
			}
			// change direction when hitting others
			for (Drone_type b: manyDroneTypes)
				if(b.uniq_id != id && b.hitting(x,y,rad))
					ans = 180*Math.atan2(y - b.y_pos, x - b.x_pos)/ Math.PI;


		return ans;	//return angle
		
	}
	public boolean checkHit(Drone_type t) {
		boolean ans = false;
		for (Drone_type d: manyDroneTypes) {
			
			if(d instanceof Drone && d.hitting(t))
				ans = true;
			}
		return ans;
	}

	public void checkgoal(Goal g){
		boolean ans = false;
		for (Drone_type d: manyDroneTypes){
			if(d instanceof Drone && d.hitting(g))
				manyDroneTypes.remove(d);
		}

	}



	public void setXY(int loadx, int loady) {
		x_size = loadx;
		y_size = loady;
	}
}
