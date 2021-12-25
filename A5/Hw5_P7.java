import java.util.Random;
import java.lang.System;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class Hw5_P7 {
    /**
     * Insertion sorts an array of integers into increasing order.
     * 
     * @param data as array of pseudo-random integers.
     * @return no return.
     */
    public static void insertionSort(int[] data) {
        int n = data.length;
        // Iterates over integer array, begins with second integer.
        for (int k = 1; k < n; k++) {
            // Sets current (cur) integer.
            int cur = data[k];
            // Sets index to cur integer.
            int j = k;
            // Slides data rightwards if true - to free up correct place for cur integer.
            while (j > 0 && data[j - 1] > cur) {
                data[j] = data[j - 1];
                j--;
            }
            // Puts cur integer in correct place.
            data[j] = cur;
        }
    }

    /**
     * Merges contents of arrays S1 and S2 into properly sized array S.
     * 
     * @param S1   as array of sorted pseudo-random integers.
     * @param S2   as array of sorted pseudo-random integers.
     * @param S    as array of pseudo-random integers.
     * @param comp as Comparator<Integer> object to impose total ordering on
     *             compared integers.
     * @return no return.
     */
    public static void merge(int[] S1, int[] S2, int[] S, Comparator<Integer> comp) {
        int i = 0, j = 0;
        // Takes first integer from S1 and S2, compares them and puts small integer in
        // array S, increments indices, and repeats until completely iterated over S1
        // and S2.
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0)) {
                // Copys ith element of S1 and increments i.
                S[i + j] = S1[i++];
            } else {
                // Copys jth element of S2 and increments j.
                S[i + j] = S2[j++];
            }
        }
    }

    /**
     * Merge sorts an array of integers into increasing order.
     * 
     * @param S    as array of pseudo-random integers to be sorted.
     * @param comp as Comparator<Integer> object to impose total ordering on
     *             compared integers.
     * @return no return.
     */
    public static void mergeSort(int[] S, Comparator<Integer> comp) {
        int n = S.length;
        // It true, array is trivially sorted.
        if (n < 2) {
            return;
        }
        // Divides array.
        int mid = n / 2;
        int[] S1 = Arrays.copyOfRange(S, 0, mid); // Copy of ﬁrst array half.
        int[] S2 = Arrays.copyOfRange(S, mid, n); // Copy of second array half.
        // Conquers with recursion.
        mergeSort(S1, comp); // Sorts copy of ﬁrst half.
        mergeSort(S2, comp); // Sorts copy of second half.
        // Merges results.
        merge(S1, S2, S, comp); // Merges sorted halves back into original.
    }

    /**
     * Quick sorts an array of integers into increasing order.
     * 
     * @param S    as array of pseudo-random integers to be sorted.
     * @param comp as Comparator<Integer> object to impose total ordering on
     *             compared integers.
     * @param a    leftmost index of S as integer.
     * @param b    rightmost index of S as integer.
     * @return no return.
     */
    public static void quickSort(int[] S, Comparator<Integer> comp, int a, int b) {
        // Base case.
        if (a >= b) {
            return;
        }
        // Recursion case.
        // Sorts subarray.
        int left = a;
        int right = b - 1;
        int pivot = S[b];
        int temp; // temp object used for swapping.
        while (left <= right) {
            // Scans until reaching value equal or larger than pivot (or right marker).
            while (left <= right && comp.compare(S[left], pivot) < 0) {
                left++;
            }
            // Scans until reaching value equal or smaller than pivot (or left marker).
            while (left <= right && comp.compare(S[right], pivot) > 0) {
                right--;
            }
            // True if indices did not cross.
            if (left <= right) {
                // Swaps values and shrinks range.
                temp = S[left];
                S[left] = S[right];
                S[right] = temp;
                left++;
                right--;
            }
        }

        // Puts pivot (marked by left index) into its ﬁnal place.
        temp = S[left];
        S[left] = S[b];
        S[b] = temp;
        // Recursively quick sorts array.
        quickSort(S, comp, a, left - 1); // Range: left to middle.
        quickSort(S, comp, left + 1, b); // Range: middle to right.
    }

    /**
     * Heap sorts an array of integers into increasing order.
     * 
     * @param arr as array of pseudo-random integers to be sorted.
     * @return no return.
     */
    public static void heapSort(int arr[]) {
        int n = arr.length;
        // Builds heap.
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extracts an element from heap.
        for (int i = n - 1; i > 0; i--) {
            // Moves current root to end.
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Calls heapify on reduced heap.
            heapify(arr, i, 0);
        }
    }

    /**
     * Heapifys a subtree rooted with node with index i.
     * 
     * @param arr as array of pseudo-random integers.
     * @param n   is size of heap as integer.
     * @param i   is index of root of subtree as integer.
     * @return no return.
     */
    public static void heapify(int arr[], int n, int i) {
        // Initializes largest as root.
        int largest = i;
        // Sets heap parent-child formula.
        int l = 2 * i + 1; // leftChildIndex = 2 * parentIndex + 1.
        int r = 2 * i + 2; // rightChildIndex = 2 * parentIndex + 2.

        // True if left child is larger than root.
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // True if right child is larger than largest so far.
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // True if largest is not root, swaps root integer and largest integer.
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapifys subtree.
            heapify(arr, n, largest);
        }
    }

    /**
     * Measures running times (in milliseconds) of sorting algorithms (insertion
     * sort, merge sort, quick sort, and heap sort) for randomly generated integer
     * arrays of different input sizes (10000, 20000, 30000, 40000, 50000, 60000,
     * 70000, 80000, 90000, 100000), and outputs results.
     * 
     * @param args command-line args as array of String objects.
     * @return no return.
     */
    public static void main(String[] args) {
        // Declares variables.
        long startTime, endTime;
        int[] currentArrayOriginal;
        int[] currentArrayClone;
        // Initializes variables.
        ArrayList<Long> measuredTimesInsertionSort = new ArrayList<>();
        ArrayList<Long> measuredTimesMergeSort = new ArrayList<>();
        ArrayList<Long> measuredTimesQuickSort = new ArrayList<>();
        ArrayList<Long> measuredTimesHeapSort = new ArrayList<>();
        int[] arraySizes = { 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000 };

        // Measures running times (in milliseconds) of sorting algorithms for all input
        // sizes in arraySizes.
        for (int arraySize : arraySizes) {
            // Generates arraySize (i.e., 10,000; 20,000; and so
            // on) random integers in range [1, 1,000,000].
            currentArrayOriginal = new Random().ints(1, 1000000 + 1).limit(arraySize).toArray();

            // Runs insertion sort and calculates the elapsed time in milliseconds.
            currentArrayClone = currentArrayOriginal.clone(); // Clones array to use unsorted array.
            startTime = System.currentTimeMillis();
            insertionSort(currentArrayClone);
            endTime = System.currentTimeMillis();
            measuredTimesInsertionSort.add(endTime - startTime);

            // Runs merge sort and calculates the elapsed time in milliseconds.
            currentArrayClone = currentArrayOriginal.clone(); // Clones array to use unsorted array.
            startTime = System.currentTimeMillis();
            mergeSort(currentArrayClone, new CustomComparator());
            endTime = System.currentTimeMillis();
            measuredTimesMergeSort.add(endTime - startTime);

            // Runs quick sort and calculates the elapsed time in milliseconds.
            currentArrayClone = currentArrayOriginal.clone(); // Clones array to use unsorted array.
            startTime = System.currentTimeMillis();
            quickSort(currentArrayClone, new CustomComparator(), 0, currentArrayClone.length - 1);
            endTime = System.currentTimeMillis();
            measuredTimesQuickSort.add(endTime - startTime);

            // Runs heap sort and calculates the elapsed time in milliseconds.
            currentArrayClone = currentArrayOriginal.clone(); // Clones array to use unsorted array.
            startTime = System.currentTimeMillis();
            heapSort(currentArrayClone);
            endTime = System.currentTimeMillis();
            measuredTimesHeapSort.add(endTime - startTime);
        }

        // Prints measured running times (in milliseconds).
        System.out.println("n: " + Arrays.toString(arraySizes));
        System.out.println("Insertion sort: " + measuredTimesInsertionSort.toString());
        System.out.println("Merge sort: " + measuredTimesMergeSort.toString());
        System.out.println("Quick sort: " + measuredTimesQuickSort.toString());
        System.out.println("Heap sort: " + measuredTimesHeapSort.toString());
        System.out.println("Elapsed times in milliseconds.");
    }
}