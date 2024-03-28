package day0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 
 ���� ���� ���ٹ�� 
 bfs : ���Ž�� - map : �� q�� ���� ������ �ִ� ���踦 map���� ��Ÿ��
 
 ����� �� - ���� bfs�� �ٸ��� ���� ������ �ٽ� �湮 �ؾ� �Ǵ� ����� �������� �𸣰ڴ�. 
 - visited ó���� �Ѵٸ� �ٽ� �湮�� �ȵǰ� ���ϸ� q�� ���̰� ��������� ���� ���̴� ���� �߻�

���� �ذ���
- ��Ʈ����ŷ + 3���� �迭�� �ش� ���踦 �������� �湮ó��
-> �湮�ߴ� �� �ٽ� �� �� �ִ�.

��Ʈ����ŷ ����
a b c d e f  index ��ŭ ���� ����Ʈ �� ���� 1�̸� �ش� ���踦 ������ �ִ�
a= 000001  1<<0      
b= 000010  1<<1   
c= 000100  1<<2
d= 001000  1<<3
e= 010000  1<<4
f= 100000  1<<5   

 * 
 */
public class Main_1194_�̺��� {
	static class Location {
		int r, c, depth, key;

		public Location(int r, int c, int depth, int key) {
			super();
			this.r = r;
			this.c = c;
			this.depth = depth;
			this.key = key;
		}

		public Location(int depth) {
			super();
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Location [r=" + r + ", c=" + c + ", depth=" + depth + ", key=" + key + "]";
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static int N;
	private static int M;
	private static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Location startLoc = null;
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					startLoc = new Location(i, j, 0, 0);

				}
			}
		}

		int ans = bfs(startLoc);
		System.out.println(ans);
	}

	private static int bfs(Location start) {
		int answer = -1;
		Queue<Location> q = new ArrayDeque<>();
		// 3���������� ���踦 ȹ���� �� �湮�� ������ ��湮�ϱ����ؼ� 2������ ���� ��湮 X
		boolean[][][] visited = new boolean[N][M][1 << 6];
		visited[start.r][start.c][0] = true;
		q.offer(start);

		while (!q.isEmpty()) {
			Location loc = q.poll();
			int key = loc.key;
			for (int d = 0; d < 4; d++) {
				int nr = loc.r + dr[d];
				int nc = loc.c + dc[d];
				if (isIn(nr, nc) && !visited[nr][nc][key]) {
					char ch = map[nr][nc];
					// ���̸� x
					if (ch == '#') {
						continue;
					}

					// ����� ��Ʈ����ŷ���� true
                    // int newKey = (1 << (ch - 'a')) | key;  ���� Ű�� ���� Ű�� ��ġ��
					else if (ch >= 'a' && ch <= 'f') {
						int newKey = (1 << (ch - 'a')) | key;
						visited[nr][nc][newKey] = true;
						q.offer(new Location(nr, nc, loc.depth + 1,newKey));
					}
					else if (ch >= 'A' && ch <= 'F') {
						// �� ���谡 ������
						if ((key & (1 << ch - 'A')) != 0) {
							visited[nr][nc][key] = true;
							q.offer(new Location(nr, nc, loc.depth + 1, key));
						}
					}
					// Ż�ⱸ�� ������
					else if (ch == '1') {
						answer = loc.depth + 1;
						return answer;
					}
					else {
						q.offer(new Location(nr, nc, loc.depth + 1, key));
						visited[nr][nc][key] = true;
					}
			
					

				}

			}

		}
		return answer;
	}

	private static boolean isIn(int nr, int nc) {
		// TODO Auto-generated method stub
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}