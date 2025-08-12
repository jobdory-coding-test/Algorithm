import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day14_줄기세포배양 {
	
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, K;
	static final int MAX=350;
	static Cell[][] arr;
	static int total;
	static Queue<DupCell> dupCells;
	// 오른쪽 아래 왼쪽 위
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static class Cell implements Comparable<Cell>{
		int X, bornAt, activeAt, duplicateAt, dieAt;
		
		Cell(int X, int bornAt){
			this.X = X;
			this.bornAt = bornAt;
			this.activeAt = bornAt+X;
			this.duplicateAt = bornAt+X+1;
			this.dieAt = bornAt+X+X;
			total++;
		}
		
		Cell(int X){
			this.X = X;
		}

		@Override
		public int compareTo(Cell c) {
			// 생명력 기준 내림차순 정렬
			return Integer.compare(c.X, this.X);
		}
	}
	
	static class DupCell extends Cell{
		int r, c;
		
		DupCell(int X, int r, int c) {
			super(X);
			this.r=r;
			this.c=c;
		}		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			total = 0;
			arr = new Cell[MAX][MAX];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					int X = Integer.parseInt(st.nextToken());
					
					if(X!=0) {
						arr[150+i][150+j] = new Cell(X, 0);
					}
				}
			}
			
			runcell();
			sb.append(total).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void runcell() {
		for(int time = 1; time<=K; time++) {
			dupCells = new PriorityQueue<>();
			
//			System.out.println(" [ time: "+time+"/ total:"+total+" ]");
			for(int i=0; i<arr.length; i++) {
				for(int j=0; j<arr[i].length; j++) {
					if(arr[i][j] == null) continue;
//					System.out.printf("> (%d, %d) is not null\n", i, j);
					
					if(arr[i][j].duplicateAt == time) {
						for(int d=0; d<dr.length; d++) {
							if(arr[i+dr[d]][j+dc[d]]!=null) continue;
//							System.out.printf("  > %d : duplicate add\n", d);
							dupCells.add(new DupCell(arr[i][j].X, i+dr[d], j+dc[d]));
						}
					}
					
					if(arr[i][j].dieAt == time) {
//						System.out.printf("  > %d : died\n", time);
						total--;
					}
				}
			}
			
			addduplicate(time);
//			show();
		}
	}

	private static void addduplicate(int time) {
		while(!dupCells.isEmpty()) {
			DupCell cell = dupCells.poll();
			if(arr[cell.r][cell.c]==null) {
				arr[cell.r][cell.c] = new Cell(cell.X, time);
//				System.out.printf(" # cell at %d, %d / total : %d\n", cell.r, cell.c, total);
			}
		}
		
	}
	
	private static void show() {
		System.out.println("[total : "+total+"]");
		for(int i=145; i<155; i++) {
			for(int j=145; j<155; j++) {
				if(arr[i][j]==null) {
					System.out.print("x ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
		System.out.println("--------------");
	}
	
	

}
