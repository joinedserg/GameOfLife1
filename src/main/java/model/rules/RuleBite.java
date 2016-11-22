package model.rules;

import java.util.List;

import model.entities.Specimen;
import model.entities.Cell;
import model.Board;
import model.RemotenessComparator;


public class RuleBite extends Rule {

	@Override
	public void apply(Specimen c, Board b) {
		stepBite(c, b);
	}
	
	public void stepBite(Specimen c, Board b) {
		
		List<Cell> eatCandidates = b.getEatCandidate(c);
		eatCandidates.sort(new RemotenessComparator());
		
		if(!eatCandidates.isEmpty()) {
			Specimen eatSpecimen = (Specimen)eatCandidates.get(0);
			
			eatSpecimen.setHealth(eatSpecimen.getHealth() - c.getStrength());
			c.setHealth(c.getHealth() + c.getStrength());			
			c.getEat(c.getStrength());			
			
			if(eatSpecimen.getHealth() <= 0) {
				eatSpecimen.kill();
				eatSpecimen.setStatus("He was eaten");
			}
		}
		
	}

	
}
