/**
 * Hashtable class implements DataStructure. It uses an array and 
 * Separate chaining technique to implement.
 * @author Harmanvir Singh (40019114)
 * @author Sarabpreet Singh Rekhi (40154067)
 *
 */
public class HashTable implements DataStructure{
	/**
	 * Node that has to be inserted in the bucket array.
	 */
	//---------------Inner Class-----------------------//
	public class HashNode{
		long key = 0;
		String value = null;
		HashNode next = null;
		/**
		 * Constructor for the HashNode class.
		 * @param key
		 * @param value
		 */
		public HashNode(long key, String value) {
			this.key = key;
			this.value = value;
		}
		/**
		 * toString method to print the string value.
		 */
		public String toString() {
			return "Student Id: " + key + " Value: " + value;
		}
	}
	//-------------End of Inner Class--------------------//

	// Attributes
	HashNode[] bucketArray = null;
	private int size = 0;
	private int currentBucketSize = 0;

	/**
	 * Default Constructor
	 * an array of size 211 will be created to accommodate maximum of 
	 * 999 elements.
	 */
	public HashTable() {
		int nextPrime = calculateNextPrime(200);
		bucketArray = new HashNode[nextPrime];
		currentBucketSize = nextPrime;
	}
	
	/**
	 * Parametralised Constructor which first compresses the size by 
	 * the factor of 3 and then finds the next prime number close 
	 * to the compressed number t
	 * @param size size of the data
	 */
	public HashTable(int size) {
		int compressedBy3 = size/3;
		int nextPrime = calculateNextPrime(compressedBy3);
		bucketArray = new HashNode[nextPrime];
		currentBucketSize = nextPrime;
	}
	
	/**
	 * It calculates the next prime
	 * @param number
	 * @return
	 */
	public static int calculateNextPrime(int number) {
		int prime = 1;
		boolean isPrime = true;
		while(true) {
			isPrime = true;
			if(number%2 ==0 || number%3 == 0) {
				number++;
				continue;
			}
			for(int i= 2; i<=Math.sqrt(number); i++) {
				if(number%i == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime == true) {
				prime = number;
				break;
			}
			number++;
		}
		return prime;
	}

	/**
	 * Hash function implemented that will provide us the index at 
	 * which we have to insert the value.
	 * @param key
	 * @param prime
	 * @return
	 */
	public int hashFunction(long key, int prime) {
		int index = (int)(key % prime);
		return index;
	}

	/**
	 * Adds a node in the bucket Array using separate hashing.
	 * Duplicate keys are not allowed.
	 * @param key
	 * @param value
	 */
	public boolean addElement(long key, String value) {
		int index = hashFunction(key, bucketArray.length);
		HashNode head = bucketArray[index];
		HashNode temp = head;
		HashNode secondLast = null;
		if(contains(key)) {
			return false;
		}
		if(head == null) {
			bucketArray[index] = new HashNode(key, value);
		}
		else {
			while(temp != null) {
				secondLast = temp;
				temp = temp.next;
			}
			secondLast.next = new HashNode(key, value);
		}
		size++;
		return true;
	}

	/**
	 * Removes element from the bucketArray
	 * @param key
	 * @return returns true if the element is remove
	 * 		   or it is not present in the list.
	 */
	public boolean remove(long key) {
		int index = hashFunction(key, bucketArray.length);
		HashNode node = bucketArray[index];
		HashNode secondLast = null;
		// Case 1 when the index at which we hash to is null
		if(node == null) {
			return true;
		}
		// Case 2 if index is not null then iterate to find the
		// the node and change the reference.
		if(node.key == key) {
			bucketArray[index] = node.next;
			return true;
		}
		while(node != null) {
			if(node.key == key) {
				size--;
				break;
			}
			secondLast = node;
			node = node.next;
		}
		// if second last is null then this means there was only one
		// element in the list. After removing the array index is null.
		if(secondLast == null) {
			bucketArray[index] = null;
			return true;
		}
		// Case 4 when the element to remove is the last element.
		if(node == null) {
			secondLast.next = null;
		}
		else {
			secondLast.next = node.next;
		}
		return true;
	}

	/**
	 * All the keys present in the data set are represented 
	 * in the sorted order.
	 * @return sorted array of keys.
	 */
	public long[] allKeys() {
		long[] sortedKeys = new long[size];
		int index = 0;
		// Making sure to visit every bucket.
		for(int i=0; i<bucketArray.length; i++) {
			// index is null
			if(bucketArray[i] == null) {
				continue;
			}
			HashNode node = bucketArray[i];
			// index not null, then iterating through the linked list.
			while(node != null) {
				sortedKeys[index] = node.key;
				index++;
				node = node.next;
			}
		}
		// Sorting all the keys.
		mergeSort(sortedKeys, 0, size-1);
		return sortedKeys;
	}

	/**
	 * sorts the array from low to high
	 * @param array to sort
	 * @param low lowest index
	 * @param high highest index
	 */
	public static void mergeSort(long[] array, int low, int high) {
		if (high <= low) return;

		int mid = (low+high)/2;
		mergeSort(array, low, mid);
		mergeSort(array, mid+1, high);
		merge(array, low, mid, high);
	}

	/**
	 * Helper method for the mergeSort.
	 * @param array
	 * @param low
	 * @param mid
	 * @param high
	 */
	public static void merge(long[] array, int low, int mid, int high) {
		// Creating temporary subarrays
		long leftArray[] = new long[mid - low + 1];
		long rightArray[] = new long[high - mid];

		// Copying our subarrays into temporaries
		for (int i = 0; i < leftArray.length; i++)
			leftArray[i] = array[low + i];
		for (int i = 0; i < rightArray.length; i++)
			rightArray[i] = array[mid + i + 1];

		// Iterators containing current index of temp subarrays
		int leftIndex = 0;
		int rightIndex = 0;

		// Copying from leftArray and rightArray back into array
		for (int i = low; i < high + 1; i++) {
			// If there are still uncopied elements in R and L, copy minimum of the two
			if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
				if (leftArray[leftIndex] < rightArray[rightIndex]) {
					array[i] = leftArray[leftIndex];
					leftIndex++;
				} else {
					array[i] = rightArray[rightIndex];
					rightIndex++;
				}
			} else if (leftIndex < leftArray.length) {
				// If all elements have been copied from rightArray, copy rest of leftArray
				array[i] = leftArray[leftIndex];
				leftIndex++;
			} else if (rightIndex < rightArray.length) {
				// If all elements have been copied from leftArray, copy rest of rightArray
				array[i] = rightArray[rightIndex];
				rightIndex++;
			}
		}
	}

