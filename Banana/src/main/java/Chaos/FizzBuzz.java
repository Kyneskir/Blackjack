package Chaos;

public class FizzBuzz {



    public static void isItDivisible(int[] userNumber){

        for(int loopIterationCount = 0; loopIterationCount < userNumber.length; loopIterationCount ++) {

            if (userNumber[loopIterationCount] == 0) {
                System.out.println("Fuck you");
            } else if (userNumber[loopIterationCount] % 3 == 0 && userNumber[loopIterationCount] % 5 == 0) {
                System.out.println("Fizzbuzz");
            } else if (userNumber[loopIterationCount] % 3 == 0) {
                System.out.println("Fizz");
            } else if (userNumber[loopIterationCount] % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println("Unavailable");

            }
        }

    }




}
