package pl.sda.api;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pl.sda.api.swapi.config.Resource;
import pl.sda.api.swapi.domain.PersonDomain;
import pl.sda.api.swapi.model.Person;
import pl.sda.api.swapi.repository.SWAPIGenericRepository;
import pl.sda.api.swapi.repository.SWAPIRepository;
import pl.sda.api.swapi.service.PeopleService;
import pl.sda.api.swapi.service.SWAPIPeopleService;

import java.io.IOException;
import java.util.Optional;

public class FxApiClient extends Application {
    public PeopleService peopleService = new SWAPIPeopleService();
    public VBox root = new VBox();
    public VBox personDetails = new VBox();
    public HBox buttons = new HBox();
    public SWAPIRepository<Person> people = new SWAPIGenericRepository<>(Resource.PEOPLE);
    public Spinner<Integer> personId = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 1));
    public Button showPersonBtn = new Button("Pobierz");
    public Button showPrevPersonBtn = new Button("Poprzedni");
    public Label labelName = new Label("Hello from JavaFx!");
    public Label heightLabel = new Label();
    public Label genderLabel = new Label();
    @Override
    public void start(Stage stage) throws Exception {
        personDetails.setAlignment(Pos.TOP_LEFT);
        personDetails.setSpacing(15);
        personDetails.setPadding(new Insets(15, 10, 20, 10));
        personDetails.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(10));

        showPersonDetails(1);
        labelName.setFont(Font.font("Lato", FontWeight.BLACK, 24));
        showPersonBtn.setOnAction(event -> {
            int id = personId.getValue();
            showPersonDetails(id);
        });
        showPrevPersonBtn.setOnAction(event -> {
            int id = personId.getValue();
            personId.getValueFactory().setValue(--id);
            showPersonDetails(id);
        });

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        personDetails.getChildren().addAll(labelName, heightLabel, genderLabel);
        buttons.getChildren().addAll(showPrevPersonBtn, showPersonBtn);
        //dodać etykiety do root
        root.getChildren().addAll(personId, buttons, personDetails);
        Scene scene = new Scene(root, 400,300);
        stage.setScene(scene);
        stage.setTitle("Demo FX App");
        stage.show();

    }

    private void showPersonDetails(int id) {
            final Optional<PersonDomain> optionalPerson = peopleService.findById(id);
            if (optionalPerson.isPresent()) {
                labelName.setText(optionalPerson.get().getName());
                heightLabel.setText(optionalPerson.get().getHeight() + "");
                genderLabel.setText(optionalPerson.get().getMass() + "");
            } else {
                labelName.setText("Brak takiej postaci!");
            }
    }

    public static void main(String[] args) {
        launch();
    }
}
