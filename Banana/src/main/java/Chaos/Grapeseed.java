package Chaos;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Grapeseed {

    public static void main(String[] externalInputs) {
        File scrabbleFile = new File("C:\\Users\\mashi\\Documents\\Scrabble.txt");

        String theAnswer = "satire";


        try {


            Scanner scanner = new Scanner(scrabbleFile);

            int numberOfWordsInDictionary = scanner.nextInt();
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            List<String> wordsInTheDictionary = new ArrayList<>();

            for (int i = 0; i < numberOfWordsInDictionary; i++) {
                wordsInTheDictionary.add(scanner.nextLine());
            }
            String myHand = scanner.nextLine();

            List<String> usableWords = getUsuableWords(wordsInTheDictionary, myHand);

            String myAnswer = getTheAnswer(usableWords);

            System.out.println(myAnswer.equals(theAnswer));


        } catch (FileNotFoundException exception) {

        }
    }

    public static String getTheAnswer(List<String> usableWords) {
        return getHighestWordValue(usableWords);
    }

    public static int getLetterValue(String letter) {
        switch (letter) {
            case "q", "z":
                return 10;

            case "j", "x":
                return 8;

            case "k":
                return 5;

            case "f", "h", "v", "w", "y":
                return 4;

            case "b", "c", "m", "p":
                return 3;

            case "d", "g":
                return 2;

            default:
                return 1;
        }
    }

    public static int getWordValue(String word) {
        int wordValueTotal = 0;
        String[] splitword = word.split("");
        for (String letter : splitword) {
            wordValueTotal += getLetterValue(letter);
        }
        return wordValueTotal;
    }

    public static String getHighestWordValue(List<String> dictionaryOfWords) {
        String lastWord = "";

        for (String dictionaryOfWord : dictionaryOfWords) {
            if (getWordValue(dictionaryOfWord) > getWordValue(lastWord)) {
                lastWord = dictionaryOfWord;
            }
        }
        return lastWord;
    }

    public static List<String> getUsuableWords(List<String> listOfDictionaryWords, String myHand) {
        List<String> ourUsableWords = new ArrayList<>();
        for (String dictionaryWord : listOfDictionaryWords) {
            if (doWeHaveAllTheLetters(dictionaryWord, myHand)) {
                ourUsableWords.add(dictionaryWord);
            }

        }
        return ourUsableWords;
    }

    private static boolean compareAgainstHand(String dictionaryWord, String myHand) {
        String[] wordLetters = dictionaryWord.split("");
        for (String letterOfWord : wordLetters) {
            if (!compareLetterAgainstHand(letterOfWord, myHand)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareLetterAgainstHand(String letterToCompare, String myHand) {
        String[] setOfTiles = myHand.split("");
        for (String tile : setOfTiles) {
            if (tile.contains(letterToCompare)) {
                return true;
            }
        }
        return false;
    }

    private static boolean doWeHaveAllTheLetters(String dictionaryWord, String myHand) {
        List<String> tilesLeft = new ArrayList<>(Arrays.asList(myHand.split("")));
        String[] lettersInDictionaryWord = dictionaryWord.split("");
        for (String letter : lettersInDictionaryWord) {
          if (tilesLeft.contains(letter)){
              tilesLeft.remove(letter);
          }else {
              return false;
          }

        }
        return true;
    }
}
// letter value
// word value
// filtering system for usable words
// words that are too long yeet

















//        int[] intArray = new int[4];
//
//        intArray[0] = 12;
//        intArray[1] = 15;
//        intArray[2] = 0;
//        intArray[3] = 55;
//
//
//        FizzBuzz runfizzbuzz = new FizzBuzz();
//        FizzBuzz.isItDivisible(intArray);
//
//
//
//
//
//
//        //Get two numbers from the user
//        //Choose a math type
//        //ADD, SUBTRACT, MULTIPLY, DIVIDE
//        //Show the user the process and outcome
////        switchStatement("-");
////        getInputFromUser();
////        whatMathToDo(userMath);
  // }
//
//    private static void switchStatement(String number) {
//
//
//
//
//
//        System.out.println(answer);
//    }
//
//
//    static int firstNumberFromUser;
//    static int secondNumberFromUser;
//    static String add = "+";
//    static String subtract = "-";
//    static String multiply = "*";
//    static String divide = "/";
//    static int answer;
//    static String userMath;
//
//
//    private static void getInputFromUser() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter Equation");
//
//
//
//         userMath = scanner.nextLine();
//        System.out.println(userMath);
//        firstNumberFromUser = scanner.nextInt();
//        System.out.println(firstNumberFromUser);
//
//
//        secondNumberFromUser = scanner.nextInt();
//
//        System.out.println(secondNumberFromUser);
//
//
//
//
//    }
//
//    private static void whatMathToDo(String addSubtractMultiplyDivide) {
//System.out.println(userMath);
//        System.out.println(add);
//            if (userMath.equals(add)) {
//            answer = firstNumberFromUser + secondNumberFromUser;
//            }
//            if (userMath.equals(subtract)) {
//                answer = firstNumberFromUser - secondNumberFromUser;
//            }
//            if (userMath.equals(multiply)) {
//                answer = firstNumberFromUser * secondNumberFromUser;
//            }
//            if (userMath.equals(divide)) {
//                answer = firstNumberFromUser / secondNumberFromUser;
//            }
//            else {
//                answer = 70;
//            }
//        System.out.println("Answer is: " + answer);
//        }
//
//
//
//    private static void doTheMath() {
//
//    }
//
//    private static void showAnswer() {
//            System.out.println(answer);
//    }
//
//
//
//
//
//
//
//
//
//
//
//}