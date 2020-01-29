/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/
package assign1;

import java.util.*;

public class Taboo<T> {
	private HashMap<Integer, Set<T>> ruleMap = new HashMap<>();
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		for(int i = 0; i < rules.size() - 1; i++) {
			if(rules.get(i) == null || rules.get(i+1) == null) {continue;}
			else {
				int key = rules.get(i).hashCode();
				T elem2 = rules.get(i+1);

				if(this.ruleMap.get(key) == null) {
					Set<T> value = new HashSet<T>();
					value.add(elem2);
					this.ruleMap.put(key, value);
				}else {
					this.ruleMap.get(key).add(elem2);
				}
			}
		}
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		 return this.ruleMap.get(elem.hashCode()); // TODO YOUR CODE HERE
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		for(int i = 1; i < list.size(); i++) {
			T elem = list.get(i);
			if(list.get(i-1) == null || elem == null) {continue;}
			int key = list.get(i-1).hashCode();
			if(ruleMap.get(key) == null) {continue;}
			if(this.ruleMap.get(key).contains(elem)) {
				list.remove(i);
				i--;
			}
			
		}
	}
}
