import java.util.Comparator;

public class CustomComparator implements Comparator<Integer> {
    /**
     * Comparator that evaluates integer objects based on their value.
     * 
     * @param a reference to an integer object.
     * @param b reference to an integer object.
     * @return negative, zero, or positive integer indicating that a is less than,
     *         equal to, or greater than b.
     */
    public int compare(Integer a, Integer b) {
        if (a < b) {
            return -1;
        } else if (a == b) {
            return 0;
        } else {
            return 1;
        }
    }
}