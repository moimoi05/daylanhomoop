package org.example.demodictionary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Note {
    private static final String pathNote = "D:\\javacode\\demoDictionary\\data\\FavoriteEnglishWords.txt";

    private Map<String, Word> notedWords = new HashMap<>();

    public Note() throws IOException {
        Path path = Path.of(pathNote);
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            String target = "";
            String explain = "";
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '\t' || line.charAt(i) == ' ') {
                    target = line.substring(0, i).trim();
                    explain = line.substring(i + 1).trim();
                    break;
                }
            }
            this.notedWords.put(target, new Word(target, explain));
        }
    }

    public int addNote(String target, String explain) {
        if (target.isEmpty() || explain.isEmpty()) {
            return -1;
        } else if (this.notedWords.containsKey(target)) {
            return 0;
        } else {
            this.notedWords.put(target, new Word(target, explain));
            return 1;
        }
    }

    public void removeNote(String target) {
        this.notedWords.remove(target);
    }

    public void fixNote(String target, String explain) {
            this.notedWords.get(target).setWord_explain(explain);
    }

    public void exportToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pathNote));
        String newContent = "";
        TreeMap<String, Word> tmp = new TreeMap<>(this.notedWords);
        Set<String> keyWT = tmp.keySet();
        for (String word_target : keyWT) {
            Word word = tmp.get(word_target);
            newContent = newContent + word.getWord_target() + "\t"
                    + word.getWord_explain() + "\n";
        }
        bw.write(newContent);
        bw.close();
    }


    public Map<String, Word> getNotedWords() {
        return notedWords;
    }
}
