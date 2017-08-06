package controller;

import model.Model;

public class DBInsertCommand extends SokobanCommand {

	private Model model;
	private String name;

	public DBInsertCommand(Model model) {
		this.model = model;

	}

	@Override
	public void execute() {

		model.saveToDB(params);

	}

}
