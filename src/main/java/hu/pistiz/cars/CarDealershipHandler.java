// CHECKSTYLE:OFF
package hu.pistiz.cars;

import hu.pistiz.cars.model.*;
import hu.pistiz.cars.model.service.CarService;
import hu.pistiz.cars.model.service.DealershipService;
import hu.pistiz.cars.util.PathUtil;
import hu.pistiz.cars.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

/**
 * A fő JavaFX osztály, amely iráníytja az alkalmazás működését.
 */
public class CarDealershipHandler extends Application {

	private Stage primaryStage;
	private BorderPane rootPane;

	public static Logger logger = LoggerFactory.getLogger(CarDealershipHandler.class);

	private Dealership dealership;
	public DealershipDAO dealershipDAO;
	public DealershipService dealershipService;
	public CarService carService;
	public CarDAO carDAO;
	public PersonDAO personDAO;

	private ObservableList<Car> carData = FXCollections.observableArrayList();

	public CarDealershipHandler() {
		dealership = new Dealership();
		dealershipDAO = XMLDAOFactory.getDealershipDAO();
		carDAO = XMLDAOFactory.getCarDAO();
		personDAO = XMLDAOFactory.getPersonDAO();
		dealershipService = new DealershipService();
		carService = new CarService();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public BorderPane getRootPane() {
		return rootPane;
	}

	public Dealership getDealership() {
		return dealership;
	}

	public DealershipService getDealershipService() {
		return dealershipService;
	}

	public CarService getCarService() {
		return carService;
	}

	public DealershipDAO getDealershipDAO() {
		return dealershipDAO;
	}

	public CarDAO getCarDAO() {
		return carDAO;
	}

	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public ObservableList<Car> getCarData() {
		return carData;
	}

	public void setCarData(ObservableList<Car> carData) {
		this.carData = carData;
	}

	public void setDealership(Dealership dealership) {
		this.dealership = dealership;
	}

	public void initRootPane() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("fxml/RootLayout.fxml"));
		try {
			rootPane = (BorderPane) loader.load();
			logger.info("Alapnézet betöltve.");
		} catch (IOException e) {
			logger.error("RootLayout.fxml betöltése sikertelen!", e);
		}

