package WorldElements;

import processing.core.PApplet;

public class DetectingZone {

	private PApplet app;
	private int posX, posY, size, state, stateSelected;
	private int g, b, r;

	public DetectingZone(int posX, int posY, int size, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.app = app;
		r = 240;
		g = 240;
		b = 240;
		state = 0;
		stateSelected = 0;
	}

	public void display() {
		app.rect(0, 0, size, size);
		app.noStroke();
		app.fill(r, g, b);
		app.rect(0, 0, size - 53, size - 53);
	}

	public void moved() {
		app.pushMatrix();

		app.translate(posX, posY);
		app.scale(1f, 0.58f);
		app.rotate(app.radians(45));

		if (app.dist(posX, posY, app.mouseX, app.mouseY) < size - 60) {
			r = 210;
			g = 10;
			b = 10;

		} else {
			r = 240;
			g = 240;
			b = 240;
		}

		app.popMatrix();
	}

	public void clicked() {
		if (app.dist(posX, posY, app.mouseX, app.mouseY) < size - 60) {
			state= stateSelected;
		}
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public int getStateSelected() {
		return stateSelected;
	}

	public void setStateSelected(int stateSelected) {
		this.stateSelected = stateSelected;
	}
	
	
}
