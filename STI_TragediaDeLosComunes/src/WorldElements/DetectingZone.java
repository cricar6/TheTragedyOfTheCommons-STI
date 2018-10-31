package WorldElements;

import java.util.ArrayList;

import InterfaceElements.NotificationText;
import processing.core.PApplet;

public class DetectingZone {

	private PApplet app;
	private int posX, posY, size, state, stateSelected;
	private int g, b, r;
	private int energyCanUse;
	private boolean ocupied;

	private int treesPositioned;

	private ArrayList<NotificationText> notifs;

	public DetectingZone(int posX, int posY, int size, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.app = app;
		r = 184;
		g = 188;
		b = 188;
		state = 0;
		stateSelected = 0;

		energyCanUse = 0;

		ocupied = false;

		notifs = new ArrayList<NotificationText>();

	}

	public void addNotif(String notification) {
		notifs.add(new NotificationText(posX, posY, notification, app));
	}

	public void display() {

		for (int i = 0; i < notifs.size(); i++) {
			NotificationText notif = notifs.get(i);
			notif.display();
			if (notif.getOpacity() <= 0) {
				notifs.remove(notif);
			}
		}

		app.rect(0, 0, size, size);
		app.noStroke();
		app.fill(r, g, b);
		app.rect(0, 0, size - 53, size - 53);
		if (ocupied == true) {
			if (app.dist(posX, posY, app.mouseX, app.mouseY) < size - 60) {
				app.tint(0, 153, 204);
			} else {
				app.tint(255);
			}
		}

	}

	public void moved() {
		app.pushMatrix();

		app.translate(posX, posY);
		app.scale(1f, 0.58f);
		app.rotate(app.radians(45));

		if (ocupied == false && stateSelected != 0) {
			if (app.dist(posX, posY, app.mouseX, app.mouseY) < size - 60) {
				if (stateSelected == 1) {
					state = 6;
				} else if (stateSelected == 2) {
					state = 7;
				}
			} else {
				state = 0;
			}

		}

		app.popMatrix();

	}

	public void clicked() {

		System.out.println(energyCanUse);

		if (app.dist(posX, posY, app.mouseX, app.mouseY) <= size - 60 && ocupied == false) {

			if (stateSelected == 2) {
				ocupied = true;

				state = (int) app.random(4, 6);

				treesPositioned++;

				// app.text("Has puesto un arbol", app.width / 2, app.height - 110);
				notifs.add(new NotificationText(app.width / 2, app.height - 80, "Has puesto un arbol", app));

			}
		}

		if (app.dist(posX, posY, app.mouseX, app.mouseY) <= size - 60 && ocupied == false) {

			ocupied = true;

			state = (int) app.random(1, 4);

			notifs.add(new NotificationText(app.width / 2, app.height - 80, "QUEJESTO", app));
			// app.text("Deberia crear algo aqui", app.width / 2, app.height - 110);

		}

	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public int getStateSelected() {
		return stateSelected;
	}

	public void setStateSelected(int stateSelected) {
		this.stateSelected = stateSelected;
	}

	public void setEnergyCanUse(int energyCanUse) {
		this.energyCanUse = energyCanUse;
	}

	public boolean isOcupied() {
		return ocupied;
	}

	public void setOcupied(boolean ocupied) {
		this.ocupied = ocupied;
	}

	public int getTreesPositioned() {
		return treesPositioned;
	}

	public void setTreesPositioned(int treesPositioned) {
		this.treesPositioned = treesPositioned;
	}

}
