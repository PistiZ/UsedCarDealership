// CHECKSTYLE:OFF
package hu.pistiz.cars.view;

import hu.pistiz.cars.CarDealershipHandler;
import hu.pistiz.cars.model.Dealership;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Az üdvözlőképernyő kezelője.
 */
public class WelcomeStageController {

	@FXML
	private AnchorPane pane;
	@FXML
	private TextField dealershipNameField;
	@FXML
	private TextField dealershipCompanyField;
	@FXML
	private TextField dealershipAddressField;
	@FXML
	private TextField startingMoneyField;
	@FXML
	private TextField ownerLastNameField;
	@FXML
	private TextField ownerFirstNameField;
	@FXML
	private DatePicker birthdayPicker;

	private Pane rootPane;

	private CarDealershipHandler handler;

	public WelcomeStageController() {
	}

	@FXML
	private void initialize() {
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0);
		AnchorPane.setRightAnchor(pane, 0.0);
		/*pane.prefHeightProperty().bind(rootPane.heightProperty());
		pane.prefWidthProperty().bind(rootPane.widthProperty());*/
	}

	@FXML
	private void handleSave() {
		Dealership dealership = handler.getDealership();
		if (isInputValid()) {
			dealership.setName(dealershipNameField.getText());
			dealership.setCompanyName(dealershipCompanyField.getText());
			dealership.setAddress(dealershipAddressField.getText());
			handler.getDealershipService().incrementStartingMoney(dealership, Long.parseLong(startingMoneyField.getText()));
			handler.getDealershipService().incrementRemainder(dealership, Long.parseLong(startingMoneyField.getText()));
			//dealership.setStartingMoney(Long.parseLong(startingMoneyField.getText()));

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

		if (dealershipNameField.getText() == null || dealershipNameField.getText().length() == 0 || dealershipNameField.getText().trim().length() == 0) {
			errorMessage += "Üres kereskedésnév-mező!\n";
		}

		if (dealershipCompanyField.getText() == null || dealershipCompanyField.getText().length() == 0 || dealershipCompanyField.getText().trim().length() == 0) {
			errorMessage += "Üres cégnév-mező!\n";
		}

		if (dealershipAddressField.getText() == null || dealershipAddressField.getText().length() == 0 || dealershipAddressField.getText().trim().length() == 0) {
			errorMessage += "Üres székhely-mező!\n";
		}

		if (startingMoneyField.getText() == null || startingMoneyField.getText().length() == 0 || dealershipAddressField.getText().trim().length() == 0) {
			errorMessage += "Üres induló tőke mező!\n";
		} else {
			try {
				Long.parseLong(startingMoneyField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Nem megfelelő induló tőke!\n";
			}
		}

		if (ownerLastNameField.getText() == null || ownerLastNameField.getText().length() == 0 || ownerLastNameField.getText().trim().length() == 0) {
			errorMessage += "Üres vezetéknév-mező!\n";
		}

		if (ownerFirstNameField.getText() == null || ownerFirstNameField.getText().length() == 0 || ownerFirstNameField.getText().trim().length() == 0) {
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
		this.rootPane = handler.getRootPane();
	}

}
