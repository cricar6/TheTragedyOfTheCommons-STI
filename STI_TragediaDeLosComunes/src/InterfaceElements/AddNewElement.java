package InterfaceElements;

import java.util.ArrayList;

import processing.core.PApplet;

public class AddNewElement {

	private int posX, posY;
	private int size;
	private PApplet app;
	private ArrayList<AddElements> elements;

	int stateSelected;

	public AddNewElement(int posX, int posY, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.app = app;

		elements = new ArrayList<AddElements>();

		size = 50;
		elements.add(new AddElements(posX, posY - (size +10) , size - 10, 1, app));
		elements.add(new AddElements(posX, posY - (size * 2 + 10), size - 10, 2, app));

		stateSelected = 0;
	}

	public void display() {
		app.ellipse(posX, posY, size, size);

		for (int i = 0; i < elements.size(); i++) {
			AddElements element = elements.get(i);
			element.display();
		}

	}

	public void clicked() {
		for (int i = 0; i < elements.size(); i++) {
			AddElements element = elements.get(i);
			System.out.println(app.dist(app.mouseX, app.mouseY, element.getPosX(), element.getPosY()));
			if (app.dist(app.mouseX, app.mouseY, element.getPosX(), element.getPosY()) < 50) {

				if (element.getKind() == 1) {
					System.out.println("AddNewElement: State 1");
					stateSelected = 1;
				}

				if (element.getKind() == 2) {
					System.out.println("AddNewElement: State 2");
					stateSelected = 2;
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

}
