
/*
 * 철인 N종 경기
 * https://algospot.com/judge/problem/read/NTHLON
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NTHLON {
	static final int START = 401, INF = Integer.MAX_VALUE;

	static class Edge implements Comparable<Edge> {
		int v, m;

		public Edge(int v, int m) {
			this.v = v;
			this.m = m;
		}

		@Override
		public int compareTo(Edge e) {
			if (this.m > e.m) {
				return 1;
			} else if (this.m < e.m) {
				return -1;
			}
			return 0;
		}
	}

	static int V;
	static ArrayList<ArrayList<Edge>> adj;

	static int vertex(int delta) {
		return delta + 200;
	}

	static int[] dijkstra(int src) {
		int[] dist = new int[V];
		for (int i = 0; i < V; i++) {
			dist[i] = i == src ? 0 : INF;
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(src, dist[src]));
		while (!pq.isEmpty()) {
			int here = pq.peek().v;
			int minute = pq.peek().m;
			pq.poll();

			if (dist[here] < minute) {
				continue;
			}

			for (Edge e : adj.get(here)) {
				int there = e.v;
				int nextDist = minute + e.m;

				if (nextDist < dist[there]) {
					dist[there] = nextDist;
					pq.add(new Edge(there, nextDist));
				}
			}
		}
		return dist;
	}

	static int solve(int[] A, int[] B) {
		V = 402;
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
		for (int i = 0; i < A.length; i++) {
			int delta = A[i] - B[i];
			adj.get(START).add(new Edge(vertex(delta), A[i]));
		}

		for (int delta = -200; delta <= 200; delta++) {
			for (int i = 0; i < A.length; i++) {
				int next = delta + A[i] - B[i];

				if (Math.abs(next) > 200) {
					continue;
				}

				adj.get(vertex(delta)).add(new Edge(vertex(next), A[i]));
			}
		}

		int[] shortest = dijkstra(START);
		int ret = shortest[vertex(0)];
		if (ret == INF) {
			return -1;
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			int M = Integer.parseInt(br.readLine());
			int[] A = new int[M];
			int[] B = new int[M];
			StringTokenizer st;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				A[i] = Integer.parseInt(st.nextToken());
				B[i] = Integer.parseInt(st.nextToken());
			}

			adj = new ArrayList<>();
			int min = solve(A, B);
			System.out.println(min != -1 ? min : "IMPOSSIBLE");
		}
	}
}
