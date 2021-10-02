package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class AdminStatistics extends Application {
    User currentUser;

    public AdminStatistics(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label start = new Label("Start date");
        DatePicker startDate = new DatePicker();
        Label end = new Label("End date");
        DatePicker endDate = new DatePicker();

        GridPane gp = new GridPane();
        gp.addColumn(0,start,end);
        gp.addColumn(1,startDate,endDate);

        gp.setHgap(10);
        gp.setVgap(10);

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        gp.addRow(1,submit);

        BorderPane bp = new BorderPane();
        bp.setCenter(gp);
        Scene sc = new Scene(bp,600,600);
        stage.setScene(sc);
        stage.setTitle("Finances");
        stage.show();
    }
}
