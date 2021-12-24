package com.example.weatherproject;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.util.converter.DoubleStringConverter;


import java.text.DecimalFormat;





public class Controller implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bishkek;

    @FXML
    private Button naryn;

    @FXML
    private Text humidity;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private ImageView image;

    @FXML
    private Text pressure;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private ComboBox<Currency> outputCurrencyComboBox;

    @FXML
    private ComboBox<Currency> inputCurrencyComboBox;

    @FXML
    private TextField outputAmount;

    @FXML
    private TextField inputAmount;


    @FXML
    private Button convertButton;

    private final static DoubleStringConverter DOUBLE_STRING_CONVERTER = new DoubleStringConverter();

    private final static DecimalFormat CURRENCY_FORMAT = new DecimalFormat("#0.000");

    private final ObservableList<Currency> currencies = FXCollections.observableArrayList();

    private final ChangeListener<String> inputAmountChangeListener = (observable, oldValue, newValue) -> convertAction(null);

    public void initialize(URL location, ResourceBundle resources) {

        currencies.addAll(Currency.values());
        inputCurrencyComboBox.setItems(currencies);
        outputCurrencyComboBox.setItems(currencies);
        inputCurrencyComboBox.getSelectionModel().selectFirst();
        outputCurrencyComboBox.getSelectionModel().selectLast();

        clearAction(null);

        inputCurrencyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            convertAction(null);
        });
        outputCurrencyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            convertAction(null);
        });

    }

    @FXML
    private void convertAction(ActionEvent actionEvent) {
        Currency inputCurrency = inputCurrencyComboBox.getValue();
        Currency outputCurrency = outputCurrencyComboBox.getValue();
        double inputValue;
        if (!inputAmount.getText().equals("") && isNumeric(inputAmount.getText())) {
            inputValue = DOUBLE_STRING_CONVERTER.fromString(inputAmount.getText());
            double inputValueInDollars = inputValue * inputCurrency.getRupeeConversionRate();
            double outputValue = inputValueInDollars / outputCurrency.getRupeeConversionRate();
            outputAmount.setText(CURRENCY_FORMAT.format(outputValue));
        }
    }

    @FXML
    private void clearAction(ActionEvent actionEvent) {
        inputAmount.setText("");
        outputAmount.setText("");
    }

    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    @FXML
    public void handleEnterPressed(KeyEvent event){
    if (event.getCode() == KeyCode.ENTER) {
        //System.out.println("Success");
        String getUserCity = city.getText().trim();
        if (!getUserCity.equals("")) {
            String output = getURLContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=05476eaa3a92546e9c0ae9d54efeabc4&units=metric");
//System.out.println(output);
            if (!output.isEmpty()) {
                JSONObject obj = new JSONObject(output);
                temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp") +" °C");
                temp_feels.setText("Feels like: " + obj.getJSONObject("main").getDouble("feels_like") + " °C");
                temp_max.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max") + " °C");
                temp_min.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min") + " °C");
                pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure") + " hPa");
                humidity.setText("Humidity: " + obj.getJSONObject("main").getDouble("humidity") + " %");

            }
        }
    }
}

    @FXML
    void btn(ActionEvent event) {
        //System.out.println("Hello World!");
                String output = getURLContent("https://api.openweathermap.org/data/2.5/weather?q=" + "Bishkek" + "&appid=05476eaa3a92546e9c0ae9d54efeabc4&units=metric");
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp") +" °C");
                    temp_feels.setText("Feels like: " + obj.getJSONObject("main").getDouble("feels_like") + " °C");
                    temp_max.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max") + " °C");
                    temp_min.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min") + " °C");
                    pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure") + " hPa");
                    humidity.setText("Humidity: " + obj.getJSONObject("main").getDouble("humidity") + " %");


        };

    }

    @FXML
    void btn1(ActionEvent event) {
        String output = getURLContent("https://api.openweathermap.org/data/2.5/weather?q=" + "Naryn" + "&appid=05476eaa3a92546e9c0ae9d54efeabc4&units=metric");
        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp") +" °C");
            temp_feels.setText("Feels like: " + obj.getJSONObject("main").getDouble("feels_like") + " °C");
            temp_max.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max") + " °C");
            temp_min.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min") + " °C");
            pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure") + " hPa");
            humidity.setText("Humidity: " + obj.getJSONObject("main").getDouble("humidity") + " %");


        };
    }

    @FXML
    void btn2(ActionEvent event) {
        String output = getURLContent("https://api.openweathermap.org/data/2.5/weather?q=" + "Dushanbe" + "&appid=05476eaa3a92546e9c0ae9d54efeabc4&units=metric");
        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp") +" °C");
            temp_feels.setText("Feels like: " + obj.getJSONObject("main").getDouble("feels_like") + " °C");
            temp_max.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max") + " °C");
            temp_min.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min") + " °C");
            pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure") + " hPa");
            humidity.setText("Humidity: " + obj.getJSONObject("main").getDouble("humidity") + " %");
        };
    }

    @FXML
    void btn3(ActionEvent event) {
        String output = getURLContent("https://api.openweathermap.org/data/2.5/weather?q=" + "Nur-Sultan" + "&appid=05476eaa3a92546e9c0ae9d54efeabc4&units=metric");
        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp") +" °C");
            temp_feels.setText("Feels like: " + obj.getJSONObject("main").getDouble("feels_like") + " °C");
            temp_max.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max") + " °C");
            temp_min.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min") + " °C");
            pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure") + " hPa");
            humidity.setText("Humidity: " + obj.getJSONObject("main").getDouble("humidity") + " %");
        };
    }

    @FXML
    void btn4(ActionEvent event) {
        String output = getURLContent("https://api.openweathermap.org/data/2.5/weather?q=" + "London" + "&appid=05476eaa3a92546e9c0ae9d54efeabc4&units=metric");
        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp") +" °C");
            temp_feels.setText("Feels like: " + obj.getJSONObject("main").getDouble("feels_like") + " °C");
            temp_max.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max") + " °C");
            temp_min.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min") + " °C");
            pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure") + " hPa");
            humidity.setText("Humidity: " + obj.getJSONObject("main").getDouble("humidity") + " %");
        };
    }

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String output = getURLContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=05476eaa3a92546e9c0ae9d54efeabc4&units=metric");
//System.out.println(output);
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp") +" °C");
                    temp_feels.setText("Feels like: " + obj.getJSONObject("main").getDouble("feels_like") + " °C");
                    temp_max.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max") + " °C");
                    temp_min.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min") + " °C");
                    pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure") + " hPa");
                    humidity.setText("Humidity: " + obj.getJSONObject("main").getDouble("humidity") + " %");

                }
            }

        });


    }

    private static String getURLContent(String urlAddress){
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("The city was not found");
        }
        return content.toString();
    }


}
