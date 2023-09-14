package Calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculate {

    List<Double> numbers = new ArrayList<>();
    List<String> operators = new ArrayList<>();

    double a;

    public static void main(String[] defaultBS) {
        Calculate calculator = new Calculate();
        String equation = ReadFromFile.getRawEquation();
        StringBuilder equationBuilder = new StringBuilder();
        calculator.solve(equation, equationBuilder);

        //WriteToFile.writeToFile(Double.toString(answer));
        //System.out.println(answer);
    }

    public void solve(String equation, StringBuilder equationBuilder) {

        solveParenthesis(equation.contains("("), equationBuilder);
        doMath(equationBuilder.toString());

    }

    public void solveParenthesis(boolean containsParenthesis, StringBuilder equationBuilder) {
        while (containsParenthesis) {
            String whileEquation = equationBuilder.toString();
            String insideParenthesisEquation = getOriginalEquationAndClearBuilder(equationBuilder);
            double parenthesisAnswer = doMath(insideParenthesisEquation);
            equationBuilder.append(whileEquation.replace("( " + insideParenthesisEquation + " )", Double.toString(parenthesisAnswer)));
            containsParenthesis = equationBuilder.toString().contains("(");
        }
    }

        private String getOriginalEquationAndClearBuilder(StringBuilder equationBuilder) {

        String equationFromStringBuilder = equationBuilder.toString();
        parenthesisMath(equationFromStringBuilder);
        equationBuilder.setLength(0);
        return equationFromStringBuilder;

    }

    public void understandEquation(String equation) {

        clearLists();
        fillLists(equation.split(" "));
    }
    private void fillLists(String[] equationPieces) {

        for (int i = 0; i < equationPieces.length; i++) {
            try {
                numbers.add(getNumber(equationPieces[i]));
            } catch (NumberFormatException exception) {
                operators.add(equationPieces[i]);
            }
        }
    }
    private void clearLists() {

        numbers.clear();
        operators.clear();

    }

    private String doubleToString(double number) {
        return Double.toString(number);

    }

    // handle pemdas
    private String makeNewEquation() {
        StringBuilder newEquation = new StringBuilder();
        return sortNumberAndOperatorIntoEquation(newEquation).toString().trim();

    }

    private StringBuilder sortNumberAndOperatorIntoEquation(StringBuilder newEquation) {
        for (int i = 0; i < numbers.size(); i++) {
            appendNumber(newEquation, numbers.get(i));
            try {
                appendOperator(newEquation, operators.get(i));
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
       return newEquation;
    }

    private StringBuilder appendNumber(StringBuilder equation, double number) {
        return appendToEquation(equation, Double.toString(number));

    }

    private StringBuilder appendOperator(StringBuilder equation, String operator) {
        return appendToEquation(equation, operator);

    }

    private StringBuilder appendToEquation(StringBuilder equation, Object whatToAppend) {
        equation.append(whatToAppend);
        equation.append(" ");
        return equation;
    }

    public double performOperation(String operator, double firstNumber, double secondNumber) {

        switch (operator) {
            case "+": return firstNumber + secondNumber;
            case "-": return firstNumber - secondNumber;
            case "*": return firstNumber * secondNumber;
            case "/": return firstNumber / secondNumber;
            case "^": return exponentMath(firstNumber, secondNumber);
            default: throw new NumberFormatException();
        }
    }

    private double exponentMath(double firstNumber, double secondNumber){
        double tempNumber = 1;
        for (int i = 0; i < secondNumber; i++) {
            tempNumber *= firstNumber;
        }
        return tempNumber;
    }

    private String parenthesisMath(String equation){

        int indexOfOpener = equation.indexOf("(");
        int indexOfCloser = equation.indexOf(")");
        if (indexOfOpener > -1) {
            String newEquation = equation.substring(indexOfOpener + 1,indexOfCloser).trim();
            int indexOfOtherParenthesis = newEquation.indexOf("(");

            StringBuilder equationBuilder = new StringBuilder();
            equationBuilder.append(newEquation);

            while (indexOfOtherParenthesis > -1) {
                String whileEquation = equationBuilder.toString();
                equationBuilder.setLength(0);
                equationBuilder.append(whileEquation.substring(indexOfOtherParenthesis + 1).trim());
                indexOfOtherParenthesis = equationBuilder.indexOf("(");
            }

            return equationBuilder.toString();
        }
        return equation;
    }

    private double doMath(String equation) {
        double answer = 0;
        understandEquation(equation);
        for (int i = 0; operators.size() > 0; i++) {
            int pemdasIndex = getPemdasIndex();
            if (pemdasIndex > -1) {
                answer = performOperation(operators.get(pemdasIndex), numbers.get(pemdasIndex), numbers.get(pemdasIndex + 1));
                operators.remove(pemdasIndex);
                numbers.remove(pemdasIndex);
                numbers.remove(pemdasIndex);
                numbers.add(pemdasIndex, answer);
                understandEquation(makeNewEquation());
            }
        }
        dropLastDigit(answer);
        return answer;
    }

    private double getNumber(String equationPieces) {
        if (equationPieces.equals("a")) {
            return Double.parseDouble(ReadFromFile.getAnswer());
        } else {
            return Double.parseDouble(equationPieces);
        }
    }

    private int getPemdasIndex() {
        if (operators.contains("^")) {
            return operators.indexOf("^");

        } else if (operators.contains("*")) {
            return operators.indexOf("*");
        } else if (operators.contains("/")) {
            return operators.indexOf("/");

        } else if (operators.contains("+")) {
            return operators.indexOf("+");

        } else if (operators.contains("-")) {
            return operators.indexOf("-");
        }
        return -1;
    }

    private double dropLastDigit(double answer) {
        String answerAsString = Double.toString(answer);
        int decimalIndex = answerAsString.indexOf(".");
        int charactersAfterDecimal = answerAsString.split("\\.")[1].length();
        return dropLastCharacterIfDesired(decimalIndex, charactersAfterDecimal, answerAsString);

    }

    private double dropLastCharacterIfDesired(int decimalIndex, int charactersAfterDecimal, String answerAsString) {
        if (decimalIndex >= 0 & charactersAfterDecimal > 5) {
            return Double.parseDouble(dropLastCharacterFromString(answerAsString));
        }
        return Double.parseDouble(answerAsString);
    }

    private String dropLastCharacterFromString(String string) {
        return string.substring(0, string.length() - 1);
    }

}



