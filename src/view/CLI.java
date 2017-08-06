package view;


import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import common.Level;
import javafx.beans.property.StringProperty;

public class CLI extends Observable implements View {

	@SuppressWarnings("unused")
	private boolean stop = false;

	public CLI() {

	}

	/**
	 * initialzer
	 * 
	 * @param policy
	 * @param displayer
	 */

	@SuppressWarnings("resource")
	/**
	 * Game manager - type work exactly as said. Move example: Move up Load Save
	 * example - Load c:/dir/file.txt
	 */
	public void start() {
		Scanner in = new Scanner(System.in);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("****Welcome to SoKoBan!****:");
				System.out.println("****Please choose option:****:");
				System.out.println("Load");
				System.out.println("Display");
				System.out.println("Move {up,down,left,right}:");
				System.out.println("Save");
				System.out.println("Exit\n");
				for (;;) {

					String[] sa;
					String commandline;

					System.out.println("Please choose option:");

					commandline = in.nextLine();

					sa = commandline.split(" ");

					List<String> params = new LinkedList<String>();

					for (String s : sa) {
						params.add(s);
					}

					setChanged();
					notifyObservers(params);
				}
			}
		});
		thread.start();
	}

	@Override
	public void Display(Level myLevel) {

		TxtDisplayer dis = new TxtDisplayer();
		dis.display(myLevel.getCharMat());

	}

	@Override
	public void DisplayMess(String s) {
		System.out.println(s);

	}

	public void stop() {
		stop = true;
		System.exit(0);

	}

	@Override
	public void bindForSteps(StringProperty count) {
		// TODO Auto-generated method stub

	}

}

// mylevel = invoke.get(sa[0]).execute(sa, mylevel);
