package common;

import java.io.Serializable;
import java.util.ArrayList;

import model.data.Box;
import model.data.Floor;
import model.data.GameCharacter;
import model.data.GameObject;
import model.data.GeneralMovable;
import model.data.Position;
import model.data.Target;

public class Level implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4478978714368374149L;
	/**
	 * 
	 */

	private ArrayList<ArrayList<GameObject>> staticPattern = new ArrayList<ArrayList<GameObject>>();
	private ArrayList<ArrayList<GeneralMovable>> movables = new ArrayList<ArrayList<GeneralMovable>>();
	private ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();// set
	private ArrayList<Target> targets = new ArrayList<Target>();// set
	private String levelName = new String();
	private int steps;

	public Level() {

		steps = 0;

	}

	public int getSteps() {
		return steps;
	}

	public void stepsJump() {
		steps++;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * 
	 * @param targets
	 *            Sets ArrayList of Target() to targets
	 */
	public void setTargets(ArrayList<Target> targets) {
		this.targets = targets;
	}

	/**
	 * 
	 * @param targets
	 *            Sets ArrayList of GameCharacter() to characters
	 */
	public void setCharacters(ArrayList<GameCharacter> characters) {
		this.characters = characters;
	}

	/**
	 * 
	 * @param targets
	 *            Sets ArrayList of Target() to targets
	 */

	/**
	 * 
	 * @return ArrayList<GameCharacters>
	 */
	public ArrayList<GameCharacter> getCharacters() {

		if (characters != null)
			return characters;
		else
			return null;
	}

	/*
	 * public void changeObjectPlace(GeneralMovable obj) { // The function
	 * assume the conditions are right to change place
	 * 
	 * Position newPosition = obj.getPosition(); // Position oldP =
	 * obj.getPosition();
	 * movables.get(newPosition.getX()).set(newPosition.getY(), obj);
	 * 
	 * }
	 */

	/**
	 * 
	 * updates "movables" matrix by object (new) position
	 * 
	 * @param obj
	 */
	public void changeMovables(GeneralMovable obj) {
		int x = obj.getPosition().getX();
		int y = obj.getPosition().getY();

		movables.get(x).set(y, obj);

	}

	/**
	 * 
	 * @param Position
	 *            delete GeneralMovable object in Position p
	 */
	public void deleteObjInMov(Position p) {
		int x = p.getX();
		int y = p.getY();

		movables.get(x).set(y, null);

	}

	/**
	 * 
	 * Returns matrix of all static (not GeneralMovables) object - Wall, Floor,
	 * Target
	 * 
	 * @return ArrayList<ArrayList<GameObject>>
	 */
	public ArrayList<ArrayList<GameObject>> getStaticPattern() {

		if (staticPattern != null)
			return staticPattern;
		else
			return null;
	}

	/**
	 * 
	 * 
	 * @param staticPattern
	 *            sets staticPattren - matrix of all static (not
	 *            GeneralMovables) object - Wall, Floor, Target
	 */
	public void setStaticPattern(ArrayList<ArrayList<GameObject>> staticPattern) {

		this.staticPattern = staticPattern;

	}

	/**
	 * 
	 * Returns matrix of all GeneralMovables object - Character, Box
	 * 
	 * @return ArrayList<ArrayList<GameObject>>
	 */

	public ArrayList<ArrayList<GeneralMovable>> getMovables() {

		if (movables != null)
			return movables;
		else
			return null;

	}

	/**
	 * 
	 * 
	 * @param movables
	 *            sets movables - matrix of all GeneralMovables object -
	 *            Character, Box
	 */
	public void setMovables(ArrayList<ArrayList<GeneralMovable>> movables) {

		this.movables = movables;

	}

	/**
	 * 
	 * Find GeneralMovable object by position and returns it
	 * 
	 * @param p
	 * @return GeneralMovable
	 */
	public GameObject getMovaObj(Position p) {
		GameObject obj = movables.get(p.getX()).get(p.getY());
		return obj;
	}

	/**
	 * 
	 * Find Static object and returns it
	 * 
	 * @param p
	 * @return GameObject
	 */
	public GameObject getStaticObj(Position p) {
		GameObject obj = staticPattern.get(p.getX()).get(p.getY());
		return obj;
	}

	/**
	 * returns all targets
	 * 
	 * @return ArrayList<Target>
	 */
	public ArrayList<Target> getTargets() {

		if (targets != null)
			return targets;
		else
			return null;
	}

	/**
	 * 
	 * Update target flag if box on target for each target
	 */
	public void updateTargets() {
		for (Target t : targets) {
			Position p = t.getPosition();
			if ((getMovaObj(p)) instanceof Box)
				t.setUndrBox(true);
			else
				t.setUndrBox(false);

		}
	}

	/**
	 * 
	 * return true if all boxes on target
	 * 
	 * @return
	 */
	public boolean isComplete() {
		boolean flag = false;
		updateTargets();
		for (Target t : targets) {
			if (t.getUnderBox() == true)
				flag = true;
			else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public char[][] getCharMat() {

		boolean onTarget;
		int x = getStaticPattern().size();
		int y = getStaticPattern().get(0).size();

		char[][] charDisplayer = new char[x][y];
		int i = 0, j = 0;

		for (ArrayList<GameObject> arr : getStaticPattern()) {
			for (GameObject go : arr) {

				onTarget = false;
				GameObject temp = null;
				if (((go instanceof Floor || go instanceof Target)) && ((getMovables().get(i).get(j)) != null))
					temp = (GameObject) getMovables().get(i).get(j);

				else {

					temp = (GameObject) getStaticPattern().get(i).get(j);
				}
				if (onTarget == true)
					charDisplayer[i][j] = '*';
				else {

					charDisplayer[i][j] = (temp.getSign());
				}
				j++;
			}

			j = 0;
			i++;
		}

		return charDisplayer;

	}

	public String generateName() {
		StringBuilder strBld = new StringBuilder();


	
		int count = 1;
		int i;
		char[][] mat = this.getCharMat();

		for (char[] arr : mat) {
			
			i = 0;
		
			while (i < arr.length) {
				/*
				if(arr[i] == ' ' || (arr[i]!='@' && arr[i]!='A' &&  arr[i]!='O' && arr[i]!='#' && arr[i]!='*'))
					strBld.append('_');
				else
				strBld.append(arr[i]);
				*/
				////
				switch (arr[i]) {
				case '@':
					strBld.append('B');
					break;
				case ' ':
					strBld.append('F');
					break;
				case 'A':
					strBld.append('C');
					break;
				case '#':
					strBld.append('W');
					break;
				case 'O':
					strBld.append('T');
					break;
				case '*':
					strBld.append('G');
					break;

				default:
					strBld.append('E');
					break;
				}
				
				
				/////
				 count = 1;
				while (i < arr.length - 1 && arr[i + 1] == arr[i]) {
					
						count++;
						i++;

					}
					strBld.append(count);
					i++;
				
			}
			strBld.append("L");

	
		}

		return strBld.toString();
	}

	public String toStringLevel() {

		StringBuilder result = new StringBuilder();
		char[][] mat = this.getCharMat();
		for (char[] arr : mat) {
			result.append(arr);
			result.append('\n');
		}

		return result.toString();
	}

}
