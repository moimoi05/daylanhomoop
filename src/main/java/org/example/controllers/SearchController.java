package org.example.controllers;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import org.example.demodictionary.*;

import java.io.IOException;
import java.util.List;

public class SearchController extends MyDictionary {

    private Note note = new Note();
    @FXML
    private Button back;

    @FXML
    private ListView<String> listView = new ListView<>();

    @FXML
    private AnchorPane searchView;

    @FXML
    private TextField textField;

    @FXML
    private WebView web;

    private WebView definitionView;

    public SearchController() throws IOException {
    }

    @FXML
    public void searchWord(KeyEvent keyEvent) {
        String input = textField.getText();
        if (!input.isEmpty()) {
            List<String> result = super.getDictionary().keySet().stream()
                    .filter(word -> word.startsWith(input))
                    .toList();
            listView.getItems().clear();
            listView.getItems().addAll(result);
        } else {
            listView.getItems().clear();
            listView.getItems().addAll(super.getDictionary().keySet());
        }
    }

    @FXML
    private void clearInput() {
        textField.setText(""); // Xóa nội dung của textField
        listView.getItems().clear();
        listView.getItems().addAll(super.getDictionary().keySet()); // Nếu bạn muốn, cũng có thể xóa kết quả tìm kiếm
        web.getEngine().loadContent(""); // Xóa nội dung của WebView
    }

    @FXML
    public void onBackButton() throws IOException {
        new SceneSwitch(searchView, "hello-view.fxml");
    }

    @FXML
    public void initialize() {
        listView.getItems().addAll(super.getDictionary().keySet());
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Word selectedWord = super.getDictionary().get(newValue);
                if (selectedWord != null) {
                    // Use getWord_explain() to fetch the explanation of the word
                    web.getEngine().loadContent(selectedWord.getWord_explain());
                } else {
                    // Provide a default HTML content when no definition is found
                    web.getEngine().loadContent("<html><body>No definition found.</body></html>");
                }
            }
        });
    }

    @FXML
    private void handleClickSound1Btn() {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Voice voice = VoiceManager.getInstance().getVoice("kevin16");
            if (voice != null) {
                voice.allocate();
                voice.speak(selectedWord);
            } else {
                throw new IllegalStateException("Cannot find voice: kevin16");
            }
        }
    }

    @FXML
    public void onAdd() throws IOException {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord == null) {
            return;
        }
        String[] parts = selectedWord.split(": ");
        String word_target = parts[0].trim();

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Fix Word");
        dialog.setHeaderText("Enter the meaning:");
        TextField textField1 = new TextField();
        textField1.setPromptText("New definition");

        // Layout GridPane
        GridPane grid = new GridPane();
        grid.add(textField1, 0, 0);

        // Thêm GridPane và các ButtonType vào dialog
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Đặt hành vi cho nút OK được click
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return textField1.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            String word_explain = result.trim();
            if (word_explain.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("You should enter a word");
                alert.showAndWait();
            } else {
                note.addNote(word_target, word_explain);
                try {
                    note.exportToFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}