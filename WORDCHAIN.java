/*
 * 단어 제한 끝말잇기
 * https://algospot.com/judge/problem/read/WORDCHAIN
 */

package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class WORDCHAIN {
	static int[][] adj;
	static Map<Integer, ArrayList<String>> dictionary;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] outdegree = new int[26];
			int[] indegree = new int[26];
			adj = new int[26][26];
			dictionary = new HashMap<>();
			for (int i = 0; i < N; i++) {
				String word = br.readLine();
				int first = word.charAt(0) - 'a';
				int last = word.charAt(word.length() - 1) - 'a';
				outdegree[first]++;
				indegree[last]++;
				adj[first][last]++;
				int key = first * 26 + last;
				if (dictionary.containsKey(key)) {
					dictionary.get(key).add(word);
				} else {
					ArrayList<String> temp = new ArrayList<>();
					temp.add(word);
					dictionary.put(key, temp);
				}
			}

			boolean flag = true;
			Stack<String> wordchain = new Stack<>();
			if (isEuler(indegree, outdegree)) {
				for (int first = 0; first < 26; first++) {
					if (outdegree[first] == indegree[first] + 1) {
						getEuler(wordchain, first);
						break;
					}
				}

				for (int first = 0; first < 26; first++) {
					if (outdegree[first] > 0) {
						getEuler(wordchain, first);
						break;
					}
				}

				if (wordchain.size() != N) {
					flag = false;
				}
			} else {
				flag = false;
			}

			if (flag) {
				while (!wordchain.isEmpty()) {
					System.out.print(wordchain.pop() + " ");
				}
			} else {
				System.out.print("IMPOSSIBLE");
			}
			System.out.println();
		}
	}

	public static boolean isEuler(int[] outdegree, int[] indegree) {
		int difference = 0;
		for (int alphabet = 0; alphabet < 26; alphabet++) {
			difference += Math.abs(outdegree[alphabet] - indegree[alphabet]);
		}

		return difference == 0 || difference == 2 ? true : false;
	}

	public static void getEuler(Stack<String> wordchain, int first) {
		for (int last = 0; last < 26; last++) {
			if (adj[first][last] > 0) {
				adj[first][last]--;
				getEuler(wordchain, last);

				wordchain.push(dictionary.get(first * 26 + last).remove(0));
			}
		}
	}
}
