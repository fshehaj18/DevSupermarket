package view;

import RW.RWProduct;
import RW.RWPurchases;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DateFormat;
import model.Product;
import model.Purchases;
import model.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class BuyProducts extends Application {
    User currentUser;
    static ArrayList<Purchases> purchases = new ArrayList<>();
    boolean validBarcode, validQuantity;

    public BuyProducts(User currentUser) {
        this.currentUser = currentUser;
    }
    public BuyProducts(User currentUser,Purchases p) {
        this.currentUser = currentUser;
        purchases.add(p);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ObservableList<Purchases> purchasedProducts = FXCollections.observableArrayList(purchases);

        Label barcode = new Label("Barcode");
        TextField barcodeTf = new TextField();
        Label quantity = new Label("Quantity");
        TextField quantityTf = new TextField();
        Label expiry = new Label("Expiry date");
        DatePicker expiryDatePicker = new DatePicker();

        Button add = new Button("Add");
        Button cancel = new Button("Cancel");
        Button delete = new Button("Delete");
        HBox hb = new HBox();
        hb.getChildren().addAll(add,cancel);
        hb.setSpacing(10);

        GridPane gp = new GridPane();
        gp.addColumn(0,barcode,quantity,expiry);
        gp.addColumn(1,barcodeTf,quantityTf,expiryDatePicker);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.addRow(1,add,cancel,delete);
        delete.getStyleClass().add("button1");

        TableView table = new TableView();

        TableColumn nameC = new TableColumn("Barcode");
        nameC.setCellValueFactory(new PropertyValueFactory<>("barcode"));

        TableColumn quantityC = new TableColumn("Quantity");
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn sellPriceC = new TableColumn("Price");
        sellPriceC.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));


        table.getColumns().addAll(nameC,quantityC,sellPriceC);
        table.setEditable(true);

        table.setItems(purchasedProducts);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int day = expiryDatePicker.getValue().getDayOfMonth();
                int month = expiryDatePicker.getValue().getMonthValue();
                int year = expiryDatePicker.getValue().getYear();
                DateFormat expiryDate = new DateFormat(day+"/"+month+"/"+year);

                LocalDate localDate = java.time.LocalDate.now();

                DateFormat purchaseDate = new DateFormat(localDate.getDayOfMonth()+"/"+localDate.getMonthValue()+"/"+ localDate.getYear());

                RWProduct rwProduct = new RWProduct();
                Product product = rwProduct.getProductByBarcode(barcodeTf.getText());

                if(product == null || quantityTf.getText().matches("\\d")){

                    validBarcode = false;
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    if(product == null)
                al.setContentText("Barcode does not exist!");
                    else  if (quantityTf.getText().matches("\\d"))
                        al.setContentText("Please enter a number!");
                al.show();
                    try {
                        new BuyProducts(currentUser).start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                product.setQuantity(Integer.parseInt(quantityTf.getText()));

                Purchases p = new Purchases(product.getBarcode(),product.getQuantity(),purchaseDate,product.getBoughtPrice(),expiryDate);
                //purchases.add(p);

                try {
                    new BuyProducts(currentUser,p).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                table.refresh();
                barcodeTf.clear();
                quantityTf.clear();

            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        Label total = new Label("Total:");
        TextField totalTf = new TextField();
        totalTf.setEditable(false);

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                RWPurchases rwp = new RWPurchases();
            RWProduct rwProduct = new RWProduct();
                int total = 0;
                System.out.println();
                for(Purchases p: purchases){
                    total += p.getSellPrice() * p.getQuantity();
                    rwp.add(p);
                    rwProduct.addQuantity(p.getBarcode(),p.getQuantity());
                }
                totalTf.setText(String.valueOf(total));
            }
        });

        Button back = new Button("Back");
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

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(total,totalTf,submit,back);
        hb2.setSpacing(10);

        BorderPane bp =new BorderPane();
        bp.setLeft(gp);
        bp.setRight(table);
        bp.setBottom(hb2);
        Insets insets = new Insets(10);

        BorderPane.setMargin(gp,insets);
        BorderPane.setMargin(table,insets);
        BorderPane.setMargin(hb2,insets);

        bp.setPadding(new Insets(20, 300, 100, 20));

        Scene sc = new Scene(bp,800,800);
        stage.setScene(sc);
        sc.getStylesheets().add("stylesheet.css");
        stage.setTitle("Purchase");
        stage.show();
    }
}
