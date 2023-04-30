package Model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/** This class is for alerts. */
public class Alerts {

    public Alert alert = new Alert(Alert.AlertType.ERROR);

    /** This is for alerts when a product is not selected. */
    public void showAlertForNullProductDelete() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setTitle("Error");
        alert.setContentText("Pick a product to delete");
        alert.showAndWait();
    }

/** This is for alerts when a part is not selected. */
    public void showAlertForNullPartDelete() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setTitle("Error");
        alert.setContentText("Pick a product to delete");
        alert.showAndWait();
    }

/** This is for alerts when a part to modify is not selected. */
    public void showAlertModifyPartMsg() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setTitle("Error");
        alert.setContentText("Select a part to modify");
        alert.showAndWait();
    }

/** This is for alerts when a product to modify is not selected. */
    public void showAlertModifyProductMsg() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setTitle("Error");
        alert.setContentText("Select a product to modify");
        alert.showAndWait();
    }

/** This is for alerts when there are no results in a search. */
        public void showAlertForSearch() {
            alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
            alert.setTitle("Error");
            alert.setContentText("No results");
            alert.showAndWait();
        }

/** This is for alerts when the inventory is not in between the min & max values and the min is greater than the max. */
    public void showAlertMsg() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
            alert.setTitle("Error");
            alert.setContentText("Inventory much be between min & max values. Max should be greater than min.");
            alert.showAndWait();

        }

/** This is for alerts when a part to add to associated parts table is not selected. */
    public void showAlertNullAssociatedPart() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setTitle("Error");
        alert.setContentText("Select a part to add to associated part table.");
        alert.showAndWait();
    }

    /** This is for alerts for when you want to select an associated part to delete. */
    public void showAlertNullDeleteAssociatedPart() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setTitle("Error");
        alert.setContentText("Select an associated part to delete.");
        alert.showAndWait();
    }


    /** This is for alerts for when you want to cancel. */
    public void showAlertCancelMsg() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setTitle("Error");
        alert.setContentText("Are you sure you want to cancel?");
        alert.showAndWait();
    }

    /** This is for alerts for when max needs to be more than the min. */
    public void showAlertMinValue() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setTitle("Error");
        alert.setContentText("Max needs to be more than min");
        alert.showAndWait();
    }

    /** This is for alerts for when the inventory is not in between min and max. */
    public void showAlertInvValue() {
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setTitle("Error");
        alert.setContentText("The inventory should be in between minimum and maximum");
        alert.showAndWait();
    }

    /** This is for alerts for when you are missing or have incorrect fields. */
        public void showAlertInputValid() {
            alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
            alert.setTitle("Error");
            alert.setContentText("Name needs to be a string" + "\n"
                    + "Inventory needs to be an integer value"
                    + "\n" + "Price needs to be a decimal value"
                    + "\n" + "Min needs to be an integer value"
                    + "\n" + "Max needs to be an integer value"
                    + "\n" + "Machine ID needs to be an integer value"
                    + "\n" + "Company name needs to be a string");
            alert.showAndWait();
        }

    /** This is for alerts for when you want to confirm to delete an item. */
        public boolean showconfirmationAlert() {
            alert.setTitle("Alert");
            alert.setContentText("Erase?");
            alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
            Optional<ButtonType> result = alert.showAndWait();
            return result.get() == ButtonType.OK;

        }

/** This is for alerts when you need to delete the part before deleting associated part. */
    public void showAlertForDeleteProduct() {
        alert.setTitle("Error");
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setContentText("Delete the part before deleting associated part");
        alert.showAndWait();
    }

    /** This is for alerts when you want to exit. */
    public void showAlertForExit() {
        alert.setTitle("Error");
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setContentText("Are you sure you want to exit the application?");
        alert.showAndWait();
    }

    /** This is for alerts when you want to exit. */
    public void showAlertForDeleteProducts() {
        alert.setTitle("Error");
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-font-family: 'Times New Roman';");
        alert.setContentText("Are you sure you want to delete the product?");
        alert.showAndWait();
    }
    }
