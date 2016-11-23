package model;

import java.util.*;
import model.entities.*;

import visualization.Visualization;

public class CellularAutomat implements java.io.Serializable {

	List<Cell> cells;
	Board board;

	int countPlants;
	int countHerbivorous;
	int countPredators;

	int maxX;
	int maxY;


	//default sample
	public CellularAutomat(int width, int height) {
		cells = new ArrayList();
		try {
			board = Board.init(width, height);
		}
		catch(Exception ex) {
			board = Board.getBoard();
		}

		//cells.add(new Plant(0,2));
		cells.add(new Herbivorous(0,0));
		cells.add(new Herbivorous(0,1));
		cells.add(new Herbivorous(1,1));
		cells.add(new Herbivorous(1,2));
		
		cells.add(new Herbivorous(50,12));
		cells.add(new Herbivorous(51,12));
		cells.add(new Herbivorous(52,12));



		//cells.add(new Plant(2,2));
		//cells.add(new Plant(3,3));
		
		cells.add(new Plant(55,14));
		cells.add(new Plant(65,14));
		cells.add(new Plant(56,14));
		cells.add(new Plant(57,14));
		cells.add(new Plant(58,14));
		cells.add(new Plant(59,14));
		cells.add(new Plant(65,10));
		cells.add(new Plant(56,16));
		cells.add(new Plant(57,17));
		cells.add(new Plant(58,18));
		cells.add(new Plant(59,19));
		cells.add(new Plant(59,10));
		cells.add(new Plant(56,17));
		cells.add(new Plant(57,18));
		cells.add(new Plant(58,19));
		cells.add(new Plant(59,20));
		/*cells.add(new Herbivorous(3,4));
		cells.add(new Herbivorous(4,4));
		cells.add(new Herbivorous(4,5));
		
		

		cells.add(new Plant(24,24));
		cells.add(new Plant(14,12));*/
		cells.add(new Predator(20,6));
		cells.add(new Predator(21,7));
		
		this.maxX = width;
		this.maxY = height;

		updateCount();
	}

	public CellularAutomat(int width, int height, List<Cell> cells){

		try {
			board = Board.init(width, height);
		}
		catch(Exception ex) {
			board = Board.getBoard();
		}

		this.maxX = width;
		this.maxY = height;
		this.cells = cells;
		updateCount();

	}//TODO: test how it works
	// maybe it'll work



	void updateCount() {
		countPlants = 0; countHerbivorous = 0; countPredators = 0;
		for(Cell c : cells) {
			if(c.getClass() == Plant.class) {
				countPlants++;
			}
			else if(c.getClass() == Herbivorous.class) {
				countHerbivorous++;
			}
			else if(c.getClass() == Predator.class) {
				countPredators++;
			}
		}
	}

	public CellularAutomat(String path2config) {

	}

	public int getCountObject() {
		return cells.size();
	}

	public String toString() {
		return "Status : plants: " + countPlants + "  herbivorous: " + countHerbivorous 
				+ "  predator:  " + countPredators;
	}

	public void step(Visualization v) { // v добавил для дебага		
		//можно проверить и добавлять лишь один раз, в начале 
		board.toReflect(cells);
		
		for(ListIterator<Cell> itr = cells.listIterator(); itr.hasNext();) {			
			Cell c = itr.next();
			c.applyRule(board);

			if(c.isDeath()) {
				System.out.println("Object " + c);
				
				//удаляем клетку из поля взаимодействий и из очереди  
				board.clearCell(c);
				itr.remove();
				continue;
			}
			if(c.getChild() != null) {
				//При рождении новой клетки, её необходимо добавить в очередь и в поле взаимодействий				
				itr.add(c.getChild());
				
				//!!clear child
				c.setChild(null);
			}


			//v.toReflect(this);
			System.out.print("");
			c.updateState();
		}
		updateCount();
		System.out.println(this);
	}


	public List<Cell> getCurrentPosition() {
		return cells;
	}

}
