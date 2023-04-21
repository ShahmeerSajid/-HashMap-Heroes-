package finalproject;

import java.util.ArrayList;
import java.util.HashMap;

public class RatingByKeyword extends DataAnalyzer {

    public RatingByKeyword(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		// same as gender
		// strongly consider making helper that takes map and returns count

		return null;
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS

		// 5 hash maps for each rating number of String, Integer
		// String = keyword, Integer = num reviews

		// my_arraylist
		// for word in word_arr
		// !my_arraylist.contains(word) => add

		// for word in my_arraylist
		// determine hash map from review rating => rounding down
		// add to hashmap (1 if not exists, count + 1 otherwise)

		//ADD YOUR CODE ABOVE THIS
	}
}
