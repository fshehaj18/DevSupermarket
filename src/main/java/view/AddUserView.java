package view;

import RW.RWUser;
import javafx.application.Application;
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
import model.Cashier;
import model.DateFormat;
import model.Economist;
import model.User;

public class AddUserView extends Application {

    User currentUser;
    boolean validPhone, validSalary, validLevel;
    public AddUserView(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Label username = new Label("Username");
        TextField usernameTf = new TextField();
        Label password = new Label("Password");
        PasswordField passwordTf = new PasswordField();
        Label name = new Label("Name");
        TextField nameTf = new TextField();
        Label bday = new Label("Birthday");
        DatePicker bdayTf = new DatePicker();
        Label email = new Label("Email");
        TextField emailTf = new TextField();
        Label salary = new Label("Salary");
        TextField salaryTf = new TextField();
        Label phone = new Label("Phone");
        TextField phoneTf = new TextField();
        Label level = new Label("Level");
        ChoiceBox levelTf = new ChoiceBox();
        levelTf.getItems().add("Cashier");
        levelTf.getItems().add("Economist");


        GridPane gp = new GridPane();
        gp.addColumn(0,username,password,name,bday,email,salary,phone,level);
        gp.addColumn(1,usernameTf,passwordTf,nameTf,bdayTf,emailTf,salaryTf,phoneTf,levelTf);
        Button add = new Button("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RWUser rwu = new RWUser();
                int day = bdayTf.getValue().getDayOfMonth();
                int month = bdayTf.getValue().getMonthValue();
                int year = bdayTf.getValue().getYear();

                String date = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
                DateFormat birthday = new DateFormat(date);
                if (!phoneTf.getText().matches("\\d{10}") && !phoneTf.getText().substring(0, 2).equals("06")) {
                    validPhone = false;
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setContentText("Invalid phone number!");
                    al.show();
                    phoneTf.clear();
                }
                if (!salaryTf.getText().matches("\\d")) {
                    validSalary = false;
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setContentText("Please enter a number!");
                    al.show();
                    salaryTf.clear();
                }
                if(levelTf.getValue() == null){
                    validLevel = false;
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setContentText("Please select the role!");
                    al.show();
                    salaryTf.clear();
                }
                if (validPhone && validSalary && validLevel) {
                    if (levelTf.getSelectionModel().getSelectedItem().equals("Economist")) {
                        rwu.add(new Economist(usernameTf.getText(), passwordTf.getText(), "Economist", birthday, nameTf.getText()
                                , emailTf.getText(), phoneTf.getText(), Double.parseDouble(salaryTf.getText())));
                    } else if (levelTf.getSelectionModel().getSelectedItem().equals("Cashier")) {
                        rwu.add(new Cashier(usernameTf.getText(), passwordTf.getText(), "Cashier", birthday
                                , nameTf.getText(), emailTf.getText(), phoneTf.getText(), Double.parseDouble(salaryTf.getText())));
                    }
                    try {
                        new AdminView(currentUser).start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();

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
        sc.getStylesheets().add("stylesheet.css");
        stage.setTitle("Add Employee");
        stage.show();



    }
}
