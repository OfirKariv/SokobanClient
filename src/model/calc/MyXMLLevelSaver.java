package model.calc;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import common.Level;

public class MyXMLLevelSaver implements LevelSaver {

    @Override
    public void saveLevel(Level level, String s) {

	try {
	    XMLEncoder encod = new XMLEncoder(new FileOutputStream(s));
	    encod.writeObject(level);
	    encod.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

    }

}