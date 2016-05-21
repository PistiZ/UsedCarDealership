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
	public DealershipService dealershipService;
	public CarService carService;
	public CarDAO carDAO;
	public PersonDAO personDAO;

	private ObservableList<Car> carData = FXCollections.observableArrayList();
	//private ObservableLongValue income;
	//private ObservableLongValue profit;

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

	/*public Number getIncome() {
		return income.get();
	}

	public ObservableLongValue incomeProperty() {
		return income;
	}*/

	/*public Number getProfit() {
		return profit.get();
	}

	public ObservableLongValue profitProperty() {
		return profit;
	}*/

	public void initRootPane() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("fxml/RootLayout.fxml"));
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
		loader.setLocation(getClass().getClassLoader().getResource("fxml/WelcomeStage.fxml"));
		try {
			AnchorPane anchorPane = (AnchorPane) loader.load();
			/*anchorPane.setMaxHeight(rootPane.getPrefHeight());
			anchorPane.setMaxWidth(rootPane.getPrefWidth());*/
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
			loader.setLocation(getClass().getClassLoader().getResource("fxml/DealershipOverview.fxml"));
		try {
			AnchorPane anchorPane = (AnchorPane) loader.load();
			/*anchorPane.setMaxHeight(rootPane.getPrefHeight());
			anchorPane.setMaxWidth(rootPane.getPrefWidth());*/
			rootPane.setCenter(anchorPane);

			DealershipOverviewController controller = loader.getController();
			controller.setHandler(this);
			controller.setCompanyNameLabelText(getDealership().getName());
			controller.setIncomeFieldText(getDealership().getIncome());
			controller.setProfitFieldText(getDealership().getProfit());
			controller.setSoldCarsFieldText(getDealership().getSoldCars());
			controller.setRemainderFieldText(getDealership().getRemainder());
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

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
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

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
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

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
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

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
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
			dealership.setOwner(personDAO.getPerson());
			//income = new SimpleLongProperty(dealership.getIncome());
			//profit = new SimpleLongProperty(dealership.getProfit());
			carData = carDAO.getCarsForSale();
			carData.addListener((ListChangeListener<Car>) c -> carData = carDAO.getCarsForSale());
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
		else if (!(new File(PathUtil.getDealershipDir() + System.getProperty("file.separator") + "dealership.xml").exists())) {
			//carData = carDAO.getCarsForSale();
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
