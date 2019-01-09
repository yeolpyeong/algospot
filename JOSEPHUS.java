/*
 * JOSEPHUS
 * https://algospot.com/judge/problem/read/JOSEPHUS
 */

package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JOSEPHUS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			Queue<Integer> q = new LinkedList<Integer>();
			for (int i = 1; i <= N; i++) {
				q.add(i);
			}
			while (q.size() > 2) {
				q.poll();
				for (int i = 0; i < K - 1; i++) {
					q.add(q.poll());
				}
			}

			int[] survivors = { q.poll(), q.poll() };
			Arrays.sort(survivors);
			System.out.println(survivors[0] + " " + survivors[1]);
		}
	}
}
