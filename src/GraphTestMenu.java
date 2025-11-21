package pack;

import java.util.Arrays;

public class GraphTestMenu {
	
	public UIManager ParentUI;
	public void printTestScreen() {
		
		/*
		 * boolean[][] Test1=new boolean[][] { {false,true,true,true},
		 * {true,false,true,true}, {true,true,false,false}, {true,true,false,false}};
		 * 
		 * boolean[][] Test2=new boolean[][] { {false,true,true,true},
		 * {true,false,true,false}, {true,true,false,true}, {true,false,true,false}};
		 * 
		 * 
		 * Graph TestG=new Graph(); TestG.Relation=Test1; Graph TestG2=new Graph();
		 * TestG2.Relation=Test2;
		 * 
		 * 
		 * 
		 * 
		 */
				
		ParentUI.screen.getTextWindow().setCursorPosition(0, 28);
		System.out.println("-------------Graph Test Menu---------------");
		System.out.println("1.Connected? ->" + isConnected(ParentUI.mainGraph));
		System.out.println("2.Contains C3? ->" + containsC3(ParentUI.mainGraph));
		System.out.println("3.Isolated vertices ->" + findIsolatedVertices(ParentUI.mainGraph) );
		System.out.println("4.Complete graph? ->" +isCompleteGraph(ParentUI.mainGraph));
		System.out.println("5.Bipartite? ->" +isBipartite(ParentUI.mainGraph));
		System.out.println("6.Cycle graph? ->"+ isCycleGraph(ParentUI.mainGraph));
		System.out.println("7.Complete bipartite graph? ->" + isCompleteBipartite(ParentUI.mainGraph));
		System.out.println("8.Wheel graph? ->" +isWheelGraph(ParentUI.mainGraph));
		System.out.println("9.Star graph? ->" +isStarGraph(ParentUI.mainGraph));	
		System.out.print("10.Isomorphic? ->");				
		int[] isomorphismorder=IsomorphismOrder(ParentUI.secondaryGraph,ParentUI.mainGraph);
		
		if(isomorphismorder==null) {
			System.out.println("No");		
		}
		else if(ParentUI.mainGraph.Relation.length==0&&ParentUI.secondaryGraph.Relation.length==0) {
			System.out.println("Yes");
		}
		else {
			System.out.println("  Yes");
			ParentUI.screen.getTextWindow().setCursorPosition(40, 30);
			System.out.println("  Renaming Function:\n");
			ParentUI.screen.getTextWindow().setCursorPosition(34, 31);
			System.out.println("  Main Graph <-- Secondary Graph");			
			for(int i=0;i<isomorphismorder.length;i++) {
				ParentUI.screen.getTextWindow().setCursorPosition(34, 32+i);
				System.out.printf("           %c <-- %c\n\n",(char)i+65,(char)isomorphismorder[i]+65);
			}
		}
	}
	
	public String isConnected(Graph input) {
        int numVertices = input.VertexLocations.length; // Grafın düğüm sayısını al
        if (numVertices == 0) return "Yes"; // Eğer hiç düğüm yoksa, bağlantılı kabul edilir

        boolean[] visited = new boolean[numVertices]; // Ziyaret edilen düğümleri tutacak dizi
        
        dfs(0, visited,input); // DFS ile ilk düğümden başla
        
        // Tüm düğümlerin ziyaret edilip edilmediğini kontrol et
        for (boolean v : visited) {
            if (!v) return "No"; // Eğer bir düğüm ziyaret edilmemişse, graf bağlantılı değil
        }
        return "Yes";
    }
    // DFS Yardımcı Fonksiyonu
    private void dfs(int vertex, boolean[] visited,Graph input) {
        visited[vertex] = true; // Şu anki düğümü ziyaret edildi olarak işaretle
        
        for (int i = 0; i < input.Relation.length; i++) {
            if (input.Relation[vertex][i] && !visited[i]) { // Eğer bağlantılı ve ziyaret edilmemişse
                dfs(i, visited,input); // O düğüme git
            }
        }
    }





