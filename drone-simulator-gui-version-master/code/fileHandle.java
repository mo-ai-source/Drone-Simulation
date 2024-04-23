package GUI_package;

import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

public class fileHandle {
	private JFileChooser chooser;
	private File f;
	private Scanner s;
	private DroneArena da;
	static JLabel l;
	
	public fileHandle(DroneArena data){
		 da = data;
		 chooser = new JFileChooser("");
		 f = new File(System.getProperty("user.dir"));
		 chooser.setCurrentDirectory(f);
		 //s = new Scanner(System.in);
	}
	
	
	
	public void saveFile() throws IOException {
		//JFileChooser chooser = new JFileChooser("");
		JFrame pFrame = new JFrame();
		
		chooser.setDialogTitle("Save arena to: ");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	
	chooser.setFileFilter(new FileFilter() {
		public String getDescription() {
			return "Arena Files (*.arena)";
		}
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}else {
				String fileName = f.getName().toLowerCase();
				return fileName.endsWith(".arena");
			}
			
		}
	});
	//s = new Scanner(System.in);
	//String userInput=" ";
	 
	 
	 
	 
	 
	
	int userSelection = chooser.showSaveDialog(pFrame);
	if(userSelection == JFileChooser.APPROVE_OPTION) {
		f = chooser.getSelectedFile();
		File currDir = chooser.getCurrentDirectory();
		
		FileWriter fw = new FileWriter(f);
		BufferedWriter w = new BufferedWriter(fw);
		
		w.write(Integer.toString(da.getterXsize()));
		w.newLine();
		w.write(Integer.toString(da.getterYsize()));
		w.newLine();
		for (String str: da.toData()) {
			w.write(str);
		}
		w.close();
		
		System.out.println("Arena saved into: "+currDir.getAbsolutePath()+f.getName());
		
	}
	
	
	//chooser.showSaveDialog(f);
	}
	
	public DroneArena loadFile() throws IOException {
		chooser.setDialogTitle("Load arena from: ");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		DroneArena myArena = new DroneArena();
		String fileContents = " ";

		chooser.setFileFilter(new FileFilter(){
			public String getDescription(){
				return "Arena Files (*.arena)";
			}
			public boolean accept(File f){
				if (f.isDirectory()){
					return true;
				}else{
					String fileName = f.getName().toLowerCase();
					return fileName.endsWith(".arena");
				}
			}
		});

		JFrame pFrame = new JFrame();
	int returnVal = chooser.showOpenDialog(pFrame);
	 if(returnVal == JFileChooser.APPROVE_OPTION){
	 	f = chooser.getSelectedFile();
	 	if(chooser.getSelectedFile().isFile()){

	 		myArena.getManyDrones().clear();

	 		//loading

				FileReader fileReader = new FileReader(f);
				BufferedReader reader = new BufferedReader(fileReader);

			fileContents = reader.readLine(); //READ X LINE

			String[] lx = fileContents.split(" ");
			int loadx = Integer.parseInt(lx[0]); //GET X

			fileContents = reader.readLine(); // READ Y LINE
			String[] ly = fileContents.split(" ");
			int loady = Integer.parseInt(ly[0]); //GET Y

			//myArena = new DroneArena(loadx, loady);
			myArena.setXY(loadx, loady);
			fileContents = reader.readLine();

			while (fileContents !=null){
				String[] Drone_element = fileContents.split(" ");
				int type = Integer.parseInt(Drone_element[0]);
				double x = Double.parseDouble(Drone_element[1]);
				double y = Double.parseDouble(Drone_element[2]);
				double r = Double.parseDouble(Drone_element[3]);

				if (type == 0){ //Drone
					double ang = Double.parseDouble(Drone_element[4]);
					double sp = Double.parseDouble(Drone_element[5]);
					//if(ang.trim().isEmpty() || sp.trim().isEmpty())

					myArena.getManyDrones().add(new Drone(x, y, r, ang, sp));
				}else if(type == 1){
					// Avoider
					double ang = Double.parseDouble(Drone_element[4]);
					double sp = Double.parseDouble((Drone_element[5]));
					myArena.getManyDrones().add(new Avoider(x, y, r, ang, sp));
				}else if(type == 2){
					//obstacle
					myArena.getManyDrones().add(new Obstacle(x, y, r));
				}else if(type == 3){
					myArena.getManyDrones().add(new Goal(x, y, r));
				}
				fileContents = reader.readLine();
			}
			reader.close();

		}
	 }


	return myArena;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
