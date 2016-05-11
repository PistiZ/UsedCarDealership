package hu.pistiz.cars.view;

import hu.pistiz.cars.MainApp;
import hu.pistiz.cars.model.Vehicle;
import hu.pistiz.cars.model.VehicleDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

public class DealershipOverviewController {

	@FXML
	private TableView<Vehicle> vehicleTable;
	@FXML
	private TableColumn<Vehicle, String> brandColumn;
	@FXML
	private TableColumn<Vehicle, String> modelColumn;
	@FXML
	private TableColumn<Vehicle, String> variantColumn;
	@FXML
	private TableColumn<Vehicle, String> licensePlateNumberColumn;

	private MainApp mainApp;
	private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();

	public DealershipOverviewController() {
	}

	@FXML
	private void initialize() {
		brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
		modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
		variantColumn.setCellValueFactory(cellData -> cellData.getValue().variantProperty());
		licensePlateNumberColumn.setCellValueFactory(cellData -> cellData.getValue().licensePlateNumberProperty());

		//showVehicleDetails(null);

		/*vehicleTable.getSelectionModel().selectedItemProperty().addListener(
				((observable, oldValue, newValue) -> showVehicleDetails(newValue))
		);*/
	}

	@FXML
	private void handleNewVehicle() {
		mainApp.showNewVehicleDialog();
	}

	@FXML
	private void handleViewVehicle() {
		Vehicle vehicle = vehicleTable.getSelectionModel().getSelectedItem();
		if (vehicle != null)
			mainApp.showViewVehicleDialog(vehicle);
	}

	@FXML
	private void handleDeleteVehicle() {
		int selectedIndex = vehicleTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Törlés megerősítése");
			alert.setHeaderText("Biztosan törlöd a kiválaszott járművet?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				vehicleTable.getItems().remove(selectedIndex);
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

	/*private void showVehicleDetails(Vehicle vehicle) {
		if (vehicle != null) {
			brandLabel.setText(vehicle.getBrand());
			modelLabel.setText(vehicle.getModel());
			variantLabel.setText(vehicle.getVariant());
			licensePlateNumberLabel.setText(vehicle.getLicensePlateNumber());
			dateLabel.setText(vehicle.getDate().toString());
			purchasePriceLabel.setText(vehicleDAO.priceToString(vehicle.purchasePriceProperty().longValue()));
			salePriceLabel.setText(vehicleDAO.priceToString(vehicle.salePriceProperty().longValue()));
			kmLabel.setText(vehicleDAO.kmToString(vehicle.kmProperty().longValue()));
			fuelLabel.setText(vehicle.getFuel().toString());
			conditionLabel.setText(vehicle.getCondition().toString());
			descriptionLabel.setText(vehicle.getDescription());
		}
		else {
			brandLabel.setText("");
			modelLabel.setText("");
			variantLabel.setText("");
			dateLabel.setText("");
			purchasePriceLabel.setText("");
			salePriceLabel.setText("");
			kmLabel.setText("");
			fuelLabel.setText("");
			conditionLabel.setText("");
			descriptionLabel.setText("");
		}
	}*/

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		vehicleTable.setItems(mainApp.getVehicleData());
	}

}
