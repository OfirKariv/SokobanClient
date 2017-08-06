package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import common.Level;
import controller.SokobanCommand;
import javafx.beans.property.StringProperty;
import view.TxtDisplayer;
import view.View;

public class MyClientHandler extends Observable implements ClientHandler, View {
	private PrintWriter writer = null;
	private BlockingQueue<Character> queue = null;

	public void init(PrintWriter writer) {
		this.writer = writer;
		queue = new ArrayBlockingQueue<Character>(200);

	}

	@Override
	public void handleClient(InputStream in, OutputStream out) {

		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(in));
		PrintWriter toUser = new PrintWriter(out);
		init(toUser);
		menu();
		String userReq;

		Thread t1 = readfromClient(inFromClient);
		Thread t2 = syncToClient(writer);
		writer.flush();
		try {

			t1.join();
			t2.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// writer.print(params);
	}

	public Thread readfromClient(BufferedReader inFromClient) {

		Thread t = new Thread(new Runnable() {
			public void run() {
				String userReq = "temp";
				try {
					while (!userReq.equals("bye")) {
						userReq = inFromClient.readLine();
						String[] sa;
						sa = userReq.split(" ");
						LinkedList<String> params = new LinkedList<String>();
						for (String s : sa) {
							params.add(s);
						}
						System.out.println(params);

						setChanged();

						notifyObservers(params);

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();
		return t;

	}

	public Thread syncToClient(PrintWriter writer) {
		Thread t = new Thread(new Runnable() {
			public void run() {

				sendToClient();
			}
		});
		t.start();
		return t;

	}

	@Override
	public void Display(Level myLevel) {
		// TxtDisplayer dis = new TxtDisplayer();
		// dis.display(myLevel.getCharMat());
		// setChanged();
		// notifyObservers("Display");
		// writer.println(myLevel.getCharMat());

		char[][] mat = myLevel.getCharMat();
		char c;

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j]);
				c = mat[i][j];
				try {
					queue.put(c);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			c = '\n';
			try {
				queue.put(c);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		}
		sendToClient();

	}

	public void sendToClient() {
		while (true) {
			try {
				Character line = queue.take();
				if (line != null) {
					writer.print(line);
					writer.flush();

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void menu() {
		writer.println("****Welcome to SoKoBan!****:");
		writer.println("****Please choose option:****:");
		writer.println("Load");
		writer.println("Display");
		writer.println("Move {up,down,left,right}:");
		writer.println("Save");
		writer.println("Exit\n");
		writer.flush();
	}

	@Override
	public void DisplayMess(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindForSteps(StringProperty count) {
		// TODO Auto-generated method stub

	}
}
