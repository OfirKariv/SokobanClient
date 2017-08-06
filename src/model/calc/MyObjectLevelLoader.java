package model.calc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import common.Level;

public class MyObjectLevelLoader implements LevelLoader {

    @Override
    /**
     * loads obj file to level
     */
    public Level loadLevel(InputStream in) {

	Level mylevel = new Level();
	ObjectInputStream in1;
	try {
	    in1 = new ObjectInputStream(in);
	    mylevel = (Level) in1.readObject();
	    in.close();
	    in1.close();
	    return mylevel;
	} catch (IOException e) {

	    e.printStackTrace();
	} catch (ClassNotFoundException e) {

	    e.printStackTrace();
	}

	return null;

    }

}
