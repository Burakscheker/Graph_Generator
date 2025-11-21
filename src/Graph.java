package pack;

import java.util.Random;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

public class Graph {
	static Random rnd = new Random();
	public int penaltyPoints;
	public int[][] VertexLocations=new int[0][2]; 
	public int[][] Board=new int[25][37];//visual reflection of a graph, integer, gets interpreted when being printed
	public boolean[][] Relation; //Relation Matrix of the graph (Represents edges)
	
	private void initializeBoard() {//fills initial board with appropriate values
		for(int i = 0; i < Board.length ; i++) {
			for(int j = 0; j < Board[i].length ; j++) {
				if(i % 4 == 0 && j % 4 == 0 ) {
					Board[i][j]=-1; // -1 means dot
				}
				else {
					Board[i][j] = 0; //0 means space
				}
			}
		}
	}

	
    /*
    
    public void addVertex(int py,int px) {//the space between any two dots is one unit. for instance 
        //it must be typed 2 to add vertex 8 character away from beginning
    	
    	

    	    	
    	/*
    	//updating vertex locations array
    	int[][] last = new int[VertexLocations.length + 1][2];
    	for(int i=0;i<VertexLocations.length;i++) {
			last[i][0]=VertexLocations[i][0];
			last[i][1]=VertexLocations[i][1];
		}
    	last[VertexLocations.length][0]=py;
		last[VertexLocations.length][1]=px;
		VertexLocations=last;
		last = null;
		*/
		
		/*
    	//updating size of the relation matrix
		boolean[][] LastR=new boolean[VertexLocations.length][VertexLocations.length];
		for(int i=0;i<Relation.length;i++) {
			for(int j=0;j<Relation[i].length;j++) {
				LastR[i][j]=Relation[i][j];
			}

		}
		
		for(int i = 0; i < VertexLocations.length; i++) {
			LastR[VertexLocations.length - 1][i] = false;
			LastR[i][VertexLocations.length - 1] = false;
		}
       
		Relation=LastR;
		LastR=null;}
    	*/
    				


