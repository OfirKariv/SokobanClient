package controller;

import model.Model;

public class LoadCommand extends SokobanCommand {

	private Model model;

	public LoadCommand(Model model) {
		this.model = model;
	}

	@Override
	public void execute() {

		model.load(params.get(0));

	}

}
