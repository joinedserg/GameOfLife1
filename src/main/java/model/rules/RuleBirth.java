package model.rules;

import java.util.*;
import java.awt.Point;

import model.entities.Specimen;
import model.entities.Cell;
import model.entities.Type;
import model.Board;


public class RuleBirth extends Rule {

	@Override
	public void apply(Specimen c, Board b) {
		stepBirth(c, b);
		
	}
	
	void stepBirth(Specimen c, Board b) {
		c.stepBirth();
		int lastBirth = c.getLastBirth();
				
		if(lastBirth == Specimen.periodBirth) {
			
			c.clearBirth();			
			List<Point> listPlaces = b.getEmptyPlaces(c, 1);
			if(listPlaces.isEmpty()) {
				return;
			}
			List<Cell> listParCandidate = b.getNeighborsEqType(c);
			
			
			int numbPlaces = 0; //number empty place, can random, but after :-)
			int numbParent2 = 0;//number parent, can random, but after :-)
			if(listParCandidate.isEmpty()) {
				//Plant can birth oneself
				if(c.getType() == Type.PLANT) {
					c.birth(null, listPlaces.get(numbPlaces).x, listPlaces.get(numbPlaces).y);
				}
				return;
			}
			else {
				Specimen parent2 = (Specimen)listParCandidate.get(numbParent2);
				
				parent2.clearBirth();
				c.birth(parent2, listPlaces.get(numbPlaces).x, listPlaces.get(numbPlaces).y);
			}
			
			//cразу же добавили в рабочее окно
			b.addCell(c.getChild());
		}
		
	}
	
}
