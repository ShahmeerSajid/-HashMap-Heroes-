package finalproject;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class RatingDistributionBySchool extends DataAnalyzer {
	private MyHashTable<String, MyHashTable<String, Integer>> my_hash_table;

	public RatingDistributionBySchool(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		return my_hash_table.get(keyword);
	}

	@Override
	public void extractInformation() {
		MyHashTable<String, MyHashTable<String, ArrayList<Double>>> temp_table = new MyHashTable<>(parser.data.size());
		for (int i = 0; i < parser.data.size(); i++) {
			String[] data = parser.data.get(i);
			String school_name = data[1];
			String prof_name = data[0];

			MyHashTable<String, ArrayList<Double>> hash_table_2 = temp_table.get(school_name);
			if (hash_table_2 == null) {
				hash_table_2 = new MyHashTable<>();
				temp_table.put(school_name, hash_table_2);
			}

			ArrayList<Double> ratings = hash_table_2.get(prof_name);
			if (ratings == null) {
				ratings = new ArrayList<>();
				hash_table_2.put(prof_name, ratings);
			}
			ratings.add(Double.parseDouble(data[4]));
		}

		my_hash_table = new MyHashTable<>(parser.data.size());
		ArrayList<String> school_names = temp_table.getKeySet();
		for (int i = 0; i < school_names.size(); i++) {
			String school_name = school_names.get(i);
			MyHashTable<String, ArrayList<Double>> school_entry = temp_table.get(school_name);

			ArrayList<String> prof_names = school_entry.getKeySet();
			MyHashTable<String, Integer> final_school_entry = new MyHashTable<>();

			for (int j = 0; j < prof_names.size(); j++) {
				String prof_name = prof_names.get(j);
				ArrayList<Double> ratings = school_entry.get(prof_name);

				double ratings_sum = 0;
				for (int k = 0; k < ratings.size(); k++) {
					ratings_sum += ratings.get(k);
				}
				Double ratings_avg = ratings_sum / ratings.size();

				String avg = ratings_avg.toString();
				int places = avg.length() - avg.indexOf('.') - 1;
				if (places > 1) {
					avg = String.format("%.2f", ratings_avg);
				}

				String key = prof_name.toLowerCase().trim() + "\n" + avg;
				final_school_entry.put(key, ratings.size());
			}

			my_hash_table.put(school_name, final_school_entry);
		}
	}
}