    public String containsC3(Graph input) {
        int n = input.Relation.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (input.Relation[i][j] && input.Relation[j][k] && input.Relation[k][i]) {
                        return "Yes"; // C₃ bulundu!
                    }
                }
            }
        }
        return "No"; // Hiçbir üçlü bağlı değilse false döndür
    }

    

    public String findIsolatedVertices(Graph input) {
        StringBuilder isolated = new StringBuilder();
        for (int i = 0; i < input.VertexLocations.length; i++) {
            boolean hasEdge = false;
            for (int j = 0; j < input.VertexLocations.length; j++) {
                if (input.Relation[i][j]) {
                    hasEdge = true;
                    break;
                }
            }
            if (!hasEdge) {
                isolated.append((char) ('A' + i)).append(",");
            }
        }
        return isolated.length() > 0 ? isolated.toString() : "None";
    }

    public String isCompleteGraph(Graph input) {
    	if(input.Relation.length==0) {
        	return "Yes";
        }
    	
    	
        for (int i = 0; i < input.VertexLocations.length; i++) {
            for (int j = 0; j < input.VertexLocations.length; j++) {
                if (i != j && !input.Relation[i][j]) {
                    return "No";
                }
            }
        }
        return "Yes";
    }

    public String isBipartite(Graph input) {
        boolean result=true;
        
        if(input.Relation.length==0) {
        	return "Yes";
        }
        
        int[] PartiteVertices=new int[input.Relation.length]; //will hold if vertices mark as -1 or +1.
        
        PartiteVertices[0]=+1;
        
        for(int i=0;i<input.Relation.length;i++) {
        	for(int j=0;j<input.Relation[i].length;j++) {
        		if(i==0) {  //predetermines possible components of two parts.
        			if(input.Relation[i][j]) {
        				PartiteVertices[j]=-1;
        			}
        			else if(!input.Relation[i][j]) {
        				PartiteVertices[j]=+1;
        			}
        		}
        		else {
        			if(input.Relation[i][j]&&PartiteVertices[i]==PartiteVertices[j]) { //makes the result false if there is a contradiction with predetermined parts.
        				result=false;
        			}
        		}
        	}
        }
        
        String V1="";
        String V2="";
        
        if(result) {
        	for(int i=0;i<PartiteVertices.length;i++) {
        		if(PartiteVertices[i]==-1) {
        			V1+= (char)(65+i)+",";
        		}
        		else {
        			V2+= (char)(65+i)+",";
        		}
        	}
        	return "Yes"+ " / "+" V1:"+V1+" V2:"+V2; 
        }
        
        return "No";
        
    }

    public String isCompleteBipartite(Graph input) {
        boolean bipartite=isBipartite(input)!="No";
        
        if(input.Relation.length==0) {
        	return "Yes";
        }
        
        boolean complete=true;
        
        
        for(int i=0;i<input.Relation.length;i++) {
        	int sum=0;
        	for(int j=0;j<input.Relation.length;j++) {
        		if(input.Relation[i][j]) {
        			sum++;
        		}
        	}
        	
        	if(sum!=input.Relation.length-1) {
        		complete=false;
        	}
        }
        
        if(complete&&bipartite) {
        	return isBipartite(input);
        }
        else {
        	return "No";
        }
    }

    public String isCycleGraph(Graph input) {
        boolean result=true;
        
        if(input.Relation.length<3) { //vertex count must be bigger than or equal to 3.
        	result=false;
        }
        
        
        for(int i=0;i<input.Relation.length;i++) { //in a cycle graph all degrees must be 2.
        	int sum=0;
        	for(int j=0;j<input.Relation.length;j++) {
        		if(input.Relation[i][j]) {
        			sum++;
        		}
        	}
        	
        	if(sum!=2) {
        		result=false;
        	}
        }
        
        if(result) {
        	return "Yes";
        }
        else {
        	return "No";
        }
    }

    public String isWheelGraph(Graph input) {
         boolean result=true;
         
         if(input.Relation.length<4) { //vertex count must be bigger than or equal to 4.
         //	result=false;
         }
         
         int centerind=0;
         int grtdegree=0;
         
         
         
         
         for(int i=0;i<input.Relation.length;i++) {
         	int sum=0;
         	for(int j=0;j<input.Relation.length;j++) {
         		if(input.Relation[i][j]) {
         			sum++;
         		}
         	}
         	
         	if(sum>grtdegree) {
         		centerind=i;
         		grtdegree=sum;
         	}
         }
         
         for(int i=0;i<input.Relation.length;i++) {
          	int sum=0;
          	for(int j=0;j<input.Relation.length;j++) {
          		if(input.Relation[i][j]) {
          			sum++;
          		}
          	}
          	
          	if(i!=centerind&&sum!=3) { //in a cycle graph all degrees except center must be 3.
          		result=false;
          	}
          	
          }
         
         
         if(result) {
        	 return "Yes"+" / "  +"Center is:"+(char)(65+centerind);
         }
         else {
        	 return "No";
         }
    }

    public String isStarGraph(Graph input) {
 
    	boolean result=true;
         
         if(input.Relation.length<4) { //vertex count must be bigger than or equal to 4.
         	result=false;
         }
         
         int centerind=0;
         int grtdegree=0;
         
         
         
         
         for(int i=0;i<input.Relation.length;i++) {
         	int sum=0;
         	for(int j=0;j<input.Relation.length;j++) {
         		if(input.Relation[i][j]) {
         			sum++;
         		}
         	}
         	
         	if(sum>grtdegree) {
         		centerind=i;
         		grtdegree=sum;
         	}
         }
         
         for(int i=0;i<input.Relation.length;i++) {
          	int sum=0;
          	for(int j=0;j<input.Relation.length;j++) {
          		if(input.Relation[i][j]) {
          			sum++;
          		}
          	}
          	
          	if(i!=centerind&&sum!=1) { //in a cycle graph all degrees except center must be 1.
          		result=false;
          	}
          	
          }
         
         
         if(result) {
        	 return "Yes"+" / "  +"Center is:"+(char)(65+centerind);
         }
         else {
        	 return "No";
         }
    }
    
    
    
    static public int[] calculateNthPermutation(int[] items,int n) { //calculates the nth permutation of an item order.
    	
    	int[] factBaseNumbers=new int[items.length];
    	
    	int[] items_temp=items.clone();
    	
    	
    	int a=n;
    	
    	
    	
    	for(int i=0;i<items.length;i++) {
    		factBaseNumbers[i]=(int) a/Factorial(items.length-i-1);
    		a=a%Factorial(items.length-i-1);
    	}
    	
    	
    	
    	int[] result=new int[items.length];
    	
    	for(int j=0;j<items.length;j++) {
    		result[j]=items_temp[factBaseNumbers[j]];
    		
    			items_temp=removeFromArray(items_temp,factBaseNumbers[j]);
    		
    		
    		
    	}
    	
    	
    	return result;
    }
    
    
    
    
    
    
    
    static public boolean[][] RenameRelation(boolean[][] Relation,int[] order){
    	boolean[][] Renamed=new boolean[Relation.length][Relation.length];
    	
    	for(int i=0;i<Renamed.length;i++) {
    		for(int j=0;j<Renamed.length;j++) {
    			Renamed[i][j]=Relation[order[i]][order[j]];
    		}
    	}
    	return Renamed;
    	
    	
    }
    
    
    
    
    private static int Factorial(int i) {
		int sum=1;
    	for(int k=1;k<=i;k++) {
    		sum*=k;
    	}
    	return sum;
	}

	public static int[] IsomorphismOrder(Graph Graph1,Graph Graph2) {
		if(Graph1.Relation.length!=Graph2.Relation.length||(Graph1.Relation.length==0&&Graph2.Relation.length==0)) {
    		return null;
    	}
    	
    	
    	
    
    	
    	boolean ismorphicorderfound=false;
    	
    	
    	
    	int[] order=new int[Graph1.Relation.length];
    	for(int j=0;j<order.length;j++) {
    		order[j]=j;
    	}
    	
    	int i=0;
    	while(i<GraphTestMenu.Factorial(order.length)&&!ismorphicorderfound) {
    		
    		boolean[][] RenamedRelation=RenameRelation(Graph1.Relation,calculateNthPermutation(order,i));
    		int mismatch=-1;
    		for(int j=0;j<RenamedRelation.length;j++) {
    			if(mismatch==-1&&!Arrays.equals(RenamedRelation[j],Graph2.Relation[j])) {
    				mismatch=j;
    			}
    		}
    		if(mismatch==-1) {
    			ismorphicorderfound=true;
    			return calculateNthPermutation(order,i);
    		}
    		else {
    			i++;
    		}
    	}
    	
    	
    	
    		return null;
    	
    	
        	
        	
        
    }
	
    
    static public int[] removeFromArray(int[] arr,int index) {
    	int[] temp=new int[arr.length-1];
    	
    	int j=0;
    	for(int i=0;i<arr.length;i++) {
    		if(i==index) {
    			
    		}
    		else {
    			temp[j]=arr[i];
    			j++;
    		}
    	}
    	return temp;
    }
	public GraphTestMenu(UIManager UIMan) {
		
		ParentUI=UIMan;
	}
	
}
