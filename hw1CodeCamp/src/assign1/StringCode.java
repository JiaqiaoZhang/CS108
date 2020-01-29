package assign1;

import java.util.HashSet;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		int maxRun = 1;
		int current = 1;
		if(str.isEmpty()) {return 0;}
		for(int i = 0; i < str.length()-1; i++) {
			if(str.charAt(i) == str.charAt(i+1)) {
				current++;
			}else {
				current = 1;
			}
			if(current > maxRun) {
				maxRun = current;
			}
		}
		return maxRun; // TODO ADD YOUR CODE HERE
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		String result = "";
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) - '0' <= 9 && str.charAt(i)-'0' >=0) {
				if(i == str.length()-1) {return result;}
				int num = str.charAt(i) - '0';
				while(num > 0) {
					result += str.charAt(i+1);
					num--;
				}
			}else {
				result += str.charAt(i);
			}
		}		
		return result; // TODO ADD YOUR CODE HERE
	}	
}
