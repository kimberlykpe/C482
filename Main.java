package Main;

import Model.InHouse;
import Model.Inventory;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class starts the application.*/
public class Main extends Application {

    /** This loads the Main Form and sets the font to be Times New Roman.
     @param stage loads the Main Form information and title.*/

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
        root.setStyle("-fx-font-family: 'Times New Roman';");
        stage.setTitle("Inventory Management System");
        stage.setScene (new Scene(root, 800, 600));
        stage.show();
    }


    /** This creates initial test parts and puts test data in the parts table. */
    public static void testParts() {
        InHouse part1 = new InHouse(1,"test1",1.00,1,1,2, 1);
        Inventory.addPart(part1);
        InHouse part2 = new InHouse(2,"blob2",2.00,2,1,50, 2);
        Inventory.addPart(part2);
        InHouse part3 = new InHouse(3,"plop3",3.00,3,1,50, 3);
        Inventory.addPart(part3);
    }

    /** This creates initial test products and puts test data in the products table. */
    public static void testProducts() {
        Product product1 = new Product(1,"product1",1.00,1,1,50);
        Inventory.addProduct(product1);
        Product product2 = new Product(2,"root2",2.00,2,1,50);
        Inventory.addProduct(product2);
        Product product3 = new Product(3,"flower3",3.00,3,1,50);
        Inventory.addProduct(product3);
    }

    /** launches the application. */
    public static void main(String[] args) {
        testParts();
        testProducts();
        launch();
    }
}
