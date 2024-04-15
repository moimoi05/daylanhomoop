package org.example.demodic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DictionaryCommandLine extends DictionaryManagement {

    public void showAllWords() throws IOException {
        System.out.printf("%-6s | %-15s | %-20s \n", "NO", "ENGLISH", "VIETNAMESE");
        int i = 1;
        for (Map.Entry<String, Word> entry : super.getDictionary().entrySet()) {
            System.out.printf("%-6d | %-15s | %-20s \n", i++, entry.getValue().getWord_target(),
                    entry.getValue().getWord_explain());
        }
    }

    public void dictionaryBasic() throws IOException {
        super.insertFromCommandline();
        showAllWords();
    }

    public void dictionarySearcher(String target) throws IOException {
        Path path = Path.of("D:\\BTapJava\\OOP\\OOPELA\\daylanhomoop\\demoDIC\\src\\main\\java\\org\\example\\demodic\\E_V.txt");
        List<String> data_list = Files.readAllLines(path);
        boolean check = false;
        System.out.format("%-15s %-15s \n", "ENGLISH", "VIETNAMESE");
        for (String data : data_list) {
            String word_target = "";
            String meaning = "";
            for (int i = 0; i < data.length(); i++) {
                if (data.charAt(i) == ' ') {
                    word_target = data.substring(0, i).trim();
                    meaning = data.substring(i + 1).trim();
                    break;
                }
            }
            if (word_target.contains(target)) {
                System.out.format("%-15s %-15s \n", word_target, meaning);
                check = true;
            }
        }
        if (!check) {
            System.out.println("My dictionary doesn't have this word :(");
        }
    }


    public void dictionaryAdvance() throws IOException, URISyntaxException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to my app!");
        System.out.println("[0]\tExit");
        System.out.println("[1]\tAdd");
        System.out.println("[2]\tRemove");
        System.out.println("[3]\tUpdate");
        System.out.println("[4]\tDisplay");
        System.out.println("[5]\tLookup");
        System.out.println("[6]\tSearch");
        System.out.println("[7]\tGame");
        System.out.println("[8]\tImport from file");
        System.out.println("[9]\tExport to file");
        while (true) {
            System.out.print("Your action: ");
            int action = sc.nextInt();
            sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            switch (action) {
                case 0:
                    System.out.println("See you next time!");
                    return;
                case 1:
                    super.insertFromCommandline();
                    break;
                case 2:
                    super.removeWord();
                    break;
                case 3:
                    super.fixWord();
                    break;
                case 4:
                    showAllWords();
                    break;
                case 5:
                    super.dictionaryLookup();
                    break;
                case 6:
                    System.out.println("Enter the word you want to search: ");
                    String target = sc.nextLine().trim();
                    dictionarySearcher(target);
                    break;
                case 7:
                    System.out.println("Coming soon . . .");
                    break;
                case 8:
                    super.insertFromFile();
                    break;
                case 9:
                    super.dictionaryExportToFile();
                    System.out.println("Successfully exported to file 'E_V.txt'!");
                    break;
                default:
                    System.out.println("Action not supported!");
            }
        }
    }

    public static void main(String[] args) {
        try {
            DictionaryCommandLine dic = new DictionaryCommandLine();
            dic.dictionaryAdvance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
