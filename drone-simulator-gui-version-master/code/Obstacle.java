package GUI_package;

public class Obstacle extends Drone_type {

	public Obstacle() {
		
	}
	public Obstacle(double ix, double iy, double ir) {
		super(ix, iy, ir);
		color = 'o';
	}
	
	
	
	
	@Override
	protected void checkDrone(DroneArena da) {
		// TODO Auto-generated method stub

		/*


		for(Drone_type d: da.getManyDrones())
			if(d instanceof Drone)
			if(da.checkHit(d))
					((Drone)d).dAngle = -((Drone)d).dAngle;
		 */

	}

	@Override
	protected void adjustDrone() {
		// TODO Auto-generated method stub

	}
	protected String getStrType() {
		return "Obstacle";
	}
	public String toData() {
		return 2+
				" "+Math.round(x_pos)+
				" "+Math.round(y_pos)+
				" "+rad+
				"\n";

	}

}
