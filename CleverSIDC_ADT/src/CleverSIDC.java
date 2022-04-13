import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A Dynamic Database which changes its Structure after the insertion of 1000 elements.
 * It implements HashTables upto 1000 keys and then further it implements Binary Search Tree.
 * @author Harmanvir Singh (40019114)
 * @author Sarabpreet Singh Rekhi (40154067)
 *
 */
public class CleverSIDC {
	DataStructure ds;
	int sizeCount = 0;
	int sizeThreshold;

	/**
	 * where 100 <= Size <= 500,000 is an integer number that defines
	 * the size of the list. This size is very important as it will determine what data types or data
	 * structures will be used
	 * @param size
	 */
	public void setSIDCThreshold(int size) {
		sizeThreshold = size;
		ds = size <= 1000 ? new HashTable(size) : new BinarySearchTree();
	}

	/**
	 * Adds element to the data set.
	 * @param cs CleverSIDC object
	 * @param key key
	 * @param value value of the key
	 */
	public void add(CleverSIDC cs, long key, String value) {
		// If the size of the data grows to 1000 dataStructure 
		// will change to BST.
		if(sizeCount == 1000 & ds instanceof HashTable ) {
			convertToBST();
		}
		// 
		if(!cs.contains(cs, key)) {
			cs.ds.addElement(key, value);
			System.out.println("Information with the key: " 
					+ key + " has been added to the database.");
			sizeCount++;
		}
		else {
			System.out.println(key +" key already exists in th database, "
					+ "therefore the value: " + value +" is not inserted in the database");
		}
	}

	/**
	 * This method converts the Hash table to the Binary Search Tree.
	 */
	public void convertToBST() {
		BinarySearchTree bst = new BinarySearchTree();
		HashTable ht = (HashTable)ds;
		HashTable.HashNode[] hashNodes = ht.allHashNodes();
		for(int i=0; i < hashNodes.length; i++) {
			bst.addElement(hashNodes[i].key, hashNodes[i].value);
		}
		ds = bst;
		System.out.println("-----------------------------------------------------------------\n"
				+ "\tHashtable has been converted to Binary Search Tree\n"
				+ "-----------------------------------------------------------------");
	}

	/**
	 * This method returns an array of all keys which 
	 * can be used to extract data.
	 * @param cs
	 * @return a long data type array of keys.
	 */
	public long[] allKeys(CleverSIDC cs) {
		return cs.ds.allKeys();
	}

	/**
	 * Removes an element with given key from data set.
	 * @param cs CleverSIDC object
	 * @param key key to be removed
	 */
	public void remove(CleverSIDC cs, long key) {
		if(sizeCount == 1001 && ds instanceof BinarySearchTree) {
			convertToHashTable();
		}
		cs.ds.remove(key);
		sizeCount--;
	}

	/**
	 * This method converts the Binary Search Tree into the 
	 * Hash Table data Structure when the size is 1000. 
	 */
	public void convertToHashTable() {
		BinarySearchTree bst = (BinarySearchTree)ds;
		BinarySearchTree.BinaryNode[] allBinaryNodes = bst.allBinaryNodes();
		HashTable ht = new HashTable(allBinaryNodes.length);
		for(int i=0; i<allBinaryNodes.length; i++) {
			ht.addElement(allBinaryNodes[i].key, allBinaryNodes[i].value);
		}
		ds = ht;
		System.out.println("-----------------------------------------------------------------\n"
				+ "\tBinary Search Tree has been converted to Hash Table\n"
				+ "-----------------------------------------------------------------");
	}

	/**
	 * It returns the value of the given key.
	 * @param cs CleverSIDC object
	 * @param key key for the value
	 * @return String containing the value of key.
	 */
	public String getValues(CleverSIDC cs, long key) {
		return cs.ds.getValues(key);
	}

	/**
	 * Gets successor key in the data set
	 * @param cs
	 * @param key
	 * @return the next successor key
	 */
	public long nextKey(CleverSIDC cs, long key) {
		return cs.ds.nextKey(key);
	}

	/**
	 * Gets the predecessor key for a given key
	 * @param cs CleverSIDC object
	 * @param key key to find previous of
	 * @return previous key
	 */
	public long prevKey(CleverSIDC cs, long key) {
		return cs.ds.prevKey(key);
	}

	/**
	 * Range of keys present in the data set
	 * @param cs CleverSIDC object
	 * @param key1 lower range index
	 * @param key2 higher range index
	 * @return number of keys present in between.
	 */
	public int rangeKey(CleverSIDC cs, long key1, long key2) {
		return cs.ds.rangeKey(key1, key2);
	}

	/**
	 * True if it contains the element for the given key.
	 * @param cs CleverSIDC object
	 * @param key key to search
	 * @return true if present otherwise returns false
	 */
	public boolean contains(CleverSIDC cs, long key) {
		return cs.ds.contains(key);
	}

