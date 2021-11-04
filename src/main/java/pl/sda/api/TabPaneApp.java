package pl.sda.api;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TabPaneApp extends Application {
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

    @Override
    public void start(Stage stage) throws Exception {
        tabPane.getTabs().addAll(tabCalculator, tabForm, tabCurrency);
        root.getChildren().add(tabPane);
        createTabCalculater();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Tab App");
        stage.show();
    }

    public void createTabCalculater(){
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
