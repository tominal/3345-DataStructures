package cs3345p3;

import java.util.Queue;
import java.util.Stack;

public class LazyBinarySearchTree {

	LazyBinarySearchTree() {
		this.root = null;
	}
	
	public boolean insert(int key) {
		
		if(key < 1 || key > 99)
			throw new IllegalArgumentException();
		
		TreeNode traversal = root;
		TreeNode pointer = null;
		
		while(traversal != null) {
			
			pointer = traversal;
			
			if(key < traversal.key)
				traversal = traversal.leftChild;
			else if(key > traversal.key)
				traversal = traversal.rightChild;
			else if(key == traversal.key)
				break;
			
		}
		
		if(pointer == null) {
			root = new TreeNode(key);
			return true;
		}

		if(key < pointer.key) {
			pointer.leftChild = new TreeNode(key);
			return true;
		} else if(key > pointer.key) {
			pointer.rightChild = new TreeNode(key);
			return true;
		}
		
		if(key == pointer.key) {
			if(pointer.deleted == true) {
				pointer.deleted = false;
				return true;
			}
			// else, continue to returning logical false
		}
		
		return false;
		
	}
	
	public boolean delete(int key) {
		if(key < 1 || key > 99)
			throw new IllegalArgumentException();
		
		return recursiveDelete(root, key);
	}
	
	public int findMin() {
		TreeNode pointer = root;
		int min = -1;
		
		while(pointer.leftChild != null) {
			if(!pointer.deleted)
				min = pointer.key;
			pointer = pointer.leftChild;
		}
		
		return min;
	}
	
	public int findMax() {
		TreeNode pointer = root;
		int max = -1;
		
		if(root != null && pointer.rightChild == null) {
			max = root.key;
		}
		
		while(pointer.rightChild != null) {
			if(!pointer.deleted)
				max = pointer.key;
			pointer = pointer.rightChild;
		}
		
		return max;
	}
	
	public boolean contains(int key) {
		
		if(key < 1 || key > 99)
			throw new IllegalArgumentException();
		
		TreeNode traversal = root;
		TreeNode pointer = null;
		
		while(traversal != null) {
			
			pointer = traversal;
			
			if(key < traversal.key)
				traversal = traversal.leftChild;
			else if(key > traversal.key)
				traversal = traversal.rightChild;
			else if(key == traversal.key)
				break;
			
		}
		
		if(key == pointer.key && !pointer.deleted) {
			return true;
			// else, continue to returning logical false
		}
		
		return false;
	}
	
	public String toString() {
		
		if(root == null)
			return "";
		
		Stack<TreeNode> nodes = new Stack<>();
		String str = "";
		
		nodes.push(root);
		
		while(!nodes.isEmpty()) {
			TreeNode node = nodes.pop();
			str += ( node.deleted ? "*" : "") + node.key + " ";
			
			if(node.rightChild != null) {
				nodes.push(node.rightChild);
			}
			if(node.leftChild != null) {
				nodes.push(node.leftChild);
			}
		}
		
		return str;
	}
	
	public int height() {
		return height(root);
	}
	
	public int size() {
		return size(root);
	}
	
	private TreeNode root;
	
	private TreeNode recursiveInsert(TreeNode r, int key) {
		
		if(r == null) {
			r = new TreeNode(key);
			return r;
		}
		
		if(key == r.key) {
			// if key is found in the tree, check deleted attribute
			if(r.deleted) // if its deleted, undelete the bugger
				r.deleted = false;
			// if it is not deleted, do nothing and return.
		} else if(key < r.key) {
			r.leftChild = recursiveInsert(r.leftChild, key);
		} else { //if(key > r.key ) {
			r.rightChild = recursiveInsert(r.rightChild, key);
		}
		
		return r;
		
	}
	
	private boolean recursiveDelete(TreeNode r, int key) {
		
		if(r == null) {
			return false;
		}
		
		if(key == r.key) {
			// if key is found in the tree, check deleted attribute
			if(!r.deleted) { // delete it
				r.deleted = true;
				return true;
			}
			// if its deleted, ignore
		} else if(key < r.key) {
			return recursiveDelete(r.leftChild, key);
		} else { //if(key > r.key ) {
			return recursiveDelete(r.rightChild, key);
		}

		return false;
		
	}
	
	private int height(TreeNode r) {
		if(r == null) 
			return -1;
		
		int leftHeight = height(r.leftChild);
		int rightHeight = height(r.rightChild);
		
		if(leftHeight > rightHeight)
			return leftHeight + 1;
		else {
			return rightHeight + 1;
		}
	}
	
	private int size(TreeNode r) {
		if(r == null)
			return 0;
		else
			return size(r.leftChild) + 1 + size(r.rightChild);
	}
	
	private class TreeNode {
		int key;
		TreeNode leftChild;
		TreeNode rightChild;
		boolean deleted;
		public TreeNode (int k) {
			key = k;
			leftChild = rightChild = null;
			deleted = false;
		}
	}
	
}
