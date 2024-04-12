package org.example.daylanhomoop_daylaela;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    public int changeStringToInt(String string) {
        int result = 0;
        for (int i=0 ; i<string.length() ; ++i) {
            result = result * 10;
            result += (string.charAt(i) - 48);
        }
        return result;
    }

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int numOfWords = changeStringToInt(sc.nextLine());
        for (int i=1 ; i<=numOfWords ; ++i) {
            String wordE = sc.nextLine();
            String wordV = sc.nextLine();
            super.addWordToDictionary(wordE, new Word(wordE, wordV));
        }
    }

    public void insertFromFile() throws IOException {
        BufferedReader br = new BufferedReader
                (new FileReader("D:\\BTapJava\\OOP\\daylanhomOOP_daylaELA\\src\\main\\java\\org\\example\\daylanhomoop_daylaela\\dictionaries.txt"));
        String myString;
        while ((myString = br.readLine()) != null) {
            int isCharacterOfWordExplain = -1;
            String word_target = "";
            String word_explain = "";
            for (int index=0 ; index<myString.length() ; ++index) {
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
            super.addWordToDictionary(word_target, new Word(word_target, word_explain));
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        DictionaryManagement d = new DictionaryManagement();
        d.insertFromFile();
    }
}

