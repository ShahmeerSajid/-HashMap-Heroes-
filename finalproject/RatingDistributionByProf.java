package finalproject;

import javafx.util.Pair;


public class RatingDistributionByProf extends DataAnalyzer {
	private MyHashTable<String, Integer[]> my_hash_table;

	public RatingDistributionByProf(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		System.out.println("I AM HERE");
		// ADD YOUR CODE BELOW THIS
		MyHashTable<String, Integer> hash_table = new MyHashTable<>();

		System.out.println(keyword);
		Integer[] values = my_hash_table.get(keyword);
		if (values == null) {
			hash_table.put("1", 0);
			hash_table.put("2", 0);
			hash_table.put("3", 0);
			hash_table.put("4", 0);
			hash_table.put("5", 0);

			return hash_table;
		}

		hash_table.put("1", values[0]);
		hash_table.put("2", values[0]);
		hash_table.put("3", values[0]);
		hash_table.put("4", values[0]);
		hash_table.put("5", values[0]);

		return hash_table;
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		my_hash_table = new MyHashTable<>(parser.data.size());
		for (int i = 0; i < parser.data.size(); i++) {
			String[] data = parser.data.get(i);

			String name = data[0];
			int rating = (int) Double.parseDouble(data[4]);

			Integer[] values = my_hash_table.get(name);
			if (values == null) {
				values = new Integer[5];
				my_hash_table.put(name, values);
			}
			int index = rating - 1;
			if (values[index] == null) {
				values[index] = 1;
			} else {
				values[index] = values[index] + 1;
			}
		}
		System.out.println(my_hash_table.get("Robert  Olshansky")[2]);
		//ADD YOUR CODE ABOVE THIS
	}

}
