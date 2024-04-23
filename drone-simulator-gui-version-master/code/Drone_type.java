package GUI_package;

public abstract class Drone_type {
	protected double x_pos;
	protected double y_pos;
	
	protected double rad;
	protected char color;
	
	private static int nexId = 0;
	protected final int uniq_id;
	//private Direction direction;

	
	/**
	 * CONSTRUCTOR
	 *
	 * 
	 * 			
	 * 
	 *
	 */
	
	Drone_type(){
		this(100,100,10);
	}
	Drone_type(double x, double y, double irad){
		x_pos = x;
		y_pos = y;
		rad = irad;
		
		uniq_id = nexId++;
		color = 'b';
	}

	
	/**
	public void changeDirection(Direction nextdir, DroneArena da) {
		System.out.println("Drone "+uniq_id+",\tDir: " + direction.toString()+ " need changes");
		direction = nextdir;
		System.out.println("next Dir is "+ direction.toString()+"\n---------------");
		//tryToMove(da);						//recursion to test
	}
	**/

		
	public String toData() {
		return 0+
				" "+getUniq_id()+
				" "+Math.round(x_pos)+
				" "+Math.round(y_pos)+
				" "+rad+
				".\n";
				
	}
	
	public String toString() {
		
		return getStrType()+" " + getUniq_id() + " at "+ Math.round(x_pos) + " , " + Math.round(y_pos)+ ". \n";
	}
	/**
	 * isHere
	 */
	public boolean isHere (double potential_x2, double potential_y2) {
			if (potential_x2 == getX_pos() && potential_y2 == getY_pos()) {
				return true;
			}
			else
				return false;
	}
	 
	/*
	 * 
	public void displayDrone(ConsoleCanvas c) {
		//call the showIt method in c to put where the drone is
		//for check
		System.out.println(toString() + "Dir: "+direction);//
		char dChar = 'D';
		c.showIt((int)getX_pos(), (int)getY_pos(), dChar);
	}
	 */

	
	//--------setter func---------//

	public void setX_pos(double potential_x2) {
		this.x_pos = potential_x2;
	}
	public void setY_pos(double potential_y2) {
		this.y_pos = potential_y2;
	}

	
	
	//------getter functions------//
	//public Direction getDirection() { return direction; }
	
	public int getDroneID() {
		return getUniq_id();
	}
	
	public double getY_pos() {
		return y_pos;
	}

	
	public int getUniq_id() {
		return uniq_id;
	}
	
	public double getX_pos() {
		return x_pos;
	}
	
	/*
	 * extra function for gui version
	 * 
	 */

	public void drawDrone(GUICanvas cc) {
		// TODO Auto-generated method stub
		cc.showCircle(x_pos, y_pos, rad, color);
	}
	//abstract methods for checking a drone in arena 
	protected abstract void checkDrone(DroneArena da) throws InterruptedException;
	//abstract methods for adjusting a drone (?moving it)
	protected abstract void adjustDrone();
	
	
	
	public boolean hitting(Drone_type oD) {

		return hitting(oD.x_pos, oD.y_pos, oD.rad);
	}
	protected String getStrType() {
		return "Drone";
	}
	/**
	 * function - hitting() 
	 * 	is ball at ox, oy size or hitting this ball
	 *@param ox
	 *@param oy
	 *@param or
	 * @return true if hitting
	 */
	public boolean hitting(double ox, double oy, double or) {
		return (ox-x_pos)*(ox-x_pos) + (oy-y_pos)*(oy-y_pos) < (or+rad)*(or+rad);
	}
	public void setXY(double x, double y) {
		x_pos=x;
		y_pos=y;
	}

	
}
