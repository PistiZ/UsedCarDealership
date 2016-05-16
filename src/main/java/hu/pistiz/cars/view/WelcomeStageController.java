package hu.pistiz.cars.view;

import hu.pistiz.cars.CarDealershipHandler;
import hu.pistiz.cars.model.Dealership;
import hu.pistiz.cars.model.DealershipDAO;
import hu.pistiz.cars.util.LicensePlateNumberUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeParseException;

public class WelcomeStageController {

	@FXML
	private TextField dealershipNameField;
	@FXML
	private TextField dealershipCompanyField;
	@FXML
	private TextField dealershipAddressField;
	@FXML
	private TextField ownerLastNameField;
	@FXML
	private TextField ownerFirstNameField;
	@FXML
	private DatePicker birthdayPicker;

	private Stage stage;

	private CarDealershipHandler handler;

	public WelcomeStageController() {
	}

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleSave() {
		Dealership dealership = handler.getDealership();
		if (isInputValid()) {
			dealership.setName(dealershipNameField.getText());
			dealership.setCompanyName(dealershipCompanyField.getText());
			dealership.setAddress(dealershipAddressField.getText());
			dealership.getOwner().setLastName(ownerLastNameField.getText());
			dealership.getOwner().setFirstName(ownerFirstNameField.getText());
			dealership.getOwner().setDateOfBirth(birthdayPicker.getValue());
			handler.setDealership(dealership);
			handler.getDealershipDAO().addDealership(dealership);
			handler.getPersonDAO().addPerson(dealership.getOwner());

			handler.showDealershipOverview();
		}
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (dealershipNameField.getText() == null || dealershipNameField.getText().length() == 0) {
			errorMessage += "Üres kereskedésnév-mező!\n";
		}

		if (dealershipCompanyField.getText() == null || dealershipCompanyField.getText().length() == 0) {
			errorMessage += "Üres cégnév-mező!\n";
		}

		if (dealershipAddressField.getText() == null || dealershipAddressField.getText().length() == 0) {
			errorMessage += "Üres székhely-mező!\n";
		}

		if (ownerLastNameField.getText() == null || ownerLastNameField.getText().length() == 0) {
			errorMessage += "Üres vezetéknév-mező!\n";
		}

		if (ownerFirstNameField.getText() == null || ownerFirstNameField.getText().length() == 0) {
			errorMessage += "Üres székhely-mező!\n";
		}

		if (birthdayPicker.getValue() == null) {
			errorMessage += "Üres születési dátum!\n";
		} else {
			try {
				LocalDate.parse(birthdayPicker.getValue().toString());
			} catch (DateTimeParseException e) {
				errorMessage += "Nem megfelelő születési dátum!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(handler.getPrimaryStage());
			alert.setTitle("Hibás mezők");
			alert.setHeaderText("Hibás mezők");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	public void setHandler(CarDealershipHandler handler) {
		this.handler = handler;
	}

}
