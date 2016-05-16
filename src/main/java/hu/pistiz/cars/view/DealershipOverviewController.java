package hu.pistiz.cars.view;

import hu.pistiz.cars.CarDealershipHandler;
import hu.pistiz.cars.model.Car;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

public class DealershipOverviewController {

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
	private void handleNewVehicle() {
		handler.showNewVehicleDialog();
	}

	@FXML
	private void handleViewVehicle() {
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
	private void handleEditVehicle() {
		Car car = carTable.getSelectionModel().getSelectedItem();
		if (car != null) {
			handler.showEditVehicleDialog(car);
			carTable.setItems(handler.getCarData());
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
	private void handleDeleteVehicle() {
		int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(handler.getPrimaryStage());
			alert.setTitle("Törlés megerősítése");
			alert.setHeaderText("Biztosan törlöd a kiválaszott járművet?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				handler.getCarDAO().removeCarByLPN(carTable.getSelectionModel().getSelectedItem().getLicensePlateNumber());
				carTable.getItems().remove(selectedIndex);
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

	public void setCompanyNameLabelText(String text) {
		companyNameLabel.setText(text);
	}

	public void setHandler(CarDealershipHandler handler) {
		this.handler = handler;

		// Add observable list data to the table
		carTable.setItems(handler.getCarData());
	}

}