    public void ConnectVertices(int sourceIndex, int destinationIndex) {//Vertex indices that are intented to be connected
    
        int[] sourceLocation = new int[2];//locations of both source and destination on board
        int[] destinationLocation = new int[2];
    	
    	
    	sourceLocation[0] = VertexLocations[sourceIndex][0]*4;
    	sourceLocation[1] = VertexLocations[sourceIndex][1]*4;
    	
    	destinationLocation[0] = VertexLocations[destinationIndex][0]*4;
    	destinationLocation[1] = VertexLocations[destinationIndex][1]*4;
    	
    	
    	DrawLine(sourceLocation,destinationLocation);
    }
    	
    
    public void DrawLine(int[] source, int[] destination) {
    		
    	
    	int[] sourceC=source.clone();
    	int[] destinationC=destination.clone();
    	sourceC[0]++;
    		//to prevent unnecessary points
    		if (sourceC[0]< destinationC[0]) {
    			source[0] += 1;
    		}
    		else {
    			sourceC[0] -= 1;
    		}
    		
    		if (sourceC[1] < destinationC[1]) {
    			sourceC[1] += 1;
    		}
    		else {
    			sourceC[1] -= 1;
    		}
    		
    		
    		while((sourceC[0] != destinationC[0] || sourceC[1] != destinationC[1])&&sourceC[0]>=0&&sourceC[1]>=0) {
    			if (Board[sourceC[0]][sourceC[1]] < 60) {
    				if (Board[sourceC[0]][sourceC[1]] == -1) {
    					Board[sourceC[0]][sourceC[1]] = 1;
    				}
    				else {
    					if (Board[sourceC[0]][sourceC[1]] != 0) {
    						penaltyPoints += 1;
    					}
    					Board[sourceC[0]][sourceC[1]] += 1;
    				}
    			}
    			
    			else {
    				penaltyPoints += 1000;
    			}
    			
    			
    			
    			
    			if (sourceC[0]>destinationC[0]) {
    				sourceC[0]--;
    			}
    			else if(sourceC[0]<destinationC[0]) {
    				sourceC[0]++;
    			}
    			
    			if (sourceC[1]>destinationC[1]) {
    				sourceC[1]--;
    			}
    			else if(sourceC[1]<destinationC[1]) {
    				sourceC[1]++;
    			}
    			
    		}
    		
    		
    		
    		
    	}
    	
    
    public void generateRelation(String iDegSeq, int HowManyVertices) {//generates relation according to the degree sequence user entered
    	Relation=new boolean[VertexLocations.length][VertexLocations.length];
    	int[] degreSeq = new int[(iDegSeq.length() + 1)/2];
    	for (int i = 0; i < degreSeq.length; i += 2) {
    		degreSeq[i/2] = (int) (iDegSeq.charAt(i) - 48);
    	}
    	
    	
    	sortRandomly(degreSeq);
    	
    	
        
    	for (int i = 0; i < degreSeq.length; i++) {
    		while(degreSeq[i] > 0) {
    			int randomIndexToConnect = rnd.nextInt(degreSeq.length);
    			if (!Relation[i][randomIndexToConnect] && i != randomIndexToConnect) {
    				Relation[i][randomIndexToConnect] = true;
    				Relation[randomIndexToConnect][i] = true;
    				degreSeq[i]--;
    				degreSeq[randomIndexToConnect]--;
    			}
    		}
    	}
    	
    	
    	
    	
    	//processing relation
    	for(int i=0;i<Relation.length;i++) {
			for(int j=i+1;j<Relation[i].length;j++) {
				if(Relation[i][j]) {
					ConnectVertices(i,j);
				}
			}
		}
    	
    	
    }
    
    
    public void createVertices(int howManyVertices) {//creates as many vertices as user entered
    	VertexLocations=new int[howManyVertices][2];
    	
    	for (int i = 0; i < VertexLocations.length; i++) {
    		int randomY, randomX;
    		do {
               randomY = rnd.nextInt(7);
               randomX = rnd.nextInt(10);
               VertexLocations[i][0] = randomY;
               VertexLocations[i][1] = randomX;
               
    		} while(Board[randomY*4][randomX*4] != -1);
    		
    		
    		//adding vertices to board
    		Board[randomY*4][randomX*4] = 65 + i;
    		
    		
    		
    	}
    	
        	 
         }

    
    public void sortRandomly(int[] array) {
    	
    	for (int i = 0; i < array.length; i++) {
    		int randomIndex = rnd.nextInt(array.length);
    		int register = array[randomIndex];
    		array[randomIndex] = array[i];
    		array[i] = register;
    	}
    	
    	
    	
    }
    
    
    static public void sortRegularly(int[] arr) {
    	boolean sorted=false;
    	
    	while(!sorted) {
    		sorted=true;
    	for(int i=0;i<arr.length-1;i++) {
    		if(arr[i]<arr[i+1]) {
    			sorted=false;
    			int temp=arr[i+1];
    			arr[i+1]=arr[i];
    			arr[i]=temp;
    		}
    	}
    }
    	}
    
    
    
