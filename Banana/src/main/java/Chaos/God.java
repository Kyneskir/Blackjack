package Chaos;

public class God {

    private God() {}
    public static int howManyGods = 67;

    public static int nameTakenInVain = 0;

    public static void takeNameInVain() {
        nameTakenInVain = nameTakenInVain + 1;
    }
    public static int howManyTimesHavePeopleBeenDicks() {
        return nameTakenInVain;
    }
}
