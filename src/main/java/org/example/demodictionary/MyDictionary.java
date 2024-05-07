package org.example.demodictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.TreeMap;

public class MyDictionary {
    private TreeMap<String, Word> dictionary = new TreeMap<>();
    public static final String filePathE_V = "D:\\javacode\\demoDictionary\\data\\E_V.txt";
    public static final String filePathDictionaries = "D:\\gitproject\\daylanhomoop\\demoDIC\\src\\main\\java\\org\\example\\demodic\\dictionaries.txt";

    // Constructor no longer throws IOException
    public MyDictionary() throws IOException {
        try{
            BufferedReader rd = new BufferedReader(new FileReader(filePathE_V));
            String line;
            while((line = rd.readLine()) != null){
                String[] parts = line.split("<html>");
                String word_target = parts[0];
                String word_explain = "<html>" + parts[1];
                addWordToDictionary(word_target, new Word(word_target, word_explain));
            }
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


    // Other methods...
    public void addWordToDictionary(String word_target, Word word) throws IOException {
        dictionary.put(word_target, word);
    }
}

