package model.policy;

import java.util.ArrayList;

import common.Level;
import model.data.Floor;
import model.data.GameCharacter;
import model.data.GameObject;
import model.data.GeneralMovable;
import model.data.Position;
import model.data.Target;

public class LevelChanger {

	private Policy policy;
	private int revelantPlayer;
	private ArrayList<GameObject> path = new ArrayList<GameObject>();
	private Level myLevel = new Level();

	public LevelChanger() {
		setRevelantPlayer(0); // default
		policy = new MySokobanPolicy();

	}

	public int getRevelantPlayer() {
		return revelantPlayer;
	}

	public void setRevelantPlayer(int revelantPlayer) {
		this.revelantPlayer = revelantPlayer;
	}

	public void setPolicy(Policy policy) {

		this.policy = policy;
	}

	public Level getLevel() {
		return myLevel;
	}

	public void setLevel(Level myLevel) {
		this.myLevel = myLevel;
	}

	/**
	 * change level according to pre-initilazed policy
	 * 
	 * @return Level
	 */
	public void LevelChange() {

		GameCharacter player = myLevel.getCharacters().get(revelantPlayer);
		Position staticPos = new Position();
		if (path.size() == 0)
			return;
		staticPos.setPosition(path.get(path.size() - 1).getPosition());
		Position temp = new Position();
		if (policy.isPathValid(path)) {
			changePath(player);
			for (GameObject obj : path) {
				temp.setPosition(obj.getPosition());
				if (obj instanceof GeneralMovable)
					myLevel.changeMovables((GeneralMovable) obj);
				else {
					myLevel.deleteObjInMov(temp);
					obj.setPosition(staticPos);
				}
			}
		}
		myLevel.stepsJump();
		path.clear();

	}

	/**
	 * Update the path object's position to the wishfull position
	 * 
	 * @param player
	 */
	public void changePath(GameObject player) {

		ArrayList<GameObject> pathWithPlayer = new ArrayList<GameObject>();
		pathWithPlayer.add(player);
		pathWithPlayer.addAll(path);

		Position tempFirstPos = new Position();
		tempFirstPos.setPosition(pathWithPlayer.get(0).getPosition());

		for (int i = 0; i < pathWithPlayer.size() - 1; i++) {
			pathWithPlayer.get(i).setPosition(pathWithPlayer.get(i + 1).getPosition());
		}
		pathWithPlayer.get(pathWithPlayer.size() - 1).setPosition(tempFirstPos);

		path = pathWithPlayer;

	}

	/**
	 * 
	 * Add objects to lengthwised path
	 */
	public boolean addToPathUpDown(int currentX, int y) {

		if (myLevel.getMovables().get(currentX).get(y) != null) {
			path.add(myLevel.getMovables().get(currentX).get(y));

			return true;
		}
		if (myLevel.getStaticPattern().get(currentX).get(y) instanceof Floor
				|| myLevel.getStaticPattern().get(currentX).get(y) instanceof Target) {
			path.add(myLevel.getStaticPattern().get(currentX).get(y));
			return false;
		}
		return false;

	}

	/**
	 * 
	 * Add objects to widthwised path
	 */
	public boolean addToPathLeftRight(int x, int currentY)

	{
		if (myLevel.getMovables().get(x).get(currentY) != null) {
			path.add(myLevel.getMovables().get(x).get(currentY));
			return true;
		}
		if (myLevel.getStaticPattern().get(x).get(currentY) instanceof Floor
				|| myLevel.getStaticPattern().get(x).get(currentY) instanceof Target) {
			path.add(myLevel.getStaticPattern().get(x).get(currentY));
			return false;
		}
		return false;

	}

	/**
	 * create path when user typed Move down
	 * 
	 * @param p
	 * 
	 */
	public ArrayList<GameObject> pathDown(Position p) {
		int x = p.getX() + 1;
		int y = p.getY();
		int currentX = x;

		for (int i = 0; i < myLevel.getMovables().size() - x; i++, currentX++)

			if (!(addToPathUpDown(currentX, y)))
				break;

		return path;
	}

	/**
	 * create path when user typed Move up
	 * 
	 * @param p
	 * 
	 */
	public ArrayList<GameObject> pathUp(Position p) {
		int x = p.getX() - 1;
		int y = p.getY();
		int currentX = x;

		for (int i = 0; i < p.getY() + 1; i++, currentX--)

			if (!(addToPathUpDown(currentX, y)))
				break;

		return path;
	}

	/**
	 * create path when user typed Move right
	 * 
	 * @param p
	 * 
	 */
	public ArrayList<GameObject> pathRight(Position p) {
		int x = p.getX();
		int y = p.getY() + 1;
		int currentY = y;

		for (int i = 0; i < myLevel.getMovables().get(x).size() - y; i++, currentY++)

			if (!(addToPathLeftRight(x, currentY)))
				break;

		return path;
	}

	/**
	 * create path when user typed Move left
	 * 
	 * @param p
	 * 
	 */
	public ArrayList<GameObject> pathLeft(Position p) {
		int x = p.getX();
		int y = p.getY() - 1;
		int currentY = y;

		for (int i = 0; i < p.getX() + 1; i++, currentY--)

			if (!(addToPathLeftRight(x, currentY)))
				break;

		return path;
	}

}