/*
 * µî»ê·Î
 * https://algospot.com/judge/problem/read/MORDOR
 */

package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MORDOR {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			int[] h = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				h[i] = Integer.parseInt(st.nextToken());
			}

			int closestPowerOfTwo = (int) Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)));
			int[] maxSegmentTree = new int[2 * closestPowerOfTwo];
			int[] minSegmentTree = new int[2 * closestPowerOfTwo];
			generateSegmentTree(h, maxSegmentTree, 0, 0, N - 1);
			for (int i = 0; i < N; i++) {
				h[i] *= -1;
			}
			generateSegmentTree(h, minSegmentTree, 0, 0, N - 1);

			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				System.out.println(query(maxSegmentTree, a, b, 0, 0, N - 1) + query(minSegmentTree, a, b, 0, 0, N - 1));
			}
		}
	}

	public static int generateSegmentTree(int[] h, int[] segmentTree, int node, int left, int right) {
		if (left == right) {
			return segmentTree[node] = h[left];
		}

		int middle = (left + right) / 2;
		int maxLeft = generateSegmentTree(h, segmentTree, 2 * node + 1, left, middle);
		int maxRight = generateSegmentTree(h, segmentTree, 2 * node + 2, middle + 1, right);
		return segmentTree[node] = Math.max(maxLeft, maxRight);
	}

	public static int query(int[] segmentTree, int a, int b, int node, int left, int right) {
		if (a > right || b < left) {
			return Integer.MIN_VALUE;
		}

		if (a <= left && b >= right) {
			return segmentTree[node];
		}

		int middle = (left + right) / 2;
		return Math.max(query(segmentTree, a, b, 2 * node + 1, left, middle),
				query(segmentTree, a, b, 2 * node + 2, middle + 1, right));
	}
}
