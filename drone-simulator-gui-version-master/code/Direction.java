package GUI_package;

import java.util.Random;


	public enum Direction{
		NORTH("North"),
		EAST("East"),
		SOUTH("South"),
		WEST("WEST");
		private String direction;

		
		public static Direction getRandomDir() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
		 
		//set direction from the aug
		private Direction(String dir) {
			this.direction=dir;
		}
		
		 
		
		
		@Override
		public String toString() {
			return direction;
		}
		public Direction nextDir() {
			//int dir_size = Direction.values().length -1;
			
			Direction dir = getRandomDir();
			
			if(this.direction == dir.toString()) {
				return nextDir();
			}else {
				return dir;
			}
			
			/*
			 * 
			if(this.ordinal() == dir_size) {
				return values()[0];
			}else {
				return values()[this.ordinal() + 1];
			}
			 */
		}
		
	}
	


