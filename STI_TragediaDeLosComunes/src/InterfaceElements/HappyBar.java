package InterfaceElements;

import processing.core.PApplet;
import processing.core.PFont;

public class HappyBar {
	private PApplet app;
	private int posX, posY;
	private int size;
	private PFont bebas;
	private boolean activated;
	private SlideBar slide;
	private int indexator;

	public HappyBar(int posX, int posY, PApplet app) {

		this.app = app;
		this.posX = posX;
		this.posY = posY;

		bebas = app.createFont("./resources/fonts/BebasNeue-Regular.otf", 120);

		size = 136;
		
		int postemX = posX;
		int postemY = posY + 55;
		slide = new SlideBar(postemX, postemY, app);
		activated = false;
		
		indexator = 0;
	}

	public void display() {
		app.rectMode(app.CENTER);

		app.fill(37, 57, 73);
		app.rect(posX, posY - 20, size, 40, 15, 15, 0, 0);
		app.rect(posX, posY + 43, size + 20, 90, 15);

		app.fill(255);
		app.textFont(bebas);

		app.textSize(35);
		app.text("Felicidad", posX - size / 2 + 17, posY - 4);

		app.fill(188);
		app.textSize(25);
		app.textAlign(app.CENTER);

		app.text(0, posX - size / 2 + 15, posY + 25);
		app.text(50, posX - 5, posY + 25);
		app.text(100, posX + size / 2 - 25, posY + 25);

		app.rect(posX, posY + 55, size, 45, 15);

		
		slide.display();
		indexator = app.width/app.mouseX;
		if (activated==true) {
			app.fill(255);
			app.textSize(25);
			app.text("+" + indexator, slide.getPosXB(), posY+25);
			slide.dragged();
			
		}
	}

	public void dragged() {
		if (app.dist(app.mouseX, app.mouseY, slide.getPosX(), slide.getPosY())<slide.getSize()) {
			activated = true;
			
		}
	}
	
	public void moved () {
		slide.moved();
	}
	

	

	
	public void released () {
		activated = false;

	}
}
