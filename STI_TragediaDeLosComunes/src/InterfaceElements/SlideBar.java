package InterfaceElements;

import processing.core.PApplet;
import processing.core.PConstants;

public class SlideBar {
	private PApplet app;
	private int posX, posY;
	private int posXB;

	private int size;
	private int indexator;
	private boolean activated;
	private int min;

	public SlideBar(int posX, int posY, int indexator, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.indexator = indexator;
		activated = false;
		size = 25;
		min = 0;
		posXB = posX;
	}

	public void display() {

		indexator = ((posXB) - posX) + 50;
		if (indexator <= min)
			indexator = min;
		if (indexator >= 100)
			indexator = 100;

		app.fill(37, 57, 73);
		app.rect(posX, posY, 100, 10, 10);
		app.fill(81, 97, 109);
		app.ellipse(posXB, posY, size, size);

		if (activated == true) {
			posXB = app.mouseX;
			app.fill(255);
			app.textSize(25);
			app.text("+" + indexator, posXB, posY - 25);
		}
	}

	public void dragged() {
		if (app.mouseX > posX - 50 + min && app.mouseX < posX + 50) {
			if (app.dist(app.mouseX, app.mouseY, posXB, posY) <= size) {
				activated = true;

			} 

		}else {
			activated = false;
		}
	}

	public void released () {
		activated = false;
	}
	public void moved() {

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

	public int getPosXB() {
		return posXB;
	}

	public void setPosXB(int posXB) {
		this.posXB = posXB;
	}

	public int getIndexator() {
		return indexator;
	}

	public void setIndexator(int indexator) {
		this.indexator = indexator;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	
}
