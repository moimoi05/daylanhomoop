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
    public static final String filePathE_V = "D:\\GITHUB\\daylanhomoop\\demoDIC\\data\\E_V.txt";
    public static final String filePathDictionaries = "D:\\GITHUB\\daylanhomoop\\demoDIC\\data\\dictionaries.txt";
    public static final String filePathSaveWord = "D:\\GITHUB\\daylanhomoop\\demoDIC\\data\\saveWord.txt";
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
                if (line.charAt(i) == '\t') {
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

    // Other methods...
    public void addWordToDictionary(String word_target, Word word) throws IOException {
        dictionary.put(word_target, word);
    }
}

