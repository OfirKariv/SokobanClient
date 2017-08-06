package model.data;

import java.io.Serializable;

public abstract class GameObject implements  Serializable{
	
 
	private Position p = new Position();

	
	
	public abstract char getSign();

public GameObject(){}
	

	public Position getPosition() {
		return p;
	}

	public void setPosition(int x, int y) {
		
		this.p.setX(x);
		this.p.setY(y);
	}
	
	public void setPosition(Position p)
	{
		
		this.p.setPosition(p);
		
	}
	
	
}
