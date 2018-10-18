package WorldElements;

import processing.core.PApplet;

public class DetectingZone {

	private PApplet app;
	private int posX, posY, size, state, stateSelected;
	private int g, b, r;
	private int energyCanUse;
	private boolean ocupied;

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

		energyCanUse = 0;

		ocupied= false;
	}

	public void display() {
		
		app.rect(0, 0, size, size);
		app.noStroke();
		app.fill(r, g, b);
		app.rect(0, 0, size - 53, size - 53);
		if (ocupied == true) {
			if (app.dist(posX, posY, app.mouseX, app.mouseY) < size - 60) {
				app.tint(0, 153, 204);
			} else {
				app.tint(255);
			}
		}

	}

	public void moved() {
		app.pushMatrix();

		app.translate(posX, posY);
		app.scale(1f, 0.58f);
		app.rotate(app.radians(45));

		if (ocupied == false && stateSelected !=0) {
			
			if (app.dist(posX, posY, app.mouseX, app.mouseY) < size - 60) {
				state = 3;

			} else {
				state = 0;
			}

		}


		app.popMatrix();

	}

	public void clicked() {


		if (energyCanUse>=50) {
			if (app.dist(posX, posY, app.mouseX, app.mouseY) <= size - 60 && ocupied==false) {
				ocupied = true;

				state = stateSelected;
			}
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

	public void setEnergyCanUse(int energyCanUse) {
		this.energyCanUse = energyCanUse;
	}

	public boolean isOcupied() {
		return ocupied;
	}

	public void setOcupied(boolean ocupied) {
		this.ocupied = ocupied;
	}



	
	

}
