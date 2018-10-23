package InterfaceElements;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class AddNewElement {

	private int posX, posY;
	private int size, elementsSize;
	private PApplet app;
	private ArrayList<AddElements> elements;
	private int choose;
	private int stateSelected;

	private PImage add;
	
	public AddNewElement(int posX, int posY, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.app = app;

		elements = new ArrayList<AddElements>();

		size = 80;
		elementsSize = 60;
		elements.add(new AddElements(posX, posY - (size ) , elementsSize, 1, app));
		elements.add(new AddElements(posX, posY - (size * 2 -10), elementsSize, 2, app));

		stateSelected = 0;
		
		choose = 0;
		add = app.loadImage("/resources/add.png");


	}

	public void display() {
		app.shapeMode(app.CENTER);
		app.ellipse(posX, posY, size, size);
		app.image(add, posX, posY, size-35, size-35);

		if (choose == 0) {
			
			for (int i = 0; i < elements.size(); i++) {
				AddElements element = elements.get(i);
				element.display();
			}
		}

	}

	public void clicked() {
		if (choose == 0) {
			
			for (int i = 0; i < elements.size(); i++) {
				AddElements element = elements.get(i);
				System.out.println(app.dist(app.mouseX, app.mouseY, element.getPosX(), element.getPosY()));
				if (app.dist(app.mouseX, app.mouseY, element.getPosX(), element.getPosY()) < 50) {
					
					if (element.getKind() == 1) {
						System.out.println("AddNewElement: State 1");
						stateSelected = 1;
						choose = 1;
					}
					
					if (element.getKind() == 2) {
						System.out.println("AddNewElement: State 2");
						stateSelected = 2;
						choose = 2;
					}
				}
			}
		}
	}

	public int getStateSelected() {
		return stateSelected;
	}

	public void setStateSelected(int stateSelected) {
		this.stateSelected = stateSelected;
	}

	public int getChoose() {
		return choose;
	}

	public void setChoose(int choose) {
		this.choose = choose;
	}

	
}
