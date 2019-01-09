/*
 * 트리 순회 순서 변경
 * https://algospot.com/judge/problem/read/TRAVERSAL
 */

package algospot;

import java.util.Arrays;
import java.util.Scanner;

public class TRAVERSAL {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		while (C-- > 0) {
			int N = sc.nextInt();
			int[] preorder = new int[N];
			for (int i = 0; i < N; i++) {
				preorder[i] = sc.nextInt();
			}
			int[] inorder = new int[N];
			for (int i = 0; i < N; i++) {
				inorder[i] = sc.nextInt();
			}

			printPostorder(preorder, inorder);
			System.out.println();
		}
	}

	public static void printPostorder(int[] preorder, int[] inorder) {
		int root = preorder[0];
		int totalOfNodes = preorder.length;

		for (int i = 0; i < totalOfNodes; i++) {
			if (inorder[i] == root) {
				int countOfLeftNodes = i;
				int countOfRightNodes = totalOfNodes - 1 - countOfLeftNodes;

				if (countOfLeftNodes > 0) {
					int[] leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + countOfLeftNodes);
					int[] leftInorder = Arrays.copyOfRange(inorder, 0, countOfLeftNodes);
					printPostorder(leftPreorder, leftInorder);
				}
				if (countOfRightNodes > 0) {
					int[] rightPreorder = Arrays.copyOfRange(preorder, totalOfNodes - countOfRightNodes, totalOfNodes);
					int[] rightInorder = Arrays.copyOfRange(inorder, totalOfNodes - countOfRightNodes, totalOfNodes);
					printPostorder(rightPreorder, rightInorder);
				}

				System.out.print(root + " ");
			}
			continue;
		}
	}
}
