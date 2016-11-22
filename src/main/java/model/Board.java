package model;

import model.entities.Cell;
import java.util.*;
import java.awt.Point;
import model.entities.Type;

public class Board implements java.io.Serializable {

	static Board board;

	private Cell[][] cells;
	private int width;
	private int height;

	public static Board init(int width, int height) throws Exception {
		if(board != null) {
			throw new Exception("Board already init, use Board.getBoard()");
		}
		return new Board(width, height);
	}

	private Board(int width, int height) {
		cells = new Cell[width][height];

		this.width = width;
		this.height = height;
	}


	//TODO: ask4Ivan :-) 
	public static Board getBoard() /*throws Exception*/ {
		/*if(board == null) {
			throw new Exception("Board not init, use Board.init(width, height)");
		}*/

		return board;
	}

	public List<Cell> getEatCandidate(Cell c) {
		Type eat = null;
		switch(c.getType()) {
		case HERBIVORUS:
			eat = Type.PLANT;
			break;
		case PREDATOR:
			eat = Type.HERBIVORUS;
			break;
		default:
			//
			break;
		}
		return getNeighborsType4Radius(c, eat, c.getSight()+1);
	}

	public List<Cell> getNeighborsEqType(Cell c) {
		return getNeighborsType4Radius(c, c.getType(), 1);
	}

	public List<Cell> getNeighborsType4Radius(Cell c, Type t, int r) {
		List<Cell> list = getNeighbors(c, r);
		List<Cell> listEqType = new ArrayList();

		for(Cell candidate : list) {
			if(candidate.getType() == t) {
				listEqType.add(candidate);
			}			
		}

		return listEqType;
	}

	public List<Cell> getNeighbors(Cell c, int r) {
		List<Cell> list = new ArrayList();

		int x = c.getX();
		int y = c.getY();

		for(int i = -r; i < r + 1; i++) {
			for(int j = -r; j < r + 1; j++) {
				if(x + i >= 0 && x + i < width) {
					if(y + j >= 0 && y + j <height) {
						if(cells[x + i][y + j] != null && (i != 0 || j != 0)) {
							list.add(cells[x + i][y + j]);
						}
					}
				}
			}
		}
		return list;
	}

	public List<Point> getEmptyPlaces(Cell c, int r) {
		List<Point> list = new ArrayList();

		int x = c.getX();
		int y = c.getY();

		for(int i = -r; i < r + 1; i++) {
			for(int j = -r; j < r + 1; j++) {
				if(x + i >= 0 && x + i < width) {
					if(y + j >= 0 && y + j <height) {
						if(cells[x + i][y + j] == null) {
							list.add(new Point(i + x,j + y));
						}
					}
				}
			}
		}
		return list;
	}


	public List<Point> getPathShorterstWay(Point start, Point finish) {
		List<Point> list = new ArrayList<Point>();
		int[][] ar4Way = new int[width][height];

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {

				ar4Way[i][j] = -1; // Здесь еще не были 
				if(cells[i][j] != null) {
					ar4Way[i][j] = -2;
				}
			}
		}
		ar4Way[start.x][start.y] = 0;
		ar4Way[finish.x][finish.y] = -1;
		int []dx = {1, 0, -1, 0};
		int []dy = {0, 1,  0,-1};
		int d,x,y,k, len;
		boolean stop;

		d = 0;
		do {
			stop = true;
			for( x = 0; x < width; ++x) {
				for(y = 0; y < height; ++y) {
					if(ar4Way[x][y] == d) { 
						for(k = 0; k < 4; ++k) {// по всем непомеченным соседям
							int iy = y + dy[k], ix = x + dx[k];
							if(iy >= 0 && iy < height && ix >= 0 && ix < width 
									&& ar4Way[ix][iy] == -1) {
								stop = false;
								ar4Way[ix][iy] = (d + 1);
							}
						}
					}
				}
			}

			d++;
		}while(!stop && ar4Way[finish.x][finish.y] == -1);
		
		if(ar4Way[finish.x][finish.y] == -1) {
			//путь не найден
			return null;
		}
		
		//восстановление
		x = finish.x;
		y = finish.y;
		len = ar4Way[x][y];
		d = len;
		while(d > 0) {
			
			d--;
			for(k = 0; k < 4; ++k) {
				int iy = y + dy[k], ix = x + dx[k];
				if(iy >= 0 && iy < height && ix >= 0 && ix < width 
						&& ar4Way[ix][iy] == d) {
					x = x + dx[k];
					y = y + dy[k];
					break;
				}
			}
			list.add(new Point(x, y));
		}

		//list.add(new Point(start.x, start.y));
		Collections.reverse(list);
		return list;
	}


	public void toReflect(List<Cell> list) {
		cells = new Cell[width][height];

		for(Cell c : list) {
			cells[c.getX()][c.getY()] = c;
		}		
	}

	public void clearCell(Cell c) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(cells[i][j] == c) {
					cells[c.getX()][c.getY()] = null;
				}
			}
		}
	}

	public void addCell(Cell c) {
		cells[c.getX()][c.getY()] = c;
	}

	public Cell[][] getCells() {
		return cells;
	}

}
