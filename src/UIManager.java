package pack;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import enigma.core.Enigma;

public class UIManager {
	KeyListener KLis;
	public int keypr;   // key pressed?
    public int rkey;
    
    public int numOfVertices;
    public String degSequance;
    public int minDeg;
    public int maxDeg;
    
	Graph mainGraph, secondaryGraph=new Graph();//all instances of Graph class will be in this class

	Graph DepotGraphs[]=new Graph[9];
	GraphTestMenu test;
	
	public enigma.console.Console screen;
	
	public void takeNumberOfVertices() {//takes number of vertices user intend to apply 
		String iNumOfVertices = "a";
		System.out.print("Enter number of vertices: ");
		Scanner scr = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			iNumOfVertices = scr.nextLine();
			if (iNumOfVertices.length() == 1) {
				if ((int) iNumOfVertices.charAt(0) > 49 && iNumOfVertices.charAt(0) <= 57) {
					flag = false;
				} else {
					System.out.print("Invalid input.Enter again: ");
				}
			}

			else if (iNumOfVertices.length() == 2) {
				if ((int) iNumOfVertices.charAt(0) >= 48 && (int) iNumOfVertices.charAt(0) <= 50
						&& (int) iNumOfVertices.charAt(1) >= 48 && (int) iNumOfVertices.charAt(1) <= 57) {
					if (Integer.parseInt(iNumOfVertices) > 1 && Integer.parseInt(iNumOfVertices) < 27) {
						flag = false;
					} else {
						System.out.print("Invalid input.Enter again: ");
					}
				}
				else {
					System.out.print("Invalid input. Enter again: ");
				}
			}
			else {
				System.out.print("Invalid input. Enter again: ");
			}
		}		
		scr.close();
		numOfVertices = Integer.parseInt(iNumOfVertices);
		mainGraph.createVertices(numOfVertices);
	}
	
	
	public void takeDegreeSequence() throws AWTException  {//takes degree sequence from user
		
		String degSeq = "a";
		System.out.print("Enter degree sequence: ");
		Scanner scr = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			degSeq = scr.nextLine();
			
			if (degSeq.length() == numOfVertices * 2 - 1) {//true format
				boolean isValid = true;
				for (int i = 0; i < degSeq.length(); i++) {
					if (i % 2 == 1) {
						if (degSeq.charAt(i) != ',') {
							isValid = false;
						}
					} else {
						if (!((int) degSeq.charAt(i) > 48) || !((int) degSeq.charAt(i) < 57)) {
							isValid = false;
						}
					}
				}
				 int[] degseq = new int[(degSeq.length() + 1)/2];
					
					for (int i = 0; i < degSeq.length(); i += 2) {
						degseq[i/2] = (int) degSeq.charAt(i)-48;
					}
				if (isValid&&Graph.checkDegreeSequance(degseq)) {
					flag = false;
				} else {
					System.out.print("Invalid input. Enter again: ");
				}
			}
			
			else if (degSeq.length() < numOfVertices * 2 - 1) {
				/*
				 * 
				 * check whether the sequence is valid and if valid, call completeDegreeSequence
				 * 
				 */
				completeDegreeSequence();
			}

			else {
				System.out.print("Invalid input. Enter again: ");
			}
		}
		
		 //mainGraph.generateRelation(degSeq,numOfVertices);
		degSequance = degSeq;
		String[] arr=degSeq.split(",");
		
		int[] degseqt=new int[arr.length];
		
		for(int i=0;i<arr.length;i++) {
			degseqt[i]=Integer.parseInt(arr[i]);
		}
		
		int bigNum = 0;
		for(int i=0; i< degseqt.length; i++) {
			if(degseqt[i] > bigNum) {
				bigNum = degseqt[i];
			}
		}
		maxDeg = bigNum;
		
		int minNum = 10000;
		for(int i=0; i< degseqt.length; i++) {
			if(degseqt[i] < minNum) {
				minNum = degseqt[i];
			}
		}
		minDeg = minNum;
		
		mainGraph.GenerateRelation1(degseqt);
		scr.close();			
	}
	
	
	public void takeInitialInputs() throws AWTException {
		printGraphs(mainGraph,secondaryGraph);
		printRelationMatrix(mainGraph.Relation, secondaryGraph.Relation); 
		drawEdges(mainGraph,drawingMode, 0);
		drawEdges(secondaryGraph,drawingMode, 70);
		screen.getTextWindow().setCursorPosition(0, 29);

		takeNumberOfVertices();
		
		takeDegreeSequence();
		
	}
	
	
	public void completeDegreeSequence() {
		System.out.print("Degree sequence is being completed...");
	}
	
	
	public void printGraphs(Graph main, Graph secondary) {
		//clearScreen();
		//mainGraph 
		for (int i = 0; i < main.Board.length ; i++) {//row			
			for (int j = 0; j < main.Board[i].length; j++) {//column
				screen.getTextWindow().setCursorPosition(j, i);
				if (main.Board[i][j] == 0) {//0 means space
					System.out.print(' ');
				}
				else if (main.Board[i][j] >= 65) {//vertices
					System.out.print((char) main.Board[i][j]);
				}
				else if (main.Board[i][j] == -1) {//-1 means dot
					System.out.print('.');
				}
				else {
					System.out.print(' ');
				}
			}
		}
		
		//printing penalty points for main graph
		screen.getTextWindow().setCursorPosition(0, 26);
		System.out.print("Penalty points: " + mainGraph.penaltyPoints);
		
		//secondary graph
		for(int i = 0; i < secondary.Board.length; i++) {			
		    for (int j = 0; j < secondary.Board[i].length; j++) {
		    	screen.getTextWindow().setCursorPosition(70 + j, i);
		    	if (secondary.Board[i][j] == 0) {
		    		System.out.print(' ');
		    	}
		    	
		    	else if (secondary.Board[i][j] >= 65) {
		    		System.out.print((char) secondary.Board[i][j]);
		    	}
		    	else if (secondary.Board[i][j] == -1) {
		    		System.out.print('.');
		    	}
		    	
		    }
		}
		//printing penalty points for secondary graph
		screen.getTextWindow().setCursorPosition(70, 26);
		System.out.print("Penalty points: " + secondaryGraph.penaltyPoints);
		
	}
	
	
	public void printRelationMatrix(boolean[][] main, boolean[][] secondary) {
		for (int i = 0; i < main.length; i++) {
			screen.getTextWindow().setCursorPosition(40, i + 1);
			for (int j = 0; j < main[i].length; j++) {
				if (main[i][j]) {
					System.out.print('1');
				}
				else {
					System.out.print('0');
				}
			}
		}
		
		for (int i = 0; i < secondary.length; i++) {
			screen.getTextWindow().setCursorPosition(110, i + 1);
			for (int j = 0; j < secondary[i].length; j++) {
				if (secondary[i][j]) {
					System.out.print('1');
				}
				else {
					System.out.print('0');
				}
			}
		}
	}
	
	int drawingMode = 1;
	public void INPUT() throws InterruptedException {
		
	      KLis = new KeyListener() {
		         public void keyTyped(KeyEvent e) {}
		         public void keyPressed(KeyEvent e) {
		            if(keypr==0) {
		               keypr=1;
		               rkey=e.getKeyCode();
		            }
		         }
		         public void keyReleased(KeyEvent e) {}
		      };
		      screen.getTextWindow().addKeyListener(KLis);
		      
		      
		      
		      
	          boolean screenMode = true;
	          while(true) {
	          if (keypr == 1) {
		           if (rkey == 68) {//changing drawing mode, key: D
		        	   if (drawingMode == 1) {
		        		   drawingMode = 0;
		        	   }
		        	   else {
		        		   drawingMode = 1;
		        	   }
		        	   drawEdges(mainGraph, drawingMode, 0);
		        	   drawEdges(secondaryGraph, drawingMode, 70);		        	   
		           }
		           

		           if (rkey == 88) {//graph test menu, key: X
		        	   clearScreen();
		        	    
		        	   if(screenMode) {
		        		   screenMode = false;
		        	   }
		        	   else {
		        		   screenMode = true;
		        	   }
		        	   
		        	   if (screenMode) {
		        		   graphScreen(mainGraph,secondaryGraph);
		        	   }
		        	   else {
		        		   screen.getTextWindow().setCursorPosition(0, 0);
		        		   test.printTestScreen();
		        	   }
		        	   
		           }
		           
		           
		           
		           if (rkey == 67) {//graph transfer menu key C
		        	   clearScreen();
		        	    
		        	   if(screenMode) {
		        		   screenMode = false;
		        	   }
		        	   else {
		        		   screenMode = true;
		        	   }
		        	   
		        	   if (screenMode) {
		        		   graphScreen(mainGraph,secondaryGraph);
		        	   }
		        	   else {
		        		   screen.getTextWindow().setCursorPosition(0, 0);
		        		   GraphTransferMenu();
		        	   }
		        	   
		           }
		           
		           
		           if(rkey == 90) {//graph transfer menu key z
		        	   clearScreen();
		        	   if(screenMode) {
		        		   screenMode = false;
		        	   }
		        	   else {
		        		   screenMode = true;
		        	   }
		        	   
		        	   if (screenMode) {
		        		   graphScreen(mainGraph,secondaryGraph);
		        	   }
		        	   else {
		        		   screen.getTextWindow().setCursorPosition(0, 0);
		        		   rkey=0;
		        		   GraphGenerationMenu();
		        	   }
		           }		           		          
		           
		           keypr = 0;
	          }
	          Thread.sleep(20);
	          }
		      
	      }
	      
	public void GraphGenerationMenu() {
		screen.getTextWindow().setCursorPosition(0, 28);
		System.out.println("-------------Graph Generation Menu---------------");
		System.out.println("1. Generate graph: \r\n"
				+ "2. Set degrees with Degree Sequence: \r\n"
				+ "3. Set degrees with mindegree and maxdegree: \r\n"
				+ "4. Calculate R2, R3, ... , Rn, R* and Rmin matrices: \r\n"
				+ "5. Exit \r\n");
		
		String choice = "";
		System.out.print("Select a number: ");
		Scanner scr = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			choice = scr.nextLine();
			if (choice.length() == 1) {
				if ((int) choice.charAt(0) > 48 && choice.charAt(0) <= 53) {
					flag = false;
				} else {
					System.out.print("Invalid input.Enter again: ");
				}
			}
		}
		scr.close();				
		
		if(Integer.parseInt(choice) == 1) {
			for (int i = 0; i < 44; i++) {
				for (int j = 0; j < 140; j++) {
					screen.getTextWindow().setCursorPosition(j, i);
					System.out.print(' ');
				}
			}
			
			try {
				mainGraph = new Graph();
				takeInitialInputs();
			} catch (AWTException e) {
			}
			
			graphScreen(mainGraph,secondaryGraph);
			try { INPUT(); } catch(InterruptedException e){ }	
		}
		
		if(Integer.parseInt(choice) == 4) {		
			for (int i = 0; i < 40; i++) {
				for (int j = 70; j < 120; j++) {
					screen.getTextWindow().setCursorPosition(j, i);
					System.out.print(' ');
				}
			}	
						
			Graph gr = new Graph();
			int[][] mainMatrix = gr.translateBMatrix(mainGraph.Relation);
			MatrixCalculator mCalculator = new MatrixCalculator(mainMatrix);

			
			//R'n matrisler için
			int n = mainMatrix.length;
			int[][] prevMatrix = mainMatrix;

			int columnOffset = 70;
			int rowOffset = 1;
			int matrisSayaci = 0;  // Kaç matris çizildiğini takip ediyor

			for (int power = 2; power <= n; power++) {
			    // 3 matris yazıldıysa, alta geç
			    if (matrisSayaci > 0 && matrisSayaci % 3 == 0) {
			        rowOffset += n + 2; // 2 satır boşluk bırak
			        columnOffset = 70; // başa dön
			    }

			    // Başlık
			    screen.getTextWindow().setCursorPosition(columnOffset, rowOffset - 1);
			    System.out.print("R^" + power);

			    // Matris çarpımı
			    int[][] result = mCalculator.multiplyMatrix(mainMatrix, prevMatrix);

			    // Sadece 0 ve 1 bas (varsa 2+ değerleri normalize edilir)
			    for (int i = 0; i < result.length; i++) {
			        screen.getTextWindow().setCursorPosition(columnOffset, rowOffset + i);
			        for (int j = 0; j < result[i].length; j++) {
			            if (result[i][j] >= 1) {
			                System.out.print('1');
			                result[i][j] = 1;
			            } else {
			                System.out.print('0');
			            }
			        }
			    }

			    // Sonraki matris için hazırlık
			    prevMatrix = result;
			    columnOffset += 15;
			    matrisSayaci++;
			}
			
			//R* matrisi için
			screen.getTextWindow().setCursorPosition(70, 24);System.out.print("R*");
			int[][] rT = mCalculator.transitiveClosure();
			for (int i = 0; i < rT.length; i++) {
		        screen.getTextWindow().setCursorPosition(70, 25 + i);
		        for (int j = 0; j < rT[i].length; j++) {
		            if (rT[i][j] >= 1) {
		                System.out.print('1');
		                rT[i][j] = 1;
		            } else {
		                System.out.print('0');
		            }
		        }
		    }
			
			//Rmin matrisi için
			screen.getTextWindow().setCursorPosition(100, 24);System.out.print("Rmin");
			int[][] rMin = mCalculator.floydWarshallMinPath();
			for (int i = 0; i < rMin.length; i++) {
		        screen.getTextWindow().setCursorPosition(100, 25 + i);
		        for (int j = 0; j < rMin[i].length; j++) {
		            
		                System.out.print(rMin[i][j]+",");
		               
		           
		        }
		    }
		}
		
		if(Integer.parseInt(choice) == 5) {
			mainMenu();
			try { INPUT(); } catch(InterruptedException e){ }
		}
		
	}
	
	public void clearScreen() {	
		for (int i = 28; i < 40; i++) {
			for (int j = 0; j < 110; j++) {
				screen.getTextWindow().setCursorPosition(j, i);
				System.out.print(' ');
			}
		}		
		screen.getTextWindow().setCursorPosition(0, 0);
	}
	
	public void mainMenu() {
		clearScreen();
		screen.getTextWindow().setCursorPosition(0, 29);
		System.out.println("1. Graph Generation Menu    (Key: Z) \r\n"
				+ "2. Graph Test Menu   (Key: X) \r\n"
				+ "3. Graph Transfer Menu   (Key: C) \r\n");
		screen.getTextWindow().setCursorPosition(0, 0);
	}
	
	public void drawEdges(Graph inputGraph, int mode, int offsetX) {
	    for (int i = 0; i < inputGraph.Board.length; i++) {
	        for (int j = 0 ; j < inputGraph.Board[i].length; j++) {
	            if (inputGraph.Board[i][j] > 0 && inputGraph.Board[i][j] < 60) {
	                screen.getTextWindow().setCursorPosition(j + offsetX, i); // 💡 offset ekleniyor
	                if (mode == 1) {
	                    System.out.print(inputGraph.Board[i][j]);
	                }
	                else {
	                    if (inputGraph.Board[i][j] == 1) {
	                        System.out.print('+');
	                    }
	                    else if (inputGraph.Board[i][j] == 2) {
	                        System.out.print('o');
	                    }
	                    else if(inputGraph.Board[i][j] == 3) {
	                        System.out.print('#');
	                    }
	                    else {
	                        System.out.print('@');
	                    }
	                }
	            }
	        }
	    }
	}

	
	
	public void graphScreen(Graph main, Graph secondary) {
		for (int i = 0; i < 44; i++) {
			for (int j = 70; j < 130; j++) {
				screen.getTextWindow().setCursorPosition(j, i);
				System.out.print(' ');
			}
		}	
		printGraphs(main,secondary);
		printRelationMatrix(mainGraph.Relation, secondaryGraph.Relation); 
		drawEdges(main,drawingMode, 0);
		drawEdges(secondary,drawingMode, 70);
		mainMenu();
	}
	
	public void GraphTransferMenu() {
		screen.getTextWindow().setCursorPosition(0, 28);
		System.out.println("-------------Graph Transfer Menu---------------");
		System.out.println("1. Copy main graph to secondary graph  (Key: G)\r\n"
				+ "2. Copy secondary graph to main graph  (Key: H)\r\n"
				+ "3. Load a graph file (\"graph1.txt\") to main graph (Key: L)\r\n"
				+ "4. Save main graph to a file (\"graph1.txt\")          (Key: S)    \r\n"
				+ "5. Copy main graph to a depot graph (1-9) (Keys: QWE RTY UIO)\r\n"
				+ "6. Copy a depot graph to main graph (Keys: 123  456  789)\r\n"
				+ "7. Save depot graphs to files  (Key: D)\n"
				+ "8. Load depot graphs from files (Key: F)\n"
				+ "");
		
		screen.getTextWindow().removeKeyListener(KLis);
		
		KeyListener TransferLis=new KeyListener() {			

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case 71: //G
					secondaryGraph=mainGraph.clone(); 
					break; 
				case 72: //H
					mainGraph=secondaryGraph.clone();					
					break;					
				case 76: //L
					mainGraph = loadGraphFromFile(1);
					break;
				case 83: //S
					saveGraphToFile(mainGraph,1);
					break;
				case 81: //Q
					DepotGraphs[0]=mainGraph.clone(); 
					break;
				case 87: //W
					DepotGraphs[1]=mainGraph.clone(); 
					break;
				case 69: //E
					DepotGraphs[2]=mainGraph.clone(); 
					break;
				case 82: //R
					DepotGraphs[3]=mainGraph.clone(); 
					break;
				case 84: //T
					DepotGraphs[4]=mainGraph.clone(); 
					break;
				case 89: //Y
					DepotGraphs[5]=mainGraph.clone(); 
					break;
				case 85: //U
					DepotGraphs[6]=mainGraph.clone(); 
					break;
				case 73: //I
					DepotGraphs[7]=mainGraph.clone(); 
					break;					
				case 79: //O
					DepotGraphs[8]=mainGraph.clone(); 
					break;					
				case 49: //1
					mainGraph=DepotGraphs[0].clone();
					break;
				case 50: //2
					mainGraph=DepotGraphs[1].clone();
					break;
				case 51: //3
					mainGraph=DepotGraphs[2].clone();
					break;
				case 52: //4
					mainGraph=DepotGraphs[3].clone(); 
					break;
				case 53: //5
					mainGraph=DepotGraphs[4].clone();
					break;
				case 54: //6
					mainGraph=DepotGraphs[5].clone();
					break;
				case 55: //7
					mainGraph=DepotGraphs[6].clone(); 
					break;
				case 56: //8
					mainGraph=DepotGraphs[7].clone();
					break;
				case 57: //9
					mainGraph=DepotGraphs[8].clone();
					break;
					
				case 68: //D
					saveDepotsToFiles();
					break;
					
				case 70: //D
					loadDepotsFromFiles();
					break;
					
				case 67: //C key
					screen.getTextWindow().removeKeyListener(this);
					screen.getTextWindow().addKeyListener(KLis);
					break;
					
				}
				
			}

			

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		screen.getTextWindow().addKeyListener(TransferLis);
		
		
	}
	
	
	
