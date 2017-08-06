package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "level")
public class LevelInfo extends DbObject {

	public LevelInfo(String s) {

		setLevelName(s);
	}

	public LevelInfo() {
	}

	@Id
	@Column(name = "LevelID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int levelID;

	@Column(name = "LevelName")
	private String levelName;

	public int getLevelID() {
		return levelID;
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;

	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;

	}

	public String toString() {

		return "[Level: " + levelID + "]";

	}

}
