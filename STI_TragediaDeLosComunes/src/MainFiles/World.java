package MainFiles;

import InterfaceElements.Energy;
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
	private PFont avenir;

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
	
	//Interface Objects
	private Indicator people;
	private Indicator energyForUse;
	private Season season;
	private Energy energy;
	private Timer timer;
	private boolean restartTimer;
	
	public World(PApplet app) {
		this.app = app;
		avenir = app.createFont("./resources/fonts/Teko-Regular.ttf", 20);

		screen = 0;

		//Splash
		backgrounds = new PImage [3];
	    for (int i = 0; i < backgrounds.length; i++) {
	    	backgrounds[i] = app.loadImage ("/resources/back"+i+".png");
	    }
	    frontx = 0;
	    frontx2 = -app.width*2;
	    middlex = 0;
	    middlex2 = -app.width*2;
	    backx = 0;
	    backx2 = -app.width*2;

	    
	   logo = app.loadImage("/resources/logo.png");
		
	    
		// Interface
		people = new Indicator(100, 60, 0+ "" ,"POBLACIÓN" , app);
		energyForUse = new Indicator(300, 60, 0+ "" ,"DEMANDA" ,app);

		season = new Season(app.width-150, 60, 0, app);
		energy = new Energy(app.width-75, 170, app);
		
		timer = new Timer();
		Thread t = new Thread(timer);
		t.start();
		restartTimer = false;
		
		//City vars
		cols = 6;
		rows = 8;
		size = 100;

		x += app.width / 2;
		y += app.height / 2;
		city = new City(cols, rows, size, app);

		
	}

	public void display() {
		
		app.camera(x, y, (app.height / 2.0f) / app.tan(app.PI * 30.0f / 180.0f) + zoom, x, y, 0f, 0f, 1f, 0f);

		switch (screen) {
		case 0:
			timer.setStop(true);
			//System.out.println("Splash screen");

			app.imageMode(app.CENTER);
			
			app.image(backgrounds[2], backx, app.height-270);
			app.image(backgrounds[2], backx2, app.height-270);

			app.image(backgrounds[1], middlex, app.height-200);
			app.image(backgrounds[1], middlex2, app.height-200);

			app.image(backgrounds[0], frontx, app.height-110);
			app.image(backgrounds[0], frontx2, app.height-110);

			frontx++;
			frontx2++;
			middlex+=0.7;
			middlex2+=0.7;
			backx+=0.5;
			backx2+=0.5;

			if (frontx >= app.width*2) {
				frontx = -app.width*2;
			}
			if (frontx2 >=app.width*2) {
				frontx2 = -app.width*2;
			}
			if (middlex >= app.width*2) {
				middlex = -app.width*2;
			}
			if (middlex2 >=app.width*2) {
				middlex2 = -app.width*2;
			}
			if (backx >= app.width*2) {
				backx = -app.width*2;
			}
			if (backx2 >=app.width*2) {
				backx2 = -app.width*2;
			}
			
			app.image(logo, app.width -70, 100, 140 , 200);
			
			app.fill(255);
			app.textFont(avenir);
			app.textAlign(app.CENTER);
			app.textLeading(20);
			app.text("Presiona cualquier tecla para continuar", app.width/2, app.height/2, 200, 80 );
			
			break;
		case 1: 
			app.textAlign(app.CENTER);
			app.fill(0);
			app.text("¡Bienvenido!", app.width/2, app.height/2-100, app.width, 100);
			app.text("Antes de empezar nos gustaría que firmaras algunos papeles. Ya sabes, cosas de protocolo. Cuéntanos ¿cómo se llamará tu ciudad?", app.width/2, app.height/2, 500, 200);
			app.text(cityName, app.width/2, app.height/2+200, 400, 100);
			
			break;
		case 2:
			app.fill(0);
			app.textAlign(app.CENTER);
			app.text(ip, app.width/2-50, app.height/2, 100, 40);
			if (ip.equals("abcd")) System.out.println("ip accepted");;
			break;
		case 3: 
			//Interface display
			city.display();
			people.display();
			energyForUse.display();
			season.display();
			energy.display();
			
			//Timer logic
			if (restartTimer == true) {
				timer.restart();
				timer.setStop(true);
				restartTimer = false;
			}

			app.fill(37,57,73);
			app.textSize(50);
			
			app.text(20 - timer.getS(), app.width/2, 70);
			
			if (20-timer.getS() == 0)restartTimer = true;
			
			timer.setStop(false);

			//timer.setStop(true);
			
			
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
	
	private String writeVar (String varToWrite, String varPlaceHolder) {

		if (varToWrite.equals(varPlaceHolder)) {
			varToWrite="";
			varToWrite += app.key;
		} else {
			varToWrite += app.key;
		}
	    if (app.key==app.BACKSPACE) {
	    	varToWrite="";
	    } 
	    if (app.key==app.ENTER) {
	    	System.out.println(varToWrite+" Sent");
	    }
		System.out.println(varToWrite);
		
		return varToWrite;
	}

	public void moved() {
		if (screen == 3) {
			city.moved();
			energy.moved();
		}
	}

	public void clicked() {
		if (screen != 3) {
			screen++;
		}
		if (screen == 3) {
			city.clicked();	
			energy.clicked();
		}
	}

}
