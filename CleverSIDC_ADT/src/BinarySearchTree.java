/**
 * Binary Search Tree implements DataStructure 
 * It is used to store the data when the data is
 * more than 1000 keys.
 * @author Harmanvir Singh (40019114)
 * @author Sarabpreet Singh Rekhi (40154067)
 *
 */
public class BinarySearchTree implements DataStructure {
	//---------Inner Class--------------//
	class BinaryNode{
		// Attributes
		long key = 0;
		String value = null;
		BinaryNode right = null;
		BinaryNode left = null;
		// Default Constructor
		public BinaryNode() {
			key = 0;
			value = null;
			left = null;
			right = null;
		}

		// Parametralised Constructor
		public BinaryNode(long key, String value) {
			this.key = key;
			this.value = value;
			right = null;
			left = null;
		}
		/**
		 * to String method for the Binary Node.
		 * @return it returns the string with key and value.
		 */
		public String toString() {
			return "Student Id: " + key + " Value: " + value;
		}
	}
	//------End of Inner Class------------//

	// Attributes 
	BinaryNode root = null;
	int size = 0;
	long[] sortedKeys = null;
	// Default Constructor
	public BinarySearchTree() {
		root = null;
	}

	// Parametralised Constructor 
	public BinarySearchTree(long key, String value) {
		root = new BinaryNode(key, value);
	}

	/**
	 * This method adds an element to the tree.
	 * @param key
	 * @param value
	 * @return true if the element is inserted.
	 */
	public boolean addElement(long key, String value) {
		root = insertNode(root, key, value);
		size++;
		return true;
	}

	/**
	 * This is a helper method for the addElement. It adds a binary Node to 
	 * the tree if the key is not present.
	 * @param root
	 * @param key
	 * @param value
	 * @return It returns the node that is inserted.
	 */
	public BinaryNode insertNode(BinaryNode root, long key, String value) {
		// If the root is null then a new Binary Node is inserted.
		if(root == null) {
			root = new BinaryNode(key, value);
			return root;
		}
		// If the key is less than the key of the root then 
		// we move to the left of the tree.
		if(root.key > key) {
			root.left = insertNode(root.left, key, value);
		}
		// Else we move to the right if the key is greater than 
		// the key of the root.
		else if(root.key < key) {
			root.right = insertNode(root.right, key, value);
		}
		// Unchanged when keys are equal.
		return root;
	}

	/**
	 * This method removes the element from the tree.
	 * @param key
	 * @return true if inserted and false otherwise
	 */
	public boolean remove(long key) {
		removeNode(root, key);
		size--;
		return true;
	}

	/**
	 * This is the helper method for the method remove. There are three 
	 * cases for the removal of the node from the tree.
	 * > When the node to be removed is the leaf node.
	 * > When the node to be removed has one child.
	 * > When the node to be removed has two children.
	 * The important thing to note is, inorder successor is needed only 
	 * when the right child is not empty. In this particular case, inorder 
	 * successor can be obtained by finding the minimum value in the right 
	 * child of the node.
	 * @param root root of the tree
	 * @param key key of the node
	 * @return it returns the Binary Node which is removed from the tree.
	 */
	public BinaryNode removeNode(BinaryNode root, long key) {
		// Base case if the root is null then the root is returned.
		if(root == null) {
			return root;
		}
		// If the key to be deleted is less than the key of the key of 
		// the root then we move towards the left.
		if(key < root.key) {
			root.left = removeNode(root.left, key);
		}
		// If the key to be deleted is greater then the key of the root 
		// then we move to the right.
		else if(key > root.key) {
			root.right = removeNode(root.right, key);
		}
		// This means the key to be deleted is the key of the root and
		// this is the time to delete the node.
		else {
			// Root with only one child or with no child.
			if(root.left == null) {
				return root.right;
			}
			else if(root.right == null) {
				return root.left;
			}
			// Node with the two children need an inorder successor 
			// or in other words the smallest key in the right sub tree.
			root.key = minimumKey(root.right);

			// Remove the right 
			root.right = removeNode(root.right, root.key);
		}
		return root;
	}

	/**
	 * This method returns the next smallest key in the tree
	 * and it will be at the last level of the tree.
	 * @param root
	 * @return the smallest value of the key in the tree.
	 */
	public long minimumKey(BinaryNode root) {
		long minKey = root.key;
		while(root.left != null) {
			minKey = root.left.key;
			root = root.left;
		}
		return minKey;
	}

	/**
	 * This method returns an array of long data type with 
	 * all the keys sorted in the order from low to high.
	 * @return sorted array of keys.
	 */
	public long[] allKeys() {
		sortedKeys = new long[size];
		inorder();
		return sortedKeys;
	}

	/**
	 * This method provides the inorder of the binary tree.
	 * and the inorder of the Binary Search Tree is a sorted 
	 * version of the keys.
	 */
	public void inorder() {
		inorderRecursion(root, sortedKeys);
	}
	int index = 0;

