package hu.pistiz.cars.view;

import hu.pistiz.cars.model.Vehicle;
import hu.pistiz.cars.model.VehicleDAO;
import hu.pistiz.cars.model.VehicleDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ViewVehicleController {

	@FXML
	private Label brandLabel;
	@FXML
	private Label modelLabel;
	@FXML
	private Label variantLabel;
	@FXML
	private Label licensePlateNumberLabel;
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
	private TextArea descriptionArea;

	private Stage dialogStage;
	private Vehicle vehicle;
	private VehicleDAO vehicleDAO = new VehicleDAOImpl();

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void initialize() {
	}

	public void setAndPrintVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;

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
			descriptionArea.setText(vehicle.getDescription());
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
			descriptionArea.setText("");
		}
	}

	@FXML
	private void handleBack() {
		dialogStage.close();
	}
}
