package InterfaceElements;

import java.util.ArrayList;

import processing.core.PApplet;

public class Season {

	private PApplet app;
	private int posX, posY;
	private int sizeX, sizeY;
	private int elementSize;
	private int seasonSelected;
	private ArrayList<SeasonElement> elements;

	public Season (int posX, int posY, int season, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.seasonSelected = season;
		
		elementSize = 50;
		sizeX = elementSize*4+20;
		sizeY = elementSize;
		
		elements = new ArrayList<SeasonElement>();

		elements.add(new SeasonElement(-elementSize - (elementSize/2), elementSize/2, elementSize, 1, app));
		elements.add(new SeasonElement(-elementSize/2, elementSize/2, elementSize, 2, app));
		elements.add(new SeasonElement(+elementSize/2, elementSize/2, elementSize, 3, app));
		elements.add(new SeasonElement(+elementSize+ (elementSize/2), elementSize/2, elementSize, 4, app));
		
		seasonSelected = 3;
	}
	
	public void display() {
		app.pushMatrix();
		app.translate(posX, posY);
		
		app.rectMode(app.CORNER);
		app.fill(37,57,73);
		app.rect(-sizeX/2, 0, sizeX, sizeY, 10,0,10,10);

		app.fill(37,57,73);
		app.rect(0, -25, sizeX/2, 25 , 10,10,0,0);
		
		app.textSize(20);
		app.fill(255);
		app.text("ESTACIONES", +15, 0);
		app.rectMode(app.CENTER);

		
		for (int i = 0; i < elements.size(); i++) {
			SeasonElement element = elements.get(i);
			element.display();
			if (element.getState()==seasonSelected) {
				element.setSelected(true);
			}
		}
		app.popMatrix();
	
		
	}
	
}
