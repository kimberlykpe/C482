package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is for the Modify Product Screen for the application. */
public class ModifyProduct implements Initializable {
    public Label modifyProductTitle;
    public Label modifyProductID;
    public Label modifyProductName;
    public Label modifyProductInv;
    public Label modifyProductPrice;
    public Label modifyProductMax;
    public Label modifyProductMin;
    public Button addModify;
    public Button removeAssociatedPartModify;
    public TableColumn<Part, Integer> partIDCol1;
    public TableColumn<Part, String> partPartNameCol1;
    public TableColumn<Part, Integer> partInventoryLevelCol1;
    public TableColumn<Part, Double> partPriceCostPerUnitCol1;
    public TextField productIDField;
    public Button saveAdd;
    public Button cancelAdd;
    public TextField addProductNameText;
    public TextField addProductInvText;
    public TextField addProductPriceText;
    public TextField addProductMaxText;
    public TextField addProductMinText;
    public TextField searchParts;
    public TableView<Part> partTable;
    public TableColumn<Part, Integer> partsID;
    public TableColumn<Part, String> partName;
    public TableColumn<Part, Integer> partsInventoryLevel;
    public TableColumn<Part, Double> partsPriceCostPerUnit;
    public TableView<Part> associatedPartTableView;

    private Product selectedProduct;

    private ObservableList<Part> assocParts = FXCollections.observableArrayList();
    private final Alerts alert = new Alerts();

    /** This initializes the Modify Product screen and loads the selected product to modify.
     Inputs the Part table and the Associated Parts table.
     @param url
     @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        selectedProduct = MainForm.getProductModify();
        assocParts = selectedProduct.getAllAssociatedParts();

        partTable.setItems(Inventory.getAllParts());
        partsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));


        partIDCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        partPartNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCostPerUnitCol1.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartTableView.setItems(assocParts);

        productIDField.setText(String.valueOf(selectedProduct.getId()));
        addProductNameText.setText(selectedProduct.getName());
        addProductInvText.setText(String.valueOf(selectedProduct.getStock()));
        addProductPriceText.setText(String.valueOf(selectedProduct.getPrice()));
        addProductMinText.setText(String.valueOf(selectedProduct.getMin()));
        addProductMaxText.setText(String.valueOf(selectedProduct.getMax()));

    }

    /** When clicked, the Remove Associated Part button will remove the associated part.
     If the user doesn't click an associated part to delete an alert will pop up to select an associated part to delete. */
    public void onRemoveAssociatedPart() {
        if (associatedPartTableView.getSelectionModel().getSelectedItem() == null) {
            alert.showAlertNullDeleteAssociatedPart();
        } else {
            if (alert.showconfirmationAlert()) {
                associatedPartTableView.getItems().removeAll(associatedPartTableView.getSelectionModel().getSelectedItem());
            }
        }
    }

    /** Search for a product. User will enter a letter, number, or phrase.
     If there are no matches, an alert will pop up saying no matches.
     If there is a match, the application will highlight the match.
     If the search bar is empty, the table will be repopulated with all products. */
    public void onProductSearch() {
        try {
            Part lookupProduct1 = Inventory.lookupPart(Integer.parseInt(searchParts.getText()));
            partTable.getSelectionModel().select(lookupProduct1);
            if (lookupProduct1 == null) {
                alert.showAlertForSearch();
            }
        } catch (Exception e) {
            String partName = searchParts.getText();
            ObservableList<Part> Parts = Inventory.lookupPart(partName);
            partTable.setItems(Parts);
            searchParts.setText("");
            if (!(Inventory.lookupProduct1)) {
                alert.showAlertForSearch();
            }
        }
    }

    /** The add button adds a part to become an associated part for a product.
     If no part is clicked when user clicks add, an alert will pop up to select a part. */
    public void onaddAdd() {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();

        if(partTable.getSelectionModel().getSelectedItem() != null) {
            assocParts.add(selectedPart);
            associatedPartTableView.setItems(assocParts);
        }
        else {
            alert.showAlertNullAssociatedPart();
        }
    }

    /** This Save radio button saves the information inputted by the user. Once saved, the screen loads back into the Main Form.
     Create alerts for when inventory is not in between minimum and maximum, and when minimum is higher than maximum.
     @param event Event for Save Button Modify Product. */
    public void onSaveAdd(ActionEvent event) {
        try {
            int index = Inventory.getAllProducts().indexOf(selectedProduct);
            String name = addProductNameText.getText();
            int id = Integer.parseInt(productIDField.getText());
            int stock = Integer.parseInt(addProductInvText.getText());
            double price = Double.parseDouble(addProductPriceText.getText());
            int max = Integer.parseInt(addProductMaxText.getText());
            int min = Integer.parseInt(addProductMinText.getText());

            if (max < min) {
                alert.showAlertMinValue();
                return;
            }

            if (stock > max || stock < min) {
                alert.showAlertInvValue();
                return;
            }

            if (stock < max && stock > min) {
                selectedProduct.setId(id);
                selectedProduct.setName(name);
                selectedProduct.setPrice(price);
                selectedProduct.setStock(stock);
                selectedProduct.setPrice(price);
                selectedProduct.setMin(min);
                selectedProduct.setMax(max);
                Inventory.updateProduct(index, selectedProduct);
                Object scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
                Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene((Parent) scene));
                stage.show();
            }
            else {
                alert.showAlertMsg();
            }
        } catch(Exception e) {
            alert.showAlertInputValid();
        }
    }

    /** The cancel button allows you to go back to the main form.
     @param event Cancel ModifyProduct button. */
    public void onCancelAdd(ActionEvent event) throws IOException {
        Object scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
        root.setStyle("-fx-font-family: 'Times New Roman';");
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }



}
