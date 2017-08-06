package db;

import java.util.List;

public interface DBManager {

	public List<DbObject> getTable(String s);

	public void addUser(String name);

	public int addLevel(String name);

	public int checkName(String name);

	public void addUserData(String userName, int lvlID, int stepCount, int timer);

	public void stop();

}
