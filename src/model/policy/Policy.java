package model.policy;

import java.util.ArrayList;

import model.data.GameObject;

public interface Policy {
	public boolean isPathValid(ArrayList<GameObject> arr);
	

}
