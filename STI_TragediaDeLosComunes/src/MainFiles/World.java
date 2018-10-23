package MainFiles;

import Comunicacion.Multicast;
import InterfaceElements.AddElements;
import InterfaceElements.AddNewElement;
import InterfaceElements.Energy;
import InterfaceElements.HappyBar;
import InterfaceElements.Indicator;
import InterfaceElements.NotificationBar;
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
	private Indicator totalEnergy;

	private Season seasonIndicator;
	private Energy energyIndicator;
	private AddNewElement add;
	private HappyBar happy;
	private Timer timer;
	private int maxTime;
	private NotificationBar notifs;
	private boolean restartTimer;

	// Logical variables
	private int pasiveEnergy, finalEnergy, energyByEnvironment;
	private int population, demand;
	private int happiness, happinessThisTurn, deads;
	private int season;
	private int housePrice, treePrice;
	private int minSummer, minAutumn, minWinter, minSpring;
	private int turno;
	private boolean isTurno;

	// Conection
	public Multicast multicast;

	private boolean alive;
	
	public World(PApplet app) {
		this.app = app;
		teko = app.createFont("./resources/fonts/teko-regular.otf", 120);
		bebas = app.createFont("./resources/fonts/BebasNeue-Regular.otf", 120);
		brush = app.createFont("./resources/fonts/Brush-Script.ttf", 120);

		screen = 0;
		alive = true;
		turno = 0;
		maxTime = 20;
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
		pasiveEnergy = 0;
		finalEnergy = 500;

		population = 0;
		deads = 0;
		demand = 0;
		happiness = 0;
		happinessThisTurn = 0;
		season = 1;
		housePrice = happinessThisTurn;
		treePrice = 1;

		minSummer = 25;
		minAutumn = 15;
		minWinter = 30;
		minSpring = 15;

		// Interface
		people = new Indicator(100, 60, "POBLACIÓN", app);
		energyForUse = new Indicator(300, 60, "DEMANDA", app);
		totalEnergy = new Indicator(500, 60, "ENERGíA", app);

		add = new AddNewElement(app.width - 100, app.height - 100, app);

		seasonIndicator = new Season(app.width - 150, 60, season, app);
		energyIndicator = new Energy(app.width - 75, 170, app);

		happy = new HappyBar(100, app.height - 100, app);
		notifs = new NotificationBar(app.width / 2, app.height - 100, app);

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
		city = new City(cols, rows, size, housePrice, treePrice, app);

		/*
		 * al iniciar turno finalEnergy es igual a la resta de energyWasting y
		 * startEnergy
		 * 
		 * 
		 */

		multicast = new Multicast();
		multicast.start();

		multicast.getData(population, demand, finalEnergy);
	}

	int tempPopulation, tempDemand, environmental;
	int temTurn;
	String temNotif;
	int temEnergiaSolicitada;
	public void display() {

		// variable changer

		energyByEnvironment = city.getEvironmentalEnergy();
		if (environmental != energyByEnvironment) {
			multicast.setEnvironmental(energyByEnvironment);
		}
		
		environmental = energyByEnvironment;
		
		
		city.setEnergyCanUse(finalEnergy);
		city.setDemandedEnergy(demand);
		population = city.getPopulation();
		// System.out.println(demand);

		people.setIndexator(population);
		energyForUse.setIndexator(demand);
		totalEnergy.setIndexator(finalEnergy);

		happinessThisTurn = happy.getIndexator();
		housePrice = happinessThisTurn;
		city.setHousePrice(housePrice);
		demand = city.calculateDemand(housePrice);
		// System.out.println(demand);

		if (season == 1)
			happy.setMin(minSummer);
		if (season == 2)
			happy.setMin(minAutumn);
		if (season == 3)
			happy.setMin(minWinter);
		if (season == 4)
			happy.setMin(minSpring);

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
					"Bienvenido!  Antes de empezar nos gustaría que firmaras algunos papeles. Ya sabes, cosas de protocolo. Cuéntanos ¿cómo se llamará tu ciudad?",
					app.width / 2, app.height / 2 + 70, 700, 200);

			app.textSize(30);
			app.textFont(brush);
			app.text(cityName + multicast.isCanPlay(), app.width / 2, app.height / 2 + 250, app.width, 400);
			app.line(app.width / 2 - 500, app.height / 2 + 170, app.width / 2 + 500, app.height / 2 + 170);

			break;
		case 2:

			if (multicast.getEnergiaSolicitada()!=temEnergiaSolicitada) {
				finalEnergy = finalEnergy + multicast.getEnergiaSolicitada();
			}
			
			temEnergiaSolicitada = multicast.getEnergiaSolicitada();
			// Interface display
			city.display();

			people.display();
			energyForUse.display();
			totalEnergy.display();

			season = multicast.getSeason();
			seasonIndicator.setSeasonSelected(season);
			seasonIndicator.display();

			energyIndicator.display();
			add.display();
			happy.display();
			notifs.display();

			if (tempPopulation != population) {
				multicast.setPoblacion(population);
			}
			if (tempDemand != demand) {
				multicast.setDemanda(demand);
			}
			if (temTurn != multicast.getTurnoAlServidor()) {
				startTurn();
			}
			if (temNotif != multicast.getNotif()) {
				notifs.addNotif(multicast.getNotif(), 2 );
			}
			temNotif = multicast.getNotif();
			temTurn = multicast.getTurnoAlServidor();
			tempPopulation = population;
			tempDemand = demand;

			// Turn viewer
			if (multicast.getTurno() == multicast.turnoAlServidor) {
				app.text("Es tu turno", app.width / 2, app.height / 2);

			}
			// Timer logic
			if (restartTimer == true) {
				timer.restart();
				timer.setStop(true);

				restartTimer = false;
			}

			app.fill(37, 57, 73);
			app.textSize(50);

			app.text(maxTime - timer.getS(), app.width / 2, 70);

			if (maxTime - timer.getS() == 0) {
				multicast.enviar("termine");

				restartTimer = true;

				endTurn();
			}

			timer.setStop(false);

			// timer.setStop(true);

			break;
		}
	}

	public void startTurn() {
		// Inicio
		timer.restart();
		timer.setStop(false);
		energyIndicator.setCanSelect(true);
		pasiveEnergy = multicast.getPasiveEnergy();
		finalEnergy = finalEnergy + pasiveEnergy;

		add.setChoose(0);
		add.setStateSelected(0);
		city.setTreesPositioned(0);
	}

	public void endTurn() {

		if (city.getDemandedEnergy() > finalEnergy) {

			int populationAliveAfter = (finalEnergy - city.getDemandedEnergy()) / happinessThisTurn;
			int populationDead = populationAliveAfter - population;
			population = populationAliveAfter;
			deads = deads + populationDead;

			if (population < 0) {
				population = 0;
			}

			city.setPopulation(population);
			city.deleteHouses(populationDead);

			//System.out.println("deads" + deads);

			finalEnergy = finalEnergy - demand;

		} else {

			finalEnergy = finalEnergy - demand;
		}

		if (finalEnergy < 0) {
			finalEnergy = 0;
		}
		
		multicast.setEnergy(finalEnergy);
		if (population != 0) {
			happiness = happiness + (happinessThisTurn/population + (energyByEnvironment)) - deads;
				
		} else {
			happiness = happiness + (energyByEnvironment - deads);
		}
		
		multicast.setFelicidad(happiness);
		System.out.println();
		System.out.println(happiness + " happiness " + happinessThisTurn + "happinessthisturn");
		timer.setStop(true);
		timer.restart();
	}

	public void kpress() {
		if (screen == 1) {
			cityName = writeVar(cityName, cityNamePlaceHolder);
		}
		if (screen == 0 && app.ENTER == app.key) {
			screen++;
		} else if (screen == 1 && app.ENTER == app.key && multicast.isCanPlay() == true) {
			screen++;
		}

		if (multicast.getTurno() == multicast.turnoAlServidor) {
			if (app.key == 'p') {
				multicast.enviar("termine");
				endTurn();
				//System.out.println(multicast.getTurno());
				//System.out.println(multicast.turnoAlServidor);
			}
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
			multicast.setNombre(cityName);

			System.out.println(varToWrite + " Sent");
		}
		System.out.println(varToWrite);

		return varToWrite;
	}

	public void moved() {
		if (screen == 2) {
			city.moved();
			energyIndicator.moved();
			happy.moved();
		}
	}

	public void clicked() {

		if (screen == 2) {
			if (multicast.getTurno() == multicast.turnoAlServidor) {

				add.clicked();
				city.setStateSelected(add.getStateSelected());
				city.clicked();
				demand = city.getDemandedEnergy();
				energyForUse.setIndexator(demand);

				energyIndicator.setDemand(demand);
				energyIndicator.setFinalEnergy(finalEnergy);

				energyIndicator.clicked();


				if (energyIndicator.isSendNotif() == true) {
					
					multicast.notifyPlayers("Se ha añadido " + energyIndicator.getEnergyGiven() + " de energía.");
					
					multicast.solicitarEnergia(energyIndicator.getEnergyGiven());
					//CHECK IT
					//finalEnergy = finalEnergy + energyIndicator.getEnergyGiven();
					
					energyIndicator.setSendNotif(false);
					
				}

				// To add a notification

			}
		}
	}

	public void dragged() {
		happy.dragged();

	}

	public void released() {
		if (screen == 2) {

			happy.released();
		}

	}

}
