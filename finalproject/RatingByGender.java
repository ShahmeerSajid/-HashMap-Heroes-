package finalproject;

import java.util.ArrayList;

public class RatingByGender extends DataAnalyzer {
	private MyHashTable<String, Integer> F_quality;
	private MyHashTable<String, Integer> F_difficulty;
	private MyHashTable<String, Integer> M_quality;
	private MyHashTable<String, Integer> M_difficulty;

	public RatingByGender(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		String output = keyword.toLowerCase().trim().replaceAll("\\s+", "");
		String[] word_array = output.split(",");
		String gender = word_array[0];
		String review = word_array[1];

		if (gender.equals("m")) {
			if (review.equals("difficulty")) {
				return M_difficulty;
			} else if (review.equals("quality")) {
				return M_quality;
			}
		} else if (gender.equals("f")) {
			if (review.equals("difficulty")) {
				return F_difficulty;
			} else if (review.equals("quality")) {
				return F_quality;
			}
		}
		return null;
	}

	@Override
	public void extractInformation() {
		F_quality = new MyHashTable<>();
		F_difficulty = new MyHashTable<>();
		M_quality = new MyHashTable<>();
		M_difficulty = new MyHashTable<>();

		for (int i = 0; i < parser.data.size(); i++) {
			String[] data = parser.data.get(i);
			String gender = data[7];
			Integer quality = (int) Double.parseDouble(data[4]);
			Integer difficulty = (int) Double.parseDouble(data[5]);

			if (gender.equals("M")) {
				Integer quality_value = M_quality.get(quality.toString());
				if (quality_value == null) {
					M_quality.put(quality.toString(), 1);
				} else {
					M_quality.put(quality.toString(), quality_value + 1);
				}

				Integer difficulty_value = M_difficulty.get(difficulty.toString());
				if (difficulty_value == null) {
					M_difficulty.put(difficulty.toString(), 1);
				} else {
					M_difficulty.put(difficulty.toString(), difficulty_value + 1);
				}
			} else if (gender.equals("F")) {
				Integer quality_value = F_quality.get(quality.toString());
				if (quality_value == null) {
					F_quality.put(quality.toString(), 1);
				} else {
					F_quality.put(quality.toString(), quality_value + 1);
				}

				Integer difficulty_value = F_difficulty.get(difficulty.toString());
				if (difficulty_value == null) {
					F_difficulty.put(difficulty.toString(), 1);
				} else {
					F_difficulty.put(difficulty.toString(), difficulty_value + 1);
				}
			}
		}
	}
}
