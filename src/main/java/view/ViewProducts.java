package view;

import RW.RWProduct;
import RW.RWSuppliers;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Product;
import model.Supplier;
import model.User;

public class ViewProducts extends Application {
    User currentUser;

    public ViewProducts(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void start(Stage stage) throws Exception {


        RWProduct rwp = new RWProduct();
        ObservableList<Product> products = FXCollections.observableArrayList(rwp.getProducts());
        TableView table = new TableView();
        TableColumn nameC = new TableColumn("Name");
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn categoryC = new TableColumn("Category");
        categoryC.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn supplierC = new TableColumn("Supplier");
        supplierC.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        TableColumn boughtPriceC = new TableColumn("Bought Price");
        boughtPriceC.setCellValueFactory(new PropertyValueFactory<>("boughtPrice"));

        TableColumn sellPriceC = new TableColumn("Sell Price");
        sellPriceC.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));

        TableColumn quantityC = new TableColumn("Quantity");
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        table.setRowFactory( tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Product rowData = row.getItem();

                }
            });
            return row ;
        });
        table.setEditable(true);
        table.getColumns().addAll(nameC,categoryC,supplierC,boughtPriceC,sellPriceC,quantityC);
        table.setItems(products);

        Button add = new Button("Add");
        Button edit = new Button("Edit");
        Button back = new Button("Back");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new AddProductView(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new EconomistView(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(add,edit,back);
        BorderPane bp = new BorderPane();
        bp.setCenter(table);
        bp.setBottom(hb);
        hb.setSpacing(10);
        Scene sc = new Scene(bp,500,500);
        sc.getStylesheets().add("stylesheet.css");
        stage.setScene(sc);
        stage.setTitle("View Products");
        stage.show();

    }
}
