package hu.pistiz.cars;

import hu.pistiz.cars.model.Vehicle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootPane;

	private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();

	public MainApp() {
		vehicleData.add(new Vehicle("Suzuki", "Swift"));
		vehicleData.add(new Vehicle("Koenigsegg", "Agera"));
		vehicleData.add(new Vehicle("Opel", "Astra"));
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<Vehicle> getVehicleData() {
		return vehicleData;
	}

	public void initRootPane() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getClassLoader().getResource("fxml/RootLayout.fxml"));
		try {
			rootPane = (BorderPane) loader.load();
		} catch (IOException e) {
			System.out.println("RootLayout.fxml betöltése sikertelen!");
		}

		Scene scene = new Scene(rootPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void showCarDataOverview() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getClassLoader().getResource("fxml/VehicleDataOverview.fxml"));
		try {
			AnchorPane anchorPane = (AnchorPane) loader.load();
			rootPane.setCenter(anchorPane);
		} catch (IOException e) {
			System.out.println("CarDatOverview.fxml betöltése sikertelen!");
		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Használtautó-kereskedés");

		initRootPane();
		showCarDataOverview();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
