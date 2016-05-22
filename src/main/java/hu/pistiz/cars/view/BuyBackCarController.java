package hu.pistiz.cars.view;

import hu.pistiz.cars.CarDealershipHandler;
import hu.pistiz.cars.model.Car;
import hu.pistiz.cars.util.LicensePlateNumberUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Optional;

public class BuyBackCarController {

	@FXML
	private TextField licensePlateNumberField;
	@FXML
	private TextField priceField;

	private Stage dialogStage;

	private CarDealershipHandler handler;

	public BuyBackCarController() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		this.dialogStage.setResizable(false);
	}

	public void setHandler(CarDealershipHandler handler) {
		this.handler = handler;
	}

	@FXML
	private void initialize() {
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (licensePlateNumberField.getText() == null || licensePlateNumberField.getText().length() == 0 || licensePlateNumberField.getText().trim().length() == 0) {
			errorMessage += "Üres rendszám-mező!\n";
		} else {
			if (! (LicensePlateNumberUtil.isValid(licensePlateNumberField.getText())))
				errorMessage += "Nem megfelelő rendszám! (3 betű, kötőjel, 3 szám)\n";
		}

		if (priceField.getText() == null || priceField.getText().length() == 0 || priceField.getText().trim().length() == 0) {
			errorMessage += "Üres visszavásárlási ár-mező!\n";
		} else {
			try {
				Long.parseLong(priceField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Egész számot adj meg visszavásárlás árnak!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
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
			String errorMessage = "";
			Car buyBackCar = handler.getCarService().createCar();
			Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
			confirmation.initOwner(dialogStage);
			confirmation.setTitle("Megerősítés szükséges");
			confirmation.setHeaderText("Biztos?");
			Optional<ButtonType> result = confirmation.showAndWait();
			if (result.get() == ButtonType.OK) {
				if (handler.getCarService().findIfSoldCarPresents(licensePlateNumberField.getText() + ".xml", handler.getCarDAO().getSoldCarsList()))
					buyBackCar = handler.getCarService().buyBack(handler, licensePlateNumberField.getText(), Long.parseLong(priceField.getText()));
				else
					errorMessage += "Nem található a gépjármű!";
			}

			if (errorMessage.length() > 0) {
				Alert error = new Alert(Alert.AlertType.ERROR);
				error.initOwner(dialogStage);
				error.initModality(Modality.WINDOW_MODAL);
				error.setTitle("Hiba");
				error.setHeaderText("Hibás bevitel");
				error.setContentText(errorMessage);

				error.showAndWait();
			} else {
				handler.getCarData().add(buyBackCar);
				handler.getDealershipService().decreaseRemainder(handler.getDealership(), buyBackCar.getPurchasePrice());
				handler.getDealershipDAO().updateDealership(handler.getDealership());

				dialogStage.close();
			}
		}
	}

	@FXML
	private void handleBack() {
		dialogStage.close();
	}

}
