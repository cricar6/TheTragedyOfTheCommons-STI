package MainFiles;

import InterfaceElements.AddElements;
import InterfaceElements.AddNewElement;
import InterfaceElements.Energy;
import InterfaceElements.HappyBar;
import InterfaceElements.Indicator;
import InterfaceElements.Season;
import InterfaceElements.Timer;
import WorldElements.City;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class World {

	private int screen;

	// Variables declarators
	private int cols, rows;
	private int size;
	private City city; // Square Grid
	PApplet app;
	private PFont teko, bebas, brush;

	float zoom = 0;
	boolean startZoom = false;
	boolean zoomIn, zoomOut;
	float x, y;

	private String ipPlaceHolder = "Escribe la ip del servidor...";
	private String ip = ipPlaceHolder;

	private String cityNamePlaceHolder = "Ciudad";
	private String cityName = cityNamePlaceHolder;

	// Splash Screen
	private PImage[] backgrounds;
	private float frontx, frontx2;
	private float middlex, middlex2;
	private float backx, backx2;

	private PImage logo;

	// Interface Objects
	private Indicator people;
	private Indicator energyForUse;
	private Season seasonIndicator;
	private Energy energyIndicator;
	private AddNewElement add;
	private HappyBar happy;
	private Timer timer;
	private boolean restartTimer;

	// Logical variables
	private int startEnergy, energyWasting, finalEnergy, population, demand, happiness, happinessThisTurn, season;
	private boolean alive;

	public World(PApplet app) {
		this.app = app;
		teko = app.createFont("./resources/fonts/teko-regular.otf", 120);
		bebas = app.createFont("./resources/fonts/BebasNeue-Regular.otf", 120);
		brush = app.createFont("./resources/fonts/Brush-Script.ttf", 120);

		screen = 0;
		alive = true;
		
		// Splash
		backgrounds = new PImage[3];
		for (int i = 0; i < backgrounds.length; i++) {
			backgrounds[i] = app.loadImage("/resources/back" + i + ".png");
		}
		frontx = 0;
		frontx2 = -app.width * 2;
		middlex = 0;
		middlex2 = -app.width * 2;
		backx = 0;
		backx2 = -app.width * 2;

		logo = app.loadImage("/resources/logo.png");

		// Logical variables
		startEnergy = 500;
		energyWasting = 0;
		population = 0;
		demand = 0;
		happiness = 50;
		happinessThisTurn = 0;
		season = 1;

		
		// Interface
		people = new Indicator(100, 60, population + "", "POBLACIÓN", app);
		energyForUse = new Indicator(300, 60, demand + "", "DEMANDA", app);

		add = new AddNewElement(app.width - 100, app.height - 100, app);

		seasonIndicator = new Season(app.width - 150, 60, season, app);
		energyIndicator = new Energy(app.width - 75, 170, app);

		happy = new HappyBar(100, app.height - 100, app);

		timer = new Timer();
		Thread t = new Thread(timer);
		t.start();
		restartTimer = false;

		// City vars
		cols = 6;
		rows = 8;
		size = 100;

		x += app.width / 2;
		y += app.height / 2;
		city = new City(cols, rows, size, app);


		/*
		 * al iniciar turno finalEnergy es igual a la resta de energyWasting y
		 * startEnergy
		 * 
		 * 
		 */

	}

	public void display() {

		//variable changer
		finalEnergy = startEnergy-energyWasting;
		if (finalEnergy <= 0) alive = false;
		city.setEnergyCanUse(finalEnergy);
		finalEnergy = city.getFinalEnergy();
		
		people.setIndexator(population);
		energyForUse.setIndexator(demand);
		happiness = happy.getIndexator();
		
		System.out.println("StartEnergy" + startEnergy + "EnergyWasting" +energyWasting + "finalEnergy" + finalEnergy);
		
		app.camera(x, y, (app.height / 2.0f) / app.tan(app.PI * 30.0f / 180.0f) + zoom, x, y, 0f, 0f, 1f, 0f);

		switch (screen) {
		case 0:
			timer.setStop(true);
			// System.out.println("Splash screen");

			app.imageMode(app.CENTER);

			app.image(backgrounds[2], backx, app.height - 270);
			app.image(backgrounds[2], backx2, app.height - 270);

			app.image(backgrounds[1], middlex, app.height - 200);
			app.image(backgrounds[1], middlex2, app.height - 200);

			app.image(backgrounds[0], frontx, app.height - 110);
			app.image(backgrounds[0], frontx2, app.height - 110);

			frontx++;
			frontx2++;
			middlex += 0.7;
			middlex2 += 0.7;
			backx += 0.5;
			backx2 += 0.5;

			if (frontx >= app.width * 2) {
				frontx = -app.width * 2;
			}
			if (frontx2 >= app.width * 2) {
				frontx2 = -app.width * 2;
			}
			if (middlex >= app.width * 2) {
				middlex = -app.width * 2;
			}
			if (middlex2 >= app.width * 2) {
				middlex2 = -app.width * 2;
			}
			if (backx >= app.width * 2) {
				backx = -app.width * 2;
			}
			if (backx2 >= app.width * 2) {
				backx2 = -app.width * 2;
			}

			app.image(logo, app.width / 2, app.height / 2, 180, 240);

			app.fill(255);
			app.textFont(teko);
			app.textAlign(app.CENTER);
			app.textLeading(20);
			app.textSize(30);
			app.text("Presiona ENTER para continuar", app.width / 2, app.height, 400, 70);

			break;
		case 1:
			app.textAlign(app.CENTER);
			app.tint(37, 57, 23);
			app.image(logo, app.width / 2, 120, 120, 180);
			app.tint(255);

			app.fill(37, 57, 73);
			app.textFont(bebas);

			app.textSize(80);
			app.text("¡Bienvenido!", app.width / 2, app.height / 2 - 100, app.width, 100);

			app.textFont(teko);
			app.textSize(30);
			app.fill(56, 88, 109);

			app.text(
					"Antes de empezar nos gustaría que firmaras algunos papeles. Ya sabes, cosas de protocolo. Cuéntanos ¿cómo se llamará tu ciudad?",
					app.width / 2, app.height / 2 + 70, 700, 200);

			app.textSize(30);
			app.textFont(brush);
			app.text(cityName, app.width / 2, app.height / 2 + 250, app.width, 400);
			app.line(app.width / 2 - 500, app.height / 2 + 170, app.width / 2 + 500, app.height / 2 + 170);
			break;
		case 2:
			app.textAlign(app.CENTER);
			app.tint(37, 57, 23);
			app.image(logo, app.width / 2, 120, 120, 180);
			app.tint(255);

			app.fill(37, 57, 73);
			app.textFont(bebas);

			app.textSize(80);
			app.text("Dirección", app.width / 2, app.height / 2 - 100, app.width, 100);

			app.textFont(teko);
			app.textSize(30);
			app.fill(56, 88, 109);

			app.text(
					"Un paso más... ¿Dónde te ubicarás? Puedes encontrar la dirección de tu nueva ciudad en la pantalla del monitor principal.",
					app.width / 2, app.height / 2 + 70, 700, 200);

			app.textSize(50);
			app.text(ip, app.width / 2, app.height / 2 + 310, app.width, 400);
			app.line(app.width / 2 - 500, app.height / 2 + 170, app.width / 2 + 500, app.height / 2 + 170);

			break;
		case 3:
			// Interface display
			city.display();
			people.display();
			energyForUse.display();
			seasonIndicator.display();
			energyIndicator.display();
			add.display();
			happy.display();
			// Timer logic
			if (restartTimer == true) {
				timer.restart();
				timer.setStop(true);
				restartTimer = false;
			}

			app.fill(37, 57, 73);
			app.textSize(50);

			app.text(60 - timer.getS(), app.width / 2, 70);

			if (60 - timer.getS() == 0)
				restartTimer = true;

			timer.setStop(false);

			// timer.setStop(true);

			break;
		}
	}

	public void kpress() {
		if (screen == 1) {
			cityName = writeVar(cityName, cityNamePlaceHolder);
		}
		if (screen == 2) {
			ip = writeVar(ip, ipPlaceHolder);
		}
	}

	private String writeVar(String varToWrite, String varPlaceHolder) {

		if (varToWrite.equals(varPlaceHolder)) {
			varToWrite = "";
			varToWrite += app.key;
		} else {
			varToWrite += app.key;
		}
		if (app.key == app.BACKSPACE) {
			varToWrite = "";
		}
		if (app.key == app.ENTER) {
			System.out.println(varToWrite + " Sent");
		}
		System.out.println(varToWrite);

		return varToWrite;
	}

	public void moved() {
		if (screen == 3) {
			city.moved();
			energyIndicator.moved();
			happy.moved();
		}
	}

	public void clicked() {
		if (screen != 3) {
			screen++;
		}
		if (screen == 3) {

			add.clicked();
			city.setStateSelected(add.getStateSelected());

			city.clicked();
			energyIndicator.clicked();

		}
	}

	public void dragged() {
		happy.dragged();

	}

	public void released() {
		if (screen == 3) {

			happy.released();
		}

	}

}
