package model.rules;

import model.entities.Specimen;
import model.Board;

public class RuleStarve extends Rule {

	@Override
	public void apply(Specimen c, Board b) {
		stepHunger(c);
	}

	void stepHunger(Specimen c) { 
		int hunger = c.getHunger();
		
		if(hunger > Specimen.limitHunger2) {
			c.kill();
			c.setStatus("He died of hunger");
		}
		else if(hunger > Specimen.limitHunger1) {	
			
		}
		else {
			
		}

		c.hunger();
	}
}
