package org.example.demodic;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException, IOException {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile();
        dictionaryManagement.dictionaryLookup();
    }
}
