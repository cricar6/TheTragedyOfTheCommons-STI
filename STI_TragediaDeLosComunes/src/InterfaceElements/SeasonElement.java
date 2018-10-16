package InterfaceElements;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class SeasonElement {

	private PApplet app;
	private int posX, posY;
	private int size, selectedSize, sizeBackup;
	private PShape[] seasons;
	private int state;
	private boolean selected;

	public SeasonElement(int posX, int posY, int size, int state, PApplet app) {
		this.app = app;

		this.posX = posX;
		this.posY = posY;
		this.state = state;

		this.sizeBackup = size;
		this.size = size - 20;
		this.selectedSize = size - 10;
		selected = false;

		seasons = new PShape[4];
		for (int i = 0; i < seasons.length; i++) {
			seasons[i] = app.loadShape("/resources/season" + i + ".svg");
		}
	}

	public void display() {

		for (int i = 0; i < seasons.length; i++) {

			seasons[i].setFill(app.color(211));

		}
		if (selected == true) {
			for (int i = 0; i < seasons.length; i++) {
				seasons[state-1].setFill(app.color(255));
			}
			//System.out.println("workin");

			app.stroke(255);
			app.strokeWeight(3);
			app.noFill();
			app.ellipse(posX, posY, size+10, size+10);
			app.noStroke();
		} 
		app.shapeMode(app.CENTER);
		switch (state) {
		case 1:
			app.shape(seasons[0], posX, posY-2, size, size);
			break;
		case 2:
			app.shape(seasons[1], posX-2, posY-2, size, size);
			break;
		case 3:
			app.shape(seasons[2], posX-2, posY-2, size, size);

			break;
		case 4:
			app.shape(seasons[3], posX-2, posY-2, size, size);

			break;
		}


		//app.ellipse(posX, posY, size, size);
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

}