	/**
	 * This is the helper method for the inorder traversal.
	 * @param root
	 * @param sortedKeys
	 */
	public void inorderRecursion(BinaryNode root, long[] sortedKeys) {
		if(root != null) {
			inorderRecursion(root.left, sortedKeys);
			if(index < sortedKeys.length){
				sortedKeys[index] = root.key;
				index++;
			}
			inorderRecursion(root.right, sortedKeys);
		}
	}

	/**
	 * This method provides the next key to the key passed in the parameter.
	 * @return a next key.
	 */
	public long nextKey(long key) {
		BinaryNode node = searchKey(key);
		if(node == null) {
			return -1;
		}
		// If it has right child then it is the next key.
		if(node.right != null) {
			return node.right.key;
		}
		// If it has left key then it is the next key.
		else if(node.left != null){
			return node.left.key;
		}
		// Else -1 next key does not exists.
		return -1;	
	}

	/**
	 * This method searches the key.
	 * @param key
	 * @return searched key or null if not found.
	 */
	public BinaryNode searchKey(long key) {
		return search(root, key);
	}

	/**
	 * This method returns the node searched by the key.
	 * @param key
	 * @return
	 */
	public BinaryNode search(BinaryNode node, long key) {
		// Base case when the key of node is equal to key to be searched.
		if( node == null || node.key == key) {
			return node;
		}
		// If the searched key is less then the root key then it means it
		// is on the left of the root.
		if(key < node.key) {
			return search(node.left, key);
		}
		// If the searched key is greater than the root key then it means 
		// it lies to the right of the node. 
		else{
			return search(node.right, key);
		}
	}

	/**
	 * This method returns the previous key to 
	 * the key passed in the parameter.
	 * @param key
	 * @return the previous key if found otherwise it 
	 * returns if the key is not in the data set or 
	 * the key found is a leaf node then it returns -1. 
	 */
	public long prevKey(long key) {
		BinaryNode node = searchKey(key);
		if(node == null) {
			return -1;
		}
		// If left node is not equal to null.
		if(node.left != null) {
			return node.left.key;
		}
		// If right node not equal to null.
		else if(node.right != null) {
			return node.right.key;
		}
		// This means the node is a leaf node.
		return -1;

	}

	/**
	 * It returns the range of keys. The presence of keys 
	 * in the data set does not matter.
	 * @param key1
	 * @param key2
	 * @return number of keys present in the data set.
	 */
	public int rangeKey(long key1, long key2) {
		long[] sortedKeys = allKeys();
		int indexKey1 = runBinarySearchRecursively(sortedKeys, key1, 0, size-1);
		int indexKey2 = runBinarySearchRecursively(sortedKeys, key2, 0, size-1);
		return indexKey2 - indexKey1;
	}

	/**
	 * It returns the index of the key which is present in the array
	 * If the key is not present in the array it returns the index where 
	 * it should have been present.
	 * @param sortedArray
	 * @param key
	 * @param low
	 * @param high
	 * @return
	 */
	public int runBinarySearchRecursively(long[] sortedArray, long key, int low, int high) {
		int middle = low  + ((high - low) / 2);
		if (high < low) {
			return middle;
		}
		if (key == sortedArray[middle]) {
			return middle;
		} else if (key < sortedArray[middle]) {
			return runBinarySearchRecursively(sortedArray, key, low, middle - 1);
		} else {
			return runBinarySearchRecursively(sortedArray, key, middle + 1, high);
		}
	}

	/**
	 * It returns true if the element is present in the data set, otherwise false.
	 * @param key
	 * @return true if present, otherwise false.
	 */
	public boolean contains(long key) {
		if(searchKey(key) == null) {
			return false;
		}
		return true;
	}

	/**
	 * This method returns the value of the key passed.
	 * @param key
	 * @return Value
	 */
	public String getValues(long key) {
		BinaryNode node = searchKey(key);
		if(node == null) {
			return null;
		}
		return node.value;
	}
	
	/**
	 * This method provides an array of all the Binary nodes 
	 * present in the Binary Search Tree.
	 * @return
	 */
	public BinaryNode[] allBinaryNodes() {
		BinaryNode[] allNodes = new BinaryNode[size];
		inorderForBinaryNode(root, allNodes);
		return allNodes;
	}
	
	int index_1 = 0;
	/**
	 * This a recursive inorder method for storing all the nodes in 
	 * an array.
	 * @param root
	 * @param allNodes
	 */
	public void inorderForBinaryNode(BinaryNode root, BinaryNode[] allNodes) {
		if(root != null) {
			inorderForBinaryNode(root.left, allNodes);
			if(index_1 < allNodes.length){
				allNodes[index_1] = root;
				index_1++;
			}
			inorderForBinaryNode(root.right, allNodes);
		}
	}
}