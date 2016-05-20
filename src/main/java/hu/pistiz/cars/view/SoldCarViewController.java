package hu.pistiz.cars.view;

import hu.pistiz.cars.CarDealershipHandler;
import hu.pistiz.cars.model.Car;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Optional;

public class SoldCarViewController {

	@FXML
	private TextField purchasePriceField;
	@FXML
	private TextField salePriceField;
	@FXML
	private TextField profitField;

	private Stage dialogStage;

	private CarDealershipHandler handler;
	private Car car;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		this.dialogStage.setResizable(false);
	}

	public void setHandler(CarDealershipHandler handler) {
		this.handler = handler;
	}

	@FXML
	private void initialize() {
		salePriceField.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					try {
						long salePrice = Long.parseLong(salePriceField.getText());
						long purchasePrice = Long.parseLong(purchasePriceField.getText());
						if (salePrice > purchasePrice) {
							profitField.setText(Long.toString(salePrice - purchasePrice));
							profitField.setStyle("-fx-text-inner-color: black;");
						}
						else {
							profitField.setText(Long.toString(salePrice - purchasePrice));
							profitField.setStyle("-fx-text-inner-color: red;");
						}
					} catch (NumberFormatException e) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.initOwner(dialogStage);
						alert.setTitle("Nem megfelelő ár");
						alert.setHeaderText("A megadott ár nem megfelelő");

						alert.showAndWait();
					}
				}
		);
	}

	@FXML
	public void setAndPrintCar(Car car) {
		this.car = car;

		if (car != null) {
			purchasePriceField.setText(Long.toString(car.getPurchasePrice()));
			profitField.setText("0");
		}
	}

	@FXML
	private void handleOk() {
		try {
			long salePrice = Long.parseLong(salePriceField.getText());
			long purchasePrice = Long.parseLong(purchasePriceField.getText());
			long profit = salePrice - purchasePrice;
			if (salePrice < purchasePrice) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.initOwner(dialogStage);
				alert.setTitle("Veszteség keletkezett!");
				alert.setHeaderText("Helyesek az árak?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					car.setSalePrice(salePrice);
					handler.getCarDAO().updateCar(car);
					handler.getCarDAO().addSoldCar(car);
					handler.getCarDAO().removeCarByLPN(car.getLicensePlateNumber());
					handler.setCarData(handler.carDAO.getCarsForSale());
					handler.getService().incrementIncome(handler.getDealership(), salePrice);
					handler.getService().incrementProfit(handler.getDealership(), profit);
					handler.getService().incrementRemainder(handler.getDealership(), salePrice);
					handler.getService().saleCar(handler.getDealership());
					handler.getDealershipDAO().updateDealership(handler.getDealership());
					handler.setDealership(handler.dealershipDAO.getDealership());

					dialogStage.close();
				} else
					alert.close();
			} else if (salePrice == purchasePrice) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.initOwner(dialogStage);
				alert.setTitle("A két ár megegyezik!");
				alert.setHeaderText("A két ár megegyezik!");
				alert.setContentText("Helyesek az árak?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					car.setSalePrice(salePrice);
					handler.getCarDAO().updateCar(car);
					handler.getCarDAO().addSoldCar(car);
					handler.getCarDAO().removeCarByLPN(car.getLicensePlateNumber());
					handler.setCarData(handler.carDAO.getCarsForSale());
					handler.getService().incrementIncome(handler.getDealership(), salePrice);
					handler.getService().incrementProfit(handler.getDealership(), profit);
					handler.getService().incrementRemainder(handler.getDealership(), salePrice);
					handler.getService().saleCar(handler.getDealership());
					handler.getDealershipDAO().updateDealership(handler.getDealership());
					handler.setDealership(handler.dealershipDAO.getDealership());

					dialogStage.close();
				} else
					alert.close();
			} else {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.initOwner(dialogStage);
				alert.setTitle("Megerősítés szükséges");
				alert.setHeaderText("Biztos?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					car.setSalePrice(salePrice);
					handler.getCarDAO().updateCar(car);
					handler.getCarDAO().addSoldCar(car);
					handler.getCarDAO().removeCarByLPN(car.getLicensePlateNumber());
					handler.setCarData(handler.carDAO.getCarsForSale());
					handler.getService().incrementIncome(handler.getDealership(), salePrice);
					handler.getService().incrementProfit(handler.getDealership(), profit);
					handler.getService().incrementRemainder(handler.getDealership(), salePrice);
					handler.getService().saleCar(handler.getDealership());
					handler.getDealershipDAO().updateDealership(handler.getDealership());
					handler.setDealership(handler.dealershipDAO.getDealership());

					dialogStage.close();
				} else
					alert.close();
			}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Hibás ár!");
			alert.setHeaderText("Hibás ár!");
			alert.showAndWait();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
