package hu.pistiz.cars.view;

import hu.pistiz.cars.model.Car;
import hu.pistiz.cars.model.service.CarService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
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
	private Label kmLabel;
	@FXML
	private Label fuelLabel;
	@FXML
	private Label conditionLabel;
	@FXML
	private TextArea descriptionArea;
	@FXML
	private ImageView image;

	private Stage dialogStage;
	private CarService service;

	public ViewVehicleController() {
		service = new CarService();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		this.dialogStage.setResizable(false);
	}

	@FXML
	private void initialize() {
		/*InputStream agera = this.getClass().getClassLoader().getResourceAsStream("agera.jpg");
		Image pic = new Image(agera);
		image.setImage(pic);*/
	}

	public void setAndPrintVehicle(Car car) {

		if (car != null) {
			brandLabel.setText(car.getBrand());
			modelLabel.setText(car.getModel());
			variantLabel.setText(car.getVariant());
			licensePlateNumberLabel.setText(car.getLicensePlateNumber());
			dateLabel.setText(car.getDate().toString());
			purchasePriceLabel.setText(service.priceToString(car.getPurchasePrice()));
			kmLabel.setText(service.kmToString(car.getKm()));
			fuelLabel.setText(car.getFuel().toString());
			conditionLabel.setText(car.getCondition().toString());
			descriptionArea.setText(car.getDescription());
		}
		else {
			brandLabel.setText("");
			modelLabel.setText("");
			variantLabel.setText("");
			dateLabel.setText("");
			purchasePriceLabel.setText("");
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
