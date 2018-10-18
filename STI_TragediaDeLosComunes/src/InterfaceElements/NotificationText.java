package InterfaceElements;

import processing.core.PApplet;
import processing.core.PImage;

public class NotificationText {
	
	private PApplet app;
	private int posX, posY;
	private int sizeX, sizeY;
	private String notif;
	private int opacity;
	
	public NotificationText(int posX, int posY, String notif, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.app = app;

		this.notif = notif;
		sizeX = app.width / 2;
		sizeY = 50;
		opacity = 255;
	}
	
	public void display () {
		
		app.fill(255, opacity);
		app.textSize(20);
		app.text(notif+"a", posX, posY+10, sizeX, sizeY);
		posY--;
		opacity-=5;
	}

	public int getOpacity() {
		return opacity;
	}

	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}
	
	
}
