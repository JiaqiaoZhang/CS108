package assign1;

import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		HashMap<Integer, Integer> mapA = new HashMap<>();
		HashMap<Integer, Integer> mapB = new HashMap<>();
		for(T element : a) {
			if(mapA.get(element.hashCode()) == null) {
				mapA.put(element.hashCode(), 1);
			}else {
				int valueA = mapA.get(element.hashCode()) + 1;
				mapA.put(element.hashCode(), valueA);
			}
		}
		
		for(T element : b) {
			if(mapB.get(element.hashCode()) == null) {
				mapB.put(element.hashCode(), 1);
			}else {
				int valueB = mapB.get(element.hashCode()) + 1;
				mapB.put(element.hashCode(), valueB);
			}
		}
		int count = 0;
		Set<Integer> keys = mapA.keySet();
		for(int k : keys) {
			if(mapA.get(k) == mapB.get(k)) {
				count ++;
			}
		}

		return count; // TODO ADD CODE HERE
	}
	
}
