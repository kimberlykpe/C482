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

/** This class is for the ModifyPart Controller Screen.*/
public class ModifyPart implements Initializable {
    public Label modifyPartTitle;
    public ToggleGroup tcgroup;
    public TextField idAddPartField;
    public TextField nameAddPartField;
    public TextField invAddPartField;
    public TextField pricecostAddPartField;
    public TextField maxAddPartField;
    public TextField machineIDAddPartField;
    public TextField minAddPartField;
    public Button saveAddPart;
    public Button cancelAddPart;
    public RadioButton inHouseAddPart;
    public RadioButton outsourcedAddPart;
    public Label machineIDAddPart;
    public Label minAddPart;
    public Label maxAddPart;
    public Label pricecostAddPart;
    public Label invAddPart;
    public Label nameAddPart;
    public Label idAddPart;

    private final Alerts alert = new Alerts();
    private Part selectedPart;

    /** RUNTIME ERROR: I had a hard time loading the selected modified part to the modify part screen.
     I solved the issue by creating selectedPart and getting the value of each selected part. This initializes the Modify Part screen and loads a random number for the ID Field.
     @param url
     @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedPart = MainForm.getPartModify();

        if (selectedPart instanceof InHouse) {
            inHouseAddPart.setSelected(true);
            machineIDAddPart.setText("Machine ID");
            machineIDAddPartField.setText(String.valueOf(((InHouse) selectedPart).getMachineID()));
        } else {
            outsourcedAddPart.setSelected(true);
            machineIDAddPart.setText("Company Name");
            machineIDAddPartField.setText(((Outsourced) selectedPart).getCompanyName());
        }

        idAddPartField.setText(String.valueOf(selectedPart.getId()));
        nameAddPartField.setText(selectedPart.getName());
        invAddPartField.setText(String.valueOf(selectedPart.getStock()));
        pricecostAddPartField.setText(String.valueOf(selectedPart.getPrice()));
        minAddPartField.setText(String.valueOf(selectedPart.getMin()));
        maxAddPartField.setText(String.valueOf(selectedPart.getMax()));



    }

    /** This Save radio button saves the information inputted by the user. Once saved, the screen loads back into the Main Form.
     Create alerts for when inventory is not in between minimum and maximum, and when minimum is higher than maximum.
     @param event Save button for Modify Part.*/
    public void onSaveAddPart(ActionEvent event) {
        try {
            int index = Inventory.getAllParts().indexOf(selectedPart);
            String name = nameAddPartField.getText();
            int id = selectedPart.getId();
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
                InHouse inHousePart = new InHouse(id, name, price, stock, min, max, machineID);
                Inventory.updatePart(index, inHousePart);

            } else if (outsourcedAddPart.isSelected()) {
                String companyName = machineIDAddPartField.getText();
                Outsourced outsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.updatePart(index, outsourcedPart);
            }
            else {
                alert.showAlertMsg();
            }
            Inventory.partId = id;
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
            root.setStyle("-fx-font-family: 'Times New Roman';");
            stage.setScene(new Scene((Parent) scene));
            stage.show();

            } catch(Exception e) {
                alert.showAlertInputValid();
            }
        }

    /** The cancel button allows you to go back to the main form. I added an alert that displays a message to confirm if the user wants to go back.
     @param event Cancel ModifyPart button. */
    public void onCancelAddPart(ActionEvent event) throws IOException {
        alert.showAlertCancelMsg();

        Object scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
        root.setStyle("-fx-font-family: 'Times New Roman';");
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /** When the user clicks the Outsourced radio button it will display the Company Name. Generates font to be Times New Roman. */
    public void onOutsourced() throws IOException {
        if (outsourcedAddPart.isSelected()) {
            inHouseAddPart.setSelected(false);
            machineIDAddPart.setText("Company Name");
            Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
            root.setStyle("-fx-font-family: 'Times New Roman';");
        }
    }

    /** When the user clicks the In-House radio button it will display Machine ID. Generates font to be Times New Roman. */
    public void onInHouse() throws IOException {
        if(inHouseAddPart.isSelected()) {
            outsourcedAddPart.setSelected(false);
            machineIDAddPart.setText("Machine ID");
            Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
            root.setStyle("-fx-font-family: 'Times New Roman';");
        }
    }

}
