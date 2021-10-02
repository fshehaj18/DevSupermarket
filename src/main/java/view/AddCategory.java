package view;

import RW.RWCategory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Category;
import model.User;

public class AddCategory extends Application {
    User currentUser;

    public AddCategory(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void start(Stage stage) throws Exception {
    Label category = new Label("Category");
        TextField categoryTf = new TextField();
        Button add = new Button("Add");
        Button back = new Button(" Back");

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RWCategory rwc = new RWCategory();
                Category c = new Category(categoryTf.getText());
                rwc.add(c);
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
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.getChildren().addAll(category,categoryTf,hb);
        vb.setPadding(new Insets(10,10,10,10));
        Scene sc = new Scene(vb,500,500);
        stage.setScene(sc);
        stage.setTitle("Add Category");

    }
}
