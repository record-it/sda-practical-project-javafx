package pl.sda.api;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.sda.api.nbp.NBPAPIService;
import pl.sda.api.nbp.NBPService;
import pl.sda.api.nbp.Rate;

import java.io.IOException;
import java.util.List;

public class TabPaneApp extends Application {
    NBPService nbpService = new NBPAPIService();
    VBox root = new VBox();
    TabPane tabPane = new TabPane();
    Tab tabCalculator = new Tab("Kalkulator");
    Tab tabForm = new Tab("Formularz");
    Tab tabCurrency = new Tab("Przelicznik walut");

    HBox calculatorContent = new HBox();
    TextField numberA = new TextField();
    TextField numberB = new TextField();
    ComboBox<String> operatorBox = new ComboBox<>();
    Label equalsLabel = new Label("=");
    TextField result = new TextField();
    /************** UI kalkulatora walut ************************/
    //VBox jako główny kontener tabCurrency o nazwie currencyRoot
    VBox currencyRoot = new VBox();
    //ComboBox dla obiektów typu Rate o nazwie sourceCurrency
    ComboBox<Rate> sourceCurrency = new ComboBox<>();
    //ComboBox dla obiektów typu Rate o nazwie targetCurrency
    ComboBox<Rate> targetCurrency = new ComboBox<>();
    //Label o nazwie rateResult
    Label rateResult = new Label("0.0");
    Spinner<Double> amount =
            new Spinner<>(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1_000_000, 1));


    @Override
    public void start(Stage stage) throws Exception {
        tabPane.getTabs().addAll(tabCalculator, tabForm, tabCurrency);
        root.getChildren().add(tabPane);
        createTabCalculator();
        createTabCurrencyCalculator();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Tab App");
        stage.show();
    }

    public void createTabCalculator(){
        calculatorContent.getChildren().addAll(numberA, operatorBox, numberB, equalsLabel, result);
        operatorBox.getItems().addAll("+","-","*","/");
        tabCalculator.setContent(calculatorContent);
        operatorBox.setOnAction(event -> {
            calculate();
        });
        numberA.setOnAction(event ->{
            calculate();
        });
        numberB.setOnAction(event ->{
            calculate();
        });
    }

    public void createTabCurrencyCalculator() throws IOException, InterruptedException {
        //dodaj do content tabCurrency currencyRoot
        tabCurrency.setContent(currencyRoot);
        //do rootCurrency dodaj kolejno (wg własnego uznania)
        currencyRoot.getChildren().addAll(sourceCurrency, amount, targetCurrency, rateResult);
        //sourceCurrency
        //targetCurrency
        //amount
        //rateResult
        //ewentualnie dodaj padding, spacing, wyrównanie dla rootCurrency
        currencyRoot.setPadding(new Insets(10));
        currencyRoot.setSpacing(10);
        currencyRoot.setAlignment(Pos.CENTER);
        final List<Rate> rates = nbpService.getRates();
        sourceCurrency.getItems().addAll(rates);
        targetCurrency.getItems().addAll(rates);
        amount.setEditable(true);
        amount.getEditor().setOnAction(event -> {
            final double result = nbpService.calculate(
                    sourceCurrency.getSelectionModel().getSelectedItem(),
                    amount.getValue(),
                    targetCurrency.getSelectionModel().getSelectedItem()
            );
            rateResult.setText(String.format("%.2f", result));
        });
    }

    private void calculate() {
        final String operatorStr = operatorBox.getSelectionModel().getSelectedItem();
        double a = Double.parseDouble(numberA.getText());
        double b = Double.parseDouble(numberB.getText());
        switch (operatorStr){
            case "+":
                result.setText(String.valueOf(a + b));
                break;
            case "-":
                result.setText(String.valueOf(a - b));
                break;
            case "*":
                result.setText(String.valueOf(a * b));
                break;
            case "/":
                result.setText(String.valueOf(a / b));
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
