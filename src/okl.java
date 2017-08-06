

import controller.MySokobanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MyModel;
import view.MainWindowController;

public class okl extends Application {
	
	private MySokobanController controller;

	public static void main(String[] args) {

		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MainWindowController view = loader.getController();
			view.setStage(primaryStage);

			Scene scene = new Scene(root, 650, 650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			init(view);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		private void init(MainWindowController view) {
			MyModel model = new MyModel();
			controller = new MySokobanController(model, view);

			model.addObserver(controller);
			view.addObserver(controller);
			view.start();
		}
	

}
