import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day08_배열돌리기3 {
	
	private static int N, M, R;
	private static int[][] arr;
	private static int[][] res;
	private static int runtype;
	
	// 2차 구현
	private static int[] runtypes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		res = new int[N][M];
		runtypes = new int[R];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			runtypes[i] = Integer.parseInt(st.nextToken());
		}
		
		rotateArray_chg();
		
		/* 1차 구현 당시 코드*/
//		runtype = Integer.parseInt(st.nextToken());
//		if(R%4==0) {
//			res = arr;
//		} else {
//			R%=4;
//			rotateArray();
//		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(res[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void rotateArray_chg(){
		for(int i=0; i<R; i++) {
			switch(runtypes[i]) {
				case 1: flipupdown_chg(); break;
				case 2: flipleftright_chg(); break;
				case 3: turnright_chg(); break;
				case 4: turnleft_chg(); break;
				case 5: moveright_chg(); break;
				case 6: moveleft_chg(); break;
			}
		}
		res = arr;
	}

	private static void flipupdown_chg() {
		 int[][] temp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[N-i-1][j] = arr[i][j];
			}
		}
		arr = temp;
	}
	
	
	private static void flipleftright_chg() {
		 int[][] temp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][M-j-1] = arr[i][j];
			}
		}
		arr = temp;
	}

	
	private static void turnright_chg() {
		 int[][] temp = new int[M][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				temp[j][N-1-i] = arr[i][j];
			}
		}
		
		int tmp = N;
		N = M;
		M = tmp;
		
		arr = temp;
	}
	
	private static void turnleft_chg() {
		 int[][] temp = new int[M][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				temp[M-1-j][i] = arr[i][j];
			}
		}
		
		int tmp = N;
		N = M;
		M = tmp;
		
		arr = temp;
	}
	
	private static void moveright_chg() {
		int[][] temp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(i<N/2) {
					if(j<M/2) temp[i][j+M/2]=arr[i][j];
					else temp[i+N/2][j]=arr[i][j];
				} else {
					if(j<M/2) temp[i-N/2][j]=arr[i][j];
					else temp[i][j-M/2]=arr[i][j];
				}
			}
		}
		arr = temp;
	}
	
	private static void moveleft_chg() {
		 int[][] temp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(i<N/2) {
					if(j<M/2) temp[i+N/2][j]=arr[i][j];
					else temp[i][j-M/2]=arr[i][j];
				} else {
					if(j<M/2) temp[i][j+M/2]=arr[i][j];
					else temp[i-N/2][j]=arr[i][j];
				}
			}
		}
		arr = temp;
	}

	
	/* -------------------- 초기 구현 -------------------- */
	
	private static void rotateArray(){
		switch(runtype) {
			case 1: flipupdown(); break;
			case 2: flipleftright(); break;
			case 3: turnright_2(); break;
			case 4: turnleft_2(); break;
			case 5: moveright(); break;
			case 6: moveleft(); break;
		}
	}

	private static void flipupdown() {
		if(R%2==0) {
			res = arr;
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				res[N-i-1][j] = arr[i][j];
			}
		}
	}	
	
	private static void flipleftright() {
		if(R%2==0) {
			res = arr;
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				res[i][M-j-1] = arr[i][j];
			}
		}
	}

	private static void turnright_2() {
		for (int r=0; r<R; r++) {
			int[][] temp = new int[M][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					temp[j][N-1-i] = arr[i][j];
				}
			}
			arr = temp;
			int tmp = N;
			N = M;
			M = tmp;
		}
		res = arr;
	}
	
	private static void turnright() {
		for(int r=0; r<R; r++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					res[j][N-1-i]=arr[i][j];
				}
			}
		}
	}

	private static void turnleft_2() {
		for (int r=0; r<R; r++) {
			int[][] temp = new int[M][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					res[M-1-j][i] = arr[i][j];
				}
			}
			arr = temp;
			int tmp = N;
			N = M;
			M = tmp;
		}
		res = arr;
	}
	
	private static void turnleft() {
		for(int r=0; r<R; r++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					res[M-j-1][i]=arr[i][j];
				}
			}
		}
	}
	
	
	private static void moveright() {
		for(int r=0; r<R; r++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(i<N/2) {
						if(j<M/2) res[i][j+M/2]=arr[i][j];
						else res[i+N/2][j]=arr[i][j];
					} else {
						if(j<M/2) res[i-N/2][j]=arr[i][j];
						else res[i][j-M/2]=arr[i][j];
					}
				}
			}
		}
	}
	
	private static void moveleft() {
		for(int rotate=0; rotate<R; rotate++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(i<N/2) {
						if(j<M/2) res[i+N/2][j]=arr[i][j];
						else res[i][j-M/2]=arr[i][j];
					} else {
						if(j<M/2) res[i][j+M/2]=arr[i][j];
						else res[i-N/2][j]=arr[i][j];
					}
				}
			}
		}
	}
	

}