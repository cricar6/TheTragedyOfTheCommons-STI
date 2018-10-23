package WorldElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import processing.core.PApplet;

public class City {

	private ArrayList<Square> squares;
	private int cols, rows, size;
	private int stateSelected;
	private int energyCanUse;
	private int demandedEnergy;
	private int evironmentalEnergy;
	private int population;
	private int housePrice, treePrice;
	private int treeLimit;
	private int treesPositioned;

	public City(int cols, int rows, int size, int housePrice, int treePrice, PApplet app) {
		boolean contador = false;

		this.cols = cols;
		this.rows = rows;
		this.size = size;

		this.housePrice = housePrice;
		this.treePrice = treePrice;

		treeLimit = 1;
		treesPositioned = 0;

		squares = new ArrayList<Square>();
		energyCanUse = 0;

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {

				int sizing;
				if (contador) {
					sizing = (i) * ((size + 40)) + 70;
					contador = !contador;

				} else {
					sizing = (i) * ((size + 40));
					contador = !contador;

				}
				squares.add(new Square(sizing + (app.width / 2 - cols / 2 * 120),
						j * (size - 57) + (app.height / 2 - 150), size, app));
			}
		}
	}

	public void display() {

		// System.out.println(demandedEnergy);

		
			
		for (int i = 0; i < squares.size(); i++) {
			Square square = squares.get(i);
			square.display();
			square.setEnergyCanUse(energyCanUse);
			if (treesPositioned != 0)
				square.setTreesPositioned(0);
			if (treesPositioned == 0)
				treesPositioned += square.getTreesPositioned();
		}
		
		

	}

	public void moved() {
		for (int i = 0; i < squares.size(); i++) {
			Square square = squares.get(i);

			square.moved();
		}

	}

	public void clicked() {

		// System.out.println("fnal" + demandedEnergy);

		if (treesPositioned == 0) {

			for (int i = 0; i < squares.size(); i++) {
				Square square = squares.get(i);
				square.setStateSelected(stateSelected);
				square.clicked();

			}
		}

		demandedEnergy = 0;
		demandedEnergy = calculateDemand(housePrice);
		evironmentalEnergy = calculateEnvironmentEnergy(treePrice);

		population = calculatePopulation(5);

		Collections.sort(squares, new Comparator<Square>() {

			@Override
			public int compare(Square sq0, Square sq1) {
				if (sq0.getPosY() > sq1.getPosY())
					return 1;
				if (sq0.getPosY() < sq1.getPosY())
					return -1;
				// TODO Auto-generated method stub
				return 0;
			}

		});

	}

	public void deleteHouses(int population) {

		int Housesq;
		if (population != 0) {
			if (population > 5) {
				Housesq = population / 5;
			} else {
				Housesq = 1;
			}

		} else {
			Housesq = 0;
		}
		for (int i = 0; i < squares.size() - Housesq; i++) {
			Square square = squares.get(i);

			if (square.isOcupied() == true) {
				if (square.getState() == 1) {
					square.restartVars();
				}
			}
		}

	}

	public int calculatePopulation(int peoplePerHouse) {
		int houseQ = 0;

		for (int i = 0; i < squares.size(); i++) {
			Square square = squares.get(i);

			if (square.isOcupied() == true) {
				if (square.getState() == 1) {
					houseQ++;
				}
			}
		}
		return houseQ * peoplePerHouse;
	}

	public int calculateDemand(int housePrice) {
		int houseTEnergy = 0;
		for (int i = 0; i < squares.size(); i++) {
			Square square = squares.get(i);

			houseTEnergy = population * housePrice;

		}
		// System.out.println("houses " + houseTEnergy + "trees" + treeTEnergy);
		return houseTEnergy;

	}

	private int calculateEnvironmentEnergy(int treePrice) {
		int treeTEnergy = 0;

		for (int i = 0; i < squares.size(); i++) {
			Square square = squares.get(i);

			if (square.isOcupied() == true) {
				if (square.getState() == 2) {
					treeTEnergy += treePrice;
				}
			}
		}
		return treeTEnergy;

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

	public int getDemandedEnergy() {
		return demandedEnergy;
	}

	public void setDemandedEnergy(int demandedEnergy) {
		this.demandedEnergy = demandedEnergy;
	}

	public int getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getEvironmentalEnergy() {
		return evironmentalEnergy;
	}

	public void setEvironmentalEnergy(int evironmentalEnergy) {
		this.evironmentalEnergy = evironmentalEnergy;
	}

	public int getTreesPositioned() {
		return treesPositioned;
	}

	public void setTreesPositioned(int treesPositioned) {
		this.treesPositioned = treesPositioned;
	}

}
