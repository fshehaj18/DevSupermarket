package view;

import RW.RWUser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Admin;
import model.Cashier;
import model.Economist;
import model.User;
import javafx.scene.control.Alert.AlertType;
public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    Label username = new Label("Username");
        username.setStyle("-fx-font-weight: bold;-fx-font-size:14px");

        TextField usernameTf = new TextField();
    usernameTf.setPromptText("Enter Username");
    usernameTf.setFont(Font.font("Arial", 12));

    Label password = new Label("Password");
    password.setStyle("-fx-font-weight: bold;-fx-font-size:14px");
    PasswordField passwordTf = new PasswordField();
    passwordTf.setPromptText("Enter Password");
    passwordTf.setFont(Font.font("Arial", 12));

    Button login = new Button("Login");
    Button cancel = new Button("Cancel");
    RWUser rwu = new RWUser();
    login.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            String username = usernameTf.getText();
            String password = passwordTf.getText();

            if (username == "" && password == "") {
                Alert al = new Alert(AlertType.ERROR);
                al.setContentText("Please enter credentials!");
                al.show();
            } else {

                if (rwu.checkLogin(username, password) != null) {
                    User u = rwu.checkLogin(username, password);

                    if (u instanceof Admin) {
                        System.out.println("Hello Admin");
                        try {
                            new AdminView(u).start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (u instanceof Economist) {
                        try {
                            new EconomistView(u).start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("Hello Economist");

                    } else if (u instanceof Cashier) {
                        System.out.println("Hello Cashier");
                        try {


                            new CashierView(u).start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                } else {

                    Alert al = new Alert(AlertType.ERROR);
                    al.setContentText("Wrong credentials!");
                    al.show();
                    passwordTf.clear();
                }
            }
        }
    });
        GridPane gp = new GridPane();
        HBox hb = new HBox();
        hb.getChildren().addAll(login,cancel);
       // vb.getChildren().add(2,hb);
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(2);
        gp.addColumn(0,username,password);
        gp.addColumn(1,usernameTf,passwordTf);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.getChildren().addAll(gp,hb);
        vb.setPadding(new Insets(10,10,10,10));
        BorderPane bp = new BorderPane();
        bp.setCenter(vb);

        Scene sc = new Scene(bp,600,600);
        stage.setScene(sc);
        sc.getStylesheets().add("stylesheet.css");
        cancel.getStyleClass().add("button1");
        stage.setTitle("Login");
        stage.show();
    }
}
