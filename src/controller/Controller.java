package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Controller {

	private BlockingQueue<SokobanCommand> queue = null;
	private boolean stop = false;

	public Controller() {

		queue = new ArrayBlockingQueue<SokobanCommand>(15);
	}

	public void insertCommand(SokobanCommand cmd) {

		try {
			queue.put(cmd);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!stop) {
					try {

						SokobanCommand cmd = queue.poll(1, TimeUnit.SECONDS);
						if (cmd != null) {
							cmd.execute();

						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		});

		thread.start();

	}

	public void stop() {

		stop = true;

	}

}
