package controller;

import model.Model;
import view.View;

public class DisplayCommand extends SokobanCommand {

	Model model;
	View view;

	public DisplayCommand(Model model, View view) {

		this.model = model;
		this.view = view;

	}

	public void execute() {

		
		
		if (params.size() != 0 && params.get(0) == "Fin") {
			view.DisplayMess("Level Completed!");
			params.remove(0);
		}

		view.Display(model.getCurrentLevel());

	}

}
