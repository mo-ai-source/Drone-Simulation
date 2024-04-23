package GUI_package;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;

public class GUICanvas {
	int xCS = 512;
	int yCS = 512;
	GraphicsContext gc;
	
	public GUICanvas(GraphicsContext g, int xcs, int ycs) {
		gc = g;
		xCS = xcs;
		yCS = ycs;
	}
	
	//functions
	public void clearCanvas() {
		gc.clearRect(0, 0, xCS, yCS);
	}
	
	public void drawIt(Image i, double x, double y, double sz) {
		gc.drawImage(i, xCS*(x-sz/2), yCS*(y-sz/2), xCS*sz, yCS*sz);
	}
	
	/** function colFromChar
	 * function to convert char c to actual colour used
	 * @param c
	 * @return
	 */
	Color colFromChar (char c) {
		Color ans = Color.BLACK;
		switch (c) {
		case 'y': 	ans = Color.YELLOW;
					break;
		case 'w':   ans = Color.WHITE;
					break;
		case 'r':	ans = Color.RED;
					break;
		case 'g': 	ans = Color.GREEN;
					break;
		case 'b': 	ans = Color.BLUE;
					break;
		case 'o': 	ans = Color.ORANGE;
					break;
		case 'l':	ans = Color.BLACK;
					break;
		
		}
		return ans;
	}
	public void setFillColour (Color c) {
		gc.setFill(c);
	}
	/**
	 * function showCircle()
	 *  - show the ball at position x, y , radius r in colour defined by color
	 * @param x
	 * @param y
	 * @param rad
	 * @param color
	 */
	public void showCircle(double x, double y, double rad, char color) {
		setFillColour(colFromChar(color));
		gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0, 360, ArcType.ROUND);
		
	}
	public void showCircle(double x, double y, double rad, char color, double r, double angle){
		setFillColour(colFromChar(color));
		double radian = Math.toRadians(angle);
		/*
		 *
		 *
		 *
		 */
		//if ((angle > 315 && angle <360) ||( angle > 0 && angle <45)) //up{
			gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0,360, ArcType.ROUND);

		//if (angle > 45 && angle < 135) //right
		//	gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0,360, ArcType.ROUND);
		//if (angle > 135 && angle < 225) //down
		//gc.fillArc(x-rad*2, y-rad*2, rad*2, rad*2, 0,360, ArcType.ROUND);
		//if (angle > 225 && angle <315)//left
		//	gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0,360, ArcType.ROUND);

	}
	public void showSquare(double x, double y, double rad, double angle, char color){
		setFillColour(colFromChar(color));
		//gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0, 360, ArcType.CHORD);

		gc.fillRoundRect(x-rad, y-rad, rad*2, rad*2, 2, 2);
		gc.setStroke(Color.WHITE);
	}

	
	/** function showText()
	 * 	- show Text .. by writing string s at position x, y
	 * @param x
	 * @param y
	 * @param s
	 */
	public void showText(double x, double y, String s) {
	
		// set horizontal alignment
		gc.setTextAlign(TextAlignment.CENTER);
		
		//text colour in white
		gc.setTextBaseline(VPos.CENTER);
		
		// print score as text
		gc.fillText(s,  x, y);
	}
	
	/**	function showInt()
	 * 	- show int .. by writing int i at position x, y
	 * @param x
	 * @param y
	 * @param i
	 */
	public void showInt (double x, double y, int i) {
		showText(x, y, Integer.toString(i));
	}
	
	
	
	
}
