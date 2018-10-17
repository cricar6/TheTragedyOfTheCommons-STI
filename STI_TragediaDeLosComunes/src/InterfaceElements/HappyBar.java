package InterfaceElements;

import processing.core.PApplet;
import processing.core.PFont;

public class HappyBar {
	private PApplet app;
	private int posX, posY;
	private int size;
	private PFont bebas;
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
		slide = new SlideBar(postemX, postemY, 50, app);

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
		
		indexator = slide.getIndexator();

	}

	public void dragged() {
		slide.dragged();

	}

	public void moved() {
			//slide.moved();
		
	}

	public void released() {
		slide.released();
	}

	public int getIndexator() {
		return indexator;
	}

	public void setIndexator(int indexator) {
		this.indexator = indexator;
	}
	
	
}
