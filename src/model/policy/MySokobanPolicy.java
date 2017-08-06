package model.policy;

import java.util.ArrayList;

import common.Level;
import model.data.Box;
import model.data.Floor;
import model.data.GameObject;
import model.data.Position;
import model.data.Target;

public class MySokobanPolicy implements Policy {

	private int numOfBoxToMove;
	private Level myLevel;

	public MySokobanPolicy() {
		numOfBoxToMove = 1;
		myLevel = new Level();
	}

	public Level getLevel() {
		return myLevel;
	}

	public void setLevel(Level myLevel) {
		this.myLevel = myLevel;
	}

	/**
	 * gets a path via array and return true if according to the policy the path
	 * is valid
	 */
	public boolean isPathValid(ArrayList<GameObject> arr) {

		if (arr.size() < 1)
			return false;

		if (isWalkble(arr))
			return true;

		else
			return false;

	}

	/**
	 * 
	 * return true if the path can move
	 */
	public boolean isWalkble(ArrayList<GameObject> arr) {
		if (!(arr.get(arr.size() - 1) instanceof Floor || arr.get(arr.size() - 1) instanceof Target))
			return false;

		int count = 0;

		for (int i = 0; i < arr.size() - 1; i++) {

			if (count == numOfBoxToMove)
				return false;

			if (!(isObjValid(arr.get(i))))
				return false;
			count++;

		}

		return true;
	}

	/**
	 * checks if an object is valid to move
	 * 
	 * 
	 */
	public boolean isObjValid(GameObject obj) {

		if (obj instanceof Box)
			return true;
		return false;

	}

	public boolean isClear(Position p)

	{

		int x = p.getX();
		int y = p.getY();
		int limitX = (myLevel.getStaticPattern().size()) - 1;
		int limitY = (myLevel.getStaticPattern().get(0).size()) - 1;

		if ((x < 0) || (x > limitX) || (y < 0) || (y > limitY))
			return false;

		if (myLevel.getMovables().get(x).get(y) != null)
			return false;

		if (myLevel.getStaticPattern().get(x).get(y) instanceof Floor
				|| myLevel.getStaticPattern().get(x).get(y) instanceof Target) {
			return true;
		}
		return false;

	}

}
