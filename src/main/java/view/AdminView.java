package view;

import RW.RWUser;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import model.Economist;
import model.Cashier;

import java.util.ArrayList;

public class AdminView {
    private User currentUser;

    public AdminView(User currentUser)
    {
        this.currentUser = currentUser;
    }
    public void start(Stage stage) throws Exception {

        RWUser rwu = new RWUser();
        ObservableList<User> users = FXCollections.observableArrayList(rwu.getUsers());
        ArrayList<User> employees= new ArrayList<>();
        for(User i:rwu.getUsers()){
                if(i instanceof Economist || i instanceof Cashier)
                employees.add(i);
        }
        users = FXCollections.observableArrayList(employees);

        TableView table = new TableView();
        TableColumn idC = new TableColumn("ID");
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn usernameC = new TableColumn("Username");
        usernameC.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn nameC = new TableColumn("Name");
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn dateC = new TableColumn("Date");
        dateC.setCellValueFactory(new PropertyValueFactory<>("bday"));
        TableColumn emailC = new TableColumn("Email");
        emailC.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn phoneC = new TableColumn("Phone");
        phoneC.setCellValueFactory(new PropertyValueFactory<>("phone"));
        TableColumn salaryC = new TableColumn("Salary");
        salaryC.setCellValueFactory(new PropertyValueFactory<>("salary"));
        TableColumn levelC = new TableColumn("Level");
        levelC.setCellValueFactory(new PropertyValueFactory<>("level"));

        table.setItems(users);
        table.setEditable(true);
        table.getColumns().addAll(idC,usernameC,nameC,dateC,emailC,phoneC,salaryC,levelC);
        Button add = new Button("Add Employee");
        Button delete = new Button("Delete");
        Button edit = new Button("Edit");
        Button back = new Button("Back");
        Button statistics = new Button("Statistics");
        Button logout = new Button("Log Out");

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new AddUserView(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                table.refresh();
            }

        });
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    User selectedUser = (User) table.getSelectionModel().getSelectedItem();

                    new EditUserView(currentUser, selectedUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                table.refresh();
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                User selectedUser = (User) table.getSelectionModel().getSelectedItem();

                rwu.delete(selectedUser);
        table.refresh();
                try {
                    new AdminView(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new Login().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        statistics.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new AdminStatistics(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        HBox hb = new HBox();
        hb.getChildren().addAll(add,edit,delete,statistics,logout);
        BorderPane bp = new BorderPane();
        bp.setCenter(table);
        bp.setBottom(hb);
        hb.setSpacing(10);
        Scene sc = new Scene(bp,500,500);
        stage.setScene(sc);
        sc.getStylesheets().add("stylesheet.css");
        stage.setTitle("Admin View");
        stage.show();


    }
}
