package pack;

public class MatrixCalculator {

	private int[][] adjacencyMatrix;	
	
	MatrixCalculator(int[][] matrix){
		adjacencyMatrix = matrix;
	}
	
	public int[][] multiplyMatrix(int[][] A, int[][] B) {
		 int n = A.length;
		    int[][] result = new int[n][n];

		    for (int i = 0; i < n; i++) {
		        for (int j = 0; j < n; j++) {
		            for (int k = 0; k < n; k++) {
		                if (A[i][k] == 1 && B[k][j] == 1) {
		                    result[i][j] = 1; // sadece erişim varsa 1 yaz
		                    break; // daha fazla kontrol etmeye gerek yok
		                }
		            }
		        }
		    }

		    return result;
	}	
	
	public int[][] transitiveClosure() {
	    int n = adjacencyMatrix.length;
	    int[][] closure = new int[n][n];

	    // Başlangıçta doğrudan erişim
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            closure[i][j] = adjacencyMatrix[i][j];
	        }
	    }

	    // Floyd-Warshall benzeri
	    for (int k = 0; k < n; k++) {
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                if (closure[i][k] == 1 && closure[k][j] == 1) {
	                    closure[i][j] = 1;
	                }
	            }
	        }
	    }

	    return closure;
	}
	
	public int[][] floydWarshallMinPath() {
	    int n = adjacencyMatrix.length;
	    int[][] dist = new int[n][n];
	    int INF = 9999;

	    // Başlangıç mesafeleri
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            if (i == j) dist[i][j] = 0;
	            else if (adjacencyMatrix[i][j] != 0) dist[i][j] = 1;
	            else dist[i][j] = INF;
	        }
	    }

	    // Floyd-Warshall
	    for (int k = 0; k < n; k++) {
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                if (dist[i][j] > dist[i][k] + dist[k][j]) {
	                    dist[i][j] = dist[i][k] + dist[k][j];
	                }
	            }
	        }
	    }

	    // Erişilemeyen yerleri 0 yap
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            if (dist[i][j] == INF) dist[i][j] = 0;
	        }
	    }

	    return dist;
	}
	//Multiplies two boolean matrices and return the result in boolean matrix form.
	 public boolean[][] multiplyMatrixB(boolean[][] matrix1,boolean[][] matrix2){
		boolean[][] Result=null;
		
		
		if(matrix1[0].length==matrix2.length) {
			Result=new boolean[matrix1.length][matrix2[0].length];
			for(int i=0;i<matrix1.length;i++) {
				
				for(int j=0;j<matrix2.length;j++) {
					boolean sum=false;
					for(int k=0;k<Result[i].length;k++) {
						sum=(matrix1[i][k]&&matrix2[k][j])||sum;
					}
					Result[i][j]=sum;
				}
			}
		}
		return Result;
	}
	//Multiplies two boolean matrices and returns the result in integer matrix form.
	 public int[][] multiplyMatrixI(boolean[][] matrix1,boolean[][] matrix2){
			int[][] Result=null;
			
			
			if(matrix1[0].length==matrix2.length) {
				Result=new int[matrix1.length][matrix2[0].length];
				for(int i=0;i<matrix1.length;i++) {
					
					for(int j=0;j<matrix2.length;j++) {
						int sum=0;
						for(int k=0;k<Result[i].length;k++) {
							int temp1=0;
							int temp2=0;
							if(matrix1[i][k])temp1=1;
							if(matrix2[k][j])temp2=1;
							sum+=temp1*temp2;
						}
						Result[i][j]=sum;
					}
				}
			}
			return Result;
		}

	//Multiplies two integer matrices and return the result in integer matrix form.
	  public int[][] multiplyMatrixI(int[][] matrix1,int[][] matrix2){
	 	int[][] Result=null;
	 	
	 	
	 	if(matrix1[0].length==matrix2.length) {
	 		Result=new int[matrix1.length][matrix2[0].length];
	 		for(int i=0;i<matrix1.length;i++) {
	 			
	 			for(int j=0;j<matrix2.length;j++) {
	 				int sum=0;
	 				for(int k=0;k<Result[i].length;k++) {
	 					sum+=matrix1[i][k]*matrix2[k][j];
	 				}
	 				Result[i][j]=sum;
	 			}
	 		}
	 	}
	 	return Result;
	 }

	//Calculates Rmin using matrix multiplication.
	  public int[][] getRmin(){
	  	int[][] Temp=adjacencyMatrix.clone();
	  	boolean flag=true;
	  	while(flag) {
	  		flag=false;
	  		Temp=multiplyMatrixI(Temp,adjacencyMatrix);
	  		for(int[] i:Temp) {
	  			for(int j:i) {
	  				if(j==0) flag=true;
	  			}
	  		}
	  	}
	  	
	  	return Temp;
	  }




	 
	//Translates target boolean matrix to 0-1 integer matrix.
	  static public int[][] translateBMatrix(boolean[][] matrix){
	  	int[][] Result=new int[matrix.length][matrix[0].length];
	  	for(int i=0;i<matrix.length;i++) {
	  		for(int j=0;j<matrix[i].length;j++) {
	  			if(matrix[i][j]) Result[i][j]=1;
	  			else Result[i][j]=0;
	  		}
	  	}
	  	return Result;
	  }
}
