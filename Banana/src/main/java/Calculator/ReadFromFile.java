package Calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile {

    private static final File calculatorInput = new File("C:\\Users\\mashi\\Documents\\Calculator Input.txt");
    private static final File answer = new File("C:\\Users\\mashi\\Documents\\Answer.txt");
    public static String getRawEquation() {

        try {
            Scanner scanner = new Scanner(calculatorInput);
            return scanner.nextLine();

        } catch (FileNotFoundException exception) {}
        return "";


    }
    public static String getAnswer() {

        try {
            Scanner scanner = new Scanner(answer);
            return scanner.nextLine();

        } catch (FileNotFoundException exception) {
        }
        return "";
    }
}