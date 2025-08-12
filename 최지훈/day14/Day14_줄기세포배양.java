import java.util.*;
import java.io.*;

public class Solution {
	public static class Cell {
		int x, y, baseTime, curTime;
		
		public Cell(int x, int y, int baseTime, int curTime) {
			this.x = x;
			this.y = y;
			this.baseTime = baseTime;
			this.curTime = curTime;
		}
	}
	
	private static int T, N, M, K;
	private static int[][] visited;
	private static Queue<Cell> waitCell;
	private static Queue<Cell> activeCell;
	
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			visited = new int[K*2+N+1][K*2+M+1];
			for(int i=0; i<K*2+N+1; i++) {
				Arrays.fill(visited[i], -1);
			}
			
			waitCell = new ArrayDeque<>();
			activeCell = new ArrayDeque<>();
			
			// 초기 줄기 세포를 waitCell에 담아주기
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					int n = Integer.parseInt(st.nextToken());
					if(n > 0) {
						visited[i+K][j+K] = 0;
						waitCell.offer(new Cell(i+K, j+K, n, 0));
					}
				}
			}
			
			// 초기 줄기세포로 300초 동안 번식
			breeding();
			
			sb.append("#").append(t).append(" ").append(waitCell.size() + activeCell.size()).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// bfs로 번식
	private static void breeding() {
		int curT = 0;
		
		while(curT < K) {
			curT++;
			
			int waitCellSize = waitCell.size();
			int activeCellSize = activeCell.size();
			
			// 1. 비활성 세포 큐에 있는 모든 애들의 시간을 증가시키거나 baseTime에 도달하면 activeCell에 추가
			while(waitCellSize > 0) {
				waitCellSize--;
				Cell cell = waitCell.poll();
				// 활성으로 넘어간다면 활성 큐에 추가
 				if(cell.curTime + 1 == cell.baseTime) {
					activeCell.offer(new Cell(cell.x, cell.y, cell.baseTime, 0));
					continue;
				}
 				
 				// 아직 비활성이라면
 				waitCell.offer(new Cell(cell.x, cell.y, cell.baseTime, cell.curTime + 1));
			}
			
			// 2. 활성 세포 큐에 있는 모든 애들을 번식
			HashMap<Integer, Integer> addCellMap = new HashMap<>();
			while(activeCellSize > 0) {
				activeCellSize--;
				Cell cell = activeCell.poll();
				int xpos = cell.x;
				int ypos = cell.y;
				int base = cell.baseTime;
				int cur = cell.curTime;
				
				// 아직 활성 가능하면  cur 시간 증가
				if(cur + 1 < base) {
					activeCell.offer(new Cell(xpos, ypos, base, cur+1));
				}
				
				// 상하좌우로 번식
				for(int d=0; d<4; d++) {
					int nx = xpos + dx[d];
					int ny = ypos + dy[d];
					
					// 빈칸이라면 번식
					if(visited[nx][ny] == -1) {
						addCellMap.merge(nx * 701 + ny, base, Math::max);
					}
				}
			}
			
			for(Map.Entry<Integer, Integer> map : addCellMap.entrySet()) {
				int xpos = map.getKey() / 701;
				int ypos = map.getKey() % 701;
				int base = map.getValue();
				
				waitCell.offer(new Cell(xpos, ypos, base, 0));
				visited[xpos][ypos] = curT;
			}
		}
	}
}