public void loadDepotsFromFiles() { //loads depot graphs from corresponding text files to program memory.	
		for(int i=0;i<DepotGraphs.length;i++) {
			int vertexcount=0;
			try(BufferedReader reader=new BufferedReader(new FileReader("depot"+i+".txt"))){
				for(int j=0;j<=(2*vertexcount)+1;j++) {
				if(j==0) {
					String line=reader.readLine();
					int b=Integer.parseInt(line);
					vertexcount=b;
					DepotGraphs[i].VertexLocations=new int[b][2];
					DepotGraphs[i].Relation=new boolean[b][b];
				}
				else if(j<=vertexcount+1&&vertexcount!=0) {
					String line=reader.readLine();
					String[] words=line.split(" ");
					int y=Integer.parseInt(words[1]);
					int x=Integer.parseInt(words[2]);
					DepotGraphs[i].VertexLocations[j-1][0]=y;
					DepotGraphs[i].VertexLocations[j-1][1]=x;
					
				}
				else if(j<=(2*vertexcount)+1&&vertexcount!=0){
					String line=reader.readLine();
					char[] digits=line.toCharArray();
					for(int k=0;k<vertexcount;k++) {
						if(digits[k]=='1') {
							DepotGraphs[i].Relation[j-vertexcount-1][k]=true;
						}
						else if(digits[k]=='0') {
							DepotGraphs[i].Relation[j-vertexcount-1][k]=false;}
					}
				}
				
			}
				reader.close();
				}
			catch(IOException e) {
			
			}
		}
		
		
	}

	public void saveDepotsToFiles() { //saves depot graphs in memory to corresponding text files.
		for(int i=0;i<DepotGraphs.length;i++) {
			try(BufferedWriter writer=new BufferedWriter(new FileWriter("depot"+i+".txt"))){
				if(!DepotGraphs[i].equals(null)&&DepotGraphs[i].Relation.length>0) {

					
					writer.write(Integer.toString(DepotGraphs[i].Relation.length));
					writer.newLine();
					for(int j=0;j<DepotGraphs[i].Relation.length;j++) {
						int y= DepotGraphs[i].VertexLocations[j][0];
						int x =DepotGraphs[i].VertexLocations[j][1];
						writer.write((char)(j+65)+" "+Integer.toString(y)+" "+Integer.toString(x)+"\n");
					}
					int[][] Rel=Graph.translateBMatrix(DepotGraphs[i].Relation);
					for(int j=0;j<Rel.length;j++) {
						for(int k=0;k<Rel[j].length;k++) {
							writer.write(Integer.toString(Rel[j][k]));
							writer.write("");
						}
						writer.newLine();
					}
				}
				else {
					writer.write(Integer.toString(0));
				}
				
			}
			catch(IOException e) {
			 i++;
			}
		}
	}
	
	
	public void saveGraphToFile(Graph SavedGraph,int i) {
		
			try(BufferedWriter writer=new BufferedWriter(new FileWriter("graph"+i+".txt"))){
				if(!SavedGraph.equals(null)) {
					
					
					writer.write(Integer.toString(SavedGraph.Relation.length));
					writer.newLine();
					for(int j=0;j<SavedGraph.Relation.length;j++) {
						int y= SavedGraph.VertexLocations[j][0];
						int x =SavedGraph.VertexLocations[j][1];
						writer.write((char)(j+65)+" "+Integer.toString(y)+" "+Integer.toString(x)+"\n");
					}
					int[][] Rel=Graph.translateBMatrix(SavedGraph.Relation);
					for(int j=0;j<Rel.length;j++) {
						for(int k=0;k<Rel[j].length;k++) {
							writer.write(Integer.toString(Rel[j][k]));
							writer.write("");
						}
						writer.newLine();
					}
				}
			}
			catch(IOException e) {
				
			}
		
	}
	
	public Graph loadGraphFromFile(int i) {
	    Graph loadedGraph = new Graph();

	    try (BufferedReader reader = new BufferedReader(new FileReader("graph" + i + ".txt"))) {
	        String line;

	        // Düğüm sayısını oku
	        int vertexCount = Integer.parseInt(reader.readLine());

	        // VertexLocations dizisini oluştur
	        loadedGraph.VertexLocations = new int[vertexCount][2];

	        for (int j = 0; j < vertexCount; j++) {
	            line = reader.readLine(); // Örn: "A 2 5"
	            String[] parts = line.split(" ");
	            int y = Integer.parseInt(parts[1]);
	            int x = Integer.parseInt(parts[2]);
	            loadedGraph.VertexLocations[j][0] = y;
	            loadedGraph.VertexLocations[j][1] = x;

	            // Board üzerine harf yaz
	            loadedGraph.Board[y * 4][x * 4] = 65 + j; // 'A' + index
	        }

	        // Relation matrisini oku
	        boolean[][] relation = new boolean[vertexCount][vertexCount];

	        for (int j = 0; j < vertexCount; j++) {
	            line = reader.readLine(); // Örn: 0110
	            for (int k = 0; k < vertexCount; k++) {
	                relation[j][k] = line.charAt(k) == '1';
	            }
	        }

	        // Relation matrisini ata
	        loadedGraph.Relation = relation;

	        // Çizimi yap (Board üzerine çizgi çizmek için)
	        for (int a = 0; a < vertexCount; a++) {
	            for (int b = a + 1; b < vertexCount; b++) {
	                if (relation[a][b]) {
	                    loadedGraph.ConnectVertices(a, b); // sadece çizim yapar
	                }
	            }
	        }

	    } catch (IOException e) {
	        System.out.println("Dosya okuma hatası: " + e.getMessage());
	        return null;
	    }

	    return loadedGraph;
	}
	
	
	public UIManager() throws InterruptedException, AWTException {// constructor
		screen = Enigma.getConsole("Graph Generator", 140, 45, 13);
		mainGraph = new Graph();
		secondaryGraph = new Graph();
		takeInitialInputs();
		graphScreen(mainGraph,secondaryGraph);
		test = new GraphTestMenu(this);
		for(int i=0;i<DepotGraphs.length;i++) {
			DepotGraphs[i]=new Graph();
		}
		INPUT();
		
		
	}
}
