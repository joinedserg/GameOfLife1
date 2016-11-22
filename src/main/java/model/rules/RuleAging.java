package model.rules;

import model.entities.Specimen;
import model.Board;

public class RuleAging extends Rule {

	@Override
	public void apply(Specimen c, Board b) {
		stepAge(c);
	}
	
	void stepAge(Specimen c) {
		int age = c.getAge();
		
		if(age > Specimen.ageOldLimit) {
			c.kill();
			c.setStatus("He died of old age");
		}
		else if(age > Specimen.ageMaturityLimit) {	

		}
		else if(age > Specimen.ageYouthLimit) {

		}
		c.aging();
	}



}
