package org.example.demodic;

import java.io.IOException;
import java.net.URISyntaxException;
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

    public List<String> dictionarySearcher(String target) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Word> entry : super.getDictionary().entrySet()) {
            if (entry.getValue().getWord_target().contains(target)) {
                list.add(entry.getValue().getWord_target());
            }
        }
        return list;
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
            System.out.println("Your action: ");
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
