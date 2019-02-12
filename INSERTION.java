/*
 * 삽입 정렬 뒤집기
 * https://algospot.com/judge/problem/read/INSERTION
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class INSERTION {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N + 1];
			int[] moving = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				A[i] = i;
				moving[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = N; i >= 1; i--) {
				int temp = A[i - moving[i]];
				for (int j = i - moving[i]; j < i; j++) {
					A[j] = A[j + 1];
				}
				A[i] = temp;
			}

			for (int i = 1; i <= N; i++) {
				System.out.print(A[i] + " ");
			}
			System.out.println();
		}
	}
}
