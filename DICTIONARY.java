/*
 * 고대어 사전
 * https://algospot.com/judge/problem/read/DICTIONARY
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DICTIONARY {
	static String dictionary, alphabet = "abcdefghijklmnopqrstuvwxyz";
	static boolean[][] adj;

	public static void generateAdj(String[] words) {
		adj = new boolean[26][26];
		for (int i = 0; i < words.length - 1; i++) {
			int min = Math.min(words[i].length(), words[i + 1].length());
			for (int pos = 0; pos < min; pos++) {
				int a = words[i].charAt(pos) - 'a';
				int b = words[i + 1].charAt(pos) - 'a';
				if (a != b) {
					adj[a][b] = true;
					break;
				}
			}
		}
	}

	public static void dfs(int i, boolean[] visited) {
		visited[i] = true;
		for (int j = 0; j < 26; j++) {
			if (adj[i][j] && !visited[j]) {
				dfs(j, visited);
			}
		}
		dictionary += alphabet.charAt(i);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			int N = Integer.parseInt(br.readLine());
			String[] words = new String[N];
			for (int i = 0; i < N; i++) {
				words[i] = br.readLine();
			}

			generateAdj(words);
			boolean[] visited = new boolean[26];
			dictionary = "";
			for (int i = 0; i < 26; i++) {
				if (!visited[i]) {
					dfs(i, visited);
				}
			}

			boolean hypothesis = true;
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (adj[i][j] && adj[j][i]) {
						dictionary = "SISEHTOPYH DILAVNI";
					}
				}
			}

			System.out.println(new StringBuffer(dictionary).reverse());
		}
	}
}
