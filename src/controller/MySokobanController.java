package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.server.Server;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import view.View;

public class MySokobanController implements Observer {

	private Model model;
	private View view;
	private Controller controller;
	private StringProperty countSteps;
	private Map<String, Command> invoke;
	private Server server;
	// private ClientHandler clienthnd;

	public MySokobanController(Model model, View view) {
		this.model = model;
		this.view = view;
		countSteps = new SimpleStringProperty();
		initCommands(); // creates HashMap
		controller = new Controller();
		controller.start();
		view.bindForSteps(countSteps);
	}

	public MySokobanController(Model model, Server server, View clienthnd) {///////// for
		///////// server
		///////// (try)
		this.model = model;
		this.server = server;
		// this.clienthnd = (ClientHandler) clienthnd;
		this.view = clienthnd;
		initCommands(); // creates HashMap
		countSteps = new SimpleStringProperty();
		view.bindForSteps(countSteps);
		controller = new Controller();
		controller.start();
	}

	/*
	 * protected void initCommandsForServer() { invoke = new HashMap<String,
	 * Command>(); invoke.put("Display", new DisplayCommand(model, (View)
	 * clienthnd));/////// invoke.put("Move", new MoveCommand(model));
	 * invoke.put("Load", new LoadCommand(model)); invoke.put("Exit", new
	 * ExitCommand()); invoke.put("Save", new SaveCommand(model)); }
	 */
	public void initCommands() {

		invoke = new HashMap<String, Command>();
		invoke.put("Display", new DisplayCommand(model, view));
		invoke.put("Move", new MoveCommand(model, countSteps));
		invoke.put("Load", new LoadCommand(model));
		invoke.put("Exit", new ExitCommand());
		invoke.put("Save", new SaveCommand(model));
		invoke.put("Db", new DBInsertCommand(model));
		invoke.put("Solve", new SolveCommand(model));
		
	}

	public void update(Observable o, Object arg) {

		LinkedList<String> params = (LinkedList<String>) arg;

		String commandKey = params.removeFirst();

		SokobanCommand c = (SokobanCommand) invoke.get(commandKey);

		if (c == null) {

			view.DisplayMess("UNKNOWN ERROR ");
			return;
		}

		c.setParams(params);

		controller.insertCommand(c);

	}

	public class ExitCommand extends SokobanCommand {

		@Override
		public void execute() {
			controller.stop();
			view.stop();

		}

	}

}
