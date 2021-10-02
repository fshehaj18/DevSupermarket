package view;

import RW.RWCategory;
import RW.RWSuppliers;
import javafx.application.Application;
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
import model.Category;
import model.Supplier;
import model.User;

public class AddSupplierView extends Application {

    User currentUser;

    public AddSupplierView(User currentUser) {
        this.currentUser = currentUser;
    }
    @Override
    public void start(Stage stage) throws Exception {


        Label name = new Label("Name");
        TextField nameTf = new TextField();

        Label email = new Label("Email");
        TextField emailTf = new TextField();
        Label address = new Label("Address");
        TextField addressTf = new TextField();
        Label phone = new Label("Phone");
        TextField phoneTf = new TextField();
        Button add = new Button("Add supplier");
        Button back = new Button("Back");

        GridPane gp = new GridPane();
        gp.addColumn(0,name,email,address,phone);
        gp.addColumn(1,nameTf,emailTf,addressTf,phoneTf);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RWSuppliers rws = new RWSuppliers();
                Supplier s = new Supplier(nameTf.getText(),phoneTf.getText(),addressTf.getText(),emailTf.getText());
                rws.add(s);
                try {
                    new EconomistView(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        hb.getChildren().addAll(add,back);
        hb.setSpacing(10);
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.getChildren().addAll(gp,hb);
        vb.setPadding(new Insets(10,10,10,10));
        Scene sc = new Scene(vb,500,500);
        stage.setScene(sc);
        stage.setTitle("Add Supplier");
        stage.show();
    }
}
