package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import org.example.demodictionary.DictionaryManagement;
import org.example.demodictionary.SceneSwitch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends DictionaryManagement {
    public static final String quizEasyPath = "D:\\javacode\\demoDictionary\\data\\quesDataEasy.txt";
    public static final String quizMediPath = "D:\\javacode\\demoDictionary\\data\\quesDataMedium.txt";
    public static final String quizHardPath = "D:\\javacode\\demoDictionary\\data\\quesDataHard.txt";
    private List<String> quesE = new ArrayList<String>();
    private List<String> quesM = new ArrayList<String>();
    private List<String> quesH = new ArrayList<String>();
    private List<String> resE = new ArrayList<String>();
    private List<String> resM = new ArrayList<String>();
    private List<String> resH = new ArrayList<String>();
    private Random rand = new Random();
    private int timesRemain;
    private int idOfLevel;
    private int idOfQuestion;

    private Media media;

    private MediaPlayer player;

    @FXML
    private Text questionText;
    @FXML
    private Text abilityText;
    @FXML
    private Text resultText;
    @FXML
    private TextField textField;
    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;
    @FXML
    private AnchorPane gameView;

    public void addQuesFromPath(String pathS, int idOfLevel) throws IOException {
        Path path = Path.of(pathS);
        List<String> lines = Files.readAllLines(path);
        for (String myString : lines) {
            String question = "";
            String result = "";
            int indexOfMyString = 0;
            boolean isResult = false;
            while (indexOfMyString < myString.length()) {
                if (myString.charAt(indexOfMyString) == '#') {
                    isResult = true;
                    indexOfMyString++;
                    continue;
                }
                if (!isResult) {
                    question = question + myString.charAt(indexOfMyString);
                } else {
                    result = result + myString.charAt(indexOfMyString);
                }
                indexOfMyString++;
            }
            if (idOfLevel == 1)
            {
                quesE.add(question);
                resE.add(result);
            } else if (idOfLevel == 2) {
                quesM.add(question);
                resM.add(result);
            } else {
                quesH.add(question);
                resH.add(result);
            }
        }
    }

    public Game() throws IOException {
        addQuesFromPath(quizEasyPath, 1);
        addQuesFromPath(quizMediPath, 2);
        addQuesFromPath(quizHardPath, 3);
    }

    @FXML
    public void onAnswerButton() {
        String yourAnswer = textField.getText();
        if (timesRemain <= 0) return;
        if (idOfLevel == 1)
        {
            if (!yourAnswer.equals(resE.get(idOfQuestion))) {
                --timesRemain;
                if (timesRemain > 0) {
                    abilityText.setText("Không đúng! " + "Bạn còn : " + timesRemain + " lượt trả lời.");
                } else {
                    abilityText.setText("Không đúng! Bạn đã hết lượt trả lời!");
                    resultText.setText("Đáp án : " + resE.get(idOfQuestion) + "\n"
                            + "         " + super.getDictionary().get(resE.get(idOfQuestion)).getWord_explain());
                    return;
                }
            } else {
                abilityText.setText("Đúng rồi! Tuyệt vời!");
                resultText.setText("Đáp án : " + resE.get(idOfQuestion) + "\n"
                        + "         " + super.getDictionary().get(resE.get(idOfQuestion)).getWord_explain());
                timesRemain = 0;
                return;
            }
        } else if (idOfLevel == 2) {
            if (!yourAnswer.equals(resM.get(idOfQuestion))) {
                --timesRemain;
                if (timesRemain > 0) {
                    abilityText.setText("Không đúng! " + "Bạn còn : " + timesRemain + " lượt trả lời.");
                } else {
                    abilityText.setText("Không đúng! Bạn đã hết lượt trả lời!");
                    resultText.setText("Đáp án : " + resM.get(idOfQuestion) + "\n"
                            + "         " + super.getDictionary().get(resM.get(idOfQuestion)).getWord_explain());
                    return;
                }
            } else {
                abilityText.setText("Đúng rồi! Tuyệt vời!");
                resultText.setText("Đáp án : " + resM.get(idOfQuestion) + "\n"
                        + "         " + super.getDictionary().get(resM.get(idOfQuestion)).getWord_explain());
                timesRemain = 0;
                return;
            }
        } else {
            if (!yourAnswer.equals(resH.get(idOfQuestion))) {
                --timesRemain;
                if (timesRemain > 0) {
                    abilityText.setText("Không đúng! " + "Bạn còn : " + timesRemain + " lượt trả lời.");
                } else {
                    abilityText.setText("Không đúng! Bạn đã hết lượt trả lời!");
                    resultText.setText("Đáp án : " + resH.get(idOfQuestion) + "\n"
                            + "         " + super.getDictionary().get(resH.get(idOfQuestion)).getWord_explain());
                    return;
                }
            } else {
                abilityText.setText("Đúng rồi! Tuyệt vời!");
                resultText.setText("Đáp án : " + resH.get(idOfQuestion) + "\n"
                        + "         " + super.getDictionary().get(resH.get(idOfQuestion)).getWord_explain());
                timesRemain = 0;
                return;
            }
        }
    }

    @FXML
    public void momentOfEasyQuestion() {
        if (timesRemain > 0) return;
        timesRemain = 3;
        idOfLevel = 1;
        idOfQuestion = rand.nextInt(304) % 8;
        questionText.setText(quesE.get(idOfQuestion));
        abilityText.setText("Bạn còn : " + timesRemain + " lượt trả lời.");
        resultText.setText("/+/");
        easyButton.setStyle("-fx-background-color: blue;");
        mediumButton.setStyle("-fx-background-color: #c6e3ff;");
        hardButton.setStyle("-fx-background-color: #c6e3ff;");
    }

    @FXML
    public void momentOfMediumQuestion() {
        if (timesRemain > 0) return;
        timesRemain = 3;
        idOfLevel = 2;
        idOfQuestion = rand.nextInt(304) % 8;
        questionText.setText(quesM.get(idOfQuestion));
        abilityText.setText("Bạn còn : " + timesRemain + " lượt trả lời.");
        resultText.setText("/+/");
        mediumButton.setStyle("-fx-background-color: blue;");
        easyButton.setStyle("-fx-background-color: #c6e3ff;");
        hardButton.setStyle("-fx-background-color: #c6e3ff;");
    }

    @FXML
    public void momentOfHardQuestion() {
        if (timesRemain > 0) return;
        timesRemain = 3;
        idOfLevel = 3;
        idOfQuestion = rand.nextInt(304) % 4;
        questionText.setText(quesH.get(idOfQuestion));
        abilityText.setText("Bạn còn : " + timesRemain + " lượt trả lời.");
        resultText.setText("/+/");
        hardButton.setStyle("-fx-background-color: blue;");
        mediumButton.setStyle("-fx-background-color: #c6e3ff;");
        easyButton.setStyle("-fx-background-color: #c6e3ff;");
    }

    @FXML
    public void goBack() throws IOException {
        new SceneSwitch(gameView, "hello-view.fxml");
    }
}
