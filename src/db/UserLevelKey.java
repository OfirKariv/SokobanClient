package db;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserLevelKey implements Serializable {

	private String UserName;
	private int LevelID;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getLevelID() {
		return LevelID;
	}

	public void setLevelID(int levelID) {
		LevelID = levelID;
	}

	public UserLevelKey(String userName, int levelID) {
		super();
		UserName = userName;
		LevelID = levelID;
	}

	public UserLevelKey() {
	}

	@Override
	public int hashCode() {

		int result = 1;
		result = 31 * result + UserName.hashCode();
		result = 31 * result + LevelID;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLevelKey other = (UserLevelKey) obj;

		if (!(UserName.equals(other.UserName)))
			return false;
		if (LevelID != other.LevelID)
			return false;
		return true;
	}
}
