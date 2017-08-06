package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.MyModel;

public class Server extends Observable implements Observer {

	private MyClientHandler clientHndlr = new MyClientHandler();
	private MyModel model = null;
	private List<String> params = null;
	private int port = 5070;

	public void init(MyClientHandler clientHndlr, MyModel model) {
		this.clientHndlr = clientHndlr;
		this.model = model;
	}

	public void startServer() throws IOException { // posibel mistek
		ServerSocket server = new ServerSocket(port);
		// server.setSoTimeout(1000);
		try {
			Socket aClient = server.accept(); // blocking call
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						int g = countObservers();//
						System.out.println(g);
						clientHndlr.handleClient(aClient.getInputStream(), aClient.getOutputStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}).start();

			server.close();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Observable o, Object params) {
		System.out.println("im in server update");
		this.params = (LinkedList<String>) params;
		System.out.println(params);
		setChanged();
		notifyObservers(this.params);

	}

}
