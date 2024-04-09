package org.example.demodic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Dictionary {
    private TreeMap<String, Word> dictionary = new TreeMap<>();

    // Constructor no longer throws IOException
    public Dictionary() {
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

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                if (data.length >= 2) {
                    dictionary.put(data[0], new Word(data[0], data[1]));
                }
            }
        }
    }

    // Other methods...
    public void addWordToDictionary(String word_target, Word word) throws IOException {
        dictionary.put(word_target, word);
    }
}

