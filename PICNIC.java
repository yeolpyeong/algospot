
/*
 * 소풍
 * https://algospot.com/judge/problem/read/PICNIC
 */

import java.util.Scanner;

public class PICNIC {
	static int n;
	static boolean[][] adj;

	public static int countPairing(boolean[] visited) {
		int first = -1;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				first = i;
				break;
			}
		}

		if (first == -1) {
			return 1;
		}

		int count = 0;
		for (int second = first + 1; second < n; second++) {
			if (!visited[second] && adj[first][second]) {
				visited[first] = visited[second] = true;
				count += countPairing(visited);
				visited[first] = visited[second] = false;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		while (C-- > 0) {
			n = sc.nextInt();
			int m = sc.nextInt();
			adj = new boolean[n][n];
			while (m-- > 0) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				adj[a][b] = adj[b][a] = true;
			}

			boolean[] visited = new boolean[n];
			System.out.println(countPairing(visited));
		}
	}
}
