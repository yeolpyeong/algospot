/*
 * Mismatched Brackets
 * https://algospot.com/judge/problem/read/BRACKETS2
 */

package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BRACKETS2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			String line = br.readLine();
			Stack<Integer> s = new Stack<>();
			s.push(0);
			for (int i = 0; i < line.length(); i++) {
				if (!pushAndPop(s, (int) line.charAt(i))) {
					s.push(0);
					break;
				}
			}

			System.out.println(s.size() == 1 ? "YES" : "NO");
		}
	}

	public static boolean pushAndPop(Stack<Integer> s, int n) {
		int[] opening = { '(', '{', '[' };

		int open = -1;
		for (int i = 0; i < 3; i++) {
			if (n == opening[i]) {
				open = i;
			}
		}

		if (open != -1) {
			if (open == 0) {
				s.push(39);
			} else {
				s.push(n);
			}
		} else {
			if (s.peek() + 2 != n) {
				return false;
			} else {
				s.pop();
			}
		}

		return true;
	}
}
