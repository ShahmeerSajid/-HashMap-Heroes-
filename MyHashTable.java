package finalproject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;




public class MyHashTable<K,V> implements Iterable<MyPair<K,V>>{
	// num of entries to the table
	private int size;
	// num of buckets 
	private int capacity = 16;
	// load factor needed to check for rehashing 
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<MyPair<K,V>>> buckets; 


	// constructors
	public MyHashTable() {
		this.size = 0;
		this.buckets = new ArrayList<>();
	}

	public MyHashTable(int initialCapacity) {
		// ADD YOUR CODE BELOW THIS
		this.capacity= initialCapacity;
		this.size = 0;
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
	public ArrayList<LinkedList< MyPair<K,V> > > getBuckets(){
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key. 
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode())%this.capacity;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {
		int bucketIndex = hashFunction(key);
		LinkedList<MyPair<K,V>> bucket_List = this.buckets.get(bucketIndex);
		// Over-writing the previous value with the new value...
		for (MyPair<K,V> pair : bucket_List) {
			if (pair.getKey().equals(key)) {
				V previous_value = pair.getValue();
				pair.setValue(value);
				return previous_value;
			}
		}
		//Creating an object of type MyPair<K,V> to add in the bucket list...
		MyPair<K,V> new_pair = new MyPair<K,V>(key,value);
		bucket_List.add(new_pair);
		this.size = this.size + 1;
		// To ensure that the load factor is lesser than max load factor...
		if(this.capacity/this.size > this.MAX_LOAD_FACTOR){
			rehash();
		}
		return null;
	}


	/**
	 * Get the value corresponding to key. Expected average runtime O(1)
	 */

	public V get(K key) {
		int bucket_index = hashFunction(key);
		LinkedList<MyPair<K,V>> bucket_list = this.buckets.get(bucket_index);
		for (MyPair<K,V> pair : bucket_list){
			if (pair.getKey().equals(key)){
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
		LinkedList<MyPair<K,V>> bucket_list = this.buckets.get(bucket_index);
		for (MyPair<K,V> pair : bucket_list){
			if (pair.getKey().equals(key)){
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
		int new_Capacity = this.buckets.size()*2;
		ArrayList<LinkedList<MyPair<K,V>>> new_Bucket_List = new ArrayList<>(new_Capacity);
		for (int i=0; i<new_Capacity;i++){
			new_Bucket_List.add(new LinkedList<MyPair<K,V>>());
		}
		for (int i=0; i<this.buckets.size(); i++){
			LinkedList<MyPair<K,V>> old_linked_list = this.buckets.get(i);
			for (MyPair<K,V> pair : old_linked_list){
				K key = pair.getKey();
				V value = pair.getValue();

				// Calculating the new hashcode for the key due to increased...
				// ... capacity of new bucket list
				int new_Bucket_Index = Math.abs(key.hashCode())%new_Capacity;
				LinkedList<MyPair<K,V>> new_linked_List = new_Bucket_List.get(new_Bucket_Index);
				new_linked_List.add(new MyPair<K,V>(key, value));
			}
		}
		this.buckets = new_Bucket_List;
		this.capacity = this.capacity*2;
	}

	/**
	 * Return a list of all the keys present in this hashtable.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */

	public ArrayList<K> getKeySet() {
		ArrayList<K> keys_array_list = new ArrayList<K>();
		for (int i=0; i<this.buckets.size();i++){
			LinkedList<MyPair<K,V>> linked_list = this.buckets.get(i);
			for(MyPair<K,V> pair : linked_list){
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
	public ArrayList<MyPair<K,V>> getEntries() {
		ArrayList<MyPair<K, V>> pairs_array_list = new ArrayList<MyPair<K, V>>();
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


	private class MyHashIterator implements Iterator<MyPair<K,V>> {
		private int curr_Index;
		private Iterator<MyPair<K,V>> curr_Iterator;
		private MyHashIterator() {
			int curr_Index = 0;
			curr_Iterator = null;
		}

		@Override
		public boolean hasNext() {
			if (curr_Iterator.hasNext() && curr_Iterator!=null){
				return true;
			}
			while (curr_Index < buckets.size()) {
				LinkedList<MyPair<K,V>> currentBucket = buckets.get(curr_Index);
				if (currentBucket.isEmpty()) {
					curr_Index = curr_Index+1;
				} else {
					curr_Iterator = currentBucket.iterator();
					return true;
				}
			}
			return false;
		}


		@Override
		public MyPair<K,V> next() {
			try{
				return curr_Iterator.next();
			}
			catch(Exception e){
				System.out.println("The next element does not exist...");
			}
			return null;
		}
	}
}
