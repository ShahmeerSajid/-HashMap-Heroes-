package finalproject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class MyHashTable<K, V> implements Iterable<MyPair<K, V>> {
	// num of entries to the table
	private int size;
	// num of buckets 
	private int capacity = 16;
	// load factor needed to check for rehashing 
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<MyPair<K, V>>> buckets;


	private void ConstructorHelper() {
		this.size = 0;
		this.buckets = new ArrayList<>(this.capacity);
		for (int i = 0; i < this.capacity; i++) {
			this.buckets.add(new LinkedList<>());
		}
	}

	// constructors
	public MyHashTable() {
		ConstructorHelper();
	}

	public MyHashTable(int initialCapacity) {
		// ADD YOUR CODE BELOW THIS
		this.capacity = initialCapacity;
		ConstructorHelper();
		//ADD YOUR CODE ABOVE THIS
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int numBuckets() {
		return this.capacity;
	}

	/**
	 * Returns the buckets variable. Useful for testing  purposes.
	 */
	public ArrayList<LinkedList<MyPair<K, V>>> getBuckets() {
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key.
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode()) % this.capacity;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {
		int bucketIndex = hashFunction(key);
		LinkedList<MyPair<K, V>> bucket_List = this.buckets.get(bucketIndex);

		// Over-writing the previous value with the new value...
		for (MyPair<K, V> pair : bucket_List) {
			if (pair.getKey().equals(key)) {
				V previous_value = pair.getValue();
				pair.setValue(value);
				return previous_value;
			}
		}
		//Creating an object of type MyPair<K,V> to add in the bucket list...
		MyPair<K, V> new_pair = new MyPair<K, V>(key, value);
		bucket_List.add(new_pair);
		this.size = this.size + 1;
		// To ensure that the load factor is lesser than max load factor...
		if ((double) this.size / this.capacity > this.MAX_LOAD_FACTOR) {
			rehash();
		}
		return null;
	}


	/**
	 * Get the value corresponding to key. Expected average runtime O(1)
	 */

	public V get(K key) {
		int bucket_index = hashFunction(key);
		LinkedList<MyPair<K, V>> bucket_list = this.buckets.get(bucket_index);
		for (MyPair<K, V> pair : bucket_list) {
			if (pair.getKey().equals(key)) {
				return pair.getValue();
			}
		}
		return null;
	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1)
	 */
	public V remove(K key) {
		int bucket_index = hashFunction(key);
		LinkedList<MyPair<K, V>> bucket_list = this.buckets.get(bucket_index);
		for (MyPair<K, V> pair : bucket_list) {
			if (pair.getKey().equals(key)) {
				V value = pair.getValue();
				bucket_list.remove(pair);
				this.size--;
				return value;
			}
		}
		return null;
	}


	/**
	 * Method to double the size of the hashtable if load factor increases
	 * beyond MAX_LOAD_FACTOR.
	 * Made public for ease of testing.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */
	public void rehash() {
		this.capacity *= 2;
		ArrayList<LinkedList<MyPair<K, V>>> new_Bucket_List = new ArrayList<>(this.capacity);
		for (int i = 0; i < this.capacity; i++) {
			new_Bucket_List.add(new LinkedList<>());
		}
		for (int i = 0; i < this.buckets.size(); i++) {
			LinkedList<MyPair<K, V>> old_linked_list = this.buckets.get(i);
			for (MyPair<K, V> pair : old_linked_list) {
				K key = pair.getKey();

				// Calculating the new hashcode for the key due to increased...
				// ... capacity of new bucket list
				int new_Bucket_Index = hashFunction(key);
				LinkedList<MyPair<K, V>> new_linked_list = new_Bucket_List.get(new_Bucket_Index);
				new_linked_list.add(pair);
			}
		}
		this.buckets = new_Bucket_List;
	}

	/**
	 * Return a list of all the keys present in this hashtable.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */

	public ArrayList<K> getKeySet() {
		ArrayList<K> keys_array_list = new ArrayList<>();
		for (int i = 0; i < this.buckets.size(); i++) {
			LinkedList<MyPair<K, V>> linked_list = this.buckets.get(i);
			for (MyPair<K, V> pair : linked_list) {
				keys_array_list.add(pair.getKey());
			}
		}
		return keys_array_list;

	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<V> getValueSet() {
		ArrayList<V> values_array_list = new ArrayList<V>();
		for (int i = 0; i < this.buckets.size(); i++) {
			LinkedList<MyPair<K, V>> linked_list = this.buckets.get(i);
			for (MyPair<K, V> pair : linked_list) {
				V value = pair.getValue();
				// to check that the array list does not contain duplicate values...
				if (!values_array_list.contains(value)) {
					values_array_list.add(value);
				}
			}
		}
		return values_array_list;
	}


	/**
	 * Returns an ArrayList of all the key-value pairs present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<MyPair<K, V>> getEntries() {
		ArrayList<MyPair<K, V>> pairs_array_list = new ArrayList<>();
		for (int i = 0; i < this.buckets.size(); i++) {
			LinkedList<MyPair<K, V>> linked_list = this.buckets.get(i);
			for (MyPair<K, V> pair : linked_list) {
				pairs_array_list.add(pair);
			}
		}
		return pairs_array_list;
	}


	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}


	private class MyHashIterator implements Iterator<MyPair<K, V>> {
		private LinkedList<MyPair<K, V>> total_list;

		private MyHashIterator() {
			total_list = new LinkedList<>();
			for (int i = 0; i < capacity; i++) {
				LinkedList<MyPair<K, V>> linked_list = buckets.get(i);
				for (MyPair<K, V> pair : linked_list) {
					total_list.add(pair);
				}
			}
		}

		@Override
		public boolean hasNext() {
			return total_list.size() > 0;
		}


		@Override
		public MyPair<K, V> next() {
			return total_list.remove();
		}
	}
}
