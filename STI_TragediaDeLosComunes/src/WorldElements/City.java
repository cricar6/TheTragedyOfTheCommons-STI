package WorldElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import processing.core.PApplet;

public class City {
	
	private ArrayList<Square> squares;
	private int cols, rows, size;
	private int stateSelected;
	private int energyCanUse, finalEnergy;
	
	public City (int cols, int rows, int size, PApplet app) {
	    boolean contador = false;

		this. cols = cols;
		this.rows = rows;
		this.size = size;
		
	    squares = new ArrayList <Square>();
	    energyCanUse = 0;
	    finalEnergy = 0;
	    
	    for (int i= 0; i< cols; i++) {
	        for (int j= 0; j< rows; j++) {
	          
	          int sizing ;
	          if (contador) {
	            sizing = (i)*((size+40))+70 ;
	            contador = !contador;

	          } else {
	            sizing = (i)*((size+40));
	            contador = !contador;

	          }
	          squares.add(new Square (sizing + (app.width/2 - cols/2*120), j*(size-57) + (app.height/2 - 150 ), size, app));
	        }
	      }
	}
	
	public void display () {

	    for (int i = 0; i< squares.size(); i++) {
	        Square square = squares.get (i);
	        square.display();
	        square.setEnergyCanUse(energyCanUse);
	        if (square.getEnergyCanUse() != square.getFinalEnergy()) {
	        	finalEnergy = square.getFinalEnergy();
	        }
	    }


		
	}
	
	public void moved () {
	    for (int i = 0; i< squares.size(); i++) {
	        Square square = squares.get (i);

	        square.moved();
	      }
		
	}
	
	public void clicked () {
		
		
		
	    for (int i = 0; i< squares.size(); i++) {
	        Square square = squares.get (i);
	        square.setStateSelected(stateSelected);

	        square.clicked();
	        
	      }
	    Collections.sort(squares, new Comparator <Square> () {

			@Override
			public int compare(Square sq0, Square sq1) {
				if (sq0.getPosY() > sq1.getPosY()) return 1;
				if (sq0.getPosY() < sq1.getPosY()) return -1;
				// TODO Auto-generated method stub
				return 0;
			}
	    	
	    });
	}

	public int getStateSelected() {
		return stateSelected;
	}

	public void setStateSelected(int stateSelected) {
		this.stateSelected = stateSelected;
	}

	public int getEnergyCanUse() {
		return energyCanUse;
	}

	public void setEnergyCanUse(int energyCanUse) {
		this.energyCanUse = energyCanUse;
	}

	public int getFinalEnergy() {
		return finalEnergy;
	}

	public void setFinalEnergy(int finalEnergy) {
		this.finalEnergy = finalEnergy;
	}
	
	
}
