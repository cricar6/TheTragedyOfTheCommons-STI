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
	private int finalEnergy;

	public Square(int posX, int posY, int size, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.app = app;
		app.rectMode(app.CENTER);

		energyCanUse = 0;
		finalEnergy = 0;
		
		detectZone = new DetectingZone(posX, posY, size, app);

		state = 0;
		states = new PImage[3];
		for (int i = 0; i < states.length; i++) {
			states[i] = app.loadImage("/resources/" + i + ".png");
		}
	}

	void display() {

		detectZone.setEnergyCanUse(energyCanUse);
		app.pushMatrix();

		app.translate(posX, posY);
		app.scale(1f, 0.58f);
		app.rotate(app.radians(45));

		detectZone.display();

		app.rect(0, 0, size, size);

		app.popMatrix();

		state = detectZone.getState();
		finalEnergy = detectZone.getFinalEnergy();
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

			app.image(states[2], 0, 0);

			app.popMatrix();
			break;
		case 3:
			detectZone.setState(0);
		}
	}

	void moved() {
		detectZone.moved();
	}

	void clicked() {
		detectZone.setStateSelected(stateSelected);
		detectZone.clicked();
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

	public int getEnergyCanUse() {
		return energyCanUse;
	}

	public void setEnergyCanUse(int energyCanUse) {
		this.energyCanUse = energyCanUse;
	}

	public int getFinalEnergy() {
		return finalEnergy;
	}

	public void setFinalEnergy(int finalEnergy) {
		this.finalEnergy = finalEnergy;
	}

	
}
