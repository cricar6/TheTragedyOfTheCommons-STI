package InterfaceElements;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Energy {

	private PApplet app;
	private int posX, posY;
	private int size;
	private boolean canSelect = true;
	private int energySelected;
	private PImage energy;

	private ArrayList<EnergyElement> elements;

	public Energy(int posX, int posY, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;

		size = 70;
		elements = new ArrayList<EnergyElement>();
		
		elements.add(new EnergyElement(posX , posY + size, size - 10, 1, app));
		elements.add(new EnergyElement(posX - size , posY + size, size - 10, 2, app));
		elements.add(new EnergyElement(posX - size *2 , posY + size, size - 10, 3, app));
		elements.add(new EnergyElement(posX - size *3 , posY + size, size - 10, 4, app));

		energy = new PImage();
		energy = app.loadImage("/resources/energy2.png");


	}

	public void display() {
		app.tint(255,255);
		for (int i = 0; i < elements.size(); i++) {
			EnergyElement element = elements.get(i);
			element.display();
			if (element.isSelected()==true) {
				canSelect=false;
			}
			if (canSelect == false) {
				element.setVisible(false);
				app.tint(255,40);

			}
		}
		app.fill(37, 57,73);
		app.rect(posX, posY, size, size, 15);
		app.image(energy, posX, posY, size, size);


	}

	public void moved() {
		if (canSelect == true) {
			if (app.dist(app.mouseX, app.mouseY, posX, posY) < size) {
				for (int i = 0; i < elements.size(); i++) {
					EnergyElement element = elements.get(i);
					element.setVisible(true);
				}	
			}	
		}


	}
	
	public void clicked () {
		for (int i = 0; i < elements.size(); i++) {
			EnergyElement element = elements.get(i);
			
			if (element.isVisible()==true) {
				if (app.dist(app.mouseX, app.mouseY, element.getPosX(), element.getPosY())< element.getSize()) {
					element.setSelected(true);
					energySelected = element.getState();
					System.out.println(energySelected);
				}
			}

		}
	}
}
