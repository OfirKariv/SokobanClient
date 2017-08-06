package model;

import java.util.List;

import common.Level;

public interface Model {

	public void load(String path);

	public void save(String path);

	public Level getCurrentLevel();

	public void saveToDB(List<String> params);

	public void move(String direction);

	public int getSteps();
	
	public void solve();

}
