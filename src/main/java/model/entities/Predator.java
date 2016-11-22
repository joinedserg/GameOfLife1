package model.entities;

import model.rules.RuleAging;
import model.rules.RuleBirth;
import model.rules.RuleBite;
import model.rules.RuleMove2Eat;
import model.rules.RuleStarve;

public class Predator extends Specimen {

	public static int F = 0;
	public Predator(int xPos, int yPos) {
		super(xPos, yPos);
		
		this.rulesOneself.add(new RuleAging());
		this.rulesOneself.add(new RuleStarve());
		this.rulesOneself.add(new RuleBirth());
		this.rulesOneself.add(new RuleMove2Eat());
		this.rulesOneself.add(new RuleBite());
		this.maxSpeed = 20;
		this.currentSpeed = this.maxSpeed;
		this.type = Type.PREDATOR;
	}

	@Override
	public Cell birth(Specimen parent2, int xPos, int yPos) {
		child = new Predator(xPos, yPos);		
		return child;
	}
	
	@Override
	public int getSight() {
		return currentSpeed + currentSight;
	}
	
	/*
	@Override
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
