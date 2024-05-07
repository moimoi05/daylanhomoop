package org.example.demodictionary;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Scanner;

public class DictionaryCommandLine extends DictionaryManagement {
    public DictionaryCommandLine() throws IOException {
    }

    public void dictionaryBasic() throws IOException {
        super.insertFromCommandline();
        showAllWords();
    }


    public void dictionaryAdvance() throws IOException, URISyntaxException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to my app!");
        System.out.println("[0]\tExit");
        System.out.println("[1]\tAdd"); // ALERT
        System.out.println("[2]\tRemove"); // ALERT
        System.out.println("[3]\tUpdate"); // ALERT
        System.out.println("[4]\tDisplay"); // TRA TU
        System.out.println("[5]\tLookup"); // TRA TU
        System.out.println("[6]\tSearch"); // TRA TU
        System.out.println("[7]\tGame"); // GAME
        System.out.println("[8]\tImport from file"); // KHONG CAN
        System.out.println("[9]\tExport to file"); // KHONG CAN
        // TU YEU THICH
        // TU DA TRA
        while (true) {
            System.out.print("Your action: ");
            int action = sc.nextInt();
            sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            switch (action) {
                case 0:
                    System.out.println("Do you want to save before exit?");
                    System.out.print("Enter your choice [Y/N]: ");
                    String choice = sc.nextLine().trim();
                    if (choice.equals("Y")) {
                        super.dictionaryExportToFile();
                        System.out.println("Successfully saved!");
                    }
                    System.out.println("See You Next Time!");
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
                    System.out.println("Use 'dictionary.txt' to import.");
                    super.insertFromFile();
                    System.out.println("Successfully imported from file 'dictionary.txt'!");
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
