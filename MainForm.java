package Controller;

import Model.*;
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
import java.util.Optional;
import java.util.ResourceBundle;

/** This class creates the Main Screen for the application.*/
public class MainForm implements Initializable {
    public TableColumn<Part, String> partName;
    public TableColumn<Part, Integer> partsInventoryLevel;
    public TableColumn<Part, Double> partsPriceCostPerUnit;
    public TableColumn<Product, Integer> productID;
    public TableColumn<Product, String> productName;
    public TableColumn<Product, Integer> productInventoryLevel;
    public TableColumn<Product, Double> productPriceCostPerUnit;
    public Label partTitle;
    public Label productTitle;
    public Label inventoryManagementSystemTitle;
    public Button partAddButton;
    public Button partModifyButton;
    public Button partDeleteButton;
    public Button productAddButton;
    public Button productModifyButton;
    public Button productDeleteButton;
    public TableView<Product> productTable;
    public TableView<Part> partTable;
    public Button exitSystem;
    public TextField searchParts;
    public TextField searchProducts;
    public Alerts alert = new Alerts();
    private static Part partModify;
    public TableColumn<Part, Integer> partsID;

    /** @return partModify */
    public static Part getPartModify() {
        return partModify;
    }

    private static Product productModify;

    /** @return productModify */
    public static Product getProductModify () {return productModify;}

    /** This initializes the Main Form and loads the part and product table information.
     @param url
     @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partTable.setItems(Inventory.getAllParts());
        partsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** When clicked, the Add button changes to the Add Part screen.
     @param event Add button for Add Part.
     */
    public void onPartAdd(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View/AddPart.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddPart.fxml"));
        root.setStyle("-fx-font-family: 'Times New Roman';");
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /** When clicked, the Modify button changes from the Main screen to the Modify Part screen.
     @param event Modify button for Modify Part.
     */
    public void onPartModify(ActionEvent event) throws IOException {
        partModify = partTable.getSelectionModel().getSelectedItem();

        if (partTable.getSelectionModel().getSelectedItem() == null) {
            alert.showAlertModifyPartMsg();

        } else {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    /** When clicked, the Delete button deletes the part.
     If no part is clicked, then an alert pops up to select a part.
     if a part is clicked, an alert pops up asking to confirm to delete a part. */

    public void onPartDelete() {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            alert.showAlertForNullPartDelete();

        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("Would you like to delete the selected part?");
            DialogPane dp = alert.getDialogPane();
            dp.setStyle("-fx-font-family:Times New Roman");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
}


    /** Search for a part. User will enter a letter, number, or phrase.
     If there are no matches, an alert will pop up saying no matches.
     If there is a match, the application will highlight the match.
     If the search bar is empty, the table will be repopulated with all parts. */
    public void onPartSearch() {
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


    /** When clicked, the Add button changes to the Add Product screen.
     @param event Add button for Add Product. */
    public void onProductAdd(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        root.setStyle("-fx-font-family: 'Times New Roman';");
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /** When clicked, the Modify button changes from the Main screen to the Modify Product screen.
     @param event Modify button for Modify Product. */
    public void onProductModify(ActionEvent event) throws IOException {
        productModify = productTable.getSelectionModel().getSelectedItem();

        if (productModify == null) {
            alert.showAlertModifyProductMsg();
        } else {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/ModifyProduct.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    /** When clicked, the Delete button deletes the product.
     If no product is clicked, then an alert pops up to select a product.
     If a product is clicked, an alert pops up asking to confirm to delete the product.
     If the product has an associated part, an alert will pop up to delete the associated part before you can delete the product. */
    public void onProductDelete() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            alert.showAlertForNullProductDelete();

        } else {
                ObservableList<Part> assocParts = selectedProduct.getAllAssociatedParts();

                if (assocParts.size() >= 1) {
                    alert.showAlertForDeleteProduct();
                } else {
                    alert.showAlertForDeleteProducts();
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }

    /** Search for a product. User will enter a letter, number, or phrase.
     If there are no matches, an alert will pop up saying no matches.
     If there is a match, the application will highlight the match.
     If the search bar is empty, the table will be repopulated with all products. */
    public void onProductSearch() {
        try {
            Product lookupProduct2 = Inventory.lookupProduct(Integer.parseInt(searchProducts.getText()));
            productTable.getSelectionModel().select(lookupProduct2);
            if (lookupProduct2 == null) {
                alert.showAlertForSearch();
            }
        } catch (Exception e) {
            String productName = searchProducts.getText();
            ObservableList<Product> Product = Inventory.lookupProduct(productName);
            productTable.setItems(Product);
            searchProducts.setText("");
            if (!(Inventory.lookupProduct2)) {
                alert.showAlertForSearch();
            }
        }
    }


    /** Exit button to exit system. */
    public void onexitSystem() {
        alert.showAlertForExit();
        System.exit(0);
    }
}

