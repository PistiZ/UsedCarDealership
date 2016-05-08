package hu.pistiz.cars;

import hu.pistiz.cars.model.Vehicle;
import hu.pistiz.cars.view.DealershipOverviewController;
import hu.pistiz.cars.view.ViewVehicleController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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
		loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
		try {
			rootPane = (BorderPane) loader.load();
		} catch (IOException e) {
			System.out.println("RootLayout.fxml betöltése sikertelen!");
		}

		Scene scene = new Scene(rootPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void showDealershipOverview() {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DealershipOverview.fxml"));
		try {
			AnchorPane anchorPane = (AnchorPane) loader.load();
			anchorPane.setMaxHeight(rootPane.getPrefHeight());
			anchorPane.setMaxWidth(rootPane.getPrefWidth());
			rootPane.setCenter(anchorPane);

			DealershipOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (LoadException e) {
			System.out.println("DealershipOverview.fxml betöltése sikertelen!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showViewVehicleDialog(Vehicle vehicle) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ViewVehicle.fxml"));
			AnchorPane anchorPane = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.initOwner(primaryStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setTitle("Jármű adatai");
			Scene scene = new Scene(anchorPane);
			dialogStage.setScene(scene);

			ViewVehicleController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAndPrintVehicle(vehicle);

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Használtautó-kereskedés");

		initRootPane();
		showDealershipOverview();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
