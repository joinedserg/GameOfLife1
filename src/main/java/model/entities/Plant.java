package model.entities;

import model.rules.RuleAging;
import model.rules.RuleBirth;


public class Plant extends Specimen {
	
	public Plant(int xPos, int yPos) {
		super(xPos, yPos);
		
		this.rulesOneself.add(new RuleAging());
		this.rulesOneself.add(new RuleBirth());
		currentSpeed = 0;
		this.type = Type.PLANT;
	}

	@Override
	public Cell birth(Specimen parent2, int xPos, int yPos) {
		
		child = new Plant(xPos, yPos);		
		return child;
	}
	
}
