package gamelogic;

/**
 * Counter
 * keep track on the number of existing obj.
 * @author itamar shachenc tov
 * @since 2021-04-28
 * id: 207497769
 */
public class Counter {
    private int numOf;

    /**
     * add number to current count.
     *
     * @param number we want to add
     */
    void increase(int number) {
        numOf += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number we want to subtract.
     */
    void decrease(int number) {
        numOf -= number;
    }

    /**
     * get current count.
     *
     * @return the current num
     */
    int getValue() {
        return numOf;
    }
}