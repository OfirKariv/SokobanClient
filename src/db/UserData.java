package db;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "user_data")

public class UserData extends DbObject {
	// @Id
	// @JoinColumn(name = "UserName")
	// private String userName;

	// @JoinColumn(name = "LevelID")
	// private int levelID;

	@EmbeddedId
	private UserLevelKey key;

	@Column(name = "TimeFinished")
	private long timeFinished;

	@Column(name = "steps")
	private int steps;

	public UserData() {
	};

	public UserData(String userName, int lvlID, int stepCount, int timer) {

		super();
		this.key = new UserLevelKey(userName, lvlID);
		this.steps = stepCount;
		this.timeFinished = timer;
	}

	public UserData(UserData ud) {
		this.key.setUserName(ud.getUserName());
		this.key.setLevelID(ud.getLevelID());

	}

	public String getUserName() {
		return key.getUserName();
	}

	public int getLevelID() {
		return key.getLevelID();
	}

	public void setLevelID(int levelID) {
		this.key.setLevelID(levelID);
	}

	public long getTimeFinished() {
		return timeFinished;
	}

	public void setTimeFinished(long timeFinished) {
		this.timeFinished = timeFinished;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public String toString() {

		return "[UserName: " + key.getUserName() + ", Level: " + key.getLevelID() + ", steps: " + steps + ", time: "
				+ timeFinished + "]";
	}

}
