package model;

import java.util.*;
import model.entities.Cell;

public class RemotenessComparator implements Comparator<Cell> {
	
	public int compare(Cell a, Cell b) {
		int aRem = Math.abs(a.getX()) + Math.abs(a.getY());
		int bRem = Math.abs(b.getX()) + Math.abs(b.getY());
		
		return aRem > bRem ? -1 : aRem == bRem ? 0 : 1;
	}

}
