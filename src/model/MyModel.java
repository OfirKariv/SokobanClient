package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import common.Level;
import db.DBManager;
import db.HbrntDBManager;
import db.LevelInfo;
import db.User;
import db.UserData;
import model.data.GameCharacter;
import model.data.Position;
import model.policy.LevelChanger;

public class MyModel extends Observable implements Model {

	private Level myLevel = null;
	private LevelChanger change = new LevelChanger();
	private int relevantPlayer;
	private User user;
	private UserData ud;
	private LevelInfo level;
	private DBManager db;

	public MyModel() {
		myLevel = new Level();
		setRelevantPlayer(0); // default
	}

	public void setLevel(Level level) {
		this.myLevel = level;
	}

	public void setRelevantPlayer(int relevantPlayer) {
		this.relevantPlayer = relevantPlayer;
	}

	@Override
	public void load(String path) {

		LoadLevelFactory lvlLoad = new LoadLevelFactory();
		setLevel(lvlLoad.toFile(path));
		/////////////////////////////////////////
		myLevel.setLevelName(path);
		myLevel.toStringLevel();
		this.setChanged();
		LinkedList<String> params = new LinkedList<String>();
		params.add("Display");
		this.notifyObservers(params);

	}

	@Override
	public void save(String path) {

		SaveLevelFactory lvlSav = new SaveLevelFactory();
		lvlSav.setFile(getCurrentLevel(), path);

	}

	@Override
	public Level getCurrentLevel() {

		return myLevel;

	}

	///////////////////////////////
	@Override
	public void saveToDB(List<String> params) {

		if (params != null) {
			for (int i = 0; i < 3; i++)
				if (params.get(i) == null) {
					/* TODO - add error displayer */

				}
			int lvlID, steps, time;
			db = new HbrntDBManager();
			String name = params.remove(0);
			// add user to DBManager
			db.addUser(name);

			// Add Level to DBManager
			String levelName = myLevel.getLevelName();
			lvlID = db.addLevel(levelName);

			// Add userData

			String timeStr = params.remove(0);
			time = Integer.parseInt(timeStr);
			String stepsStr = params.remove(0);
			steps = Integer.parseInt(stepsStr);

			db.addUserData(name, lvlID, steps, time);
			db.stop();

		}
	}

	/*
	 * public Query<UserData> getFromDB() {
	 * 
	 * if (db == null) return null;
	 * 
	 * db.getTable(myLevel.getLevelName()); return null; }
	 */
	@Override
	public void move(String direction) {

		change.setLevel(this.getCurrentLevel());
		GameCharacter player = myLevel.getCharacters().get(relevantPlayer);
		Position playerPos = player.getPosition();
		switch (direction) {

		case "up":

			change.pathUp(playerPos);
			break;

		case "down":

			change.pathDown(playerPos);
			break;

		case "left":
			change.pathLeft(playerPos);
			break;

		case "right":

			change.pathRight(playerPos);

			break;
		case "E":

			return;

		default:
			return;

		}

		change.LevelChange();
		setLevel(change.getLevel());

		this.setChanged();
		LinkedList<String> params = new LinkedList<String>();

		params.add("Display");
		this.notifyObservers(params);

		if (myLevel.isComplete()) {
			params.add("Display");
			params.add("Fin");
			this.setChanged();
			this.notifyObservers(params);

		}

	}

	public LinkedList<String> serverHandler() {
		LinkedList<String> list;

		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 2222);

			ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

			char[][] levelArr = myLevel.getCharMat();

			outToServer.writeObject(myLevel);

			list = (LinkedList<String>) inFromServer.readObject();

			outToServer.close();
			inFromServer.close();
			socket.close();

			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void solve() {

		LinkedList<String> moves = serverHandler();
		if (moves == null) {
			return;
		}
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				for (String str : moves) {
					try {

						Thread.sleep(400);
						move(str);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		t.start();

	}

	@Override
	public int getSteps() {

		return myLevel.getSteps();

	}

}