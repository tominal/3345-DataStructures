package cs3345p2;

public class IDedLinkedList<AnyType> {
	IDedLinkedList() {
		head = new Node<>(null,null);
		tail = new Node<>(null,null);
		size = 0;
	}
	
	void makeEmpty() {
		head = new Node<>(null,null);
		tail = new Node<>(null,null);
		size = 0;
	}
	
	AnyType findID(int id) {
		boolean found = false;
		AnyType temp = null;
		if(size > 0) {
			// loop through linked list with a temp pointer on head
			Node<AnyType> pointer = head;
			while(pointer != null) {
				if(((IDedObject) pointer.data).getID() == id) {
					found = true;
					temp = pointer.data;
				}
				pointer = pointer.next;
			}
		}
		return found ? temp : null;
	}
	
	boolean insertAtFront(AnyType x) {
		// if list is empty
		if(size == 0) {
			head = new Node<AnyType>( x, null);
			tail = head;
		} else {
			// if findID does not return null, then the id was found.
			if(findID( ((IDedObject) x).getID() ) != null) {
				return false;
			} else { // findID returned NULL. insert new node
				head = new Node<AnyType>( x, head);
			}
		}
		size++;
		return true;
	}
	
	AnyType deleteFromFront() {
		Node<AnyType> deletedPointer = head;
		head = head.next;
		size--;
		return deletedPointer.data;
	}
	
	AnyType delete(int id) {
		Node<AnyType> deletedPointer = null;
		// loop through linked list with a temp pointer on head
		Node<AnyType> beforePointer = head;
		// check the head
		if(((IDedObject) beforePointer.data).getID() == id) {
			deletedPointer = beforePointer;
			// forget about the found node
			head = head.next;
			size--;
		}
		Node<AnyType> pointer = head.next;
		// check the rest of the items
		while(pointer != null) {
			// if pointer matches
			if(((IDedObject) pointer.data).getID() == id) {
				// point around the found node
				beforePointer.next = pointer.next;
				deletedPointer = pointer;
				size--;
			}
			// move on
			beforePointer = pointer;
			pointer = pointer.next;
		}
		// check first and last first
		return deletedPointer.data;
	}
	
	int printTotal() {
		int total = 0;
		// loop through linked list with a temp pointer on head
		Node<AnyType> pointer = head;
		// i could use an iterator here.. but i'll make an executive decision not to.
		while(pointer != null) {
			total += ((IDedObject) pointer.data).getID();
			pointer = pointer.next;
		}
		return total;
	}
	
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> n )
        {
            data = d; next = n;
        }
        
        public AnyType data;
        public Node<AnyType> next;
    }
    
    private Node<AnyType> head;
	private Node<AnyType> tail;
	private long size;
}
