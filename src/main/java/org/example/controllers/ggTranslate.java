package org.example.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.demodictionary.SceneSwitch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ggTranslate extends Application {

    @FXML
    private AnchorPane translateView;

    @FXML
    private TextArea textArea1; // TextArea for input text

    @FXML
    private TextArea textArea2; // TextArea for translated text

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ggTranslate.class.getResource("/org/example/demodictionary/translate.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("My Dictionary");

        stage.setScene(scene);
        stage.show();
        textArea1= (TextArea) fxmlLoader.getNamespace().get("textArea1");
        textArea2= (TextArea) fxmlLoader.getNamespace().get("textArea2");

        // The button with fx:id "Tsl" should already be linked with the FXML file
        // The text areas with fx:id "Textarea1" and "Textarea2" should also be linked
    }

    // Event handler for the translate button
    @FXML
    private void Hanbutton(ActionEvent actionEvent) {
        // Get the text from textArea1, translate it, and set the result in textArea2
        String textToTranslate = textArea1.getText();
        String translatedText = translate(textToTranslate, "vi");
        textArea2.setText(translatedText);
    }

    // Translate method
    public String translate(String textToTranslate, String targetLanguage) {
        String translatedText = "";
        try {
            String urlStr = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl="
                    + targetLanguage + "&dt=t&q=" + URLEncoder.encode(textToTranslate, "UTF-8");

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            translatedText = parseTranslationResult(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translatedText;
    }

    // Simple method to parse the Google Translate API JSON response
    private static String parseTranslationResult(String json) {
        // This is a simplified way to parse the JSON response.
        // In a real application, you should use a JSON parsing library for reliability.
        return json.split("\"")[1];
    }

    @FXML
    public void onBackButton() throws IOException {
        new SceneSwitch(translateView, "hello-view.fxml");
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}