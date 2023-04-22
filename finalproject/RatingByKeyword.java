package finalproject;

import java.util.ArrayList;
import java.util.HashMap;

public class RatingByKeyword extends DataAnalyzer {
	private MyHashTable<String, Integer> Hashmap_1;
	private MyHashTable<String, Integer> Hashmap_2;
	private MyHashTable<String, Integer> Hashmap_3;
	private MyHashTable<String, Integer> Hashmap_4;
	private MyHashTable<String, Integer> Hashmap_5;

	public RatingByKeyword(Parser p) {
		super(p);
	}


	// Helper method to determine the keyword count in each Hashmap...
	private int getCountByKeyword(String keyword, MyHashTable<String, Integer> hashMap) {
		Integer count = hashMap.get(keyword);
		if (count == null) {
			return 0;
		}
		return count;
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		MyHashTable<String, Integer> final_hashmap = new MyHashTable<>();
		String clean_keyword = keyword.toLowerCase().trim();

		int count_1 = getCountByKeyword(clean_keyword, Hashmap_1);
		final_hashmap.put("1", count_1);

		int count_2 = getCountByKeyword(clean_keyword, Hashmap_2);
		final_hashmap.put("2", count_2);

		int count_3 = getCountByKeyword(clean_keyword, Hashmap_3);
		final_hashmap.put("3", count_3);

		int count_4 = getCountByKeyword(clean_keyword, Hashmap_4);
		final_hashmap.put("4", count_4);

		int count_5 = getCountByKeyword(clean_keyword, Hashmap_5);
		final_hashmap.put("5", count_5);

		return final_hashmap;
	}

	@Override
	public void extractInformation() {
		Hashmap_1 = new MyHashTable<>();
		Hashmap_2 = new MyHashTable<>();
		Hashmap_3 = new MyHashTable<>();
		Hashmap_4 = new MyHashTable<>();
		Hashmap_5 = new MyHashTable<>();

		for (int i = 0; i < parser.data.size(); i++) {
			String[] data = parser.data.get(i);
			String rating = data[4];
			double rating1 = Double.parseDouble(rating);

			MyHashTable<String, Integer> current_Hashmap;
			if (rating1 >= 1 && rating1 < 2) {
				current_Hashmap = Hashmap_1;
			} else if (rating1 >= 2 && rating1 < 3) {
				current_Hashmap = Hashmap_2;
			} else if (rating1 >= 3 && rating1 < 4) {
				current_Hashmap = Hashmap_3;
			} else if (rating1 >= 4 && rating1 < 5) {
				current_Hashmap = Hashmap_4;
			} else {
				current_Hashmap = Hashmap_5;
			}

			String comments = data[6].toLowerCase();
			String output = comments.replaceAll("[^a-zA-Z']", " ");
			String[] word_arr = output.split("\\s+");

			ArrayList<String> my_array_list = new ArrayList<>();
			for (String word : word_arr) {
				if (!my_array_list.contains(word)) {
					my_array_list.add(word);
				}
			}

			for (String word : my_array_list) {
				Integer count = current_Hashmap.get(word);
				if (count == null) {
					current_Hashmap.put(word, 1);
				} else {
					current_Hashmap.put(word, count + 1);
				}
			}
		}
	}
}
