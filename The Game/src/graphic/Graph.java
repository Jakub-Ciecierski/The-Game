package graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Graph {
	
	int screenWidth;
	int screenHeight;
	int tileWidth;
	int tileHeight;
	int numberOfVerticesInRow;
	int numberOfVerticesInColumn;
	int totalNumberOfVertices;
	Vertex[] listOfVertices;
	ArrayList<ArrayList<Vertex>> listRepresentation;
	
	int counter;
	int previousObstacle;
	
	
	public Graph(int screenWidth, int screenHeight, int tileWidth,
			int titleHeight) {
		
		super();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.tileWidth = tileWidth;
		this.tileHeight = titleHeight;
		this.numberOfVerticesInRow = (this.screenWidth / this.tileWidth);
		this.numberOfVerticesInColumn = this.screenHeight / this.tileHeight;
		this.totalNumberOfVertices = (this.numberOfVerticesInColumn * this.numberOfVerticesInRow);
		
		this.counter=0;
		this.previousObstacle=-1;
		
		createVertices();
		createListRepresentation();
				
		System.out.println("I have created graph with "
				+ this.numberOfVerticesInRow + " vertices in row and "
				+ this.numberOfVerticesInColumn + " vertices in Column.");
	}
	
	
	
	private void createListRepresentation() {
		this.listRepresentation = new ArrayList<ArrayList<Vertex>>();
		
		for(int i = 0 ; i < this.totalNumberOfVertices ; i++){
			
			ArrayList<Vertex> tempArrayList = new ArrayList<Vertex>();
			
			/*__RIGHT__*/
			if(!(i >= (this.totalNumberOfVertices - this.numberOfVerticesInColumn)))
				tempArrayList.add(this.listOfVertices[i+this.numberOfVerticesInColumn]);
			/*__LEFT__*/
			 if(!(i < this.numberOfVerticesInColumn))
				tempArrayList.add(this.listOfVertices[i-this.numberOfVerticesInColumn]);
			/*__UP__*/
			if(!(i% this.numberOfVerticesInColumn == 0))
				tempArrayList.add(this.listOfVertices[i-1]);
			/*__DOWN__*/
			if(!(i % this.numberOfVerticesInColumn == (this.numberOfVerticesInColumn - 1)))
				tempArrayList.add(this.listOfVertices[i+1]);
			
			this.listRepresentation.add(tempArrayList);
				
		}
		
				
	}

	
	/*
	public static void main(String[] args) {
		Graph map = new Graph(180,150,30,30);
		map.theMomentOfDybisz();
		//map.theMomentOfDybisz();
		map.showListOfVertices();
		//map.showListRepresentation();
	}
	*/
	
	/**
	 * Special method for the illusion of constant movement.
	 */
	public void theMomentOfDybisz(){
		//System.out.println("ojojoj");
		/*
		 * counts the number of column iterations, places obstacle every nth column
		 */
		int n= 6;
		if(counter%n==0)
			createObstacle();
		counter++;
		resetObstacle();
		
		int j = 0;
		Vertex[] tempArray = new Vertex[this.numberOfVerticesInColumn];
		
		for(int i = 0 ; i < this.totalNumberOfVertices ; i++){
			if(i< this.numberOfVerticesInColumn){
				tempArray[i] = this.listOfVertices[i];
			}
			if(i>= this.numberOfVerticesInColumn
					){
				this.listOfVertices[i-this.numberOfVerticesInColumn] = this.listOfVertices[i];
			}
			if(i >=(this.totalNumberOfVertices - this.numberOfVerticesInColumn)){
				this.listOfVertices[i] = tempArray[j];
				this.listOfVertices[i].setY(this.listOfVertices[i-this.numberOfVerticesInColumn].getY() + this.tileWidth);
				
				
				j++;
			
			}
			
		}
		/*
		 * An update of graph's representation.
		 * Necessary for algorithms based on graph's structure
		 */
		createListRepresentation();
	}
	
	/*
	 * Resets the first column back to background
	 */
	private void resetObstacle()
	{
		for(int j=0;j<numberOfVerticesInColumn;j++)
		{
			//istOfVertices[j+numberOfVerticesInColumn].setType(2);
			listOfVertices[j+numberOfVerticesInColumn].setType(0);
		}
	}
	
	/*
	 *  Creates and obstacle in the last column
	 * 	saves the previousObstacle to make sure that the next jump is possible
	 * 	other than that, random math is applied to randomize the track
	 */
	private void createObstacle()
	{
		/*
		 * gap between 2 and 5 
		 */
		Random rand = new Random();
		int gap = (int)(Math.random()*4) +2;
		
		int v = rand.nextInt(numberOfVerticesInColumn-1);
		if(previousObstacle!=-1)
			while((previousObstacle-v)*(previousObstacle-v)>numberOfVerticesInColumn/2)
				v = rand.nextInt(numberOfVerticesInColumn-1);
		previousObstacle=v;
		
		int x = (v+1)-gap;
		
		int[] pos = new int[gap];
		try{
			for(int i=0;i<pos.length;i++)
			{
				if(x<0)
				{
					pos[i]=gap+x;
					x++;
					i++;
				}
				pos[i]=v;
				if(v<numberOfVerticesInColumn-1)
				{
					Random randSide = new Random();
					int side = randSide.nextInt(1);
					if(side==0)
						v++;
					else
						v--;
				}
				else
					v--;
			}
		}catch(ArrayIndexOutOfBoundsException e){}
		
		for(int j=0;j<numberOfVerticesInColumn;j++)
		{
			listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+j].setType(1);
		}
		for(int j=0;j<pos.length;j++)
			listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+pos[j]].setType(0);
	}
	
	private void createVertices(){
				
		this.listOfVertices = 
				new Vertex[this.totalNumberOfVertices];
		
		/*
		 * loop for vertices initialization. Each Vertex gets unique number (i)
		 * and appropriate coordinates x,y for his upper left corner
		 */
		for(int i = 0; i < this.totalNumberOfVertices; i++){
			
			int tempX = (i % this.numberOfVerticesInColumn) * this.tileHeight;
			int tempY = i/this.numberOfVerticesInColumn;
			tempY = tempY * tileWidth;
			Vertex tempVertex = new Vertex(i, 0, tempX, tempY);
			
			listOfVertices[i] = tempVertex;
			
		}
		
	}
	
	public void showListOfVertices(){
		/*for(int i = 0; i < this.totalNumberOfVertices; i++){
			System.out.println("vertex " + listOfVertices[i].getNumber() +" x: "+ listOfVertices[i].getX()+ " y: "	+ listOfVertices[i].getY()); 
		}*/
	}
	
	public void showListRepresentation(){
		for(int i = 0; i < this.totalNumberOfVertices; i++){
			System.out.print("tile " + i +": ");
			for(int j = 0 ; j < this.listRepresentation.get(i).size();j++)
			System.out.print("vertex Number: "+this.listRepresentation.get(i).get(j).getNumber() + ", "); 
			System.out.println("");
		}
	}



	public void draw(Graphics2D g2d) {
		for(int i = 0 ; i < this.totalNumberOfVertices; i++){
			 
			if(this.listOfVertices[i].getType()==1){
				g2d.setColor( Color.GREEN);
				g2d.drawRect(this.listOfVertices[i].getY(), this.listOfVertices[i].getX(), 
						this.tileWidth, this.tileHeight);
				g2d.drawString("X", 
						this.listOfVertices[i].getY()+6, this.listOfVertices[i].getX()+30 );
			}
			 
			if(this.listOfVertices[i].getType()==2){
				g2d.setColor( Color.BLUE);
				g2d.drawRect(this.listOfVertices[i].getY(), this.listOfVertices[i].getX(), 
						this.tileWidth, this.tileHeight);
				g2d.drawString("Y", 
						this.listOfVertices[i].getY()+6, this.listOfVertices[i].getX()+30 );
			}
			
		
			if(this.listOfVertices[i].getType()==0){
				g2d.setColor( Color.DARK_GRAY);
				g2d.drawRect(this.listOfVertices[i].getY(), this.listOfVertices[i].getX(), 
						this.tileWidth, this.tileHeight);
			
				g2d.drawString( String.format( "%s", this.listOfVertices[i].getNumber() ), 
							this.listOfVertices[i].getY()+6, this.listOfVertices[i].getX()+30 );
			}
			
		}
		
	}



	public void updatePosition() {
		for(int i = 0 ; i < this.totalNumberOfVertices; i++)
		{
			this.listOfVertices[i].decrementationY();
			
			if(this.listOfVertices[0].getY() <= - (this.tileWidth)){
				theMomentOfDybisz();
				showListOfVertices();
			}
				
		}
		
	}
}