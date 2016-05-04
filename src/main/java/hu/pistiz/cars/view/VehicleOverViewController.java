package hu.pistiz.cars.view;

import hu.pistiz.cars.MainApp;
import hu.pistiz.cars.model.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VehicleOverViewController {

	@FXML
	private TableView<Vehicle> vehicleTable;
	@FXML
	private TableColumn<Vehicle, String> brandColumn;
	@FXML
	private TableColumn<Vehicle, String> modelColumn;

	@FXML
	private Label brandLabel;
	@FXML
	private Label modelLabel;
	@FXML
	private Label variantLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label purchasePriceLabel;
	@FXML
	private Label salePriceLabel;
	@FXML
	private Label kmLabel;
	@FXML
	private Label fuelLabel;
	@FXML
	private Label conditionLabel;
	@FXML
	private Label descriptionLabel;

	private MainApp mainApp;

	public VehicleOverViewController() {
	}

	@FXML
	private void initialize() {
		brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
		modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());

		showVehicleDetails(null);

		vehicleTable.getSelectionModel().selectedItemProperty().addListener(
				((observable, oldValue, newValue) -> showVehicleDetails(newValue))
		);
	}

	private void showVehicleDetails(Vehicle vehicle) {
		if (vehicle != null) {
			brandLabel.setText(vehicle.getBrand());
			modelLabel.setText(vehicle.getModel());
			variantLabel.setText(vehicle.getVariant());
			dateLabel.setText(vehicle.getDate().toString());
			purchasePriceLabel.setText(vehicle.getPurchasePrice().toString());
			salePriceLabel.setText(vehicle.getSalePrice().toString());
			kmLabel.setText(vehicle.kmProperty().toString());
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
	}

}
