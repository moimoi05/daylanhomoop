package org.example.demodic;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DictionaryManagement extends Dictionary {
    public void insertFromCommandline() throws IOException {
        int numOfWords;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of words to insert: ");
        numOfWords = scanner.nextInt();
        scanner.nextLine();
        TreeMap<String, Word> dictionary = super.getDictionary(); // Access the dictionary from the parent class
        for (int i = 0; i < numOfWords; i++) {
            Word newWord = new Word();

            System.out.print("Enter the English word: ");
            newWord.setWord_target(scanner.nextLine());

            System.out.print("Enter the Vietnamese meaning: ");
            newWord.setWord_explain(scanner.nextLine());

            dictionary.put(newWord.getWord_target(), newWord); // Add new Word to the dictionary
        }
    }
    public void insertFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"))) {
            TreeMap<String, Word> dictionary = super.getDictionary();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                if (data.length >= 2) { // Ensure there are at least two parts for word and explanation
                    Word newWord = new Word(data[0], data[1]);
                    dictionary.put(data[0], newWord); // Add new Word to the dictionary
                }
            }
        }
    }
    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the English word to look up: ");
        String word_target = sc.nextLine().trim();

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
        String word_target = scanner.nextLine().trim();

        // Check if the word exists
        if (getDictionary().containsKey(word_target)) {
            System.out.print("Enter the new Vietnamese meaning: ");
            String word_explain = scanner.nextLine().trim();

            // Update the word's meaning
            getDictionary().get(word_target).setWord_explain(word_explain);
            System.out.println("Word updated successfully.");
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }
    public void removeWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the English word to delete: ");
        String word_target = scanner.nextLine().trim();

        // Remove the word if it exists
        if (getDictionary().remove(word_target) != null) {
            System.out.println("Word removed successfully.");
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }
}



