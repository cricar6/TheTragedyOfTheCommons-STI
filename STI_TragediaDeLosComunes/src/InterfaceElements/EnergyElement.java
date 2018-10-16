package InterfaceElements;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class EnergyElement {

	private PApplet app;
	private int posX, posY;
	private int size;
	private int state;
	private boolean visible;
	private int transparency;
	private boolean selected = false;
	private PImage[] energies;


	public EnergyElement(int posX, int posY, int size, int state, PApplet app) {
		this.app = app;

		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.state = state;

		visible = false;
		transparency = 0;
		
		energies = new PImage [4];
	    for (int i = 0; i < energies.length; i++) {
	    	energies[i] = app.loadImage ("/resources/energy"+i+".png");
	    }
	}

	public void display() {

		if (visible == false) {
			transparency = 0;
		} else {
			transparency = 255;
		}

		app.fill(37, 57, 73, transparency);


		app.rect(posX, posY, size, size, 15);
		app.imageMode(app.CENTER);
		switch (state) {
		case 1:
			app.tint(255, transparency);

	     	app.image(energies[0], posX, posY, size, size);

			break;

		case 2:
			app.tint(255, transparency);

	     	app.image(energies[1], posX, posY, size, size);

			break;
		case 3:
			app.tint(255, transparency);

	     	app.image(energies[2], posX, posY, size, size);

			break;
		case 4:
			app.tint(255, transparency);

	     	app.image(energies[3], posX, posY, size, size);

			break;

		}
		app.tint(255, 255);

	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTransparency() {
		return transparency;
	}

	public void setTransparency(int transparency) {
		this.transparency = transparency;
	}

}
