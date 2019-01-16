/*
 * 변화하는 중간 값
 * https://algospot.com/judge/problem/read/RUNNINGMEDIAN
 */

package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class RUNNINGMEDIAN {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			long A = 1983;
			int mod = 20090711;
			long sum = 0;
			PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Long> minHeap = new PriorityQueue<>();
			int sizeOfMaxHeap = 0;
			int sizeOfMinHeap = 0;
			for (int i = 1; i <= N; i++) {
				if (sizeOfMaxHeap == sizeOfMinHeap) {
					maxHeap.add(A);
					sizeOfMaxHeap++;
				} else {
					minHeap.add(A);
					sizeOfMinHeap++;
				}

				if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
					maxHeap.add(minHeap.poll());
					minHeap.add(maxHeap.poll());
				}

				A = (A * a + b) % mod;
				sum = (sum + maxHeap.peek()) % mod;
			}
			System.out.println(sum);
		}
	}
}
