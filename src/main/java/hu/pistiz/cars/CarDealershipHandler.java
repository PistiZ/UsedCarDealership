package hu.pistiz.cars;

import hu.pistiz.cars.model.*;
import hu.pistiz.cars.util.PathUtil;
import hu.pistiz.cars.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class CarDealershipHandler extends Application {

	private Stage primaryStage;
	private BorderPane rootPane;

	private Dealership dealership;
	public DealershipDAO dealershipDAO;

	//private ObservableList<Car> carData = FXCollections.observableArrayList();

	public CarDealershipHandler() {
		dealership = new Dealership();
		dealershipDAO = XMLDAOFactory.getDealershipDAO();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/*public ObservableList<Car> getCarData() {
		return carData;
	}*/

	public Dealership getDealership() {
		return dealership;
	}

	public DealershipDAO getDealershipDAO() {
		return dealershipDAO;
	}

	public void setDealership(Dealership dealership) {
		this.dealership = dealership;
	}

	public void initRootPane() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CarDealershipHandler.class.getResource("view/RootLayout.fxml"));
		try {
			rootPane = (BorderPane) loader.load();
		} catch (IOException e) {
			System.out.println("RootLayout.fxml betöltése sikertelen!");
		}

		Scene scene = new Scene(rootPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void showWelcomeStage() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CarDealershipHandler.class.getResource("view/WelcomeStage.fxml"));
		try {
			AnchorPane anchorPane = (AnchorPane) loader.load();
			anchorPane.setMaxHeight(rootPane.getPrefHeight());
			anchorPane.setMaxWidth(rootPane.getPrefWidth());
			rootPane.setCenter(anchorPane);

			WelcomeStageController controller = loader.getController();
			//controller.setStage(primaryStage);
			controller.setHandler(this);
		} catch (LoadException e) {
			System.out.println("WelcomeStage.fxml betöltése sikertelen!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showDealershipOverview() {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CarDealershipHandler.class.getResource("view/DealershipOverview.fxml"));
		try {
			AnchorPane anchorPane = (AnchorPane) loader.load();
			anchorPane.setMaxHeight(rootPane.getPrefHeight());
			anchorPane.setMaxWidth(rootPane.getPrefWidth());
			rootPane.setCenter(anchorPane);

			DealershipOverviewController controller = loader.getController();
			controller.setHandler(this);
			controller.setCompanyNameLabelText(getDealership().getName());
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
			loader.setLocation(CarDealershipHandler.class.getResource("view/NewVehicle.fxml"));
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
			loader.setLocation(CarDealershipHandler.class.getResource("view/EditVehicle.fxml"));
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
			loader.setLocation(CarDealershipHandler.class.getResource("view/ViewVehicle.fxml"));
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

	private boolean createRootDirectories() {
		if (!(new File(PathUtil.getMainDir().toUri()).exists())) {
			try {
				Files.createDirectory(PathUtil.getMainDir());
				Files.createDirectory(PathUtil.getCarDir());
				Files.createDirectory(PathUtil.getDealershipDir());
				Files.createDirectory(PathUtil.getImageDir());
				Files.createDirectory(PathUtil.getPersonDir());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else
			return false;
	}

	private void initDealership() {
		try {
			dealership = dealershipDAO.getDealership();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Használtautó-kereskedés");

		initRootPane();

		if (createRootDirectories())
			showWelcomeStage();
		else {
			initDealership();
			showDealershipOverview();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
