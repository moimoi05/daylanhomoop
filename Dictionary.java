package org.example.daylanhomoop_daylaela;

import java.util.TreeMap;

public class Dictionary {
    private TreeMap<String, Word> dictionary = new TreeMap<String, Word>();

    public Dictionary() {
    }

    public void addWordToDictionary(String wordKey, Word word) {
        this.dictionary.put(wordKey, word);
    }

    public TreeMap<String, Word> getWordList() {
        return dictionary;
    }

    public void setWordList(TreeMap<String, Word> dictionary) {
        this.dictionary = dictionary;
    }
}