	/**
	 * Randomly generates the key of 8 digits.
	 * @return
	 */
	public static long generate(CleverSIDC cs) {
		long key = (long)(Math.random()* 100000000);
		while(cs.contains(cs, key)) {
			key = (long)(Math.random()* 100000000);
		}
		return key;
	}

	/**
	 * Driver function to read file, take user input to feed into clever SIDC.
	 *
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner inputSc = new Scanner(System.in);
		CleverSIDC cs = new CleverSIDC();
		System.out.println("Please enter threshold value for SIDC : ");
		int threshold = inputSc.nextInt();
		cs.setSIDCThreshold(threshold);
		System.out.println("Enter Y to read the input from file or else enter N.");
		String inputType = inputSc.next();
		// If user whats to read the input from the file.
		if (inputType.equalsIgnoreCase("Y")) {
			System.out.println("Please enter file name to read the data with the extention : ");
			String inputFileStr = inputSc.next();
			File currentWorkingDir = new File(System.getProperty("user.dir"));
			File inputFile = new File(currentWorkingDir.getAbsolutePath() + "\\"+ inputFileStr);
			Scanner sc = new Scanner(inputFile);
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				Integer dataToInsert = Integer.parseInt(data);
				cs.add(cs, dataToInsert, "id_" + dataToInsert);
			}
			sc.close();
			System.out.println();
			System.out.println();
			System.out.println("Size of the Student tracking system before adding some stuff: " + cs.sizeCount);
			System.out.println();
			System.out.println("-----------------------------------------------------------------"
					+ "\nTesting some methods by adding some data other than from the file\n"
					+ "-----------------------------------------------------------------");
			cs.add(cs, 40350612, "S_" + "40350612");
			cs.add(cs, 40350613, "S_" + "40350613");
			System.out.println("Previous Key for 40350613: " + cs.prevKey(cs, 40350613));
			System.out.println("Next Key for 40350612: " + cs.nextKey(cs, 40350612));
			System.out.println("Existence of 89105565: " + cs.contains(cs, 89105565));
			System.out.println("Adding 89105565");
			cs.add(cs, 89105565, "S_" + "89105565");
			System.out.println("Existence of 89105565: " + cs.contains(cs, 89105565));
			System.out.println("Existence of 83747069: " + cs.contains(cs, 83747069));
			System.out.println("Size of the Student tracking system after adding data manually: " + cs.sizeCount);
			System.out.print("Number of keys between 22439726 and 69894475: ");
			System.out.println(cs.rangeKey(cs, 22439726, 69894475));
			System.out.println();
		// If user wants to generate the data randomly.
		} else {
			System.out.println("Enter Y to randomly generate the data or N to insert the data: ");
			String randomOrUserGiven = inputSc.next();
			if (randomOrUserGiven.equalsIgnoreCase("Y")) {
				System.out.println("Randomly generating " + threshold + " student IDs and trying to insert them.");
				for (int i = 0; i < threshold; i++) {
					long studentID = generate(cs);
					System.out.println("Generated student ID: " + studentID);
					cs.add(cs, studentID, "id_" + studentID);
				}
				System.out.println("Size of the Student tracking system: " + cs.sizeCount);
				System.out.println("All keys present in the Student tracking system in INCREASING ORDER:");
				long[] keys = cs.allKeys(cs);
				for(int i=0; i < keys.length; i++) {
					if(i%15 == 0) {
						System.out.println();
					}
					System.out.printf("%8d, ", keys[i]);
				}
				System.out.println();
				System.out.println("Size of the Student tracking system: " + cs.sizeCount);
				// Uncomment these lines of code to verify the shift of data structure
				 //at the threshold of 1000. 
				System.out.println("Adding 89105565");
				cs.add(cs, 89105565, "S_" + "89105565");
				System.out.println("Removing 89105565");
				cs.remove(cs, 89105565);
			// If user wants to enter the data manually.	
			} else {
				System.out.println("Enter student details for " + threshold + " number of records.");
				for (int i = 0; i < threshold; i++) {
					System.out.println("Enter student ID : ");
					int studentID = inputSc.nextInt();
					System.out.println("Enter student Info in format(Family Name, First Name, and DOB) : ");
					String studentInfo = inputSc.next();
					cs.add(cs, studentID, studentInfo);
				}
				System.out.println("All keys present in the Student tracking system in INCREASING ORDER: ");
				long[] keys = cs.allKeys(cs);
				for(int i=0; i < keys.length; i++) {
					if(i%15 == 0) {
						System.out.println();
					}
					System.out.printf("%8d", keys[i]);
				}
				System.out.println();
				System.out.println("Get values for student ID 89898989 returns : " + cs.getValues(cs, 89898989));
			}
		}
		inputSc.close();
	}
}