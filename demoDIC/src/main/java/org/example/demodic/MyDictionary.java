package org.example.demodic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.TreeMap;

public class MyDictionary {
    private TreeMap<String, Word> dictionary = new TreeMap<>();
    public static final String filePathE_V = "D:\\gitproject\\daylanhomoop\\demoDIC\\data\\E_V.txt";
    public static final String filePathDictionaries = "D:\\gitproject\\daylanhomoop\\demoDIC\\src\\main\\java\\org\\example\\demodic\\dictionaries.txt";

    // Constructor no longer throws IOException
    public MyDictionary() throws IOException {
        Path path = Path.of(filePathE_V);
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            String target = "";
            String explain = "";
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ' ') {
                    target = line.substring(0, i).trim();
                    explain = line.substring(i + 1).trim();
                    break;
                }
            }
            this.dictionary.put(target, new Word(target, explain));
        }
    }

    public TreeMap<String, Word> getDictionary() {
        return dictionary;
    }

    public void setDictionary(TreeMap<String, Word> dictionary) {
        this.dictionary = dictionary;
    }

//    // Load dictionary from file
//    public void loadDictionaryFromFile(String filePath) throws IOException {
//        try (FileReader fr = new FileReader(filePath);
//             BufferedReader br = new BufferedReader(fr)) {
//
//            String myString;
//            while ((myString = br.readLine()) != null) {
//                int isCharacterOfWordExplain = -1;
//                String word_target = "";
//                String word_explain = "";
//                for (int index = 0; index < myString.length(); ++index) {
//                    if (myString.charAt(index) == ' ') {
//                        if (isCharacterOfWordExplain == -1) {
//                            isCharacterOfWordExplain++;
//                        }
//                        if (isCharacterOfWordExplain == 1) {
//                            word_explain = word_explain + myString.charAt(index);
//                        }
//                    } else {
//                        if (isCharacterOfWordExplain == -1) {
//                            word_target = word_target + myString.charAt(index);
//                        } else if (isCharacterOfWordExplain == 0) {
//                            isCharacterOfWordExplain++;
//                            word_explain = word_explain + myString.charAt(index);
//                        } else {
//                            word_explain = word_explain + myString.charAt(index);
//                        }
//                    }
//                }
//                if (!word_target.equals("") && !word_explain.equals("")) { // Ensure there are at least two parts for word and explanation
//                    Word newWord = new Word(word_target, word_explain);
//                    dictionary.put(word_target, newWord); // Add new Word to the dictionary
//                }
//            }
//            br.close();
//        }
//    }

    // Other methods...
    public void addWordToDictionary(String word_target, Word word) throws IOException {
        dictionary.put(word_target, word);
    }
}

