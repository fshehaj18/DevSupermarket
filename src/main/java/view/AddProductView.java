package view;

import RW.RWCategory;
import RW.RWProduct;
import RW.RWSuppliers;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DateFormat;
import model.Product;
import model.Supplier;
import model.User;

public class AddProductView extends Application {
    User currentUser;
    Supplier supplier;

    public AddProductView(User currentUser) {
        this.currentUser = currentUser;
    }

    public AddProductView(User currentUser, Supplier supplier) {

        this.supplier = supplier;
        this.currentUser = currentUser;
    }



    @Override
    public void start(Stage stage) throws Exception {

        RWSuppliers rws = new RWSuppliers();
        RWCategory rwc = new RWCategory();

        Label barcode = new Label("Barcode");
        TextField barcodeTf = new TextField();
        Label name = new Label("Name");
        TextField nameTf = new TextField();
        Label supplier = new Label("Supplier");
        ChoiceBox supplierChoice = new ChoiceBox();
        supplierChoice.setItems(FXCollections.observableList(rws.getSupplierNames()));
        Label category = new Label("Category");
        ChoiceBox categoryChoice = new ChoiceBox();
        categoryChoice.setItems(FXCollections.observableList(rwc.getCategoriesNames()));
        Label sellPrice = new Label("Sell Price");
        TextField sellPriceTf = new TextField();
        Label boughtPrice = new Label("Bought Price");
        TextField boughtPriceTf = new TextField();

        GridPane gp = new GridPane();
        gp.addColumn(0,barcode,name,supplier,category,sellPrice,boughtPrice);
        gp.addColumn(1,barcodeTf,nameTf,supplierChoice,categoryChoice,sellPriceTf,boughtPriceTf);

        Button add = new Button("Add product");
        Button back = new Button("Back");

        HBox hb = new HBox();
        hb.getChildren().addAll(add,back);
        hb.setSpacing(10);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RWProduct rwp = new RWProduct();
                Product p = new Product(barcodeTf.getText(),nameTf.getText(),categoryChoice.getValue().toString(),supplierChoice.getValue().toString(),
                        Double.parseDouble(boughtPriceTf.getText()), Double.parseDouble(sellPriceTf.getText()));
                rwp.add(p);
                try {
                    new ViewProducts(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new ViewProducts(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.getChildren().addAll(gp,hb);
        vb.setPadding(new Insets(10,10,10,10));
        Scene sc = new Scene(vb,500,500);
        stage.setScene(sc);
        sc.getStylesheets().add("stylesheet.css");
        stage.setTitle("Add Product");
        stage.show();
    }
}
