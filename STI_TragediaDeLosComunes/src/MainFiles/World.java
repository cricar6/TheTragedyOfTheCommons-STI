package MainFiles;

import WorldElements.City;
import processing.core.PApplet;

public class World {

	//Variables declarators
	private int cols, rows;
	private int size;
	private City city; //Square Grid
	PApplet app;

	float zoom = 0;
	boolean startZoom=false;
	boolean zoomIn, zoomOut;
	float x, y;
	
	
	public World (PApplet app) {
		this.app = app;
		cols = 6;
		rows = 8;
		size = 100;
		
		x+= app.width/2;
		y+= app.height/2;
		city = new City (cols, rows, size, app);
		
	}
	
	public void display () {
		city.display();
		
		app.camera(x, y, (app.height/2.0f) / app.tan(app.PI*30.0f / 180.0f) + zoom, x, y, 0f, 0f, 1f, 0f);

		if (zoomIn == true) {
			zoomIn();
		}
		if (zoomOut == true) {
			zoomOut();
		}
	}
	
	private void zoomIn () {
		if (zoomIn) {
			if (zoom < 1000) {
				zoom +=10;
			}
		}
	}
	
	private void zoomOut () {
		if (zoomOut) {
			if (zoom >= 0) {
				zoom -= 10;
			}
		}
	}
	
	public void moved () {
		city.moved();
	}
	
	public void clicked () {
		city.clicked();

		if (zoomIn && zoomOut) {
			zoomIn = true;
		} else if (zoomIn==true) {
			zoomIn=false;
			zoomOut=true;
		} else if (zoomOut==true) {
			zoomOut=false;
			zoomIn=true;
		}

	}

}
