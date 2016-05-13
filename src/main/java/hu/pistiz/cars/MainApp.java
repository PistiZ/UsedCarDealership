package hu.pistiz.cars;

import hu.pistiz.cars.model.Car;
import hu.pistiz.cars.view.DealershipOverviewController;
import hu.pistiz.cars.view.EditVehicleController;
import hu.pistiz.cars.view.NewVehicleController;
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

	private ObservableList<Car> carData = FXCollections.observableArrayList();

	public MainApp() {
		carData.add(new Car());
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<Car> getCarData() {
		return carData;
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

	public void showNewVehicleDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/NewVehicle.fxml"));
			AnchorPane anchorPane = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.initOwner(primaryStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setTitle("Új jármű felvétele");
			Scene scene = new Scene(anchorPane);
			dialogStage.setScene(scene);

			NewVehicleController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			dialogStage.showAndWait();

		} catch (LoadException e) {
			System.out.println("NewVehicle.fxml betöltése sikertelen!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showEditVehicleDialog(Car car) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EditVehicle.fxml"));
			AnchorPane anchorPane = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.initOwner(primaryStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setTitle("Jármű szerkesztése");
			Scene scene = new Scene(anchorPane);
			dialogStage.setScene(scene);

			EditVehicleController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAndPrintVehicle(car);

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showViewVehicleDialog(Car car) {
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
			controller.setAndPrintVehicle(car);

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
