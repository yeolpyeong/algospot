
/*
 * 게임판 덮기
 * https://algospot.com/judge/problem/read/BOARDCOVER
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOARDCOVER {
	static int[][][] coverType = { { { 0, 0 }, { 1, 0 }, { 0, 1 } }, { { 0, 0 }, { 0, 1 }, { 1, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 1, 1 } }, { { 0, 0 }, { 1, 0 }, { 1, -1 } } };

	public static boolean set(int[][] board, int cy, int cx, int type, int delta) {
		boolean flag = true;
		for (int i = 0; i < 3; i++) {
			int ny = cy + coverType[type][i][0];
			int nx = cx + coverType[type][i][1];
			if (ny < 0 || nx < 0 || ny >= board.length || nx >= board[0].length) {
				flag = false;
			} else if ((board[ny][nx] += delta) > 1) {
				flag = false;
			}
		}
		return flag;
	}

	public static int cover(int[][] board) {
		int cy = -1, cx = -1;
		loop: for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					cy = i;
					cx = j;
					break loop;
				}
			}
		}

		if (cy == -1) {
			return 1;
		}
		
		int count = 0;
		for (int type = 0; type < 4; type++) {
			if (set(board, cy, cx, type, 1)) {
				count += cover(board);
			}
			set(board, cy, cx, type, -1);
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int[][] board = new int[H][W];
			int white = 0;
			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					if (line.charAt(j) == '#') {
						board[i][j] = 2;
					} else {
						board[i][j] = 0;
						white++;
					}
				}
			}

			if (white % 3 == 0) {
				System.out.println(cover(board));
			} else {
				System.out.println(0);
			}
		}
	}
}
