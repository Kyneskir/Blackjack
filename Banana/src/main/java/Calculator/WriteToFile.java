package Calculator;

import java.io.*;

public class WriteToFile {

    public static void writeToFile(String answer) {
        File answerFile = new File("C:\\Users\\mashi\\Documents\\Answer.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(answerFile));
            writer.write(answer);
            writer.flush();
            writer.close();
        }catch (IOException ignored) { }
    }

}
