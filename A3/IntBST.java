package nodeTrees;

import java.util.ArrayList;
import java.util.List;

// binary search tree storing integers
public class IntBST extends NodeBinaryTree<Integer> {

	private int size = 0;

	public IntBST() {
	}

	public int size() {
		return size;
	}

	public void setSize(int s) {
		size = s;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Places element e at the root of an empty tree and returns its new Node.
	 *
	 * @param e the new element
	 * @return the Node of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */

	public Node<Integer> addRoot(Integer e) throws IllegalStateException {
		if (size != 0)
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Print a binary tree horizontally Modified version of
	 * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	 * Modified by Keith Gutfreund
	 * 
	 * @param n Node in tree to start printing from
	 */

	public void print(Node<Integer> n) {
		print("", n);
	}

	public void print(String prefix, Node<Integer> n) {
		if (n != null) {
			print(prefix + "       ", right(n));
			System.out.println(prefix + ("|-- ") + n.getElement());
			print(prefix + "       ", left(n));
		}
	}

	public void inorderPrint(Node<Integer> n) {
		if (n == null)
			return;
		System.out.println(n.getLeft() + " " + n.getRight());
		inorderPrint(n.getLeft());
		System.out.print(n.getElement() + "  ");
		inorderPrint(n.getRight());
	}

	public Iterable<Node<Integer>> children(Node<Integer> n) {
		List<Node<Integer>> snapshot = new ArrayList<>(2); // max capacity of 2
		if (left(n) != null)
			snapshot.add(left(n));
		if (right(n) != null)
			snapshot.add(right(n));
		return snapshot;
	}

	public int height(Node<Integer> n) throws IllegalArgumentException {
		if (isExternal(n)) {
			return 0;
		}
		int h = 0; // base case if p is external
		for (Node<Integer> c : children(n))
			h = Math.max(h, height(c));
		return h + 1;
	}

	/**
	 * Recursively builds binary search tree.
	 * 
	 * @param a         reference to integer array sorted in ascending order.
	 * @param lowIndex  lowest index of array a as integer.
	 * @param highIndex highest index of array a as integer.
	 * @param bst       reference to IntBST object.
	 * @return reference to root node of binary search tree.
	 */
	public static Node<Integer> parameterizedMakeBinaryTree(int a[], int lowIndex, int highIndex, IntBST bst) {
		// Base case.
		if (lowIndex > highIndex) {
			return null;
		} else {
			// Recursion case.
			// Determines index of middle element of array a.
			int middleIndex = (lowIndex + highIndex) / 2;
			// Sets middle element as root; (element, parent, left, right).
			Node<Integer> node = new Node<>(a[middleIndex], null, null, null);
			// Increases size of bst by 1.
			bst.size += 1;

			// Recursively builds bst's left subtree from left subarray and sets left
			// subtree as left child of root.
			node.setLeft(parameterizedMakeBinaryTree(a, lowIndex, middleIndex - 1, bst));
			// Recursively builds bst's right subtree from right subarray and sets right
			// subtree as right child of root.
			node.setRight(parameterizedMakeBinaryTree(a, middleIndex + 1, highIndex, bst));
			return node;
		}
	}

	/**
	 * Calls parameterizedMakeBinaryTree method to recursively build binary search
	 * tree.
	 * 
	 * @param a reference to integer array sorted in ascending order.
	 * @return reference to IntBST object, and thus access to binary search tree.
	 */
	public static IntBST makeBinaryTree(int[] a) {
		// Creates IntBST object.
		IntBST bst = new IntBST();
		// Sets initial minimum and maximum index of array a.
		int lowIndex = 0;
		int highIndex = a.length - 1;
		// Recursively builds binary search tree and returns root of binary search tree.
		Node<Integer> root = parameterizedMakeBinaryTree(a, lowIndex, highIndex, bst);
		// Sets root in IntBST object to be able to use print methods for IntBST object.
		bst.root = root;
		return bst;
	}

}
