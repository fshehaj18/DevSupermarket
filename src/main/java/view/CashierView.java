package view;

import RW.RWProduct;
import RW.RWPurchases;
import RW.RWSells;
import RW.RWSuppliers;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class CashierView extends Application {

    User currentUser;
    static ArrayList<Sells> sells = new ArrayList<>();

    public CashierView(User currentUser) {
        this.currentUser = currentUser;
    }

    public CashierView(User currentUser, Sells s ) {

        sells.add(s);
        this.currentUser = currentUser;
    }
    @Override
    public void start(Stage stage) throws Exception {

        ObservableList<Sells> billProducts = FXCollections.observableArrayList(sells);

        Label barcode = new Label("Name");
        TextField barcodeTf = new TextField();
        Label quantity = new Label("Quantity");
        TextField quantityTf = new TextField();

        Button add = new Button("Add");
        Button cancel = new Button("Cancel");
        Button delete = new Button("Delete");
        HBox hb = new HBox();
        hb.getChildren().addAll(add,cancel);
        hb.setSpacing(10);

        GridPane gp = new GridPane();
        gp.addColumn(0,barcode,quantity);
        gp.addColumn(1,barcodeTf,quantityTf);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.addRow(2,add,cancel,delete);
        delete.getStyleClass().add("button1");


        TableView table = new TableView();

        TableColumn nameC = new TableColumn("Name");
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn quantityC = new TableColumn("Quantity");
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn sellPriceC = new TableColumn("Price");
        sellPriceC.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));

        table.getColumns().addAll(nameC,quantityC,sellPriceC);
        table.setEditable(true);
        table.setItems(billProducts);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                LocalDate localDate = java.time.LocalDate.now();

                DateFormat sellDate = new DateFormat(localDate.getDayOfMonth()+"/"+localDate.getMonthValue()+"/"+ localDate.getYear());

                RWProduct rwProduct = new RWProduct();
                Product product = rwProduct.getProductByBarcode(barcodeTf.getText());

                if(product == null || !quantityTf.getText().matches("\\d")){


                    Alert al = new Alert(Alert.AlertType.ERROR);
                    if(product == null){
                        al.setContentText("Barcode does not exist!");}
                    else  if (!quantityTf.getText().matches("\\d")) {
                        al.setContentText("Please enter a number!");
                    }
                    al.show();
                    try {
                        new CashierView(currentUser).start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else {

                    product.setQuantity(Integer.parseInt(quantityTf.getText()));
                    Sells s = new Sells(barcodeTf.getText(),product.getName(), Integer.parseInt(quantityTf.getText()), sellDate, product.getSellPrice());
                    barcodeTf.clear();
                    quantityTf.clear();
                    try {
                        new CashierView(currentUser,s).start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        Label total = new Label("Total:");
        TextField totalTf = new TextField();
        Button print = new Button("Submit");
        print.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            RWSells rws = new RWSells();
                RWProduct rwProduct = new RWProduct();
                int total = 0;
                System.out.println();
                for(Sells s: sells){
                    total += s.getSellPrice() * s.getQuantity();
                    rws.add(s);
                    rwProduct.addQuantity(s.getBarcode(),s.getQuantity());
                }
                totalTf.setText(String.valueOf(total));

            }
        });

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(total,totalTf,print);
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
        stage.setTitle("Cashier");
        stage.show();
    }


}
