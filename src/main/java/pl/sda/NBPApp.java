package pl.sda;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import pl.sda.api.lib.ApiGenericRepository;
import pl.sda.domain.Rate;
import pl.sda.domain.Table;

import java.io.IOException;
import java.net.URI;


/**
 * JavaFX App
 */
public class NBPApp extends Application {
    public URI BASE = URI.create("http://api.nbp.pl/api/exchangerates/tables/A/2021-10-15/?format=json");
    public ApiGenericRepository<Table[]> currency = new ApiGenericRepository<>(Table[].class);
    public VBox root;
    public Tab currencyCalculator;
    public Tab starWarsCollection = new Tab("Star Wars");
    public TabPane tabPane;
    public Label sourceCurrencyLabel = new Label("Waluta źródłowa");
    public Label targetCurrencyLabel = new Label("Waluta docelowa");
    public Label currencyValueLabel = new Label("Kwota");
    public ComboBox<Rate> sourceCurrencyCombo = new ComboBox<>();
    public ComboBox<Rate> targetCurrencyCombo = new ComboBox<>();
    public Spinner<Double> currencyValue = new Spinner<>(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1_000_000, 1));

    public NBPApp() throws IOException, InterruptedException {
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        root = buildRoot();
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

    public VBox buildRoot() throws IOException, InterruptedException {
        VBox box = new VBox();
        tabPane = buildTabPane();
        box.getChildren().add(tabPane);
        box.setAlignment(Pos.TOP_CENTER);
        return box;
    }

    public TabPane buildTabPane() throws IOException, InterruptedException {
        TabPane pane = new TabPane();
        currencyCalculator = buildCalculatorTab();
        pane.getTabs().add(currencyCalculator);
        return pane;
    }

    public Tab buildCalculatorTab() throws IOException, InterruptedException {
        Tab tab = new Tab("Kalkulator walut");
        VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setSpacing(10);
        box.setAlignment(Pos.TOP_CENTER);
        currencyValue.setEditable(true);
        currencyValue.getEditor().setFont( Font.font("Lato", 32));
        currencyValue.getEditor().setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(), 0.0 , change -> {
            try{
                Double.parseDouble(change.getControlNewText());
                return change;
            } catch (NumberFormatException e) {
                return null;
            }
        }));
        currencyValue.getEditor().setAlignment(Pos.CENTER_RIGHT);
        sourceCurrencyCombo.getItems().addAll(currency.get(BASE).get()[0].getRates());
        targetCurrencyCombo.getItems().addAll(currency.get(BASE).get()[0].getRates());
        sourceCurrencyCombo.setMinWidth(300);
        targetCurrencyCombo.setMinWidth(300);
        currencyValue.setMaxWidth(300);
        box.getChildren().addAll(sourceCurrencyLabel, sourceCurrencyCombo, targetCurrencyLabel, targetCurrencyCombo, currencyValueLabel, currencyValue);
        tab.setContent(box);
        return tab;
    }
}