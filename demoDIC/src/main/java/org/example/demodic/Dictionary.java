package org.example.demodic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Dictionary {
    private TreeMap<String, Word> dictionary = new TreeMap<>();
    public static final String filePathE_V = "D:\\BTapJava\\OOP\\OOPELA\\daylanhomoop\\demoDIC\\src\\main\\java\\org\\example\\demodic\\E_V.txt";
    public static final String filePathDictionaries = "D:\\BTapJava\\OOP\\OOPELA\\daylanhomoop\\demoDIC\\src\\main\\java\\org\\example\\demodic\\dictionaries.txt";

    // Constructor no longer throws IOException
    public Dictionary() {
        try (FileReader fr = new FileReader(filePathE_V);
             BufferedReader br = new BufferedReader(fr)) {

            String myString;
            while ((myString = br.readLine()) != null) {
                int isCharacterOfWordExplain = -1;
                String word_target = "";
                String word_explain = "";
                for (int index = 0; index < myString.length(); ++index) {
                    if (myString.charAt(index) == ' ') {
                        if (isCharacterOfWordExplain == -1) {
                            isCharacterOfWordExplain++;
                        }
                        if (isCharacterOfWordExplain == 1) {
                            word_explain = word_explain + myString.charAt(index);
                        }
                    } else {
                        if (isCharacterOfWordExplain == -1) {
                            word_target = word_target + myString.charAt(index);
                        } else if (isCharacterOfWordExplain == 0) {
                            isCharacterOfWordExplain++;
                            word_explain = word_explain + myString.charAt(index);
                        } else {
                            word_explain = word_explain + myString.charAt(index);
                        }
                    }
                }
                if (!word_target.equals("") && !word_explain.equals("")) { // Ensure there are at least two parts for word and explanation
                    Word newWord = new Word(word_target, word_explain);
                    dictionary.put(word_target, newWord); // Add new Word to the dictionary
                }
            }
            br.close();
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TreeMap<String, Word> getDictionary() {
        return dictionary;
    }

    public void setDictionary(TreeMap<String, Word> dictionary) {
        this.dictionary = dictionary;
    }

    // Load dictionary from file
    public void loadDictionaryFromFile(String filePath) throws IOException {
        try (FileReader fr = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fr)) {

            String myString;
            while ((myString = br.readLine()) != null) {
                int isCharacterOfWordExplain = -1;
                String word_target = "";
                String word_explain = "";
                for (int index = 0; index < myString.length(); ++index) {
                    if (myString.charAt(index) == ' ') {
                        if (isCharacterOfWordExplain == -1) {
                            isCharacterOfWordExplain++;
                        }
                        if (isCharacterOfWordExplain == 1) {
                            word_explain = word_explain + myString.charAt(index);
                        }
                    } else {
                        if (isCharacterOfWordExplain == -1) {
                            word_target = word_target + myString.charAt(index);
                        } else if (isCharacterOfWordExplain == 0) {
                            isCharacterOfWordExplain++;
                            word_explain = word_explain + myString.charAt(index);
                        } else {
                            word_explain = word_explain + myString.charAt(index);
                        }
                    }
                }
                if (!word_target.equals("") && !word_explain.equals("")) { // Ensure there are at least two parts for word and explanation
                    Word newWord = new Word(word_target, word_explain);
                    dictionary.put(word_target, newWord); // Add new Word to the dictionary
                }
            }
            br.close();
        }
    }

    // Other methods...
    public void addWordToDictionary (String word_target, Word word) throws IOException {
        dictionary.put(word_target, word);
    }
}

