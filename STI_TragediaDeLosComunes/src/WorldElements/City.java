package WorldElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import processing.core.PApplet;

public class City {
	
	private ArrayList<Square> squares;
	int cols, rows, size;

	
	public City (int cols, int rows, int size, PApplet app) {
	    boolean contador = false;

		this. cols = cols;
		this.rows = rows;
		this.size = size;
		
	    squares = new ArrayList <Square>();
	    
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
}
