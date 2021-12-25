
public class Hw2_p4 {

	// implement reverse method
	// you may want to write a separate method with additional parameters, which is
	// recursive

	/**
	 * Recursively reverses order of doubly linked list.
	 * 
	 * @param intList reference to doubly linked list.
	 * @param pointer reference to node after which nodes are inserted.
	 * @return reference to doubly linked list.
	 */
	public static DoublyLinkedList<Integer> parameterized_reverse(DoublyLinkedList<Integer> intList,
			DoubleLinkNode<Integer> pointer) {
		// Gets the position that comes exactly one after the header.
		DoubleLinkNode<Integer> oneAfterHeader = intList.getHeader().getNext();

		// Base case - is executed until pointer position is first position after
		// header.
		// Or is executed directly if doubly linked list is empty.
		if (oneAfterHeader == pointer || oneAfterHeader == intList.getTrailer()) {
			return intList;
		} else {
			// Recursion case.
			// Inserts element of first position (position after header) behind the pointer;
			// position of pointer remains the same through entire recursion.
			intList.addBetween(oneAfterHeader.getElement(), pointer, pointer.getNext());
			// And then removes first position.
			intList.removeFirst();
			return parameterized_reverse(intList, pointer); // Calls itself recursively.
		}
	}

	/**
	 * Calls parameterized_reverse method to recursively reverse order of doubly
	 * linked list.
	 * 
	 * @param intList reference to the doubly linked list.
	 * @return no return.
	 */
	public static void reverse(DoublyLinkedList<Integer> intList) {

		// complete this method
		// Second argument: first gets position directly in front of trailer and passes
		// this position as second argument.
		parameterized_reverse(intList, intList.getTrailer().getPrev());
	}

	// use the main method for testing
	// test with arrays of different lenghts
	/**
	 * Creates two doubly linked lists of different sizes, calls the reverse method
	 * to recursively reverse the order of each doubly linked list, and prints the
	 * size and reversed doubly linked lists.
	 * 
	 * @param args command-line args as array of String objects.
	 * @return no return.
	 */
	public static void main(String[] args) {

		DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();

		int[] a = { 10, 20, 30, 40, 50 };
		for (int i = 0; i < a.length; i++) {
			intList.addLast(a[i]);
		}
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());

		// Here, invoke the reverse method you implemented above
		reverse(intList);

		System.out.println("After reverse: " + intList.toString());

		intList = new DoublyLinkedList<>();
		int[] b = { 10, 20, 30, 40, 50, 60 };
		for (int i = 0; i < b.length; i++) {
			intList.addLast(b[i]);
		}
		System.out.println();
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());

		// Here, invoke the reverse method you implemented above
		reverse(intList);

		System.out.println("After reverse: " + intList.toString());

	}

}
