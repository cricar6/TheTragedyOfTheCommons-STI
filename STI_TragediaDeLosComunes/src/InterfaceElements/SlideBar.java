package InterfaceElements;

import processing.core.PApplet;
import processing.core.PConstants;

public class SlideBar {
	private PApplet app;
	private int posX, posY;
	private int posXB;

	private int size;

	private int start, end;

	public SlideBar(int posX, int posY, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;

		start = -50;
		end = 50;
		size = 25;
		
		posXB = posX-50;
	}

	public void display() {
		app.fill(37, 57, 73);
		app.rect(posX, posY, 100, 10, 10);
		app.fill(81, 97, 109);
		app.ellipse(posXB, posY, size, size);
	}
	
	
	
	public void dragged() {
		if (app.mouseX> posX-50 && app.mouseX<posX+50) {
			if (app.dist(app.mouseX, app.mouseY, posXB, posY)<size) {
				posXB=app.mouseX;
				System.out.println("hi");
	
			}
			
		} 
	}
	
	public void moved () {

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
	
	

}
