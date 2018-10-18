package InterfaceElements;

import processing.core.PApplet;
import processing.core.PFont;

public class Indicator {
	
	private PApplet app;
	private int posX, posY;
	private int imageSize, barSizeX, barSizeY, diferenceBarImage, indexator;
	private String kind;
	private PFont avenir;


	public Indicator(int posX, int posY, String kind,  PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;

		this.kind = kind;
		
		avenir = app.createFont("./resources/fonts/teko-regular.otf", 120);

		imageSize = 70;
		barSizeX = 55;
		diferenceBarImage = 40;
		barSizeY = imageSize-diferenceBarImage;
		indexator = 0;
		

	}

	public void display() {
		app.textFont(avenir);
		app.pushMatrix();

		app.translate(posX, posY);
		app.ellipse(0, 0, 10, 10);
		app.rectMode(app.CORNER);

		app.fill(37,57,73);
		app.rect(-imageSize/2, (diferenceBarImage/2 - barSizeY/2)-10 , imageSize+barSizeX, barSizeY, 10);
		app.rectMode(app.CENTER);

		app.fill( 183,186,188);
		app.stroke(37,57,73);
		app.strokeWeight(7);
		app.ellipse(-imageSize/2, 0, imageSize, imageSize);

		app.noStroke();
		app.fill(211,215,219);
		app.ellipse(-imageSize/2+5, 0, imageSize-10, imageSize);

		
		app.textAlign(app.CENTER);
		app.fill(37,57,73);
		app.textSize(50);
		app.text(indexator + "", -imageSize/2, 15);
		
		app.textAlign(app.LEFT);
		app.fill(211,215,219);
		app.textSize(20);
		app.text(kind, 10,16);

		// app.rectMode(app.CORNER);
		app.popMatrix();
	}


	public int getIndexator() {
		return indexator;
	}

	public void setIndexator(int indexator) {
		this.indexator = indexator;
	}
	
	
}
