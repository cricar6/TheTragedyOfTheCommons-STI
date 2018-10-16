package InterfaceElements;

import processing.core.PApplet;

public class AddElements {

	private PApplet app;
	private int posX, posY;
	private int size;
	private int kind;
	
	
	public AddElements (int posX, int posY, int size, int kind, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.kind = kind;
		
	}
	
	public void display() {
		app.fill(25);
		app.ellipse(posX, posY, size, size);
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
