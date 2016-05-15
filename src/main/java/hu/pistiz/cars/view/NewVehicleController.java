package hu.pistiz.cars.view;

import hu.pistiz.cars.model.*;
import hu.pistiz.cars.util.LicensePlateNumberUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.time.Year;
import java.time.format.DateTimeParseException;

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
	private ComboBox<Fuel> fuelBox;
	@FXML
	private ComboBox<Condition> conditionBox;
	@FXML
	private TextArea descriptionArea;

	private Stage dialogStage;
	private Car car;
	private CarDAO carDAO = new XMLCarDAO();
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

		if (licensePlateNumberField.getText() == null || licensePlateNumberField.getText().length() == 0) {
			errorMessage += "Üres rendszám-mező!\n";
		} else {
			if (!(LicensePlateNumberUtil.isValid(licensePlateNumberField.getText())))
				errorMessage += "Nem megfelelő rendszám! (3 betű, kötőjel, 3 szám)\n";
		}

		if (dateField.getText() == null || dateField.getText().length() == 0) {
			errorMessage += "Üres évjárat-mező!\n";
		} else {
			try {
				Year.parse(dateField.getText());
			} catch (DateTimeParseException e) {
				errorMessage += "Nem megfelelő évjárat!\n";
			}
		}

		if (purchasePriceField.getText() == null || purchasePriceField.getText().length() == 0) {
			errorMessage += "Üres vételár-mező!\n";
		} else {
			try {
				Long.parseLong(purchasePriceField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Egész számot adj meg vételárnak!\n";
			}
		}

		if (salePriceField.getText() == null || salePriceField.getText().length() == 0) {
			errorMessage += "Üres eladási ár-mező!\n";
		} else {
			try {
				Long.parseLong(salePriceField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Egész számot adj meg eladási árnak!\n";
			}
		}

		if (kmField.getText() == null || kmField.getText().length() == 0) {
			errorMessage += "Üres kilométeróra-állás-mező!\n";
		} else {
			try {
				Long.parseLong(kmField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Egész számot adj meg kilométeróra-állásnak!\n";
			}
		}

		if (fuelBox.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "Nem választottál üzemanyag-típust!\n";
		}

		if (conditionBox.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "Nem választottál állapot-típust!\n";
		}

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
			car = new Car();
			car.setBrand(brandField.getText());
			car.setModel(modelField.getText());
			car.setVariant(variantField.getText());
			car.setLicensePlateNumber(licensePlateNumberField.getText());
			car.setDate(Year.parse(dateField.getText()));
			car.setPurchasePrice(Long.parseLong(purchasePriceField.getText()));
			car.setSalePrice(Long.parseLong(salePriceField.getText()));
			car.setKm(Long.parseLong(kmField.getText()));
			car.setFuel(fuelBox.getValue());
			car.setCondition(conditionBox.getValue());
			car.setDescription(descriptionArea.getText());

			saveClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleBack() {
		dialogStage.close();
	}

}
