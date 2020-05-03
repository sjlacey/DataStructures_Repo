package projectCode20280;

import javax.xml.stream.events.EntityReference;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
	// a fixed capacity array of UnsortedTableMap that serve as buckets
	private UnsortedTableMap<K, V>[] table; // initialized within createTable

	/** Creates a hash table with capacity 11 and prime factor 109345121. */
	public ChainHashMap() {
		super();
	}

	/** Creates a hash table with given capacity and prime factor 109345121. */
	public ChainHashMap(int cap) {
		super(cap);
	}

	/** Creates a hash table with the given capacity and prime factor. */
	public ChainHashMap(int cap, int p) {
		super(cap, p);
	}

	/** Creates an empty table having length equal to current capacity. */
	@Override
	@SuppressWarnings({ "unchecked" })
	protected void createTable() {
		table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
	}

	/**
	 * Returns value associated with key k in bucket with hash value h. If no such
	 * entry exists, returns null.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return associate value (or null, if no such entry)
	 */
	@Override
	protected V bucketGet(int h, K k) {
		UnsortedTableMap<K, V> bucket = table[h];

		if(bucket==null) {
			return null;
		} else {
			return bucket.get(k);
		}
 	}

	/**
	 * Associates key k with value v in bucket with hash value h, returning the
	 * previously associated value, if any.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @param v the value to be associated
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketPut(int h, K k, V v) {
		UnsortedTableMap<K, V> bucket = table[h];

		if(bucket==null) {
			bucket = new UnsortedTableMap<K, V>();
			table[h] = bucket;
		}
		int prevSize = bucket.size();
		V temp = bucket.put(k,v);
		n+= bucket.size()-prevSize; //increments n by 1 if and only if an element has been added to the HashTable
		//error with: System.out.println("bucketPut: " +h+ "  " +k+ "  " +v+ "  " +bucket);
		return temp;
	}

	/**
	 * Removes entry having key k from bucket with hash value h, returning the
	 * previously associated value, if found.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketRemove(int h, K k) {
		UnsortedTableMap<K, V> bucket = table[h];
		if(bucket==null) {
			return null;
		}
		int prevSize = bucket.size();
		V temp = bucket.remove(k);
		n -= prevSize-bucket.size();
		return temp;
	}

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> result = new ArrayList<Entry<K,V>>(); //TODO order not guaranteed here, "construct arraylist and sort elements in the arrayList"

		for(int i=0; i<capacity; ++i) {
			UnsortedTableMap<K,V> bucket = table[i];
			if(bucket!=null) {
				for(Entry<K,V> entry : bucket.entrySet()) {
					result.add(entry);
				}
			}
		}
		return result;
	}

	public String toString() {
		return entrySet().toString();
	}

	public static void main(String[] args) {
		//HashMap<Integer, String> m = new HashMap<Integer, String>();
		ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();
		//System.out.println("Size before: " +m.size());
		//String n = m.put(1, "One");
		//String n1 = m.put(2, "Two");
		//System.out.println("Size before: " +m.size()+ " -> " +n);
		//System.out.println(m);

		//m.remove(1);
		//System.out.println("Size after: " +m.size());

		m.put(1, "One");
		m.put(10, "Ten");
		m.put(11, "Eleven");
		m.put(20, "Twenty");
		
		System.out.println("m: " + m);
		
		m.remove(11);
		System.out.println("m: " + m);

		ChainHashMap<String, Integer> counter = new ChainHashMap<String, Integer>();
		File file = new File("src/projectCode20280/ChainHashMapText.txt");
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String word = scanner.next();
				Integer temp;
				try {
					temp = counter.get(word);
					counter.put(word, temp+1);

				} catch(NullPointerException E) {
					counter.put(word, 1);
				}
			}
		} catch (FileNotFoundException E) {
			System.out.println("File not detected.");
		}

		System.out.println(counter.entrySet().toString());
	}
}
