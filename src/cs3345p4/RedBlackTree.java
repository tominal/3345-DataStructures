package cs3345p4;

import java.util.StringJoiner;

/**
 * 
 * @author Thomas Johnson
 * @class CS 3345
 * @section 001
 * @semester Fall 2019
 * @project #4
 * @description
 * 		The RedBlackTree class simply inserts and does not bother with deletion.
 * 		The node type of the binary search tree, Node, is protectedly nested within the class.
 *
 */

public class RedBlackTree<E extends Comparable<E>> {

	protected static class Node<E extends Comparable<E>> {
		
		protected E element;
		protected Node<E> leftChild;
		protected Node<E> rightChild;
		protected Node<E> parent;
		protected boolean color;
		
		public Node() {
			this(null);
		}
		
		public Node(E element) {
			this.element = element;
			leftChild = null;
			rightChild = null;
			parent = null;
			color = BLACK;
		}
		
		public String toString() {
			return (color == RED) ? "*" + element : "" + element;
		}
		
	}
	
	public RedBlackTree() {
		empty = new Node<E>();
		root = empty;
	}
	
	/**
	 * insert
	 * @param e
	 * @return boolean
	 * @throws NullPointerException
	 * @apiNote
	 * 		Insert returns true if a Node was created and inserted into the tree.
	 * 		If e is null, an exception is thrown.
	 * 		Traversal is iterative in this method.
	 */
	public boolean insert(E e) throws NullPointerException {
		if(e == null)
			throw new NullPointerException();
				
		Node<E> node = new Node<E>(e);
		Node<E> temp1 = empty;
				
		Node<E> pointer = root;
		
		while(pointer != empty) {
			temp1 = pointer;
			
			int compare = node.element.compareTo(pointer.element);
			
			if(compare < 0)
				pointer = pointer.leftChild;
			else if(compare > 0)
				pointer = pointer.rightChild;
			else
				return false;
		}
		
		node.parent = temp1;
		
		if(temp1 == empty)
			root = node;
		else if(node.element.compareTo(temp1.element) < 0)
			temp1.leftChild = node;
		else
			temp1.rightChild = node;
		
		node.leftChild = empty;
		node.rightChild = empty;
		node.color = RED;
		
		balance(node);
		
		return true;
	}
	
	/**
	 * contains
	 * @param obj
	 * @return boolean
	 * @apiNote
	 * 		Calls the private contains method.
	 */
	public boolean contains(Comparable<E> obj) {
		Node<E> temp = new Node<E>();
		
		return (obj == null || contains(root, obj) == temp) ? false : true;
	}
	
	/**
	 * contains
	 * @param null
	 * @return String
	 * @apiNote
	 * 		Creates a StringJoiner object and called the private preorder method.
	 */
	public String toString() {
		if(root == null)
			return "";
		else
			return preorder(root, new StringJoiner(" ")).toString();
	}
	
	private Node<E> root;
	private Node<E> empty;
	private static boolean BLACK = true;
	private static boolean RED = false;
	
	/**
	 * balance
	 * @param node
	 * @return void
	 * @apiNote
	 * 		Checks a node for balance and continuously rotates it until it's correct.
	 */
	private void balance(Node<E> node) {
		// if the node's parent is red, then we keep trying to fix it
		while(node.parent.color == RED) {
			// if the node's parent is in the left branch
			if(node.parent.equals(node.parent.parent.leftChild)) {
				Node<E> temp = node.parent.parent.rightChild;
				
				if(temp.color == RED) {
					node.parent.color = BLACK;
					temp.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
				} else {
					if(node.equals(node.parent.rightChild)) {
						node = node.parent;
						
						rotateLeft(node);
					}
					
					node.parent.color = BLACK;
					node.parent.parent.color = RED;
					
					rotateRight(node.parent.parent);
				}
			} else {
				Node<E> temp = node.parent.parent.leftChild;
				
				if(temp.color == RED) {
					node.parent.color = BLACK;
					temp.color = BLACK;
					node.parent.parent.color = BLACK;
					node = node.parent.parent;
				} else {
					if(node.equals(node.parent.leftChild)) {
						node = node.parent;
						
						rotateRight(node);
					}
					
					node.parent.color = BLACK;
					node.parent.parent.color = RED;

					rotateLeft(node.parent.parent);
				}
			}
		}
		
		root.color = BLACK;
	}
	
	/**
	 * rotateLeft
	 * @param node
	 * @return void
	 * @apiNote
	 * 		Performs a left rotation on a node.
	 */
	private void rotateLeft(Node<E> node) {
		Node<E> temp1 = node.rightChild;
		
		node.rightChild = temp1.leftChild;
		
		if(!temp1.leftChild.equals(empty)) {
			temp1.leftChild.parent = node;
		}
		
		temp1.parent = node.parent;
		
		if(node.parent.equals(empty))
			root = temp1;
		else if(node.equals(node.parent.leftChild))
			node.parent.leftChild = temp1;
		else
			node.parent.rightChild = temp1;
		
		temp1.leftChild = node;
		node.parent = temp1;
	}
	
	/**
	 * rotateRight
	 * @param node
	 * @return void
	 * @apiNote
	 * 		Performs a right rotation on a node.
	 */
	private void rotateRight(Node<E> node) {
		Node<E> temp1 = node.leftChild;
		
		node.leftChild = temp1.rightChild;
		
		if(!temp1.rightChild.equals(empty))
			temp1.rightChild.parent = node;
		
		temp1.parent = node.parent;
		
		if(node.parent.equals(empty))
			root = temp1;
		else if(node.equals(node.parent.rightChild))
			node.parent.rightChild = temp1;
		else
			node.parent.leftChild = temp1;
		
		temp1.rightChild = node;
		node.parent = temp1;
	}
	
	/**
	 * contains
	 * @param node
	 * @param obj
	 * @return Node<E>
	 * @apiNote
	 * 		Loops through a tree to check if a node exists.
	 */
	private Node<E> contains(Node<E> node, Comparable<E> obj) {
		
		while(node != empty && !obj.equals(node.element)) {
			if(obj.compareTo(node.element) < 0)
				node = node.leftChild;
			else
				node = node.rightChild;
		}

		return node;
	}

	/**
	 * preorder
	 * @param node
	 * @param temp
	 * @return StringJoiner
	 * @apiNote
	 * 		Utilizes a StringJoiner objects to easily add strings to temp.
	 */
	private StringJoiner preorder(Node<E> node, StringJoiner temp) {
		if(node != null && node.element != null) {
			temp.add(node.toString());
			preorder(node.leftChild, temp);
			preorder(node.rightChild, temp);
		}
		
		return temp;
	}
}
