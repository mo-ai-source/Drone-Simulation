package GUI_package;


/**
 *  bouncer
 *  
 *  it movment with change direction as 90 degree if it hitting Obstacle or other object
 * @author airys
 *
 */
public class Drone extends Drone_type {

	double dAngle;
	double dSpeed;

	/**
	 *  T_Drone constructor
	 * @param ix
	 * @param iy
	 * @param irad
	 * @param iangle
	 * @param ispeed
	 */
	public Drone(double ix, double iy, double irad, double iangle, double ispeed) {
		super(ix, iy, irad);
		//dAngle = 90 * Math.floor(iangle/90);
		dSpeed = ispeed;
		dAngle = iangle;
		color = 'l';

		//dSpeed = ispeed;
	}


	/**
	 * 	check the drone angle.. if it hit wall or others, adjust the angle.
	 * @param da
	 */
	@Override
	protected void checkDrone(DroneArena da) {
		// TODO Auto-generated method stub
		dAngle = da.CheckDroneAngle(x_pos, y_pos, rad, dAngle, uniq_id);



	}

	@Override
	protected void adjustDrone() {
		// TODO Auto-generated method stub
		double radAngle = dAngle*Math.PI/180;
		x_pos += dSpeed * Math.cos(radAngle);
		y_pos += dSpeed * Math.sin(radAngle);

	}

	/**
	 *
	 * @return String "Drone"
	 */
	protected String getStrType() {
		return "Drone";
	}
	@Override
	public String toString() {
		double radAngle = dAngle*Math.PI/180;
		return getStrType()+" " 
				+ getUniq_id() 
				+ " at "+ Math.round(x_pos) 
				+ " , " + Math.round(y_pos)+
				" moving at speed "+ dSpeed+
				" and angle "+Math.floor(dAngle)
				+". \n";
		
	}

	/**
	 *
	 * @return all the elements of the Drone
	 */
	public String toData() {
		return 0+
				" "+Math.round(x_pos)+
				" "+Math.round(y_pos)+
				" "+rad+
				" "+dAngle+
				" "+dSpeed+
				"\n";
				
	}
	
}