    public void GenerateRelation1(int[] degseq) throws AWTException {
    	int f=rnd.nextInt();
    	f=1831911947;
    	//displayTray(Integer.toString(f));
    	Random rndo=new Random(f); //for being able to be separated from main randomizer for debugging purposes.
    	boolean generationfailed=true;
    	
    	int failsafeiteration1=1000;
    	int failsafeiteration2=100;
    	int it1=0;
    	int it2=0;
    	Relation=new boolean[degseq.length][degseq.length];
    	boolean[][] GeneratedRelation=new boolean[degseq.length][degseq.length];
    	

    	while (generationfailed&&it1<=failsafeiteration1) {
    		it1++;
    		it2=0;
    	generationfailed=false;
    	GeneratedRelation=new boolean[Relation.length][Relation.length];
    	int i=0;
    	int[] seq=degseq.clone();
    	boolean[] jselection=new boolean[seq.length];
    	
    	while(i<seq.length&&it2<=failsafeiteration2) {
    		it2++;
    		int j=rndo.nextInt(seq.length);
    		jselection[j]=true;
    		if(i!=j&&!GeneratedRelation[i][j]&&seq[i]>0&&seq[j]>0) {
    			seq[i]--;
    			seq[j]--;
    			GeneratedRelation[i][j]=true;
    			GeneratedRelation[j][i]=true;
    		}
    		
    		
    		boolean alljselected=true;
    		
    		for(boolean a:jselection) {
    			if(!a) {
    				alljselected=false;
    			}
    			
    			
    			if(alljselected) {
    				i++;
    				jselection=new boolean[seq.length];
    			}
    		}   		
    	}    	
    	
    	for(int k=0;k<seq.length;k++) {
    		if(seq[k]!=0) {
    			generationfailed=true;
    		}
    	}

    	}
    	
    	if(it1>=1000||it2>=100) {
    		GeneratedRelation=GenerateHavelHakimiRelation(degseq);   		
    	}

    	Relation=GeneratedRelation;	
    	//process relation
    	
    	for(int i=0;i<Relation.length;i++) {
			for(int j=i+1;j<Relation[i].length;j++) {
				if(Relation[i][j]) {
					ConnectVertices(i,j);
				}
			}
		}    	
    }
  
    static public boolean[][] CloneMatrix(boolean[][] Matrix){
    	boolean[][] Cloned=new boolean[Matrix.length][Matrix.length];
    	
    	for(int i=0;i<Cloned.length;i++) {
    		for(int j=0;j<Cloned[i].length;j++) {
    			Cloned[i][j]=Matrix[i][j];
    		}
    	}
    	return Cloned;
    }
    
    
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
    
    public Graph() { //constructor
		penaltyPoints = 0;
    	initializeBoard(); 
    	Relation=new boolean[0][0];
		//Relation=new boolean[VertexLocations.length][VertexLocations.length];
	}
    
    
    
    
    /*public void displayTray(String input) throws AWTException{
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Hello, World", input, MessageType.ERROR);
    }*/
    
    public boolean[][] GenerateHavelHakimiRelation(int[] degseq){
    	boolean[][] GeneratedRelation=new boolean[degseq.length][degseq.length];
    	int[] seq=degseq.clone();
    	sortRegularly(seq);
    	for(int i=0;i<degseq.length;i++) {
    		sortRegularly(seq);
    		for(int j=i+1;j<degseq.length;j++) {
    			if(seq[i]>0&&seq[j]>0) {
    				GeneratedRelation[i][j]=true;
    				GeneratedRelation[j][i]=true;
    				seq[i]--;
    				seq[j]--;
    				
    			}
    			if(seq[i]==0) {
    				j=seq.length-1;
    			}
    			
    		}
    	}
    	return GeneratedRelation;
    }
    
    
    
    
    static public boolean checkDegreeSequance(int[] deq) {
    	int[] seq=deq.clone();
    	sortRegularly(seq);
		boolean Result=true;
		for(int i=0;i<seq.length-1;i++) {
			
			for(int j=i+1;j<seq.length;j++) {
				if(seq[i]>0&&seq[j]>0) {
					seq[i]--;
					seq[j]--;
				}
			}
		}
		
		for(int i=0;i<seq.length;i++) {
			if(seq[i]!=0) {
				Result=false;
			}
		}
		return Result;
    }
    
    public Graph clone() {
    	Graph Cloned=new Graph();
    	Cloned.penaltyPoints=this.penaltyPoints;
    	Cloned.Board=this.Board.clone();
    	Cloned.Relation=this.Relation.clone();
    	Cloned.VertexLocations=this.VertexLocations.clone();
    	
    	return Cloned;
    }
}
