package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is for the AddPart Controller Screen.*/
public class AddPart implements Initializable {
    public Label addPartTitle;
    public Label idAddPart;
    public Label nameAddPart;
    public Label invAddPart;
    public Label pricecostAddPart;
    public Label maxAddPart;
    public Label minAddPart;
    public RadioButton inHouseAddPart;
    public ToggleGroup tgroup;
    public RadioButton outsourcedAddPart;
    public Button saveAddPart;
    public Button cancelAddPart;
    public Label machineIDAddPart;
    public TextField idAddPartField;
    public TextField nameAddPartField;
    public TextField invAddPartField;
    public TextField pricecostAddPartField;
    public TextField maxAddPartField;
    public TextField machineIDAddPartField;
    public TextField minAddPartField;




    int id;
    private final Alerts alert = new Alerts();
/** This initializes the Add Part screen and loads a random number for the ID Field.
 @param url
 @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int random = (int)(Math.random() * 99 + 1);
        id = random;
        idAddPartField.setText(String.valueOf(random));

        
    }
/** When the user clicks the In-House radio button it will display Machine ID.*/
    public void onInHouse() {
        if (inHouseAddPart.isSelected()) {
            machineIDAddPart.setText("Machine ID");
            outsourcedAddPart.setSelected(false);
        }
    }
    /** When the user clicks the Outsourced radio button it will display the Company Name.*/
    public void onOutsourced() {
        if (outsourcedAddPart.isSelected()) {
            machineIDAddPart.setText("Company Name");
            inHouseAddPart.setSelected(false);
        }
    }
    /** RUNTIME ERROR: had an issue loading the new part back to the main screen, but solved it by inputting addPart, "Inventory.partId = id;, and loading it back to the Main Form.
     This Save radio button saves the information inputted by the user. Once saved, the screen loads back into the Main Form.
     Create alerts for when inventory is not in between minimum and maximum, and when minimum is higher than maximum.
     @param event Event for Save Button Add Part.*/
    public void onSaveAddPart(ActionEvent event) {
        try {
            int id = Integer.parseInt(idAddPartField.getText());
            String name = nameAddPartField.getText();
            int stock = Integer.parseInt(invAddPartField.getText());
            double price = Double.parseDouble(pricecostAddPartField.getText());
            int max = Integer.parseInt(maxAddPartField.getText());
            int min = Integer.parseInt(minAddPartField.getText());

            if (max < min) {
                alert.showAlertMinValue();
                return;
            }

            if (stock > max || stock < min) {
                alert.showAlertInvValue();
                return;
            }

            if ((inHouseAddPart.isSelected())) {
                int machineID = Integer.parseInt(machineIDAddPartField.getText());
                Inventory.addPart(new InHouse(id,name,price,stock,min,max,machineID));

                Inventory.partId = id;
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
                stage.setScene(new Scene((Parent) scene));
                stage.show();

            }  else if ((outsourcedAddPart.isSelected())) {
                String companyName = machineIDAddPartField.getText();
                Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));

                Inventory.partId = id;
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
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

    /** The cancel button allows you to go back to the main form. I added an alert that displays a message to confirm if the user wants to go back.
     @param event Cancel AddPart button. */

    public void onCancelAddPart(ActionEvent event) throws IOException {
        alert.showAlertCancelMsg();

        Object scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
        root.setStyle("-fx-font-family: 'Times New Roman';");
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }
}
