package hu.pistiz.cars.view;

import hu.pistiz.cars.CarDealershipHandler;
import hu.pistiz.cars.model.Car;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

public class DealershipOverviewController {

	/*@FXML
	private AnchorPane pane;*/
	@FXML
	private Label companyNameLabel;
	@FXML
	private TableView<Car> carTable;
	@FXML
	private TableColumn<Car, String> brandColumn;
	@FXML
	private TableColumn<Car, String> modelColumn;
	@FXML
	private TableColumn<Car, String> variantColumn;
	@FXML
	private TableColumn<Car, String> licensePlateNumberColumn;
	@FXML
	private TextField incomeField;
	@FXML
	private TextField profitField;
	@FXML
	private TextField soldCarsField;
	@FXML
	private TextField remainderField;

	private CarDealershipHandler handler;
	//private XMLCarDAO carDAO = new XMLCarDAO();

	public DealershipOverviewController() {
	}

	@FXML
	private void initialize() {
		brandColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
		modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
		variantColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVariant()));
		licensePlateNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLicensePlateNumber()));
	}

	@FXML
	private void handleNewCar() {
		handler.showNewVehicleDialog();
		carTable.setItems(handler.getCarData());
		setRemainderFieldText(handler.getDealership().getRemainder());
	}

	@FXML
	private void handleViewCar() {
		Car car = carTable.getSelectionModel().getSelectedItem();
		if (car != null)
			handler.showViewVehicleDialog(car);
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(handler.getPrimaryStage());
			alert.setTitle("Üres kijelölés");
			alert.setHeaderText("Nem jelöltél ki járművet");
			alert.setContentText("Jelölj ki egy járművet a megtekintéshez!");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleEditCar() {
		Car car = carTable.getSelectionModel().getSelectedItem();
		if (car != null) {
			handler.showEditVehicleDialog(car);
			carTable.setItems(handler.getCarData());
			setRemainderFieldText(handler.getDealership().getRemainder());
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(handler.getPrimaryStage());
			alert.setTitle("Üres kijelölés");
			alert.setHeaderText("Nem jelöltél ki járművet");
			alert.setContentText("Jelölj ki egy járművet a szerkesztéshez!");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleDeleteCar() {
		int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(handler.getPrimaryStage());
			alert.setTitle("Törlés megerősítése");
			alert.setHeaderText("Biztosan törlöd a kiválaszott járművet?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				handler.getCarDAO().removeCarForSale(carTable.getSelectionModel().getSelectedItem().getLicensePlateNumber());
				//handler.getDealership().getCarsForSale().remove(carTable.getSelectionModel().getSelectedItem());
				//carTable.getItems().remove(selectedIndex);
				handler.setCarData(handler.carDAO.getCarsForSale());
				carTable.setItems(handler.getCarData());
			} else {
				alert.close();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(handler.getPrimaryStage());
			alert.setTitle("Üres kijelölés");
			alert.setHeaderText("Nem jelöltél ki járművet");
			alert.setContentText("Jelölj ki egy járművet a törléshez!");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleSaleCar() {
		Car car = carTable.getSelectionModel().getSelectedItem();
		int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
		if (car != null) {
			handler.showSaleCarDialog(car);
			carTable.setItems(handler.getCarData());
			/*try {
				handler.setDealership(handler.dealershipDAO.getDealership());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}*/
			setIncomeFieldText(handler.getDealership().getIncome());
			setProfitFieldText(handler.getDealership().getProfit());
			setSoldCarsFieldText(handler.getDealership().getSoldCars());
			setRemainderFieldText(handler.getDealership().getRemainder());
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(handler.getPrimaryStage());
			alert.setTitle("Üres kijelölés");
			alert.setHeaderText("Nem jelöltél ki járművet");
			alert.setContentText("Jelölj ki egy járművet az eladáshoz!");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleBuyBack() {
		handler.showBuyBackCarDialog();
		carTable.setItems(handler.getCarData());
		setRemainderFieldText(handler.getDealership().getRemainder());
	}

	public void setCompanyNameLabelText(String text) {
		companyNameLabel.setText(text);
	}

	public void setIncomeFieldText(long income) {
		incomeField.setText(Long.toString(income));
	}

	public void setProfitFieldText(long profit) {
		profitField.setText(Long.toString(profit));
	}

	public void setSoldCarsFieldText(long soldCars) {
		soldCarsField.setText(Long.toString(soldCars));
	}

	public void setRemainderFieldText(long remainder) {
		remainderField.setText(Long.toString(remainder));
	}

	public void setHandler(CarDealershipHandler handler) {
		this.handler = handler;

		// Add observable list data to the table
		carTable.setItems(handler.getCarData());
	}

}