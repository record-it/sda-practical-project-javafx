package pl.sda.api;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pl.sda.api.swapi.config.Resource;
import pl.sda.api.swapi.model.Person;
import pl.sda.api.swapi.repository.SWAPIGenericRepository;
import pl.sda.api.swapi.repository.SWAPIRepository;

import java.util.Optional;

public class FxApiClient extends Application {
    public VBox root = new VBox();
    //public SWAPIRepository<Person> films = new SWAPIGenericRepository<>(Resource.PEOPLE);

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Hello from JavaFx!");
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label);
        Scene scene = new Scene(root, 400,300);
        stage.setScene(scene);
        stage.setTitle("Demo FX App");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