	/**
	 * This function provides the next key to the key passed in the method.
	 * @param key key
	 * @return the next key is available otherwise a negative value.
	 */
	public long nextKey(long key) {
		int index = hashFunction(key, bucketArray.length);
		HashNode head = bucketArray[index];
		if(head == null) {
			return -1;
		}
		while(head != null) {
			if(head.key == key) {
				// if we want the next key from the last element
				// then will return -1. 
				if(head.next == null) {
					return -1;
				}
				return head.next.key;
			}
			head = head.next;
		}
		return -1;
	}

	/**
	 * This function provides the previous key to the key 
	 * passed in the method. It will return -1 if there is no previous key found.
	 * @param key
	 * @return previous key
	 */
	public long prevKey(long key) {
		int index = hashFunction(key, bucketArray.length);
		HashNode head = bucketArray[index];
		HashNode secondLast = null;
		// If the index of the array is null then there is no
		// previous key.
		if(head == null) {
			return -1;
		}
		// Else until head is not null keep on finding the key
		// and then return the second Last key to the found key.
		while(head != null) {
			if(head.key == key) {
				// only one element in the separate chain. 
				if(secondLast == null) {
					return -1;
				}
				// key found and second last key is returned.
				return secondLast.key;
			}
			secondLast = head;
			head = head.next;
		}
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
	 * Returns true if the element is present in the bucket array.
	 * @param key
	 * @return true if element present
	 */
	public boolean contains(long key) {
		int index = hashFunction(key, bucketArray.length);
		// If the place where we land after hashing is null then
		// it does not contains the key.
		if(bucketArray[index] == null) {
			return false;
		}
		// Not null means we have to iterate to find the key.
		// returns true once found.
		else {
			HashNode node = bucketArray[index];
			while(node != null) {
				if(node.key == key) {
					return true;
				}
				node = node.next;
			}
		}
		return false;
	}

	/**
	 * It returns the current size of the bucketArray.
	 * @return
	 */
	public int getCurrentBucketSize() {
		return currentBucketSize;
	}

	/**
	 * It returns the size of the current data set.
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * This method returns the value of the key passed as the parameter.
	 * @return it returns the String if the key is available in the data set.
	 * 			otherwise it returns null
	 */
	public String getValues(long key) {
		int index = hashFunction(key, bucketArray.length);
		HashNode node = bucketArray[index];
		if(node == null) {
			return null;
		}
		while(node != null) {
			if(node.key == key) {
				return node.value;
			}
			node = node.next;
		}
		return null;
	}
	/**
	 * It returns all the hash nodes present in the hash table.
	 * @return
	 */
	public HashNode[] allHashNodes() {
		HashNode[] allnodes = new HashNode[size];
		int index = 0;
		for(int i=0; i<bucketArray.length; i++) {
			// When index is empty.
			if(bucketArray[i] == null) {
				continue;
			}
			// When index is not empty.
			HashNode node = bucketArray[i];
			while(node != null) {
				allnodes[index] = new HashNode(node.key, node.value);
				node = node.next;
				index++;
			}
		}
		return allnodes;
	}
}