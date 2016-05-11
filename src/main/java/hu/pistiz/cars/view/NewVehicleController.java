package hu.pistiz.cars.view;

import hu.pistiz.cars.model.*;
import hu.pistiz.cars.util.LicensePlateNumberUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.Year;

public class NewVehicleController {

	@FXML
	private TextField brandField;
	@FXML
	private TextField modelField;
	@FXML
	private TextField variantField;
	@FXML
	private TextField licensePlateNumberField;
	@FXML
	private TextField dateField;
	@FXML
	private TextField purchasePriceField;
	@FXML
	private TextField salePriceField;
	@FXML
	private TextField kmField;
	@FXML
	private ChoiceBox<Fuel> fuelBox;
	@FXML
	private ChoiceBox<Condition> conditionBox;
	@FXML
	private TextArea descriptionArea;

	private Stage dialogStage;
	private Vehicle vehicle;
	private VehicleDAO vehicleDAO = new VehicleDAOImpl();
	private boolean saveClicked = false;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isSaveClicked() {
		return saveClicked;
	}

	@FXML
	private void initialize() {
		brandField.setText("");
		modelField.setText("");
		variantField.setText("");
		licensePlateNumberField.setText("");
		dateField.setText("");
		purchasePriceField.setText("");
		salePriceField.setText("");
		kmField.setText("");
		fuelBox.getItems().addAll(Fuel.values());
		conditionBox.getItems().addAll(Condition.values());
		descriptionArea.setText("");
		// http://stackoverflow.com/questions/12744542/requestfocus-in-textfield-doesnt-work-javafx-2-1
		Platform.runLater(() -> brandField.requestFocus());
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (brandField.getText() == null || brandField.getText().length() == 0) {
			errorMessage += "Nem megfelelő márka!\n";
		}

		if (modelField.getText() == null || modelField.getText().length() == 0) {
			errorMessage += "Nem megfelelő modell!\n";
		}

		if (variantField.getText() == null || variantField.getText().length() == 0) {
			errorMessage += "Nem megfelelő típus!\n";
		}

		if (licensePlateNumberField.getText() == null || licensePlateNumberField.getText().length() == 0) {
			errorMessage += "Üres rendszám nem megengedett!\n";
		} else {
			if (!(LicensePlateNumberUtil.isValid(licensePlateNumberField.getText())))
				errorMessage += "Nem megfelelő rendszám! (3 betű, kötőjel, 3 szám)\n";
		}

		/*if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if (!DateUtil.validDate(birthdayField.getText())) {
				errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			}
		}*/

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Hibás mezők");
			alert.setHeaderText("Hibás mezők");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	@FXML
	private void handleSave() {
		if (isInputValid()) {
			vehicle.setBrand(brandField.getText());
			vehicle.setModel(modelField.getText());
			vehicle.setVariant(variantField.getText());
			vehicle.setLicensePlateNumber(licensePlateNumberField.getText());
			/*vehicle.setDate(Year.parse(dateField.getText()));
			vehicle.setPurchasePrice(Long.parseLong(purchasePriceField.getText()));
			vehicle.setSalePrice(Long.parseLong(salePriceField.getText()));
			vehicle.setKm(Long.parseLong(kmField.getText()));
			vehicle.setFuel(fuelBox.getValue());
			vehicle.setCondition(conditionBox.getValue());
			vehicle.setDescription(descriptionArea.getText());*/

			saveClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleBack() {
		dialogStage.close();
	}

}
