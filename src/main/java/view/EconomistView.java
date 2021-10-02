package view;

import RW.RWProduct;
import RW.RWSuppliers;
import RW.RWUser;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;

public class EconomistView extends Application {
    User currentUser;

    public EconomistView(User currentUser) {
        this.currentUser = currentUser;
    }
    @Override
    public void start(Stage stage) throws Exception {
        MenuBar menuBar = new MenuBar();
        Menu suppliers = new Menu("Suppliers");
        Menu products = new Menu("Products");
        MenuItem viewProducts = new MenuItem("View Products");
        MenuItem addCategory = new MenuItem("Add Category");
        MenuItem buyProducts = new MenuItem("Buy products");


        menuBar.getMenus().addAll(suppliers,products);
        products.getItems().addAll(viewProducts,addCategory,buyProducts);
        RWSuppliers rws = new RWSuppliers();
        ObservableList<Supplier> suppliers1 = FXCollections.observableArrayList(rws.getSuppliers());

        TableView table = new TableView();
        TableColumn nameC = new TableColumn("Name");
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn addressC = new TableColumn("Address");
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn emailC = new TableColumn("Email");
        emailC.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn phoneC = new TableColumn("Phone");
        phoneC.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableRow<Supplier> s = new TableRow<>();
        table.setRowFactory( tv -> {
            TableRow<Supplier> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    RWProduct rwp = new RWProduct();
                    Supplier rowData = row.getItem();
                    ObservableList<Product> productsOfSupplier = FXCollections.observableArrayList(rwp.getProductsOfSupplier(rowData));
                    TableView productsTable = new TableView();
                    TableColumn name = new TableColumn("Name");
                    name.setCellValueFactory(new PropertyValueFactory<>("name"));

                    productsTable.getColumns().addAll(name);
                    productsTable.setEditable(true);
                    productsTable.setItems(productsOfSupplier);
                    Stage newStage = new Stage();
                    Scene newScene = new Scene(productsTable,500,500);
                    newStage.setScene(newScene);
                    newScene.getStylesheets().add("stylesheet.css");
                    newStage.show();

                }
            });
            return row ;
        });

        table.setItems(suppliers1);
        table.setEditable(true);
        table.getColumns().addAll(nameC,addressC,emailC,phoneC);
        suppliers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new AddSupplierView(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        addCategory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new AddCategory(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        viewProducts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new ViewProducts(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        buyProducts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new BuyProducts(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        HBox hb = new HBox();
        Button add = new Button("Add");
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        Button logout = new Button("Log Out");
        logout.getStyleClass().add("button1");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new AddSupplierView(currentUser).start(stage);
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
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new Login().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        hb.getChildren().addAll(add,edit,delete,logout);
        BorderPane bp = new BorderPane();
        bp.setCenter(table);
        bp.setBottom(hb);
        bp.setTop(menuBar);
        hb.setSpacing(10);
        Scene sc = new Scene(bp,500,500);
        stage.setScene(sc);
        sc.getStylesheets().add("stylesheet.css");
        stage.setTitle("Economist");
        stage.show();
    }
}
