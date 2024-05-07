package org.example.demodictionary;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.controllers.Game;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {

    public SceneSwitch(AnchorPane currentAnchorPane, String fxmlOfNewScene) throws IOException {
        AnchorPane newAnchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlOfNewScene)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(newAnchorPane);
    }
}
