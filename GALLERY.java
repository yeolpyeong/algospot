/*
 * 감시 카메라 설치
 * https://algospot.com/judge/problem/read/GALLERY
 */

import java.util.ArrayList;
import java.util.Scanner;

public class GALLERY {
	static int installed;
	static final int UNWATCHED = 0, WATCHED = 1, INSTALLED = 2;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> adj;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		while (C-- > 0) {
			int g = sc.nextInt();
			int h = sc.nextInt();
			adj = new ArrayList<>();
			for (int i = 0; i < g; i++) {
				adj.add(new ArrayList<>());
			}
			for (int i = 0; i < h; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				adj.get(a).add(b);
				adj.get(b).add(a);
			}

			installed = 0;
			visited = new boolean[g];
			for (int i = 0; i < g; i++) {
				if (!visited[i] && dfs(i) == UNWATCHED) {
					installed++;
				}
			}

			System.out.println(installed);
		}
	}

	public static int dfs(int here) {
		visited[here] = true;
		int[] children = { 0, 0, 0 };
		for (int i = 0; i < adj.get(here).size(); i++) {
			int there = adj.get(here).get(i);
			if (!visited[there]) {
				children[dfs(there)]++;
			}
		}

		if (children[UNWATCHED] > 0) {
			installed++;
			return INSTALLED;
		} else if (children[INSTALLED] > 0) {
			return WATCHED;
		}
		return UNWATCHED;
	}
}
