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
	int netLineConstant;
	Vertex[] listOfVertices;
	ArrayList<ArrayList<Vertex>> listRepresentation;
	
	int counter;
	int rangeOfObstacle;
	int previousObstacle;
	int previousGap;
	
	public Graph(int screenWidth, int screenHeight, int tileWidth,
			int titleHeight, Graphics2D g2d) {
		
		super();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.tileWidth = tileWidth;
		this.tileHeight = titleHeight;
		this.netLineConstant = 4;
		this.numberOfVerticesInRow = (this.screenWidth / this.tileWidth);
		this.numberOfVerticesInColumn = this.screenHeight / this.tileHeight;
		this.totalNumberOfVertices = (this.numberOfVerticesInColumn * this.numberOfVerticesInRow);
		
		
		this.counter=0;
		this.previousObstacle=-1;
		this.previousGap=-1;
		this.rangeOfObstacle = 6;
		
		createVertices();
		createListRepresentation();
				
				
		System.out.println("I have created graph with "
				+ this.numberOfVerticesInRow + " vertices in row and "
				+ this.numberOfVerticesInColumn + " vertices in Column.");
	}
	
	public int getScore()
	{
		return this.counter;
	}
	
	private void createListRepresentation() {
		this.listRepresentation = new ArrayList<ArrayList<Vertex>>();
		
		for(int i = 0 ; i < this.totalNumberOfVertices ; i++){
			
			ArrayList<Vertex> tempArrayList = new ArrayList<Vertex>();
			
			/*___IF OUR_VERTEX_IS_NOT_AN_OBSTACLE_OR_A_NONE___*/
				if(this.listOfVertices[i].getType() != 1 && this.listOfVertices[i].getType() != 2)
				{
					/*__RIGHT__*/
					if(!(i >= (this.totalNumberOfVertices - this.numberOfVerticesInColumn)) && this.listOfVertices[i+this.numberOfVerticesInColumn].getType() !=1)
						tempArrayList.add(this.listOfVertices[i+this.numberOfVerticesInColumn]);
					/*__LEFT__*/
					 if(!(i < this.numberOfVerticesInColumn) && this.listOfVertices[i-this.numberOfVerticesInColumn].getType() != 1)
						tempArrayList.add(this.listOfVertices[i-this.numberOfVerticesInColumn]);
					/*__UP__*/
					if(!(i% this.numberOfVerticesInColumn == 0) && this.listOfVertices[i-1].getType() != 1)
						tempArrayList.add(this.listOfVertices[i-1]);
					/*__DOWN__*/
					if(!(i % this.numberOfVerticesInColumn == (this.numberOfVerticesInColumn - 1)) && this.listOfVertices[i+1].getType() != 1)
						tempArrayList.add(this.listOfVertices[i+1]);
								
				}
			/*________________________________________*/
			
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
		 * counts the number of column iterations, places obstacle every rangeOfObstacle'th column
		 */
		counter++;
		if(counter%rangeOfObstacle==0)
			createObstacle();
		resetObstacle();
		
		/*
		 * An update of graph's representation.
		 * Necessary for algorithms based on graph's structure
		 */
		createListRepresentation();
	}
	
	/*
	private void createTestObstacle1()
	{
		for(int j=0;j<numberOfVerticesInColumn;j++)
			listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+j].setType(1);
		listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+5].setType(0);
		listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+6].setType(0);
		listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+7].setType(0);
		listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+8].setType(0);
		
	}
	private void createTestObstacle2()
	{
		for(int j=0;j<numberOfVerticesInColumn;j++)
			listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+j].setType(1);
		listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+1].setType(0);
		listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+2].setType(0);
		listOfVertices[(numberOfVerticesInRow-1)*numberOfVerticesInColumn+3].setType(0);	
	}
	*/
	
	/*
	 * Resets the first column back to background
	 */
	private void resetObstacle()
	{
		for(int j=0;j<numberOfVerticesInColumn;j++)
		{
			listOfVertices[j].setType(0);
		}
	}
	
	/**
	 *  Creates and obstacle in the last column
	 * 	saves the previousObstacle to make sure that the next "jump" is possible,
	 * 	saves the previousGap so that no two consecutive columns have gap equal to 2
	 * 	other than that, random math is applied to randomize the track
	 */
	private void createObstacle()
	{
		Random rand = new Random();
		
		int gap =0;
		if(previousGap==2)
			gap = (int)(Math.random()*4) +3;
		else
			gap = (int)(Math.random()*4) +2;
		previousGap=gap;
		
		int v = rand.nextInt(numberOfVerticesInColumn);
		if(previousObstacle!=-1)
			while((previousObstacle-v)*(previousObstacle-v)>numberOfVerticesInColumn/2)
				v = rand.nextInt(numberOfVerticesInColumn);
		previousObstacle=v;
		int tmpV=v;
		int x = (v+1)-gap;
		int side = rand.nextInt(2);
	
		int[] pos = new int[gap];
		
		try{
			for(int i=0;i<pos.length;i++)
			{
				if(side==1 && v+gap<numberOfVerticesInColumn)
				{
					pos[i]=tmpV;
					tmpV++;
				}
				else
				{
					if(x<0)
					{
						pos[i]=gap+x;
						x++;
					}
					else
					{
						pos[i]=tmpV;	
						tmpV--;
					}
				}
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
		for(int i = 0; i < this.totalNumberOfVertices; i++){
			System.out.println("vertex " + listOfVertices[i].getNumber() +" x: "+ listOfVertices[i].getX()+ " y: "	+ listOfVertices[i].getY()); 
		}
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
				//g2d.drawRect(this.listOfVertices[i].getY(), this.listOfVertices[i].getX(), 
					//	this.tileWidth, this.tileHeight);
				g2d.drawString("oo", 
						this.listOfVertices[i].getY()+6, this.listOfVertices[i].getX()+30 );
			}
			
		
			if(this.listOfVertices[i].getType()==0){
				g2d.setColor( Color.DARK_GRAY);
				g2d.drawRect(this.listOfVertices[i].getY(), this.listOfVertices[i].getX(), 
						this.tileWidth, this.tileHeight);
			
				g2d.drawString( String.format( "%s", this.listOfVertices[i].getNumber() ), 
							this.listOfVertices[i].getY()+6, this.listOfVertices[i].getX()+30 );
			}
			/*___DRAW_GRAPH'S_NET___*/
			//drawNet(i,g2d);
			/*___DRAW_FALLING_TILES___*/
			
		
			
		}
		
	}

	private void drawNet(int i, Graphics2D g2d) {
		g2d.setColor(Color.RED);
		/*
		 * There is a blind spot between last and first column,
		 * but it is only a matter of graphical representation.
		 * In graph's list everything is ok.
		 * 
		 */
		
		/*___DRAW_IF_TILE_HAS_NEIGHBORS___*/
		if(this.listRepresentation.get(i).size() != 0)
			for(int j =0; j < this.listRepresentation.get(i).size();j++){
				/*___LEFT___*/
				if(this.listRepresentation.get(i).get(j).number == this.listOfVertices[i].getNumber() - 
									this.numberOfVerticesInColumn )
				{
					/*___DRAW_LINE___*/
					g2d.drawLine(this.listOfVertices[i].getY()+this.netLineConstant,
							this.listOfVertices[i].getX()+this.tileHeight/2, 
							this.listRepresentation.get(i).get(j).getY() + this.tileWidth-this.netLineConstant,
							this.listRepresentation.get(i).get(j).getX()+this.tileHeight/2);
				}
				/*___UP___*/
				if(this.listRepresentation.get(i).get(j).number == this.listOfVertices[i].getNumber() - 1)
				{
					/*___DRAW_LINE___*/
					g2d.drawLine(this.listOfVertices[i].getY()+this.tileWidth/2,
							this.listOfVertices[i].getX()+this.netLineConstant, 
							this.listRepresentation.get(i).get(j).getY() + this.tileWidth/2,
							this.listRepresentation.get(i).get(j).getX()+this.tileHeight - this.netLineConstant);
				}
				/*___DOWN___*/
				if(this.listRepresentation.get(i).get(j).number == this.listOfVertices[i].getNumber() +1)
				{
					/*___DRAW_LINE___*/
					g2d.drawLine(this.listOfVertices[i].getY()+this.tileWidth/2,
							this.listOfVertices[i].getX()+this.tileHeight - this.netLineConstant, 
							this.listRepresentation.get(i).get(j).getY() + this.tileWidth/2,
							this.listRepresentation.get(i).get(j).getX()+this.netLineConstant);
				}
				/*___RIGHT___*/
				if(this.listRepresentation.get(i).get(j).number == this.listOfVertices[i].getNumber() +
									this.numberOfVerticesInColumn)
				{
					/*___DRAW_LINE___*/
					g2d.drawLine(this.listOfVertices[i].getY()+this.tileWidth-this.netLineConstant,
							this.listOfVertices[i].getX()+this.tileHeight/2, 
							this.listRepresentation.get(i).get(j).getY() + this.netLineConstant,
							this.listRepresentation.get(i).get(j).getX()+this.tileHeight/2);
				}
				
			}
		
		
		g2d.setColor(Color.DARK_GRAY);
		
	}

	public void updatePosition() {
		for(int i = 0 ; i < this.totalNumberOfVertices; i++)
		{
			this.listOfVertices[i].decrementationY();
			
			/*__MAP'S_LOOP___*/
			if(this.listOfVertices[0].getY() < - (this.tileWidth*2)){
				theMomentOfDybisz();
				//showListOfVertices();
			}
			/*___FADING_TILES___*/
			fadingTiles();
			
					
		}
		
		
		
	}

	
	
	private void fadingTiles() {
		for(int k = 1 ; k < 10; k++)
			if(this.listOfVertices[(this.numberOfVerticesInColumn-k)*this.numberOfVerticesInColumn].getY()
					< (this.tileWidth*(this.numberOfVerticesInColumn-k-1)))
			{
				/*___SAVE_PREVIOUS_TYPE___*/
				int previousType = this.listOfVertices[(this.numberOfVerticesInColumn-k)
				   				                    *this.numberOfVerticesInColumn + k -1].getType();
				
				/*___CHANGE TYPE_OF_APPROPRIATE_TILE___*/
				this.listOfVertices[(this.numberOfVerticesInColumn-k)
				                    *this.numberOfVerticesInColumn + k -1].setType(2);
				
			}
		
		}
		
	
}