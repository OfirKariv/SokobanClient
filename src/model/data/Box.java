package model.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Box extends GeneralMovable implements movable, Serializable {

	public char getSign() {
		return '@';
	}

	public Box() {
	}

}
