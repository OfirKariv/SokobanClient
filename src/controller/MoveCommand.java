package controller;

import javafx.beans.property.StringProperty;
import model.Model;

public class MoveCommand extends SokobanCommand {

	private Model model;
	private StringProperty stepsCounter;

	public MoveCommand(Model model, StringProperty steps) {

		this.model = model;
		stepsCounter = steps;
	};

	@Override

	public void execute() {
		int steps = 0;

		String direction = params.get(0);
		model.move(direction);
		steps = model.getSteps();

		stepsCounter.set("" + (steps));

	}
}