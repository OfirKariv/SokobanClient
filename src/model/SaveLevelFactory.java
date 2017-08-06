package model;

import java.util.HashMap;

import common.Level;
import model.calc.LevelSaver;
import model.calc.MyObjectLevelSaver;
import model.calc.MyTextLevelSaver;
import model.calc.MyXMLLevelSaver;

public class SaveLevelFactory {

    private HashMap<String, LevelSaver> fileType;

    public SaveLevelFactory() {

	fileType = new HashMap<String, LevelSaver>();

	fileType.put("txt", new MyTextLevelSaver());
	fileType.put("xml", new MyXMLLevelSaver());
	fileType.put("obj", new MyObjectLevelSaver());

    }

    public void setFile(Level myLevel, String path) {

	String[] s = path.split("\\.");
	fileType.get(s[1]).saveLevel(myLevel, path);

    }

}
