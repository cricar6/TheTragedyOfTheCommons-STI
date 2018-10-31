package MainFiles;

import processing.core.PApplet;

public class MainApp extends PApplet {

	private World world;


	public static void main(String[] args) {
		PApplet.main("MainFiles.MainApp");
	}

	@Override
	public void settings() {
		 fullScreen(P3D);
		//size(1200, 700, P3D);
	}

	@Override
	public void setup() {

		world = new World(this);
	}

	@Override
	public void draw() {
		background(184, 188, 188);
		smooth();
		world.display();
	}

	@Override
	public void mouseMoved() {
		world.moved();
	}

	@Override
	public void mousePressed() {
			world.clicked();	
	}

	@Override
	public void mouseReleased() {
		world.released();
	}

	public void mouseDragged() {
		world.dragged();
	}

	public void keyPressed() {
		world.kpress();
	}

}
