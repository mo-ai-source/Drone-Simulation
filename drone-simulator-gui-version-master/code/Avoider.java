package GUI_package;


//

/**
 *  hugger
 * @author airy
 *
 */

public class Avoider extends Drone {
	double dotSize;

	/**
	 * Constructor
	 * @param ix
	 * @param iy
	 * @param irad
	 * @param iangle
	 * @param ispeed
	 */
	public Avoider(double ix, double iy, double irad, double iangle, double ispeed) {
		super(ix, iy, irad, iangle,ispeed);
		dAngle = 90*Math.floor(iangle/90);
		color = 'b';
		dotSize = rad;
	}

	/**
	 *  Draw the Drone on canvas with it's scencer
	 * @param gc
	 */
	public void drawDrone(GUICanvas gc){
		super.drawDrone(gc);
		gc.showSquare(x_pos, y_pos, rad, dAngle, color);
		double radAngle = dAngle*Math.PI/180; //to radians
		gc.showCircle(x_pos+rad * Math.cos(radAngle),
				y_pos+rad * Math.sin(radAngle), dotSize, 'r', radAngle, dAngle);
	}
	
	
	@Override
	protected void checkDrone(DroneArena da) {
		// TODO Auto-generated method stub
		dAngle = da.CheckAvoiderAngle(x_pos, y_pos, rad, dAngle, uniq_id);

	}

	@Override
	protected void adjustDrone() {
		// TODO Auto-generated method stub
		double radAngle = dAngle*Math.PI/180;
		x_pos += dSpeed * Math.cos(radAngle);
		y_pos += dSpeed * Math.sin(radAngle);
	}
	protected String getStrType() {
		return "Avoider";
	}
	public String toString() {
		return getStrType()+" "
				+ getUniq_id() 
				+ " at "+ Math.round(x_pos) 
				+ " , " + Math.round(y_pos)+
				" moving at speed "+ dSpeed+
				" and angle "+Math.round(dAngle)
				+". \n";
		
	}
	public String toData() {
		return 1+" "
				+ Math.round(x_pos)+" "
				+ Math.round(y_pos)+" "
				+ rad+" "
				+ Math.round(dAngle)+" "
				+ dSpeed
				+"\n";

	}

}
