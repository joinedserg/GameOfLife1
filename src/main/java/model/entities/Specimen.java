package model.entities;

import model.rules.Rule;

import java.util.*;
import model.Board;

public class Specimen extends Cell {
	
	public List<Rule> rulesOneself;

	//TODO: make it work with this fields
	protected int youthLimit;
    protected int maturityLimit;
	protected int oldLimit;
    protected int limitHunger1a;
    protected int limitHunger2a;



	public static final int ageYouthLimit; // after Manutiry  max health, strength, speed +25%?
	public static final int ageMaturityLimit; // min health -25%
	public static final int ageOldLimit; //after only death :-)
	
	public static final int limitHunger1;
	public static final int limitHunger2;
	
	public static final int periodBirth;
	
	static {
		/*from conf file ??*/
		ageYouthLimit = 3;
		ageMaturityLimit = 9;
		ageOldLimit = 12; //32 - чтобы показать что они двигаются к зелени 12;
		
		limitHunger1 = 5;
		limitHunger2 = 20; // 10;

		periodBirth = 5;
	}
	
	protected int maxHealth;
	protected int maxSpeed;
	
	protected int currentHealth;
	protected int currentSight; // ? нужно ли?
	protected int currentSpeed; // 
	protected int currentStrength;
	protected int currentCourage;
	protected int lastBirth;
	
	protected int basicHealth;
	protected int age;
	protected int basicStrength;
	//protected int basicSpeed; - не нужна
	protected int basicCourage;
	protected int basicSight;
	protected int hunger;
	
	
	public Specimen(int x, int y) {
		super(x,y);
		
		rulesOneself = new ArrayList();
		age = 0;
		hunger = 0;
		basicHealth = 10;
		basicStrength = 4;
		lastBirth = 0;		
	}
	
	public void updateState() {
		//после каждого хода объекта, восстанавливаются расходные (скорость и м.б .еще что-то
		//
		currentSpeed = maxSpeed;
	}
	
	public int step() {
		//тратиться при передвижении (приближении к еде, преследовании)
		currentSpeed--;
		return currentSpeed;
	}
	
	public void addRulesOneself(Rule r) {
		rulesOneself.add(r);
	}
	
	public int getLastBirth() {
		return lastBirth;
	}
	
	public void clearBirth() {
		lastBirth = 0;
	}
	
	public void stepBirth() {
		lastBirth++;
	}
	
	public Cell birth(Specimen parent2, int xPos, int yPos) {
		//override in child
		
		return null;//if()
	}
	
	public int getAge() {
		return age;
	}
	
	public void aging() {
		age++;
	}
	
	public int getHunger() {
		return hunger;
	}
	
	public void hunger() {
		hunger++;
	}
	
	public void getEat(int eat) {
		if(hunger >= eat) {
			hunger -= eat;
		}
		else {
			hunger = 0;
		}
	}
	
	public int getStrength() {
		return basicStrength;
	}
	
	public int getHealth() {
		return basicHealth;
	}
	

	@Override
	public void applyRule(Board b) {
		//rulesOneself
		for(Rule r: rulesOneself) {
			r.apply(this, b);
		}
	}


    public void setHealth(int health) {
        this.basicHealth = health;
    }

	public void setYouth(int youth) {
		this.youthLimit = youth;
	}

    public void setMaturity(int maturity) {
        this.maturityLimit = maturity;
    }

    public void setOldness(int oldness) {
        this.oldLimit = oldness;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }


    public void setHungerFirstLimit(int hungerFirstLimit) {
        this.limitHunger1a = hungerFirstLimit;
    }

    public void setHungerSecondLimit(int hungerSecondLimit) {
        this.limitHunger2a = hungerSecondLimit;
    }

    public void setStrength(int strength) {
        this.basicStrength = strength;
    }

    public void setSight(int sight) {
        this.basicSight = sight;
    }

    public void setCourage(int courage) {
        this.basicCourage = courage;
    }

    public void setSpeed(int speed) {
		this.currentSpeed = speed;
    }
}
