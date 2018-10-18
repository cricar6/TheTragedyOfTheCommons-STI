package InterfaceElements;

import java.util.ArrayList;

import WorldElements.Square;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class NotificationBar {
	private PApplet app;
	private int posX, posY;
	private int sizeX, sizeY;
	private PImage[] icons;
	private int kind;
	private int iconSize;
	private ArrayList<NotificationText> notifs;

	public NotificationBar(int posX, int posY, PApplet app) {
		this.posX = posX;
		this.posY = posY;

		this.app = app;

		sizeX = app.width / 2;
		sizeY = 50;

		iconSize = sizeY - 20;
		kind = 0;
		icons = new PImage[4];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = app.loadImage("/resources/icon" + i + ".png");
		}
		notifs = new ArrayList<NotificationText>();

	}

	public void addNotif(String notification, int kind) {
		this.kind=kind;
		notifs.add(new NotificationText(posX, posY, notification, app));
	}

	public void display() {
		app.fill(37, 57, 73);
		app.rect(posX, posY, sizeX, sizeY, 15);

		for (int i = 0; i < notifs.size(); i++) {
			NotificationText notif = notifs.get(i);
			notif.display();
			if (notif.getOpacity() <= 0) {
				notifs.remove(notif);
			}
		}

		switch (kind) {
		case 0:
			app.image(icons[0], posX - app.width / 4 + iconSize, posY, iconSize, iconSize);
			break;

		case 1:
			app.image(icons[1], posX - app.width / 4 + iconSize, posY, iconSize, iconSize);

			break;
		case 2:
			app.image(icons[2], posX - app.width / 4 + iconSize, posY, iconSize, iconSize);

			break;

		case 3:
			app.image(icons[3], posX - app.width / 4 + iconSize, posY, iconSize, iconSize);

			break;
		}
	}
}
