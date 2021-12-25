import java.util.Comparator;

public class CustomComparatorQ implements Comparator<Process> {
    /**
     * Comparator that evaluates proccesses based on their priority (pr).
     * 
     * @param a reference to a Process object.
     * @param b reference to a Process object.
     * @return negative, zero, or positive integer indicating that a.pr is less
     *         than, equal to, or greater than b.pr.
     */
    public int compare(Process a, Process b) {
        if (a.pr < b.pr) {
            return -1;
        } else if (a.pr == b.pr) {
            return 0;
        } else {
            return 1;
        }
    }
}