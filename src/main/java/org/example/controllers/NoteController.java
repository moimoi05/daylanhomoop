package org.example.controllers;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;
import org.example.demodictionary.Note;
import org.example.demodictionary.Word;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteController extends Note {

    @FXML
    private AnchorPane rootPane = new AnchorPane();

    @FXML
    private Button addButton;

    @FXML
    private ListView<String> listNote = new ListView<>();

    @FXML
    private TextField searchBox = new TextField();

    private List<String> list = new ArrayList<>();

    public NoteController() throws IOException {
    }

    @FXML
    public void onSearchBox(KeyEvent keyEvent) {
        String input = searchBox.getText();
        if (input.isEmpty()) {
            listNote.getItems().clear();
            listNote.getItems().addAll(super.getNotedWords().keySet());
        }
        List<String> result = list.stream().filter(word -> word.startsWith(input)).toList();
        listNote.getItems().clear();
        listNote.getItems().addAll(result);
    }

    @FXML
    public void initialize() throws IOException {
        for (Map.Entry<String, Word> entry : super.getNotedWords().entrySet()) {
            list.add(entry.getKey() + ": " + entry.getValue().getWord_explain());
        }
        listNote.getItems().addAll(list);

        // tránh null pointer exception
        Platform.runLater(() -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setOnCloseRequest(this::handleCloseRequest);
        });
    }

    private void handleCloseRequest(WindowEvent event)  {
        try {
            super.exportToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onAddButton() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Word");
        dialog.setHeaderText("Enter the word you want to add:");

        TextField textField1 = new TextField();
        textField1.setPromptText("English word");
        TextField textField2 = new TextField();
        textField2.setPromptText("Definition");

        // Layout GridPane
        GridPane grid = new GridPane();
        grid.add(textField1, 0, 0);
        grid.add(textField2, 0, 1);
        grid.setVgap(10);

        // Thêm GridPane và các ButtonType vào dialog
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Đặt hành vi cho nút OK được click
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(textField1.getText(), textField2.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            String word_target = result.getKey().trim();
            String word_explain = result.getValue().trim();
            if (super.addNote(word_target, word_explain) == 1) {
                list.add(word_target + ": " + word_explain);
                listNote.getItems().add(word_target + ": " + word_explain);
            } else if (super.addNote(word_target, word_explain) == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Word is already exists");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("You should enter a word");
                alert.showAndWait();
            }
        });
    }

    @FXML
    public void onRemoveButton() throws IOException {
        String selectedWord = listNote.getSelectionModel().getSelectedItem();
        if (selectedWord == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Do you want to delete?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            listNote.getItems().remove(selectedWord);
            list.remove(selectedWord);
            String[] parts = selectedWord.split(": ");
            String word_target = parts[0].trim();
            super.removeNote(word_target);
        }
    }

    @FXML
    public void onFixButton() {
        String selectedWord = listNote.getSelectionModel().getSelectedItem();
        if (selectedWord == null) {
            return;
        }
        String[] parts = selectedWord.split(": ");
        String word_target = parts[0].trim();

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Fix Word");
        dialog.setHeaderText("Enter the new meaning:");
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
                super.fixNote(word_target, word_explain);
                listNote.getItems().add(word_target + ": " + word_explain);
                list.add(word_target + ": " + word_explain);
                listNote.getItems().remove(selectedWord);
                list.remove(selectedWord);
            }
        });
    }
}
