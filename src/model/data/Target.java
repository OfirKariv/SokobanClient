package model.data;

import java.io.Serializable;

public class Target extends GameObject implements Serializable {
    private boolean UnderBox = false;

    public char getSign() {
	return 'O';
    }

    public Target() {
    }

    public void setUndrBox(boolean b) {
	this.UnderBox = b;
    }

    public boolean getUnderBox() {
	return this.UnderBox;
    }
}
