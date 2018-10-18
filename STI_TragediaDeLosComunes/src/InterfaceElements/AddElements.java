package InterfaceElements;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class AddElements {

	private PApplet app;
	private int posX, posY;
	private int size;
	private int kind;
	private PImage  house, tree;

	public AddElements(int posX, int posY, int size, int kind, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.kind = kind;

		house = app.loadImage("/resources/house.png");
		tree = app.loadImage("/resources/tree.png");

	}

	public void display() {
		app.ellipse(posX, posY, size, size);

		switch (kind) {
		case 1:
			app.image(house, posX, posY, size-20, size-20);

			break;

		case 2:
			app.image(tree, posX, posY, size-15, size-15);


			break;
		}
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

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

}
