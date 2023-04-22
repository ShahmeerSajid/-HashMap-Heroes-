package finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GenderByKeyword extends DataAnalyzer {
	// 3 hash maps store String, Integer
	private MyHashTable<String, Integer> M_Hashmap;
	private MyHashTable<String, Integer> W_Hashmap;
	private MyHashTable<String, Integer> X_Hashmap;

	public GenderByKeyword(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		MyHashTable<String, Integer> result_hashmap = new MyHashTable<>();
		String clean_keyword = keyword.toLowerCase().trim();

		Integer M_count = M_Hashmap.get(clean_keyword);
		if (M_count == null) {
			M_count = 0;
		}
		result_hashmap.put("M", M_count);

		Integer W_count = W_Hashmap.get(clean_keyword);
		if (W_count == null) {
			W_count = 0;
		}
		result_hashmap.put("F", W_count);

		Integer X_count = X_Hashmap.get(clean_keyword);
		if (X_count == null) {
			X_count = 0;
		}
		result_hashmap.put("X", X_count);

		return result_hashmap;
	}

	@Override
	public void extractInformation() {
		M_Hashmap = new MyHashTable<>();
		W_Hashmap = new MyHashTable<>();
		X_Hashmap = new MyHashTable<>();

		for (int i = 0; i < parser.data.size(); i++) {
			String[] data = parser.data.get(i);
			String gender = data[7];
			MyHashTable<String, Integer> current_Hashmap;
			if (gender.equals("M")) {
				current_Hashmap = M_Hashmap;
			} else if (gender.equals("F")) {
				current_Hashmap = W_Hashmap;
			} else {
				current_Hashmap = X_Hashmap;
			}

			String comments = data[6].toLowerCase();
			String output = comments.replaceAll("[^a-zA-Z']", " ");
			String[] word_arr = output.split("\\s+");
			for (String word : word_arr) {
				Integer count = current_Hashmap.get(word);
				if (count == null) {
					current_Hashmap.put(word, 1);
				} else {
					current_Hashmap.put(word, count + 1);
				}
			}
		}
//		MyHashTable<String, Integer> res1 = getDistByKeyword("caring");
//		for (String key : res1.getKeySet()) {
//			System.out.println(key + ": " + res1.get(key));
//		}
//		res1 = getDistByKeyword("smart");
//		for (String key : res1.getKeySet()) {
//			System.out.println(key + ": " + res1.get(key));
//		}
	}

}
