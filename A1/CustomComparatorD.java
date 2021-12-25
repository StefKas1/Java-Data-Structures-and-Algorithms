import java.util.Comparator;

public class CustomComparatorD implements Comparator<Process> {
    /**
     * Comparator that evaluates proccesses based on their arrivalTime.
     * 
     * @param a reference to a Process object.
     * @param b reference to a Process object.
     * @return negative, zero, or positive integer indicating that a.arrivalTime is
     *         less than, equal to, or greater than b.arrivalTime.
     */
    public int compare(Process a, Process b) {
        if (a.arrivalTime < b.arrivalTime) {
            return -1;
        } else if (a.arrivalTime == b.arrivalTime) {
            return 0;
        } else {
            return 1;
        }
    }
}