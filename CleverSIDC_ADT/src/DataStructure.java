/**
 * This is an interface for the Hashtable and Binary Search Tree.
 * @author Harmanvir Singh (40019114)
 * @author Sarabpreet Singh Rekhi (40154067)
 *
 */
public interface DataStructure {
	/**
	 * Adds new element with the key and value.
	 * @param key
	 * @param value
	 */
	public boolean addElement(long key, String value);

	/**
	 * Removes element with the key passed.
	 * @param key
	 */
	public boolean remove(long key);

	/**
	 * returns all elements present in the DataStructure.
	 */
	public long[] allKeys();

	/**
	 * Finds the successor key.
	 * @param key
	 * @return
	 */
	public long nextKey(long key);

	/**
	 * Finds the previous key
	 * @param key
	 * @return
	 */
	public long prevKey(long key);

	/**
	 * 
	 * @param key1
	 * @param key2
	 * @return
	 */
	public int rangeKey(long key1, long key2);

	/**
	 * Check if the key is present in the data.
	 * @param key
	 * @return
	 */
	public boolean contains(long key);

	/**
	 * It returns the value of the key passed as the parameter
	 * @param key
	 * @return
	 */
	public String getValues(long key);
}
