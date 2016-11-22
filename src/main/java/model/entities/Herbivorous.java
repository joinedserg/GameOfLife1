package model.entities;


import model.rules.*;


public class Herbivorous extends Specimen {


	public Herbivorous(int xPos, int yPos) {
		super(xPos, yPos);
		
		this.rulesOneself.add(new RuleAging());
		this.rulesOneself.add(new RuleStarve());
		this.rulesOneself.add(new RuleBirth());
		this.rulesOneself.add(new RuleMove2Eat());
		this.rulesOneself.add(new RuleBite());

		this.maxSpeed = 20;
		this.currentSpeed = this.maxSpeed;
		this.type = Type.HERBIVORUS;
	}

	@Override
	public Cell birth(Specimen parent2, int xPos, int yPos) {
		child = new Herbivorous(xPos, yPos);
		return child;
	}
	
	@Override
	public int getSight() {
		return currentSpeed;
	}
	
	/*@Override
	public void stepHunger() {
		if(isDeath == true) {
			return;
		}

		this.hunger++;
		if(hunger > limitHunger2) {
			currentHealth -= hunger;
			if(currentHealth <= 0) {
				isDeath = true;
				status = "He died of hunger";
			}
		}
		else if(hunger > limitHunger1) {
			//
		}

	}*/
}
