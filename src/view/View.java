package view;


import common.Level;
import javafx.beans.property.StringProperty;

public interface View {

	public void Display(Level myLevel);

	public void DisplayMess(String s);

	public void start();

	public void stop();

	public void bindForSteps(StringProperty count);
}
