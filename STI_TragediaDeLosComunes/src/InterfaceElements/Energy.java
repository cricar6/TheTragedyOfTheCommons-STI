package InterfaceElements;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Energy {

	private PApplet app;
	private int posX, posY;
	private int size;
	private boolean canSelect = true;
	private int energyGiven;
	private int finalEnergy;
	private int energySelected;
	private PImage energy;
	private int demand;
	private boolean sendNotif = false;
	private int delay;
	private boolean aumentarDelay = false;
	private ArrayList<EnergyElement> elements;
	private int retraso;

	public Energy(int posX, int posY, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;

		delay = 0;

		size = 70;
		elements = new ArrayList<EnergyElement>();

		elements.add(new EnergyElement(posX, posY + size, size - 10, 1, app));
		elements.add(new EnergyElement(posX - size, posY + size, size - 10, 2, app));
		elements.add(new EnergyElement(posX - size * 2, posY + size, size - 10, 3, app));
		elements.add(new EnergyElement(posX - size * 3, posY + size, size - 10, 4, app));

		energy = new PImage();
		energy = app.loadImage("/resources/energy2.png");

	}

	public void display() {
		// System.out.println(canSelect);
		app.tint(255, 255);
		for (int i = 0; i < elements.size(); i++) {
			EnergyElement element = elements.get(i);
			element.display();
			if (canSelect == true) {
				app.tint(255, 255);

			}
			if (element.isSelected() == true) {
				canSelect = false;
			}
			if (canSelect == false) {
				element.setVisible(false);

			}
		}
		app.fill(37, 57, 73);
		app.rect(posX, posY, size, size, 15);
		app.image(energy, posX, posY, size, size);
		app.tint(255, 255);

		if (aumentarDelay == true) {
			delay++;
			canSelect = false;
			if (delay == 30) {
				aumentarDelay = false;
				delay = 0;
			}
		}
	}

	public void moved() {

	}

	public void clicked() {
		

		if (canSelect == true) {
			

			if (app.dist(app.mouseX, app.mouseY, posX, posY) < size) {
				for (int i = 0; i < elements.size(); i++) {
					EnergyElement element = elements.get(i);
					element.setVisible(true);
				}
			}
		}

		if (canSelect == true) {
			

			for (int i = 0; i < elements.size(); i++) {
				EnergyElement element = elements.get(i);

				if (element.isVisible() == true) {
					if (app.dist(app.mouseX, app.mouseY, element.getPosX(), element.getPosY()) < element.getSize()) {
						element.setSelected(true);

						energySelected = element.getState();

						if (energySelected == 1)
							energyGiven = (int) (demand * 0.25);
						if (energySelected == 2)
							energyGiven = (int) (demand * 0.50);
						if (energySelected == 3)
							energyGiven = (int) (demand * 1);
						if (energySelected == 4)
							energyGiven = (int) (demand * 2);

						sendNotif = true;
						canSelect = false;
						element.setSelected(false);
					}
				}

			}
		}

	}

	public boolean isCanSelect() {
		return canSelect;
	}

	public void setCanSelect(boolean canSelect) {
		this.canSelect = canSelect;
	}

	public int getEnergySelected() {
		return energySelected;
	}

	public void setEnergySelected(int energySelected) {
		this.energySelected = energySelected;
	}

	public PImage getEnergy() {
		return energy;
	}

	public void setEnergy(PImage energy) {
		this.energy = energy;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public int getEnergyGiven() {
		return energyGiven;
	}

	public void setEnergyGiven(int energyGiven) {
		this.energyGiven = energyGiven;
	}

	public int getFinalEnergy() {
		return finalEnergy;
	}

	public void setFinalEnergy(int finalEnergy) {
		this.finalEnergy = finalEnergy;
	}

	public boolean isSendNotif() {
		return sendNotif;
	}

	public void setSendNotif(boolean sendNotif) {
		this.sendNotif = sendNotif;
	}

}
