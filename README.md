# Dynamic_DataStructure
The North American Student Tracking Association (NASTA) maintains and operates multiple lists of
students. Some of these student lists are local to villages, towns and remote areas, where the lists
contain only a few hundred students each, and possibly less. Others are in large urban centers where
lists may contain tens of thousands or more students each.
Each student is identified by a unique 8-digit code, called StudentIDentificationCode (SIDC); (e.g. #
SIDC: 47203235).
NASTA needs the help to design a clever “student tracking” ADT called CleverSIDC. Keys of
CleverSIDC entries are long integers of 8 digits, and one can retrieve the keys/values of an
CleverSIDC or access a single element by its key. Furthermore, similar to Sequences, given a
CleverSIDC key, one can access its predecessor or successor.
CleverSIDC adapts to their usage and keep the balance between memory and runtime requirements.
For instance, if an CleverSIDC contains only a small number of entries (e.g., few hundreds), it might
use less memory overhead but slower (sorting) algorithms. On the other hand, if the number of
contained entries is large (greater than 1000 or even in the range of tens of thousands of elements), it
might have a higher memory requirement but faster (sorting) algorithms. CleverSIDC might be almost
constant in size or might grow and/or shrink dynamically. Ideally, operations applicable to a single
CleverSIDC entry should be O(1) but never worse than O(n). Operations applicable to a complete
CleverSIDC should not exceed O(n2).
The CleverSIDC ADT, which automatically adapts to the dynamic content that it operates on.
In other words, it accepts the size (total number of students, n, identified by their
8 digits SIDC number as a key) as a parameter and uses an appropriate (set of)
data structure(s), or other data types, from the one(s) studied in class in order to perform the operations
below efficiently1. You are NOT allowed however to use any of the built-in data types (that is, you
must implement whatever you need, for instance, linked lists, expandable arrays, hash tables, etc.
yourself).
The CleverSIDC must implement the following methods:
• SetSIDCThreshold (Size): where 100 ≤ Size ≤ ~500,000 is an integer number that defines
the size of the list. This size is very important as it will determine what data types or data
structures will be used (i.e. a Tree, Hash Table, AVL tree, binary tree, sequence, etc.);
• generate(): randomly generates new non-existing key of 8 digits;
• allKeys(CleverSIDC): return all keys in CleverSIDC as a sorted sequence;
• add(CleverSIDC,key,value2): add an entry for the given key and value;
• remove(CleverSIDC,key): remove the entry for the given key;
• getValues(CleverSIDC,key): return the values of the given key;
• nextKey(CleverSIDC,key): return the key for the successor of key;
• prevKey(CleverSIDC,key): return the key for the predecessor of key;
• rangeKey(key1, key2): returns the number of keys that are within the specified range of
the two keys key1 and key2.
