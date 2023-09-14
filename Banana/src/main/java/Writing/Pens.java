package Writing;


public class Pens {

    public static void main(String[] asdfa) {
        Pens pen = new Pens("red",4);
        pen.write("red", "red", "red", "red", "white", "purple", "blue");

    }

    public Pens(String color, int inkRemaining) {
        inkColor = color;
    }

    String inkColor;
    int inkRemaining;


    String write(String... whatYoureGoingToWrite) {
        for (int i = 0; i < whatYoureGoingToWrite.length; i++){
            System.out.println(whatYoureGoingToWrite[i]);
        }

        for (String individualString : whatYoureGoingToWrite){
            System.out.println(individualString);
        }

        if (whatYoureGoingToWrite.length != 0) {

        }

        try {
            System.out.println(whatYoureGoingToWrite[100]);
            System.out.println("Slow down");
        } catch (ArrayIndexOutOfBoundsException exceptionName) {
            System.out.println("You could do more");
        }



        return null;
    }




}
