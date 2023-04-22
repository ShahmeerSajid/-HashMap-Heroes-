package finalproject;

import javafx.util.Pair;


public class RatingDistributionByProf extends DataAnalyzer {
	private MyHashTable<String, Integer[]> my_hash_table;

	public RatingDistributionByProf(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		MyHashTable<String, Integer> hash_table = new MyHashTable<>();

		Integer[] values = my_hash_table.get(keyword.toLowerCase().trim());
		if (values == null) {
			hash_table.put("1", 0);
			hash_table.put("2", 0);
			hash_table.put("3", 0);
			hash_table.put("4", 0);
			hash_table.put("5", 0);

			return hash_table;
		}

		hash_table.put("1", values[0]);
		hash_table.put("2", values[1]);
		hash_table.put("3", values[2]);
		hash_table.put("4", values[3]);
		hash_table.put("5", values[4]);

		return hash_table;
	}

	@Override
	public void extractInformation() {
		my_hash_table = new MyHashTable<>(parser.data.size());
		for (int i = 0; i < parser.data.size(); i++) {
			String[] data = parser.data.get(i);

			String name = data[0].toLowerCase().trim();
			int rating = (int) Double.parseDouble(data[4]);

			Integer[] values = my_hash_table.get(name);
			if (values == null) {
				values = new Integer[]{0, 0, 0, 0, 0};
				my_hash_table.put(name, values);
			}

			int index = rating - 1;
			if (values[index] == null) {
				values[index] = 1;
			} else {
				values[index] = values[index] + 1;
			}
		}
	}

}
