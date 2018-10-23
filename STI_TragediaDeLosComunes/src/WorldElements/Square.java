package WorldElements;

import processing.core.PApplet;
import processing.core.PImage;

public class Square {

	private PApplet app;

	private int posX, posY, size, state;
	private PImage[] states;
	private DetectingZone detectZone;

	private int stateSelected;

	private int energyCanUse;

	private boolean ocupied;
	
	private int treesPositioned;

	public Square(int posX, int posY, int size, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.app = app;
		app.rectMode(app.CENTER);

		energyCanUse = 0;

		detectZone = new DetectingZone(posX, posY, size, app);

		state = 0;
		states = new PImage[3];
		for (int i = 0; i < states.length; i++) {
			states[i] = app.loadImage("/resources/" + i + ".png");
		}

		ocupied = false;

	}

	int temstate;

	void display() {

		if (temstate != state) {
			detectZone.setState(state);
			detectZone.setOcupied(ocupied);
		}

		if (treesPositioned != 0) {
			detectZone.setTreesPositioned(0);
		}
		if (treesPositioned == 0)
		treesPositioned = detectZone.getTreesPositioned();
		
		temstate = state;
		detectZone.setEnergyCanUse(energyCanUse);
		app.pushMatrix();

		app.translate(posX, posY);
		app.scale(1f, 0.58f);
		app.rotate(app.radians(45));

		detectZone.display();

		app.rect(0, 0, size, size);

		app.popMatrix();

		switch (state) {
		case 0:
			// println ("nothing");

			break;
		case 1:
			// println ("house");
			app.imageMode(app.CENTER);

			app.pushMatrix();
			app.translate(posX, posY - 12);
			app.scale(.233f);

			app.image(states[1], 0, 0);

			app.popMatrix();

			break;
		case 2:
			// println ("tree");

			app.imageMode(app.CENTER);

			app.pushMatrix();
			app.translate(posX, posY - 12);
			app.scale(.233f);

			app.tint(255, 255);
			app.image(states[2], 0, 0);
			app.tint(255, 255);

			app.popMatrix();
			break;
		case 3:
			app.imageMode(app.CENTER);

			app.pushMatrix();
			app.translate(posX, posY - 12);
			app.scale(.233f);

			app.tint(255, 100);
			app.image(states[1], 0, 0);
			app.tint(255, 255);

			app.popMatrix();
			break;
		case 4:
			app.imageMode(app.CENTER);

			app.pushMatrix();
			app.translate(posX, posY - 12);
			app.scale(.233f);

			app.tint(255, 100);
			app.image(states[2], 0, 0);
			app.tint(255, 255);

			app.popMatrix();
			break;
		}
	}

	void moved() {
		detectZone.moved();
		state = detectZone.getState();
	}

	void restartVars() {
		ocupied = false;
		state = 0;
		stateSelected = 0;
		detectZone.setState(0);
		detectZone.setOcupied(false);
		detectZone.setStateSelected(0);
		
	}
	void clicked() {

		setVars();
		detectZone.clicked();
		setVars2();

	}

	public void setVars() {

		detectZone.setOcupied(ocupied);
		detectZone.setStateSelected(stateSelected);
	}
	
	public void setVars2 () {

		state = detectZone.getState();
		ocupied = detectZone.isOcupied();
	}

	public int getPosY() {
		return posY;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTreesPositioned() {
		return treesPositioned;
	}

	public void setTreesPositioned(int treesPositioned) {
		this.treesPositioned = treesPositioned;
	}

	
}
