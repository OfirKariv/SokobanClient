
package controller;

import java.util.HashMap;

import model.Model;
import model.calc.LevelSaver;

public class SaveCommand extends SokobanCommand {
    private Model model;
    HashMap<String, LevelSaver> filetype;

    public SaveCommand(Model model) {

	this.model = model;
    }

    @Override
    public void execute() {
	model.save(params.get(0));
    }

}