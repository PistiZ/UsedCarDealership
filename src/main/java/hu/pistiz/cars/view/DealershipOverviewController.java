package hu.pistiz.cars.view;

import hu.pistiz.cars.MainApp;
import hu.pistiz.cars.model.Car;
import hu.pistiz.cars.model.CarDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

public class DealershipOverviewController {

	@FXML
	private TableView<Car> carTable;
	@FXML
	private TableColumn<Car, String> brandColumn;
	@FXML
	private TableColumn<Car, String> modelColumn;
	@FXML
	private TableColumn<Car, String> variantColumn;
	@FXML
	private TableColumn<Car, String> licensePlateNumberColumn;

	private MainApp mainApp;
	private CarDAOImpl carDAO = new CarDAOImpl();

	public DealershipOverviewController() {
	}

	@FXML
	private void initialize() {
		brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
		modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
		variantColumn.setCellValueFactory(cellData -> cellData.getValue().variantProperty());
		licensePlateNumberColumn.setCellValueFactory(cellData -> cellData.getValue().licensePlateNumberProperty());

		//showVehicleDetails(null);

		/*carTable.getSelectionModel().selectedItemProperty().addListener(
				((observable, oldValue, newValue) -> showVehicleDetails(newValue))
		);*/
	}

	@FXML
	private void handleNewVehicle() {
		mainApp.showNewVehicleDialog();
	}

	@FXML
	private void handleViewVehicle() {
		Car car = carTable.getSelectionModel().getSelectedItem();
		if (car != null)
			mainApp.showViewVehicleDialog(car);
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Üres kijelölés");
			alert.setHeaderText("Nem jelöltél ki járművet");
			alert.setContentText("Jelölj ki egy járművet a megtekintéshez!");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleEditVehicle() {
		Car car = carTable.getSelectionModel().getSelectedItem();
		if (car != null) {
			mainApp.showEditVehicleDialog(car);
			initialize();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Üres kijelölés");
			alert.setHeaderText("Nem jelöltél ki járművet");
			alert.setContentText("Jelölj ki egy járművet a szerkesztéshez!");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleDeleteVehicle() {
		int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Törlés megerősítése");
			alert.setHeaderText("Biztosan törlöd a kiválaszott járművet?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				carTable.getItems().remove(selectedIndex);
			} else {
				alert.close();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Üres kijelölés");
			alert.setHeaderText("Nem jelöltél ki járművet");
			alert.setContentText("Jelölj ki egy járművet a törléshez!");

			alert.showAndWait();
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		carTable.setItems(mainApp.getCarData());
	}

}
