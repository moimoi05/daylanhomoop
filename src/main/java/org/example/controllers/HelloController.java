package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.demodictionary.NewWindow;
import org.example.demodictionary.Note;
import org.example.demodictionary.SceneSwitch;

import java.io.File;
import java.io.IOException;

public class HelloController {
    private Note note = new Note();
    @FXML
    private Label welcomeText;

    public HelloController() throws IOException {
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    private Button addButton;

    @FXML
    private Button fixButton;

    @FXML
    private Button lookUpButton;

    @FXML
    private AnchorPane mainMenu;

    @FXML
    private Button removeButton;

    @FXML
    public void onLookUpButton() throws IOException {
        new SceneSwitch(mainMenu, "SearchController.fxml");
    }

    @FXML
    public void onNoteButton(ActionEvent event) {
        new NewWindow("note-view.fxml");
    }

    @FXML
    public void onGameButton() throws IOException {
        new SceneSwitch(mainMenu, "Game.fxml");
    }

    @FXML
    public void onTranslateButton() throws IOException {
        new SceneSwitch(mainMenu, "translate.fxml");
    }
}