package model.entities;


import model.Board;

abstract public class Cell implements java.io.Serializable {
	protected Type type;
	protected int x,y;
	protected boolean isDeath;
	protected String status = "";
	
	//!!!!!!!{crutch :-) } only for birth mechanism
	protected Cell child;
	//!!!!!!!
	
	 
	
	public void updateState() {
		//после каждого хода объекта, восстанавливаются расходные (скорость и м.б .еще что-то
		//
	}
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		isDeath = false;
	}
	
	public int getSight() {
		return 0;
	}
	
	public void applyRule(Board b) {
		
	}
	
	public void setChild(Cell c) {
		this.child = c;
	}
	
	public Cell getChild() {
		return child;
	}
	
	public void kill() {
		isDeath = true;
	}
	
	public boolean isDeath() {
		return isDeath;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public Type getType() {
		return type;
	}


	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		
		return type + " {" + x + "," + y + "} " + status;
	}
	
	
}
