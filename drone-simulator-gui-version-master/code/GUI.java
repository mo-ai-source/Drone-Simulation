package GUI_package;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{

	private GUICanvas cc;
	private VBox rtPane;
	private DroneArena arena;
	private AnimationTimer timer;
	Random rand_Gen = new Random();

	
	private void showAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText(null);
		alert.setContentText("Drone Simulator - GUI version"
				+ "\nby Airy Sam_26017583");
		alert.showAndWait();
	}
	/** setMouseEvents
	 *  set up the mouse event - when mouse pressed.. to do ..
	 */
	void setMouseEvents(Canvas canvas) {
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
			//	arena.getManyDrones().add(new T_Drone(e.getX(), e.getY(), 10, 45, 10));
				arena.getManyDrones().add(new Obstacle(e.getX(), e.getY(), 10));

				drawWorld();
				drawStatus();
			}
		});
	}
	
	MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();
		Menu mFile = new Menu("File");
		// Exit label on menu bar
		MenuItem mExit = new MenuItem("Exit");
		mExit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				timer.stop();
				System.exit(0);
			}
		});
		
		// save label on menu bar
		MenuItem mSave = new MenuItem("Save");
		mSave.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				// call save arena function here
				fileHandle fh = new fileHandle(arena);
				try {
					fh.saveFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		//new
		MenuItem mNew = new MenuItem("New");
		mNew.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				// new Arena
				//clear all drones
				arena.getManyDrones().clear();
				drawWorld();
				drawStatus();
			}
		});
		//load
		MenuItem mLoad = new MenuItem("Load");
		mLoad.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				// load data
				fileHandle fh = new fileHandle(arena);
				try {
					arena = fh.loadFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				drawWorld();
				drawStatus();
			}
		});
		
		mFile.getItems().addAll(mNew);
		mFile.getItems().addAll(mLoad);
		mFile.getItems().addAll(mSave);
		mFile.getItems().addAll(mExit);
		
		Menu mHelp = new Menu("Help");
		MenuItem mAbout = new MenuItem("About");
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
				public void handle(ActionEvent actionEvent) {
					showAbout();
				}
			
		});
		mHelp.getItems().addAll(mAbout);
		menuBar.getMenus().addAll(mFile,mHelp);
		return menuBar;
	}

	public double getRandom(){
		double rx;
		rx = rand_Gen.nextDouble();
		rx = rx* arena.getterXsize();
			return rx;
	}

	
	// set Buttons functoin
	private HBox setButtons() {
		// Drone
		Button btnAdd = new Button("Drone");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//arena.addDrone();
				double rx;
				double ry;
				do{
					rx = getRandom();
					ry = getRandom();
				} while(DroneArena.hasDroneAt(rx, ry));
				arena.getManyDrones().add(new Drone(rx, ry, 10, 45, 2));
				drawWorld();
			}
		});
		// Avoider
		Button btnAvoider = new Button("Avoider");
		btnAvoider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				double rx;
				double ry;
				do{
					rx = getRandom();
					ry = getRandom();
				} while(DroneArena.hasDroneAt(rx, ry));
					arena.getManyDrones().add(new Avoider(rx, ry, 10, 45, 2));
				drawWorld();
			}
		});
		
		// Obstacle
		Button btnObstacle = new Button("Obstacle");
		btnObstacle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				double rx;
				double ry;
				do{
					rx = getRandom();
					ry = getRandom();
				} while(DroneArena.hasDroneAt(rx, ry));
				arena.getManyDrones().add(new Obstacle(rx, ry, 10));
				drawWorld();
			}
		});
		
		
		//Start btn
		Button btnStart = new Button("Start");
		btnStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.start();
			}
		});
		//Pause btn
		Button btnStop= new Button("Pause");
		btnStop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.stop();
			}
		});
		
		return new HBox(new Label("Add Drone: "), btnAdd,btnAvoider, btnObstacle, new Label("Run: "), btnStart, btnStop);
	}
	
	
	public void showScore(double x, double y, int score) {
		cc.showText(x, y, Integer.toString(score));
	}
	public void drawWorld() {
		cc.clearCanvas();
		arena.drawArena(cc);
	}
	public void drawStatus() {
		rtPane.getChildren().clear();
		ArrayList<String>allBs = arena.describeAll();
		for (String s:allBs) {
			Label l = new Label(s);
			rtPane.getChildren().add(l);
		}
	}
	
	
	@Override
	public void start(Stage stage) throws Exception{
		//String Drone="Drone";
		//Label l = new Label(Drone);
		stage.setTitle("Drone Simulation");
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,20,10,20));
		
		bp.setTop(setMenu());
		
		Group root = new Group();
		Canvas canvas = new Canvas(400, 500);
		root.getChildren().add(canvas);
		bp.setLeft(root);
		
		cc = new GUICanvas(canvas.getGraphicsContext2D(), 400, 500);
		
		setMouseEvents(canvas);
		arena = new DroneArena(400, 500); 
		drawWorld();
		
		timer = new AnimationTimer() {
			public void handle(long currentNanoTime) {
				try {
					arena.checkDrones();
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
				arena.adjustDrones();
				drawWorld();
				drawStatus();
				drawStatus();
			}
		};
		
		// 
		
		//
		//information pane on top left
		rtPane = new VBox();
		rtPane.setAlignment(Pos.TOP_LEFT);
		rtPane.setPadding(new Insets(5,75,75,5));
		bp.setRight(rtPane);
		
		//set buttons
		bp.setBottom(setButtons());
		
		
		Scene scene = new Scene(bp, 800, 600);
		bp.prefHeightProperty().bind(scene.heightProperty());
		bp.prefWidthProperty().bind(scene.widthProperty());
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}