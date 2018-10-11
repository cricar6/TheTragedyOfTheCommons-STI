package MainFiles;

import processing.core.PApplet;

public class MainApp extends PApplet {
	
	private World world;

	public static void main(String[] args) {
		PApplet.main("MainFiles.MainApp");
	}
	
	@Override 
	public void settings() {
		size (1200, 700, P3D);
	}
	
	@Override
	public void setup() {

		world = new World (this) ;
	}
	
	@Override 
	public void draw() {
		background (255);
		//smooth();
		world.display();
	}
	
	@Override 
	public void mouseMoved() {
		world.moved();
	}
	
	@Override 
	public void mouseClicked() {
		world.clicked();
	}
	
	
}
