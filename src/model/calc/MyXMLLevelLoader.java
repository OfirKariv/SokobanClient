package model.calc;

import java.beans.XMLDecoder;
import java.io.InputStream;

import common.Level;

public class MyXMLLevelLoader implements LevelLoader {

	public Level loadLevel(InputStream in) {
		Level mylevel = new Level();
		XMLDecoder decoder = new XMLDecoder(in);
		mylevel = (Level) decoder.readObject();
		decoder.close();
		return mylevel;

	}
}