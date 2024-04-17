package org.example.demodic;

import java.io.*;
import java.util.*;

public class DictionaryManagement extends MyDictionary {

    public DictionaryManagement() throws IOException {
    }

    public void insertFromCommandline() throws IOException {
        int numOfWords;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of words to insert: ");
        numOfWords = scanner.nextInt();
        scanner.nextLine();// Access the dictionary from the parent class
        for (int i = 0; i < numOfWords; i++) {
            Word newWord = new Word();

            System.out.print("Enter the English word: ");
            newWord.setWord_target(scanner.nextLine());

            System.out.print("Enter the Vietnamese meaning: ");
            newWord.setWord_explain(scanner.nextLine());

            super.addWordToDictionary(newWord.getWord_target(), newWord); // Add new Word to the dictionary
        }
    }

    public void insertFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(MyDictionary.filePathDictionaries))) {
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
                if (!word_target.isEmpty() && !word_explain.isEmpty()) { // Ensure there are at least two parts for word and explanation
                    Word newWord = new Word(word_target, word_explain);
                    super.addWordToDictionary(word_target, newWord); // Add new Word to the dictionary
                }
            }
            br.close();
        }
    }

    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the English word to look up: ");
        String word_target = sc.nextLine().toLowerCase().trim();

        Word word = super.getDictionary().get(word_target);
        if (word != null) {
            System.out.println(word.getWord_explain());
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }

    public void fixWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the English word to edit: ");
        String word_target = scanner.nextLine().toLowerCase().trim();

        // Check if the word exists
        if (super.getDictionary().containsKey(word_target)) {
            System.out.print("Enter the new Vietnamese meaning: ");
            String word_explain = scanner.nextLine().trim();

            // Update the word's meaning
            super.getDictionary().get(word_target).setWord_explain(word_explain);
            System.out.println("Word updated successfully.");
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }

    public void removeWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the English word to delete: ");
        String word_target = scanner.nextLine().toLowerCase().trim();

        // Remove the word if it exists
        if (super.getDictionary().remove(word_target) != null) {
            System.out.println("Word removed successfully.");
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }

    public void dictionaryExportToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MyDictionary.filePathE_V))) {
            String newContent = "";
            TreeMap<String, Word> dictionary = super.getDictionary();
            Set<String> keyWT = dictionary.keySet();
            for (String word_target : keyWT) {
                Word word = dictionary.get(word_target);
                newContent = newContent + word.getWord_target() + "\t"
                        + word.getWord_explain() + "\n";
            }
            bw.write(newContent);
            bw.close();
        }
    }
    public void saveWordsToFile() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the English word to look up & save: ");
        String word_target = sc.nextLine().toLowerCase().trim();

        Word word = super.getDictionary().get(word_target);
        if (word == null) {
            System.out.println("Word not found in the dictionary.");
            return;  // Exit the method if no word is found
        }

        System.out.println(word.getWord_explain());

        // Prepare the content to write to the file
        String newContent = word.getWord_target() + "\t" + word.getWord_explain() + "\n";

        // Write to the file using try-with-resources to ensure the writer is closed after the write operation
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePathSaveWord, true))) { // true to append, false or omit for overwrite
            bw.write(newContent);
            System.out.println("Word saved successfully in saveWord.txt.");
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + e.getMessage());
            throw e; // Rethrow the exception after logging or handling it
        }
    }

}
