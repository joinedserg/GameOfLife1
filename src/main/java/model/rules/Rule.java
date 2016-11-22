package model.rules;

import model.entities.Specimen;
import model.Board;

public abstract class Rule implements java.io.Serializable {
	
	abstract public void apply(Specimen c, Board b);
}
