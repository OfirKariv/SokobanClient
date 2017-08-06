package model.calc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import common.Level;

public class MyObjectLevelSaver implements LevelSaver {

    @Override
    public void saveLevel(Level level, String s) {
	ObjectOutputStream out = null;
	try {
	    out = new ObjectOutputStream(new FileOutputStream(s));
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	try {
	    out.writeObject(level);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// //out.flush();
	try {
	    out.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
