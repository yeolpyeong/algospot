/*
 * 너드인가, 너드가 아닌가? 2
 * https://algospot.com/judge/problem/read/NERD2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class NERD2 {
	static TreeMap<Integer, Integer> map;

	public static boolean isDominated(int x, int y) {
		int higher;
		if (map.higherKey(x) == null) {
			return false;
		} else {
			higher = map.higherKey(x);
		}
		return y < map.get(higher);
	}

	public static void removeDominated(int x, int y) {
		while (true) {
			int lower, temp = x;
			if (map.lowerKey(temp) == null) {
				return;
			} else {
				lower = map.lowerKey(temp);
			}

			if (y > map.get(lower)) {
				temp = lower;
				map.remove(lower);
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			int N = Integer.parseInt(br.readLine());
			map = new TreeMap<>();
			int sum = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				map.put(x, y);
				if (isDominated(x, y)) {
					map.remove(x);
				} else {
					removeDominated(x, y);
				}
				sum += map.size();
			}
			System.out.println(sum);
		}

	}
}
