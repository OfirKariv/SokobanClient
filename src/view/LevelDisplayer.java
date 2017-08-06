package view;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class LevelDisplayer extends Canvas {

	char[][] levelData;
	private HashMap<Character, Image> ImagesHM;
	private HashMap<String, Image> CharaHM;
	private StringProperty firstFileName;
	private StringProperty wallFileName;
	private StringProperty boxFileName;
	private StringProperty targetFileName;
	private StringProperty charaFileName;
	private StringProperty floorFileName;

	public LevelDisplayer() {
		firstFileName = new SimpleStringProperty();
		wallFileName = new SimpleStringProperty();
		boxFileName = new SimpleStringProperty();
		targetFileName = new SimpleStringProperty();
		charaFileName = new SimpleStringProperty();
		floorFileName = new SimpleStringProperty();

	}

	public void setImageHashMap() {

		ImagesHM = new HashMap<Character, Image>(); // Character as object of
													// char. not game character.
		CharaHM = new HashMap<String, Image>();

		try {

			ImagesHM.put(' ', new Image(new FileInputStream(getFloorFileName())));
			ImagesHM.put('#', new Image(new FileInputStream(getWallFileName())));
			ImagesHM.put('@', new Image(new FileInputStream(getBoxFileName())));
			ImagesHM.put('A', new Image(new FileInputStream(getCharaFileName())));
			ImagesHM.put('O', new Image(new FileInputStream(getTargetFileName())));
			CharaHM.put("up", new Image(new FileInputStream("./resources/up.png")));
			CharaHM.put("down", new Image(new FileInputStream("./resources/down.png")));
			CharaHM.put("left", new Image(new FileInputStream("./resources/left.png")));
			CharaHM.put("right", new Image(new FileInputStream("./resources/right.png")));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void setCharacterMovesHM(String direction) {

		ImagesHM.remove('A');
		ImagesHM.put('A', CharaHM.get(direction));

	}

	public char[][] getLevelData() {
		return levelData;
	}

	public void setLevelData(char[][] levelData) {
		this.levelData = levelData;
		redraw();
	}

	public String getFirstFileName() {
		return firstFileName.get();
	}

	public String getWallFileName() {
		return wallFileName.get();
	}

	public String getBoxFileName() {
		return boxFileName.get();
	}

	public String getTargetFileName() {
		return targetFileName.get();
	}

	public String getCharaFileName() {
		return charaFileName.get();
	}

	public String getFloorFileName() {
		return floorFileName.get();
	}

	public void setFirstFileName(String firstFileName) {
		this.firstFileName.set(firstFileName);
	}

	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}

	public void setBoxFileName(String boxFileName) {
		this.boxFileName.set(boxFileName);
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName.set(targetFileName);
	}

	public void setCharaFileName(String charaFileName) {
		this.charaFileName.set(charaFileName);
	}

	public void setFloorFileName(String floorFileName) {
		this.floorFileName.set(floorFileName);
	}

	public void setFirstScreen() {

		GraphicsContext aa = getGraphicsContext2D();
		try {

			Image image = new Image(new FileInputStream(getFirstFileName()));
			aa.drawImage(image, 0, 0, getWidth(), getHeight());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public void redraw() {

		if (levelData != null) {
			double W = getWidth();
			double H = getHeight();
			double w = W / levelData[0].length;

			double h = H / levelData.length;
			GraphicsContext gc = getGraphicsContext2D();

			gc.clearRect(0, 0, getWidth(), getHeight());
			for (int i = 0; i < levelData.length; i++)
				for (int j = 0; j < levelData[i].length; j++) {
					gc.drawImage(ImagesHM.get(' '), j * w, i * h, w, h);
					gc.drawImage(ImagesHM.get(levelData[i][j]), j * w, i * h, w, h);

				}

		}

	}
	/*
	 * public void pause() {
	 * 
	 * this.setOnKeyPressed(new EventHandler<KeyEvent>() {
	 * 
	 * @Override public void handle(KeyEvent event) {
	 * 
	 * } });
	 * 
	 * }
	 */
}