		Scene scene = new Scene(rootPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void showWelcomeStage() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("fxml/WelcomeStage.fxml"));
		try {
			AnchorPane anchorPane = (AnchorPane) loader.load();
			logger.info("Üdvözlőképernyő betöltve.");
			rootPane.setCenter(anchorPane);

			WelcomeStageController controller = loader.getController();
			controller.setHandler(this);
		} catch (IOException e) {
			logger.error("WelcomeStage.fxml betöltése sikertelen!", e);
		}
	}

	public void showDealershipOverview() {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("fxml/DealershipOverview.fxml"));
		try {
			AnchorPane anchorPane = (AnchorPane) loader.load();
			logger.info("Kereskedés-nézet betöltve.");
			rootPane.setCenter(anchorPane);

			DealershipOverviewController controller = loader.getController();
			controller.setHandler(this);
			controller.setCompanyNameLabelText(getDealership().getName());
			controller.setIncomeFieldText(getDealership().getIncome());
			controller.setProfitFieldText(getDealership().getProfit());
			controller.setSoldCarsFieldText(getDealership().getSoldCars());
			controller.setRemainderFieldText(getDealership().getRemainder());
			logger.info("Szövegmezők feltöltve.");
		} catch (IOException e) {
			logger.error("DealershipOverview.fxml betöltése sikertelen!", e);
		}
	}

	public void showNewVehicleDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("fxml/NewVehicle.fxml"));
			AnchorPane anchorPane = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.initOwner(primaryStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setTitle("Új jármű felvétele");
			Scene scene = new Scene(anchorPane);
			dialogStage.setScene(scene);

			NewVehicleController controller = loader.getController();
			controller.setHandler(this);
			controller.setDialogStage(dialogStage);

			logger.info("Új autó-nézet betöltve.");
			dialogStage.showAndWait();

		}  catch (IOException e) {
			logger.error("NewVehicle.fxml betöltése sikertelen!", e);
		}
	}

	public void showEditVehicleDialog(Car car) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("fxml/EditVehicle.fxml"));
			AnchorPane anchorPane = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.initOwner(primaryStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setTitle("Jármű szerkesztése");
			Scene scene = new Scene(anchorPane);
			dialogStage.setScene(scene);

			EditVehicleController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setHandler(this);
			controller.setAndPrintCar(car);

			logger.info("Autó szerkesztése-nézet betöltve.");
			dialogStage.showAndWait();

		} catch (IOException e) {
			logger.error("EditVehicle.fxml betöltése sikertelen!", e);
		}
	}

	public void showViewVehicleDialog(Car car) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("fxml/ViewVehicle.fxml"));
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

			logger.info("Autó megtekintése-nézet betöltve.");
			dialogStage.showAndWait();

		} catch (IOException e) {
			logger.error("ViewVehicle.fxml betöltése sikertelen!", e);
		}
	}

	public void showSaleCarDialog(Car car) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("fxml/SoldCarView.fxml"));
			BorderPane borderPane = (BorderPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.initOwner(primaryStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setTitle("Jármű eladása");
			Scene scene = new Scene(borderPane);
			dialogStage.setScene(scene);

			SoldCarViewController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setHandler(this);
			controller.setAndPrintCar(car);

			logger.info("Autó eladása-nézet betöltve.");
			dialogStage.showAndWait();

		} catch (IOException e) {
			logger.error("SoldCarView.fxml betöltése sikertelen!", e);
		}
	}

	public void showBuyBackCarDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("fxml/BuyBackCar.fxml"));
			AnchorPane anchorPane = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.initOwner(primaryStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setTitle("Autó visszavásárlása");
			Scene scene = new Scene(anchorPane);
			dialogStage.setScene(scene);

			BuyBackCarController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setHandler(this);

			logger.info("Autó visszavásárlása-nézet betöltve.");
			dialogStage.showAndWait();

		} catch (IOException e) {
			logger.error("BuyBackCar.fxml betöltése sikertelen!", e);
		}
	}

	private boolean createRootDirectories() {
		if (!(new File(PathUtil.getMainDir().toUri()).exists())) {
			try {
				Files.createDirectory(PathUtil.getMainDir());
				Files.createDirectory(PathUtil.getDealershipDir());
				Files.createDirectory(PathUtil.getCarDir());
				Files.createDirectory(PathUtil.getCarsForSaleDir());
				Files.createDirectory(PathUtil.getSoldCarsDir());
				Files.createDirectory(PathUtil.getImageDir());
				Files.createDirectory(PathUtil.getPersonDir());
				logger.info("Mappák elkészítve");
			} catch (IOException e) {
				logger.error("Mappák készítése sikertelen", e);
			}
			return true;
		} else
			return false;
	}

	private void initDealership() {
		try {
			dealership = dealershipDAO.getDealership();
			dealership.setOwner(personDAO.getPerson());
			carData = carDAO.getCarsForSale();
			carData.addListener((ListChangeListener<Car>) c -> carData = carDAO.getCarsForSale());
			logger.info("Gépjármű-adatok betöltve.");
		} catch (FileNotFoundException e) {
			logger.error("Fájl nem található!", e);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Használtautó-kereskedés");

		initRootPane();

		if (createRootDirectories()) {
			showWelcomeStage();
		}
		else if (!(new File(PathUtil.getDealershipDir() + System.getProperty("file.separator") + "dealership.xml").exists())) {
			carData = carDAO.getCarsForSale();
			showWelcomeStage();
		}
		else {
			initDealership();
			showDealershipOverview();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
