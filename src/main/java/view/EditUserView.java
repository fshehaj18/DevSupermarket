package view;

import RW.RWUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DateFormat;
import model.User;

import java.time.LocalDate;

public class EditUserView {
    User currentUser;
    User selectedUser;

    public EditUserView(User currentUser, User selectedUser) {
        this.currentUser = currentUser;
        this.selectedUser = selectedUser;
    }


    public void start(Stage stage) throws Exception {

        Label name = new Label("Name");
        TextField nameTf = new TextField();
        nameTf.setText(selectedUser.getName());
        Label bday = new Label("Birthday");
        DatePicker bdayTf = new DatePicker();
        bdayTf.setValue(LocalDate.of(selectedUser.getBday().getYear(),selectedUser.getBday().getMonth(),selectedUser.getBday().getDate()));
        Label email = new Label("Email");
        TextField emailTf = new TextField();
        emailTf.setText(selectedUser.getEmail());
        Label salary = new Label("Salary");
        TextField salaryTf = new TextField();
        salaryTf.setText(String.valueOf(selectedUser.getSalary()));
        Label phone = new Label("Phone");
        TextField phoneTf = new TextField();
        phoneTf.setText(selectedUser.getPhone());


        GridPane gp = new GridPane();
        gp.addColumn(0, name, bday, email, salary, phone);
        gp.addColumn(1, nameTf, bdayTf, emailTf, salaryTf, phoneTf);
        HBox hb = new HBox();

        Button save = new Button("Save changes");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RWUser rwu = new RWUser();
                int day = bdayTf.getValue().getDayOfMonth();
                int month = bdayTf.getValue().getMonthValue();
                int year = bdayTf.getValue().getYear();
                DateFormat date = new DateFormat(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
                rwu.editUser(selectedUser, nameTf.getText(),date,emailTf.getText(),phoneTf.getText(),Double.parseDouble(salaryTf.getText()));
                System.out.println(selectedUser.getPhone());
                try {
                    new AdminView(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new AdminView(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        hb.getChildren().addAll(save,back);
        hb.setSpacing(10);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.getChildren().addAll(gp,hb);
        vb.setPadding(new Insets(10,10,10,10));

        Scene sc = new Scene(vb,500,500);
        sc.getStylesheets().add("stylesheet.css");
        stage.setScene(sc);
        stage.setTitle("Edit Employee");
        stage.show();
    }

}
