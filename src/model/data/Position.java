package model.data;

import java.io.Serializable;

public class Position implements Serializable {

	// data members
	private int x;
	private int y;

	public Position() {

	}

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	// getters and setters

	public void setPosition(Position p) {
		this.x = p.getX();
		this.y = p.getY();

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {

		return ("" + x + "" + y).hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		Position temp = (Position) obj;
		if (this.getX() == temp.getX() && this.getY() == temp.getY())
			return true;

		return false;
	}

	@Override
	public String toString() {

		return ("[" + x + "," + y + "]");

	}

}
