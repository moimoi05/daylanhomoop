package org.example.daylanhomoop_daylaela;

import java.io.IOException;
import java.util.Set;

public class DictionaryCommandLine extends DictionaryManagement {
    public void showAllWords() {
        System.out.println("No / English / Vietnamese");
        Set<String> wordKey = super.getWordList().keySet();
        int count = 0;
        for (String key : wordKey) {
            ++count;
            System.out.println(count + " / " + key + " / " + super.getWordList().get(key).getWord_explain());
        }
    }

    public void dictionaryBasic() {
        super.insertFromCommandline();
        showAllWords();
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandLine dcl = new DictionaryCommandLine();
        dcl.dictionaryBasic();
        dcl.addWordToDictionary("On", new Word("On", "Trên"));
        System.out.println("-------------------");
        dcl.showAllWords();
        System.out.println("-------------------");
        dcl.insertFromFile();
        dcl.showAllWords();
    }
}

